package gb.gongbaek.v1.backend.service.recommendation.module

import gb.gongbaek.v1.backend.domain.Recommendation
import gb.gongbaek.v1.backend.domain.partner.Partner
import gb.gongbaek.v1.backend.dto.PartnerType
import gb.gongbaek.v1.backend.dto.RecommendTab
import gb.gongbaek.v1.backend.dto.RecommendCategory
import gb.gongbaek.v1.backend.exception.WrongRecommendCategoryException
import gb.gongbaek.v1.backend.service.partner.PartnerService
import gb.gongbaek.v1.backend.service.recommendation.RecommendationService

class TodayRecommendModule(
        private val partnerService: PartnerService,
        private val recommendationService: RecommendationService
): BaseRecommendModule() {

    override fun getRecommendations(): List<Recommendation> =
            recommendationService.getRecommendationByCategory(RecommendTab.ACADEMY)

    override fun getPartners(recommendCategory: RecommendCategory): List<Partner> {

        return when(recommendCategory){
            RecommendCategory.AI_PICK -> recommendAIPick()
            RecommendCategory.PEER_PICK -> recommendPeerPick()
            RecommendCategory.MD_PICK -> recommendMDPick()
            RecommendCategory.POPULAR_PICK -> recommendPopularPick()
            RecommendCategory.BEST -> recommendBest()
            RecommendCategory.FAVORITE -> recommendFavorite()
            else -> throw WrongRecommendCategoryException("wrong recommend title on today tab. $recommendCategory")
        }
    }

    private fun recommendAIPick() = partnerService.getPartnersByType(PartnerType.ACADEMY)

    private fun recommendPeerPick() = partnerService.getPartnersByType(PartnerType.ACADEMY)

    private fun recommendPopularPick() = partnerService.getPartnersByType(PartnerType.ACADEMY)

    private fun recommendMDPick() = partnerService.getPartnersByType(PartnerType.ACADEMY)

    private fun recommendBest() = partnerService.getPartnersByType(PartnerType.ACADEMY)

    private fun recommendFavorite() = partnerService.getPartnersByType(PartnerType.ACADEMY)
}