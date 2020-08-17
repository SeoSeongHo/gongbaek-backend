package gb.gongbaek.v1.backend.dto

import gb.gongbaek.v1.backend.domain.User
import gb.gongbaek.v1.backend.domain.UserType
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import javax.persistence.EnumType
import javax.persistence.Enumerated

class SignUpDto {

    data class SignUpReq(

            val name: String,
            val email: String,
            val password: String,
            val contact: String,

            @Enumerated(EnumType.STRING)
            val type: UserType,
            val isNotificationAgreed: Boolean
    ){
        fun toEntity(bCryptPasswordEncoder: BCryptPasswordEncoder) = User(
                name = name,
                email = email,
                password = bCryptPasswordEncoder.encode(password),
                contact = contact,
                type = type,
                isNotificationAgreed = isNotificationAgreed
        )
    }

    data class SignUpRes(

            val email: String,
            val token: String
    )
}