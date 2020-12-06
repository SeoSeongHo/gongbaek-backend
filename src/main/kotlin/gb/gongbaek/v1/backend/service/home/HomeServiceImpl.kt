package gb.gongbaek.v1.backend.service.home

import gb.gongbaek.v1.backend.domain.User
import gb.gongbaek.v1.backend.dto.*
import gb.gongbaek.v1.backend.exception.RecommendationNotFoundException
import gb.gongbaek.v1.backend.service.like.LikeService
import gb.gongbaek.v1.backend.service.recommendation.RecommendationService
import gb.gongbaek.v1.backend.service.recommendation.trigger.RecommendationTrigger
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class HomeServiceImpl(
        @Autowired private val recommendationTrigger: RecommendationTrigger,
        @Autowired private val likeService: LikeService,
        @Autowired private val recommendationService: RecommendationService
): HomeService {

    // 단일 category 에 대한 home card 반환
    override fun getCards(category: RecommendCategory, userId: Long?): List<HomeCardDto.HomeCardRes> {

        // category 에 맞는 recommend module 가져오기
        val recommendModule = recommendationTrigger.getRecommendModule(category)
        
        // category 에 포함되는 recommendation 가져오기
        val recommendations = recommendModule.getRecommendations()

        // 해당 recommendation 에 해당하는 파트너 가져오기 및 맵핑
        return recommendations.map { recommendation -> HomeCardDto.HomeCardRes(
                title = recommendation.title,
                description = recommendation.description,
                cards = recommendModule.getPartners(recommendation.title)
                        .map { partner -> partner.toHomeCard(
                                if(userId != null) likeService.existsLikeByUserIdAndPartnerId(userId, partner.id!!)
                                else false ) }
        ) }
    }

    // 단일 title 에 대한 home card 반환
    override fun getCards(category: RecommendCategory, title: RecommendTitle, userId: Long?): HomeCardDto.HomeCardRes{

        // category 에 맞는 recommend module 가져오기
        val recommendModule = recommendationTrigger.getRecommendModule(category)

        // category 에 포함되는 recommendation 가져오기
        val recommendation = recommendationService.getRecommendationByTitle(title) ?: throw RecommendationNotFoundException("can not find recommendation. title: $title")

        // 해당 recommendation 에 해당하는 파트너 가져오기 및 맵핑
        return HomeCardDto.HomeCardRes(
                title = recommendation.title,
                description = recommendation.description,
                cards = recommendModule.getPartners(recommendation.title)
                        .map { partner -> partner.toHomeCard(
                                if(userId != null) likeService.existsLikeByUserIdAndPartnerId(userId, partner.id!!)
                                else false ) }
        )
    }
}