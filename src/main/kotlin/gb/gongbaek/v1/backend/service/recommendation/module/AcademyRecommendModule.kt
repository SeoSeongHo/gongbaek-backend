package gb.gongbaek.v1.backend.service.recommendation.module

import gb.gongbaek.v1.backend.domain.Recommendation
import gb.gongbaek.v1.backend.domain.partner.Partner
import gb.gongbaek.v1.backend.dto.PartnerType
import gb.gongbaek.v1.backend.dto.RecommendCategory
import gb.gongbaek.v1.backend.dto.RecommendTitle
import gb.gongbaek.v1.backend.service.partner.PartnerService
import gb.gongbaek.v1.backend.service.recommendation.RecommendationService

class AcademyRecommendModule(
        private val partnerService: PartnerService,
        private val recommendationService: RecommendationService
): BaseRecommendModule() {

    override fun getRecommendations(): List<Recommendation> =
            recommendationService.getRecommendationByCategory(RecommendCategory.ACADEMY)

    override fun triggerModule(recommendTitle: RecommendTitle): List<Partner>{

        return when(recommendTitle){
            RecommendTitle.MY_FAVORITE -> recommendMyFavorite()
            RecommendTitle.NEIGHBORHOOD -> recommendNeighborhood()
            RecommendTitle.FEW_ELITE -> recommendFewElite()
            RecommendTitle.CLOSED_COACH -> recommendClosedCoach()
            RecommendTitle.SINGLE_SUBJECT -> recommendSingleSubject()
            else -> throw Exception()
        }
    }

    private fun recommendMyFavorite() = partnerService.getPartnersByType(PartnerType.ACADEMY)

    private fun recommendNeighborhood() = partnerService.getPartnersByType(PartnerType.ACADEMY)

    private fun recommendFewElite() = partnerService.getPartnersByType(PartnerType.ACADEMY)

    private fun recommendClosedCoach() = partnerService.getPartnersByType(PartnerType.ACADEMY)
    
    private fun recommendSingleSubject() = partnerService.getPartnersByType(PartnerType.ACADEMY)
}