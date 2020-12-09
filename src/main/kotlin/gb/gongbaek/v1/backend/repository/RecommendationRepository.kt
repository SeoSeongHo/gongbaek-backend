package gb.gongbaek.v1.backend.repository

import gb.gongbaek.v1.backend.domain.Recommendation
import gb.gongbaek.v1.backend.dto.PartnerType
import gb.gongbaek.v1.backend.dto.RecommendTab
import gb.gongbaek.v1.backend.dto.RecommendCategory
import org.springframework.data.jpa.repository.JpaRepository

interface RecommendationRepository: JpaRepository<Recommendation, Long> {
    fun findByTitle(title: RecommendCategory): Recommendation?
    fun findByCategory(category: RecommendTab): List<Recommendation>
}