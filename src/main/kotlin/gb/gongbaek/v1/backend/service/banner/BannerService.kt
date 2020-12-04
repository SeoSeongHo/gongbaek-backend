package gb.gongbaek.v1.backend.service.banner

import gb.gongbaek.v1.backend.domain.BannerTab
import gb.gongbaek.v1.backend.dto.BannerDto

interface BannerService {
    fun getBanners(): List<BannerDto.BannerRes>
    fun getBannersByTab(tab: BannerTab): List<BannerDto.BannerRes>
    fun createBanner(bannerReq: BannerDto.BannerReq): BannerDto.BannerRes
}