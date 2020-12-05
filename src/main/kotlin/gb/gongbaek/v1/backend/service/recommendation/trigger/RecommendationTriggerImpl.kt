package gb.gongbaek.v1.backend.service.recommendation.trigger

import gb.gongbaek.v1.backend.dto.RecommendCategory
import gb.gongbaek.v1.backend.service.partner.PartnerService
import gb.gongbaek.v1.backend.service.recommendation.RecommendationService
import gb.gongbaek.v1.backend.service.recommendation.module.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class RecommendationTriggerImpl(

        @Autowired private val partnerService: PartnerService,
        @Autowired private val recommendationService: RecommendationService
): RecommendationTrigger {

    override fun getRecommendModule(category: RecommendCategory): BaseRecommendModule{

        return when(category){
            RecommendCategory.ACADEMY -> AcademyRecommendModule(partnerService, recommendationService)
            RecommendCategory.READING_ROOM -> ReadingRoomRecommendModule(partnerService, recommendationService)
            RecommendCategory.TODAY -> TodayRecommendModule(partnerService, recommendationService)
        }
    }
}