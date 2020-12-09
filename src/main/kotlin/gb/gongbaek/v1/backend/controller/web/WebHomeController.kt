package gb.gongbaek.v1.backend.controller.web

import gb.gongbaek.v1.backend.dto.HomeCardDto
import gb.gongbaek.v1.backend.dto.RecommendTab
import gb.gongbaek.v1.backend.dto.RecommendCategory
import gb.gongbaek.v1.backend.dto.auth.AuthPrincipal
import gb.gongbaek.v1.backend.service.home.HomeService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/web/api/v1/home")
class WebHomeController(
        @Autowired private val homeService: HomeService
) {

    @GetMapping("/{category}")
    fun getCards(@PathVariable category: RecommendTab): ResponseEntity<List<HomeCardDto.HomeCardRes>>{

        val cards = homeService.getCards(category, null)

        return ResponseEntity
                .ok()
                .body(cards)
    }

    // category, title 에 대한 home card 반환
    @GetMapping("/{category}/recommend/{title}")
    fun getCardsByTitle(@PathVariable category: RecommendTab, @PathVariable title: RecommendCategory): ResponseEntity<HomeCardDto.HomeCardRes>{

        val cards = homeService.getCards(category, title, null)

        return ResponseEntity
                .ok()
                .body(cards)
    }
}