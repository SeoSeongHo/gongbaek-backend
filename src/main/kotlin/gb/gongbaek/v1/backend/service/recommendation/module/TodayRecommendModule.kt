package gb.gongbaek.v1.backend.service.recommendation.module

import gb.gongbaek.v1.backend.domain.Recommendation
import gb.gongbaek.v1.backend.domain.partner.Partner
import gb.gongbaek.v1.backend.dto.RecommendTitle
import gb.gongbaek.v1.backend.service.partner.PartnerService
import gb.gongbaek.v1.backend.service.recommendation.RecommendationService

class TodayRecommendModule(
        private val partnerService: PartnerService,
        private val recommendationService: RecommendationService
): BaseRecommendModule() {

    override fun getRecommendations(): List<Recommendation> {
        TODO("Not yet implemented")
    }

    override fun triggerModule(recommendTitle: RecommendTitle): List<Partner> {
        TODO("Not yet implemented")
    }
}