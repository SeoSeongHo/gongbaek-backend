package gb.gongbaek.v1.backend.service.recommendation.module

import gb.gongbaek.v1.backend.domain.Recommendation
import gb.gongbaek.v1.backend.domain.partner.Partner
import gb.gongbaek.v1.backend.dto.PartnerType
import gb.gongbaek.v1.backend.dto.RecommendTab
import gb.gongbaek.v1.backend.dto.RecommendCategory
import gb.gongbaek.v1.backend.exception.WrongRecommendCategoryException
import gb.gongbaek.v1.backend.service.partner.PartnerService
import gb.gongbaek.v1.backend.service.recommendation.RecommendationService

class AcademyRecommendModule(
        private val partnerService: PartnerService,
        private val recommendationService: RecommendationService
): BaseRecommendModule() {

    override fun getRecommendations(): List<Recommendation> =
            recommendationService.getRecommendationByCategory(RecommendTab.ACADEMY)

    override fun getPartners(recommendCategory: RecommendCategory): List<Partner>{

        return when(recommendCategory){
            RecommendCategory.PREFERENCE -> recommendPreference()
            RecommendCategory.NEIGHBORHOOD -> recommendNeighborhood()
            RecommendCategory.FEW_ELITE -> recommendFewElite()
            RecommendCategory.CLOSE_COACH -> recommendCloseCoach()
            RecommendCategory.SINGLE_SUBJECT -> recommendSingleSubject()
            else -> throw WrongRecommendCategoryException("wrong recommend title on academy tab. $recommendCategory")
        }
    }

    private fun recommendPreference () = partnerService.getPartnersByType(PartnerType.ACADEMY)

    private fun recommendNeighborhood() = partnerService.getPartnersByType(PartnerType.ACADEMY)

    private fun recommendFewElite() = partnerService.getPartnersByType(PartnerType.ACADEMY)

    private fun recommendCloseCoach() = partnerService.getPartnersByType(PartnerType.ACADEMY)
    
    private fun recommendSingleSubject() = partnerService.getPartnersByType(PartnerType.ACADEMY)
}