package gb.gongbaek.v1.backend.dto

import gb.gongbaek.v1.backend.domain.User
import gb.gongbaek.v1.backend.domain.UserInfo
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
                email = email,
                password = bCryptPasswordEncoder.encode(password),
                userInfo = UserInfo(
                        nickname = nickname,
                        contact = contact,
                        type = UserType.UNSPECIFIED,
                        isServiceTermsAgreed = isServiceTermsAgreed,
                        isNotificationAgreed = isNotificationAgreed
                ),
                likes = mutableListOf()
        )
    }

    data class SignUpRes(

            val access_token: String,
            val refresh_token: String,
            val user: UserDto.UserRes
    ){
        companion object{
            fun toDto(accessToken: String, refreshToken: String, user: User): SignUpRes {
                return SignUpRes(
                        access_token = accessToken,
                        refresh_token = refreshToken,
                        user = user.toDto()
                )
            }
        }

    }
}