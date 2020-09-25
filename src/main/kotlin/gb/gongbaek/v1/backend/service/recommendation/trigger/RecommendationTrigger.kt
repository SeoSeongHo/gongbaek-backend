package gb.gongbaek.v1.backend.service.recommendation.trigger

import gb.gongbaek.v1.backend.dto.RecommendCategory
import gb.gongbaek.v1.backend.service.recommendation.module.BaseRecommendModule

interface RecommendationTrigger {
    fun getRecommendModule(category: RecommendCategory): BaseRecommendModule
}