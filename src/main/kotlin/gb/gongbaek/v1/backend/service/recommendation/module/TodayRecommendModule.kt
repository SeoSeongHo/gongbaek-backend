package gb.gongbaek.v1.backend.service.recommendation.module

import gb.gongbaek.v1.backend.domain.Recommendation
import gb.gongbaek.v1.backend.domain.partner.Partner
import gb.gongbaek.v1.backend.dto.PartnerType
import gb.gongbaek.v1.backend.dto.RecommendCategory
import gb.gongbaek.v1.backend.dto.RecommendTitle
import gb.gongbaek.v1.backend.exception.WrongRecommendTitleException
import gb.gongbaek.v1.backend.service.partner.PartnerService
import gb.gongbaek.v1.backend.service.recommendation.RecommendationService

class TodayRecommendModule(
        private val partnerService: PartnerService,
        private val recommendationService: RecommendationService
): BaseRecommendModule() {

    override fun getRecommendations(): List<Recommendation> =
            recommendationService.getRecommendationByCategory(RecommendCategory.ACADEMY)

    override fun getPartners(recommendTitle: RecommendTitle): List<Partner> {

        return when(recommendTitle){
            RecommendTitle.AI_PICK -> recommendAIPick()
            RecommendTitle.FRIEND_PICK -> recommendFriendPick()
            RecommendTitle.MD_PICK -> recommendMDPick()
            RecommendTitle.POPULAR_PICK -> recommendPopularPick()
            else -> throw WrongRecommendTitleException("wrong recommend title on today tab. $recommendTitle")
        }
    }

    private fun recommendAIPick() = partnerService.getPartnersByType(PartnerType.ACADEMY)

    private fun recommendFriendPick() = partnerService.getPartnersByType(PartnerType.ACADEMY)

    private fun recommendPopularPick() = partnerService.getPartnersByType(PartnerType.ACADEMY)

    private fun recommendMDPick() = partnerService.getPartnersByType(PartnerType.ACADEMY)
}