package gb.gongbaek.v1.backend.service.recommendation

import gb.gongbaek.v1.backend.domain.Recommendation
import gb.gongbaek.v1.backend.dto.PartnerType
import gb.gongbaek.v1.backend.dto.RecommendTab
import gb.gongbaek.v1.backend.dto.RecommendCategory
import gb.gongbaek.v1.backend.dto.RecommendationDto

interface RecommendationService {
    fun getRecommendation(id: Long): Recommendation
    fun getRecommendationByTitle(title: RecommendCategory): Recommendation?
    fun getRecommendationByCategory(category: RecommendTab): List<Recommendation>
    fun createRecommendation(recommendationReq: RecommendationDto.RecommendationReq)
    fun createRecommendations(recommendationReqs: List<RecommendationDto.RecommendationReq>)
}