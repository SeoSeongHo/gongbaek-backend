package gb.gongbaek.v1.backend.service.recommendation.module

import gb.gongbaek.v1.backend.domain.Recommendation
import gb.gongbaek.v1.backend.domain.partner.Partner
import gb.gongbaek.v1.backend.dto.PartnerType
import gb.gongbaek.v1.backend.dto.RecommendCategory
import gb.gongbaek.v1.backend.dto.RecommendTitle
import gb.gongbaek.v1.backend.exception.WrongRecommendTitleException
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
            recommendationService.getRecommendationByCategory(RecommendCategory.READING_ROOM)

    override fun getPartners(recommendTitle: RecommendTitle): List<Partner>{

        return when(recommendTitle){
            RecommendTitle.STUDY_CAFE -> recommendStudyCafe()
            RecommendTitle.CHEAP -> recommendCheap()
            RecommendTitle.PREMIUM -> recommendPremium()
            RecommendTitle.MANAGERMENT -> recommendManagerment()
            RecommendTitle.LOCAL_STUDYROOM -> recommendLocalStudyRoom()
            else -> throw WrongRecommendTitleException("wrong recommend title on reading room tab. $recommendTitle")
        }
    }

    private fun recommendStudyCafe() = partnerService.getPartnersByType(PartnerType.READING_ROOM)

    private fun recommendCheap() = partnerService.getPartnersByType(PartnerType.READING_ROOM)

    private fun recommendPremium() = partnerService.getPartnersByType(PartnerType.READING_ROOM)

    private fun recommendManagerment() = partnerService.getPartnersByType(PartnerType.READING_ROOM)

    private fun recommendLocalStudyRoom() = partnerService.getPartnersByType(PartnerType.READING_ROOM)
}