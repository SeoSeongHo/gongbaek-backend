package gb.gongbaek.v1.backend.controller

import gb.gongbaek.v1.backend.dto.HomeCardDto
import gb.gongbaek.v1.backend.dto.RecommendTab
import gb.gongbaek.v1.backend.dto.RecommendCategory
import gb.gongbaek.v1.backend.dto.auth.AuthPrincipal
import gb.gongbaek.v1.backend.service.home.HomeService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/home")
class HomeController(
        @Autowired private val homeService: HomeService
) {

    // category 에 대한 home card 반환
    @GetMapping("/{category}")
    fun getCards(@PathVariable category: RecommendTab, @AuthenticationPrincipal authPrincipal: AuthPrincipal): ResponseEntity<List<HomeCardDto.HomeCardRes>>{

        val cards = homeService.getCards(category, authPrincipal.userId)

        return ResponseEntity
                .ok()
                .body(cards)
    }

    // category, title 에 대한 home card 반환
    @GetMapping("/{category}/recommend/{title}")
    fun getCardsByTitle(@PathVariable category: RecommendTab, @PathVariable title: RecommendCategory, @AuthenticationPrincipal authPrincipal: AuthPrincipal): ResponseEntity<HomeCardDto.HomeCardRes>{

        val cards = homeService.getCards(category, title, authPrincipal.userId)

        return ResponseEntity
                .ok()
                .body(cards)
    }
}