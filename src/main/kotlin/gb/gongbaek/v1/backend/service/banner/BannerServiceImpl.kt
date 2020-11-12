package gb.gongbaek.v1.backend.service.banner

import gb.gongbaek.v1.backend.domain.Banner
import gb.gongbaek.v1.backend.dto.BannerDto
import gb.gongbaek.v1.backend.repository.BannerRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
@Transactional
class BannerServiceImpl(
        @Autowired private val bannerRepository: BannerRepository
): BannerService{

    fun getBanner(id: Long): Banner{
        return bannerRepository.findById(id).orElseThrow { throw Exception() }
    }

    override fun getBanners(): List<BannerDto.BannerRes>{

        val banners = bannerRepository.findAll()
        return banners.map{ banner -> banner.toDto() }
    }

    override fun createBanner(bannerReq: BannerDto.BannerReq): BannerDto.BannerRes{
        val createdBanner = bannerRepository.save(bannerReq.toEntity())
        return createdBanner.toDto()
    }
}