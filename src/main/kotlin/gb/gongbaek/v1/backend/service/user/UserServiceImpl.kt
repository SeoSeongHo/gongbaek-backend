package gb.gongbaek.v1.backend.service.user

import gb.gongbaek.v1.backend.config.jwt.JwtTokenProvider
import gb.gongbaek.v1.backend.domain.BlackList
import gb.gongbaek.v1.backend.domain.RefreshTokenJob
import gb.gongbaek.v1.backend.domain.User
import gb.gongbaek.v1.backend.domain.UserType
import gb.gongbaek.v1.backend.dto.*
import gb.gongbaek.v1.backend.exception.*
import gb.gongbaek.v1.backend.repository.BlackListRepository
import gb.gongbaek.v1.backend.repository.RefreshTokenJobRepository
import gb.gongbaek.v1.backend.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Service
@Transactional
class UserServiceImpl(
        @Autowired private val userRepository: UserRepository,
        @Autowired private val blackListRepository: BlackListRepository,
        @Autowired private val refreshTokenJobRepository: RefreshTokenJobRepository,
        @Autowired private val bCryptPasswordEncoder: BCryptPasswordEncoder,
        @Autowired private val jwtTokenProvider: JwtTokenProvider,
        @Value("\${jwt.refresh.expire.time}") private val refreshTokenExpiredTime: Long
): UserService {

    override fun getUserById(id: Long): User =
            userRepository.findById(id).orElseThrow { throw UserNotFoundException("") }


    override fun getUserByEmail(email: String): User =
            userRepository.findByEmail(email) ?: throw UserNotFoundException("")


    override fun signUp(signUpReq: SignUpDto.SignUpReq): SignUpDto.SignUpRes {

        // 1. check email duplication
        if(userRepository.existsByEmail(signUpReq.email))
            throw DuplicateEmailException("duplicate email: ${signUpReq.email}")

        // 2. save user
        val createdUser = userRepository.save(signUpReq.toEntity(bCryptPasswordEncoder))

        // 3. create access token and refresh token
        val accessToken = jwtTokenProvider.createAccessToken(createdUser.id!!)
        val refreshToken = jwtTokenProvider.createRefreshToken(createdUser.id!!)

        // 4. save refresh token to refresh job db
        refreshTokenJobRepository.save(RefreshTokenJob(refreshToken = refreshToken, userId = createdUser.id!!, expiredTime = LocalDateTime.now().plusSeconds(refreshTokenExpiredTime)))

        // 5. return signUp dto
        return SignUpDto.SignUpRes.toDto(accessToken = accessToken, refreshToken = refreshToken, user = createdUser)
    }

    override fun signIn(signInReq: SignInDto.SignInReq): SignInDto.SignInRes {

        val findUser = getUserByEmail(signInReq.email)

        // check password
        if(!findUser.isRightPassword(bCryptPasswordEncoder, signInReq.password)) throw WrongPasswordException("wrong password exception.")

        val accessToken = jwtTokenProvider.createAccessToken(findUser.id!!)
        val refreshToken = jwtTokenProvider.createRefreshToken(findUser.id!!)

        refreshTokenJobRepository.save(RefreshTokenJob(refreshToken = refreshToken, userId = findUser.id!!, expiredTime = LocalDateTime.now().plusSeconds(refreshTokenExpiredTime)))

        // generate jwt and convert dto
        return SignInDto.SignInRes.toDto(accessToken = accessToken, refreshToken = refreshToken, user = findUser)
    }


    override fun logout(oAuthReq: OAuthDto.OAuthReq){

        // 1. add accessToken to blacklist
        blackListRepository.save(BlackList(token = oAuthReq.accessToken))

        // 2. delete refreshToken to refreshJob by userId ( userId must be unique. )
        refreshTokenJobRepository.deleteByRefreshToken(oAuthReq.refreshToken)
    }


    override fun refresh(oAuthReq: OAuthDto.OAuthReq): OAuthDto.OAuthRes{

        val refreshTokenJob = refreshTokenJobRepository.findByRefreshToken(oAuthReq.refreshToken) ?: throw RefreshTokenNotFoundException("can not find refresh token by userId.")
        if(refreshTokenJob.expiredTime < LocalDateTime.now())
            throw ExpiredRefreshTokenException("refresh token is expired. please reissue token.")

        return OAuthDto.OAuthRes(accessToken = jwtTokenProvider.createAccessToken(refreshTokenJob.userId), refreshToken = refreshTokenJob.refreshToken)
    }



    override fun updateUserInfo(userInfoReq: UserInfoDto.UserInfoReq, userId: Long): UserDto.UserRes{

        val user = getUserById(userId)

        try{
            user.updateUserInfo(userInfoReq)
        }
        catch(e: Exception){
            throw Exception("failed to update user info. ${e.message}")
        }

        return user.toDto()
    }

    override fun updateUserType(userTypeReq: UserInfoDto.UserTypeReq, userId: Long): SignInDto.SignInRes {

        val user = getUserById(userId)

        user.userInfo.type = userTypeReq.type

        val accessToken = jwtTokenProvider.createAccessToken(user.id!!, userTypeReq.type)
        val refreshToken = jwtTokenProvider.createRefreshToken(user.id!!, userTypeReq.type)

        return SignInDto.SignInRes(
                accessToken = accessToken,
                refreshToken = refreshToken,
                user = user.toDto()
        )
    }


    override fun isDuplicateNickname(nickname: String): Boolean{

        return userRepository.existsByUserInfoNickname(nickname)
    }

    override fun isDuplicateEmail(email: String): Boolean{

        return userRepository.existsByEmail(email)
    }

    override fun isBlackListToken(accessToken: String): Boolean{

        return blackListRepository.existsByToken(accessToken)
    }
}