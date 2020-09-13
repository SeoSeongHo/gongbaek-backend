package gb.gongbaek.v1.backend.service.banner

import gb.gongbaek.v1.backend.dto.BannerDto

interface BannerService {
    fun getBanners(): List<BannerDto.BannerRes>
}