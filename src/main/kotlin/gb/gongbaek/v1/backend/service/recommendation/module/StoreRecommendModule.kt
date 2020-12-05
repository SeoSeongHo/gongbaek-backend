//package gb.gongbaek.v1.backend.service.recommendation.module
//
//import gb.gongbaek.v1.backend.domain.Recommendation
//import gb.gongbaek.v1.backend.domain.partner.Partner
//import gb.gongbaek.v1.backend.dto.PartnerType
//import gb.gongbaek.v1.backend.dto.RecommendCategory
//import gb.gongbaek.v1.backend.dto.RecommendTitle
//import gb.gongbaek.v1.backend.service.partner.PartnerService
//import gb.gongbaek.v1.backend.service.recommendation.RecommendationService
//
//class StoreRecommendModule(
//        private val partnerService: PartnerService,
//        private val recommendationService: RecommendationService
//): BaseRecommendModule() {
//
//    override fun getRecommendations(): List<Recommendation> =
//            recommendationService.getRecommendationByCategory(RecommendCategory.STORE)
//
//    override fun triggerModule(recommendTitle: RecommendTitle): List<Partner> {
//        TODO("Not yet implemented")
//    }
//}