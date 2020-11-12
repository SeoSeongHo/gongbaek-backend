package gb.gongbaek.v1.backend.controller

import gb.gongbaek.v1.backend.dto.BannerDto
import gb.gongbaek.v1.backend.service.banner.BannerService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/banners")
class BannerController(
        @Autowired private val bannerService: BannerService
) {

    @GetMapping
    fun getBanners(): ResponseEntity<List<BannerDto.BannerRes>> {

        return ResponseEntity
                .ok()
                .body(bannerService.getBanners())
    }

    @PostMapping
    fun createBanner(@RequestBody bannerReq: BannerDto.BannerReq): ResponseEntity<BannerDto.BannerRes>{

        return ResponseEntity
                .ok()
                .body(bannerService.createBanner(bannerReq))
    }
}