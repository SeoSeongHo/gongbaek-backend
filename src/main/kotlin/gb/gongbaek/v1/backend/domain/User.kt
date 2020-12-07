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
        var userInfo: UserInfo,

        @JsonIgnore
        @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
        var likes: MutableList<Like> = mutableListOf()
): EntityAuditing() {

    fun isRightPassword(bCryptPasswordEncoder: BCryptPasswordEncoder, rawPassword: String): Boolean{
        return bCryptPasswordEncoder.matches(rawPassword, password)
    }

    fun updateUserInfo(userInfoReq: UserInfoDto.UserInfoReq){
        userInfo.userRole = userInfoReq.userRole
        userInfo.contact = userInfoReq.contact
        userInfo.nickname = userInfoReq.nickname
        userInfo.isNotificationAgreed = userInfoReq.isNotificationAgreed
        userInfo.isServiceTermsAgreed = userInfoReq.isServiceTermsAgreed
    }

    fun toDto(): UserDto.UserRes {
        return UserDto.UserRes(
                id = id!!,
                email = email,
                userInfo = userInfo
        )
    }
}

@Embeddable
data class UserInfo(

        @Column(name = "nickname", unique = true, length = 20)
        var nickname: String,
        var contact: String,
        
        @Enumerated(EnumType.STRING)
        var userRole: UserRole,
        
        // 이용약관 동의
        var isServiceTermsAgreed: Boolean,
        // SNS 등 알림 동의
        var isNotificationAgreed: Boolean
)

enum class UserRole {
    UNSPECIFIED, STUDENT, PARENT, BUSINESS
}
