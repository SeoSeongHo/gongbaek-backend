package gb.gongbaek.v1.backend.service.user

import gb.gongbaek.v1.backend.domain.User
import gb.gongbaek.v1.backend.dto.*

interface UserService {
    fun getUserById(id: Long): User
    fun getUserByEmail(email: String): User

    fun signUp(signUpReq: SignUpDto.SignUpReq): SignUpDto.SignUpRes
    fun signIn(signInReq: SignInDto.SignInReq): SignInDto.SignInRes
    fun logout(oAuthReq: OAuthDto.OAuthReq)
    fun refresh(oAuthReq: OAuthDto.OAuthReq): OAuthDto.OAuthRes

    fun updateUserInfo(userInfoReq: UserInfoDto.UserInfoReq, userId: Long): UserDto.UserRes

    fun isDuplicateNickname(nickname: String): Boolean
    fun isDuplicateEmail(email: String): Boolean
    fun isBlackListToken(accessToken: String): Boolean
}