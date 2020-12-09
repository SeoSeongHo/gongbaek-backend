package gb.gongbaek.v1.backend.service.recommendation.module

import gb.gongbaek.v1.backend.domain.Recommendation
import gb.gongbaek.v1.backend.domain.partner.Partner
import gb.gongbaek.v1.backend.dto.PartnerType
import gb.gongbaek.v1.backend.dto.RecommendTab
import gb.gongbaek.v1.backend.dto.RecommendCategory
import gb.gongbaek.v1.backend.exception.WrongRecommendCategoryException
import gb.gongbaek.v1.backend.service.partner.PartnerService
import gb.gongbaek.v1.backend.service.recommendation.RecommendationService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ReadingRoomRecommendModule(
        @Autowired private val partnerService: PartnerService,
        @Autowired private val recommendationService: RecommendationService
): BaseRecommendModule() {

    override fun getRecommendations(): List<Recommendation> =
            recommendationService.getRecommendationByCategory(RecommendTab.READING_ROOM)

    override fun getPartners(recommendCategory: RecommendCategory): List<Partner>{

        return when(recommendCategory){
            RecommendCategory.STUDY_CAFE -> recommendStudyCafe()
            RecommendCategory.CHEAP -> recommendCheap()
            RecommendCategory.PREMIUM -> recommendPremium()
            RecommendCategory.MANAGEMENT -> recommendManagerment()
            RecommendCategory.LOCAL_STUDY_ROOM -> recommendLocalStudyRoom()
            else -> throw WrongRecommendCategoryException("wrong recommend title on reading room tab. $recommendCategory")
        }
    }

    private fun recommendStudyCafe() = partnerService.getPartnersByType(PartnerType.READING_ROOM)

    private fun recommendCheap() = partnerService.getPartnersByType(PartnerType.READING_ROOM)

    private fun recommendPremium() = partnerService.getPartnersByType(PartnerType.READING_ROOM)

    private fun recommendManagerment() = partnerService.getPartnersByType(PartnerType.READING_ROOM)

    private fun recommendLocalStudyRoom() = partnerService.getPartnersByType(PartnerType.READING_ROOM)
}