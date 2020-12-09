package gb.gongbaek.v1.backend.domain

import gb.gongbaek.v1.backend.dto.RecommendTab
import gb.gongbaek.v1.backend.dto.RecommendCategory
import javax.persistence.*

@Entity
data class Recommendation(

        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long? = null,
        @Enumerated(EnumType.STRING)
        var category: RecommendTab,
        @Column(unique = true)
        @Enumerated(EnumType.STRING)
        var title: RecommendCategory,
        var description: String

): EntityAuditing()