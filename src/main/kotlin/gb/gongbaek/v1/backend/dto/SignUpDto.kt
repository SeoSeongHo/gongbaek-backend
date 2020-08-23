package gb.gongbaek.v1.backend.dto

import gb.gongbaek.v1.backend.domain.User
import gb.gongbaek.v1.backend.domain.UserType
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.validation.constraints.Email
import javax.validation.constraints.Size

class SignUpDto {

    data class SignUpReq(

            @Size(min = 2, max = 15)
            val nickname: String,
            @Email
            val email: String,
            @Size(min = 8)
            val password: String,
            val contact: String,

            var isServiceTermsAgreed: Boolean,
            val isNotificationAgreed: Boolean
    ){
        fun toEntity(bCryptPasswordEncoder: BCryptPasswordEncoder) = User(
                nickname = nickname,
                email = email,
                password = bCryptPasswordEncoder.encode(password),
                contact = contact,
                type = UserType.UNSPECIFIED,
                isServiceTermsAgreed = isServiceTermsAgreed,
                isNotificationAgreed = isNotificationAgreed
        )
    }

    data class SignUpRes(

            val email: String,
            val token: String
    )
}