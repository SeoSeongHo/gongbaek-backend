package gb.gongbaek.v1.backend.controller

import gb.gongbaek.v1.backend.dto.RecommendationDto
import gb.gongbaek.v1.backend.service.recommendation.RecommendationService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/recommendations")
class RecommendationController(
        @Autowired private val recommendationService: RecommendationService
){

    @PostMapping
    fun createRecommendation(@RequestBody recommendationReq: RecommendationDto.RecommendationReq){

        recommendationService.createRecommendation(recommendationReq)
    }

    @PostMapping("/batch")
    fun createRecommendations(@RequestBody recommendationsReqs: List<RecommendationDto.RecommendationReq>){

        recommendationService.createRecommendations(recommendationsReqs)
    }

}