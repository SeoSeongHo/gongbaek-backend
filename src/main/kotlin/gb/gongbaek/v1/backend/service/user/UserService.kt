package gb.gongbaek.v1.backend.service.user

import gb.gongbaek.v1.backend.domain.User
import gb.gongbaek.v1.backend.dto.SignInDto
import gb.gongbaek.v1.backend.dto.SignUpDto
import gb.gongbaek.v1.backend.dto.UserInfoDto

interface UserService {
    fun signUp(signUpReq: SignUpDto.SignUpReq): SignUpDto.SignUpRes
    fun signIn(signInReq: SignInDto.SignInReq): SignInDto.SignInRes

    fun updateUserInfo(userInfoReq: UserInfoDto.UserInfoReq, userId: Long)
    fun checkDuplicateNickname(nickname: String): Boolean
    fun checkDuplicateEmail(email: String): Boolean
}