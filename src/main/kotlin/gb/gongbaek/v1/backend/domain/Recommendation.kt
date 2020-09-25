package gb.gongbaek.v1.backend.domain

import gb.gongbaek.v1.backend.dto.RecommendCategory
import gb.gongbaek.v1.backend.dto.RecommendTitle
import javax.persistence.*

@Entity
data class Recommendation(

        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long? = null,
        @Enumerated(EnumType.STRING)
        var category: RecommendCategory,
        @Column(unique = true)
        var title: RecommendTitle,
        var description: String

): EntityAuditing()