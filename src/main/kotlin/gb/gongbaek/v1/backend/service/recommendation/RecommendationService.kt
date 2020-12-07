package gb.gongbaek.v1.backend.service.recommendation

import gb.gongbaek.v1.backend.domain.Recommendation
import gb.gongbaek.v1.backend.dto.PartnerType
import gb.gongbaek.v1.backend.dto.RecommendCategory
import gb.gongbaek.v1.backend.dto.RecommendTitle
import gb.gongbaek.v1.backend.dto.RecommendationDto

interface RecommendationService {
    fun getRecommendation(id: Long): Recommendation
    fun getRecommendationByTitle(title: RecommendTitle): Recommendation?
    fun getRecommendationByCategory(category: RecommendCategory): List<Recommendation>
    fun createRecommendation(recommendationReq: RecommendationDto.RecommendationReq)
    fun createRecommendations(recommendationReqs: List<RecommendationDto.RecommendationReq>)
}