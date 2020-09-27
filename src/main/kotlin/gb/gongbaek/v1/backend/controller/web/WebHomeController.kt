package gb.gongbaek.v1.backend.controller.web

import gb.gongbaek.v1.backend.dto.HomeCardDto
import gb.gongbaek.v1.backend.dto.RecommendCategory
import gb.gongbaek.v1.backend.service.home.HomeService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/w/api/v1//home")
class WebHomeController(
        @Autowired private val homeService: HomeService
) {

    @GetMapping("/{category}")
    fun getCards(@PathVariable category: RecommendCategory): ResponseEntity<List<HomeCardDto.HomeCardRes>>{

        val cards = homeService.getCards(category, null)

        return ResponseEntity
                .ok()
                .body(cards)
    }
}