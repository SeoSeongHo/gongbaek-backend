package gb.gongbaek.v1.backend.domain

import com.fasterxml.jackson.annotation.JsonIgnore
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import javax.persistence.*
import javax.validation.constraints.Size

@Entity
@Table(name = "users")
data class User (
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long? = null,

        @Column(name = "nickname", unique = true, length = 20)
        var nickname: String,

        @Column(name = "email", unique = true, length = 200)
        var email: String,
        var password: String,
        var contact: String,

        @Enumerated(EnumType.STRING)
        var type: UserType,

        // 이용약관 동의
        var isServiceTermsAgreed: Boolean,
        // SNS 등 알림 동의
        var isNotificationAgreed: Boolean

): EntityAuditing() {

    fun isRightPassword(bCryptPasswordEncoder: BCryptPasswordEncoder, rawPassword: String): Boolean{
        return bCryptPasswordEncoder.matches(rawPassword, password)
    }
}

//data class UserInfo(
//
//        @Enumerated(EnumType.STRING)
//        val type: UserType
//)

enum class UserType {
    UNSPECIFIED, STUDENT, PARENT, BUSINESS
}
