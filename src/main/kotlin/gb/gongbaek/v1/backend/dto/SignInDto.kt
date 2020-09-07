package gb.gongbaek.v1.backend.dto

import gb.gongbaek.v1.backend.domain.User
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

class SignInDto {

    data class SignInReq(

            var email: String,
            var password: String
    )

    data class SignInRes(

            val accessToken: String,
            val refreshToken: String,
            val user: UserDto.UserRes
    ){
        companion object{
            fun toDto(accessToken: String, refreshToken: String, user: User): SignInRes{
                return SignInRes(
                        accessToken = accessToken,
                        refreshToken = refreshToken,
                        user = user.toDto(user)
                )
            }
        }

    }
}