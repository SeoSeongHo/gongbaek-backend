package gb.gongbaek.v1.backend.service.recommendation.module

import gb.gongbaek.v1.backend.domain.Recommendation
import gb.gongbaek.v1.backend.domain.partner.Partner
import gb.gongbaek.v1.backend.dto.PartnerType
import gb.gongbaek.v1.backend.dto.RecommendCategory
import gb.gongbaek.v1.backend.dto.RecommendTitle
import gb.gongbaek.v1.backend.service.partner.PartnerService
import gb.gongbaek.v1.backend.service.recommendation.RecommendationService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class StudyRoomRecommendModule(
        @Autowired private val partnerService: PartnerService,
        @Autowired private val recommendationService: RecommendationService
): BaseRecommendModule() {

    override fun getRecommendations(): List<Recommendation> =
            recommendationService.getRecommendationByCategory(RecommendCategory.STUDY_ROOM)

    override fun triggerModule(recommendTitle: RecommendTitle): List<Partner>{

        return when(recommendTitle){
            RecommendTitle.DISCUSS -> recommendDiscuss()
            RecommendTitle.CHEAP -> recommendCheap()
            RecommendTitle.HIGH_QUALITY -> recommendHighQuality()
            RecommendTitle.TEACHER -> recommendTeacher()
            else -> throw Exception()
        }
    }

    private fun recommendTeacher() = partnerService.getPartnersByType(PartnerType.STUDY_ROOM)

    private fun recommendHighQuality() = partnerService.getPartnersByType(PartnerType.STUDY_ROOM)

    private fun recommendCheap() = partnerService.getPartnersByType(PartnerType.STUDY_ROOM)

    private fun recommendDiscuss() = partnerService.getPartnersByType(PartnerType.STUDY_ROOM)
}