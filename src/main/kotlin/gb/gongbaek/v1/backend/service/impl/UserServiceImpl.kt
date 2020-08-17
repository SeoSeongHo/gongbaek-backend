package gb.gongbaek.v1.backend.service.impl

import gb.gongbaek.v1.backend.config.jwt.JwtTokenProvider
import gb.gongbaek.v1.backend.dto.SignInDto
import gb.gongbaek.v1.backend.dto.SignUpDto
import gb.gongbaek.v1.backend.exception.DuplicateEmailException
import gb.gongbaek.v1.backend.exception.EmailNotFoundException
import gb.gongbaek.v1.backend.exception.WrongPasswordException
import gb.gongbaek.v1.backend.repository.UserRepository
import gb.gongbaek.v1.backend.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class UserServiceImpl(
        @Autowired private val userRepository: UserRepository,
        @Autowired private val bCryptPasswordEncoder: BCryptPasswordEncoder,
        @Autowired private val jwtTokenProvider: JwtTokenProvider
): UserService{

    override fun signUp(signUpReq: SignUpDto.SignUpReq): SignUpDto.SignUpRes {

        if(userRepository.existsByEmail(signUpReq.email))
            throw DuplicateEmailException("duplicate email: ${signUpReq.email}")

        val createdUser = userRepository.save(signUpReq.toEntity(bCryptPasswordEncoder))

        return SignUpDto.SignUpRes(email = createdUser.email, token = jwtTokenProvider.createToken(createdUser.id!!))
    }

    override fun signIn(signInReq: SignInDto.SignInReq): SignInDto.SignInRes {

        val findUser = userRepository.findByEmail(signInReq.email)
        findUser ?: throw EmailNotFoundException("can not find user. email: ${signInReq.email}")

        // check password
        if(!findUser.isRightPassword(bCryptPasswordEncoder, signInReq.password)) throw WrongPasswordException("wrong password exception.")

        // generate jwt and convert dto
        return SignInDto.SignInRes(email = findUser.email, token = jwtTokenProvider.createToken(findUser.id!!))
    }
}