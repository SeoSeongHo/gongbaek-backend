package gb.gongbaek.v1.backend.controller

import gb.gongbaek.v1.backend.service.partner.hashtag.HashtagService
import gb.gongbaek.v1.backend.service.recommendation.RecommendationService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/initial")
class InitialController(
        @Autowired private val recommendationService: RecommendationService,
        @Autowired private val hashtagService: HashtagService
) {

    // 초기 값 세팅
    // recommendation, hashtag, category, tab
    @GetMapping
    fun initalize(){

        // set recommendation

        // set tab

        // set category

        // set hashtag
    }
}