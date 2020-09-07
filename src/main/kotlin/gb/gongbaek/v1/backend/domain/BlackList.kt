package gb.gongbaek.v1.backend.domain

import java.time.LocalDateTime
import javax.persistence.*

@Entity
// TODO (추후 테이블 이름 변경 및 Redis 로 이전 예정)
data class BlackList(

        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long? = null,
        @Column(unique = true)
        val token: String
): EntityAuditing() {

}