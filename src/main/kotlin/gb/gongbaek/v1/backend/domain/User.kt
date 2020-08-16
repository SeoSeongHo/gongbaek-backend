package gb.gongbaek.v1.backend.domain

import com.fasterxml.jackson.annotation.JsonIgnore
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import javax.persistence.*

@Entity
@Table(name = "users")
data class User (
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long? = null,

        var name: String,

        @Column(name="email", unique = true, length = 200)
        var email: String,
        var password: String,
        var contact: String,

        @Enumerated(EnumType.STRING)
        var type: UserType,

        var isNotificationAgreed: Boolean

): EntityAuditing() {

    fun isRightPassword(bCryptPasswordEncoder: BCryptPasswordEncoder, rawPassword: String): Boolean{
        return bCryptPasswordEncoder.matches(rawPassword, password)
    }
}

enum class UserType {
    STUDENT, PARENT, BUSINESS
}
