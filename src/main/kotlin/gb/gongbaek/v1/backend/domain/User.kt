package gb.gongbaek.v1.backend.domain

import com.fasterxml.jackson.annotation.JsonIgnore
import gb.gongbaek.v1.backend.dto.UserDto
import gb.gongbaek.v1.backend.dto.UserInfoDto
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import javax.persistence.*
import javax.validation.constraints.Size

@Entity
@Table(name = "users")
data class User (
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long? = null,

        @Column(name = "email", unique = true, length = 200)
        var email: String,
        var password: String,

        @Embedded
        var userInfo: UserInfo
): EntityAuditing() {

    fun isRightPassword(bCryptPasswordEncoder: BCryptPasswordEncoder, rawPassword: String): Boolean{
        return bCryptPasswordEncoder.matches(rawPassword, password)
    }

    fun updateUserInfo(userInfoReq: UserInfoDto.UserInfoReq){
        userInfo.type = userInfoReq.type
        userInfo.contact = userInfoReq.contact
        userInfo.nickname = userInfoReq.nickname
        userInfo.isNotificationAgreed = userInfoReq.isNotificationAgreed
        userInfo.isServiceTermsAgreed = userInfoReq.isServiceTermsAgreed
    }

    fun toDto(user: User): UserDto.UserRes {
        return UserDto.UserRes(
                id = user.id!!,
                email = user.email,
                userInfo = user.userInfo
        )
    }
}

@Embeddable
data class UserInfo(

        @Column(name = "nickname", unique = true, length = 20)
        var nickname: String,
        var contact: String,
        
        @Enumerated(EnumType.STRING)
        var type: UserType,
        
        // 이용약관 동의
        var isServiceTermsAgreed: Boolean,
        // SNS 등 알림 동의
        var isNotificationAgreed: Boolean
)

enum class UserType {
    UNSPECIFIED, STUDENT, PARENT, BUSINESS
}
