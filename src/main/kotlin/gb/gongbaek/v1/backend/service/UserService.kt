package gb.gongbaek.v1.backend.service

import gb.gongbaek.v1.backend.domain.User
import gb.gongbaek.v1.backend.dto.SignInDto
import gb.gongbaek.v1.backend.dto.SignUpDto

interface UserService {
    fun signUp(signUpReq: SignUpDto.SignUpReq): SignUpDto.SignUpRes
    fun signIn(signInReq: SignInDto.SignInReq): SignInDto.SignInRes
}