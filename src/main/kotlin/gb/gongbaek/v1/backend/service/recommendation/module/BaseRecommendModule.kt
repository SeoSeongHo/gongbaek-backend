package gb.gongbaek.v1.backend.service.recommendation.module

import gb.gongbaek.v1.backend.domain.Recommendation
import gb.gongbaek.v1.backend.domain.partner.Partner
import gb.gongbaek.v1.backend.dto.RecommendTitle

abstract class BaseRecommendModule {

    abstract fun getRecommendations(): List<Recommendation>
    abstract fun triggerModule(recommendTitle: RecommendTitle): List<Partner>
}