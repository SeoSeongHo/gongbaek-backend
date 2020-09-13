package gb.gongbaek.v1.backend.service.banner

import gb.gongbaek.v1.backend.dto.BannerDto
import gb.gongbaek.v1.backend.repository.BannerRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class BannerServiceImpl(
        @Autowired private val bannerRepository: BannerRepository
): BannerService{

    override fun getBanners(): List<BannerDto.BannerRes>{

        val banners = bannerRepository.findAll()
        return banners.map{ banner -> banner.toDto() }
    }
}