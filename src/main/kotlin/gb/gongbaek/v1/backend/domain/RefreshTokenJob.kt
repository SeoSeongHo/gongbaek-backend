package gb.gongbaek.v1.backend.domain

import java.time.LocalDateTime
import javax.persistence.*

@Entity
data class RefreshTokenJob(

        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long? = null,
        @Column(unique = true)
        val refreshToken: String,
        val userId: Long,
        val expiredTime: LocalDateTime
): EntityAuditing() {

}