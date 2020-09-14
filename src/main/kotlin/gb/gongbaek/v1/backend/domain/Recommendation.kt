package gb.gongbaek.v1.backend.domain

import gb.gongbaek.v1.backend.dto.ReCommendationType
import javax.persistence.*

@Entity
data class Recommendation(

        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long? = null,
        @Column(unique = true)
        var type: ReCommendationType,
        var description: String

): EntityAuditing()