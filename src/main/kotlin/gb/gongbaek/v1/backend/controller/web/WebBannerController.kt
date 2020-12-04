package gb.gongbaek.v1.backend.controller.web

import gb.gongbaek.v1.backend.domain.BannerTab
import gb.gongbaek.v1.backend.domain.Platform
import gb.gongbaek.v1.backend.dto.BannerDto
import gb.gongbaek.v1.backend.service.banner.BannerService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/web/api/v1/banners")
class WebBannerController(
        @Autowired private val bannerService: BannerService
) {

    @GetMapping
    fun getBanners(@RequestParam tab: BannerTab): ResponseEntity<List<BannerDto.BannerRes>> {

        return ResponseEntity
                .ok()
                .body(bannerService.getBannersByTab(tab))

    }


    @PostMapping(consumes = [ MediaType.MULTIPART_FORM_DATA_VALUE ])
    fun createBanner(bannerReq: BannerDto.BannerReq): ResponseEntity<BannerDto.BannerRes> {

        val createdBanner = bannerService.createBanner(Platform.WEB, bannerReq)

        return ResponseEntity
                .ok()
                .body(createdBanner)
    }
}