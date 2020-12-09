package gb.gongbaek.v1.backend.service.recommendation.trigger

import gb.gongbaek.v1.backend.dto.RecommendTab
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

    override fun getRecommendModule(category: RecommendTab): BaseRecommendModule{

        return when(category){
            RecommendTab.ACADEMY -> AcademyRecommendModule(partnerService, recommendationService)
            RecommendTab.READING_ROOM -> ReadingRoomRecommendModule(partnerService, recommendationService)
            RecommendTab.TODAY -> TodayRecommendModule(partnerService, recommendationService)
        }
    }
}