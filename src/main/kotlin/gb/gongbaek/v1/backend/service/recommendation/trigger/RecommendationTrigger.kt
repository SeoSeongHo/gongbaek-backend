package gb.gongbaek.v1.backend.service.recommendation.trigger

import gb.gongbaek.v1.backend.dto.RecommendTab
import gb.gongbaek.v1.backend.service.recommendation.module.BaseRecommendModule

interface RecommendationTrigger {
    fun getRecommendModule(category: RecommendTab): BaseRecommendModule
}