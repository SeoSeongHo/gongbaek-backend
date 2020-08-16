package gb.gongbaek.v1.backend.dto

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

class SignInDto {

    data class SignInReq(

            var email: String,
            var password: String
    )

    data class SignInRes(

            var email: String,
            var token: String
    )
}