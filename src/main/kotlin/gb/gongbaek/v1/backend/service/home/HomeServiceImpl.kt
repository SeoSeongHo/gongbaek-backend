package gb.gongbaek.v1.backend.service.home

import gb.gongbaek.v1.backend.domain.User
import gb.gongbaek.v1.backend.dto.*
import gb.gongbaek.v1.backend.service.like.LikeService
import gb.gongbaek.v1.backend.service.recommendation.trigger.RecommendationTrigger
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class HomeServiceImpl(
        @Autowired private val recommendationTrigger: RecommendationTrigger,
        @Autowired private val likeService: LikeService
): HomeService {

    override fun getCards(category: RecommendCategory, userId: Long?): List<HomeCardDto.HomeCardRes> {

        val recommendModule = recommendationTrigger.getRecommendModule(category)
        val recommendations = recommendModule.getRecommendations()

        return recommendations.map { recommendation -> HomeCardDto.HomeCardRes(
                title = recommendation.title,
                description = recommendation.description,
                cards = recommendModule.triggerModule(recommendation.title)
                        .map { partner -> partner.toHomeCard(
                                if(userId != null) likeService.existsLikeByUserIdAndPartnerId(userId, partner.id!!)
                                else false ) }
        ) }
    }
}