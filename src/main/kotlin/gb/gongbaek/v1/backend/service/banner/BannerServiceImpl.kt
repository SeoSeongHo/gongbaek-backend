package gb.gongbaek.v1.backend.service.banner

import com.amazonaws.services.s3.AmazonS3
import gb.gongbaek.v1.backend.domain.Banner
import gb.gongbaek.v1.backend.domain.BannerTab
import gb.gongbaek.v1.backend.domain.Platform
import gb.gongbaek.v1.backend.dto.BannerDto
import gb.gongbaek.v1.backend.exception.ImageUploadException
import gb.gongbaek.v1.backend.repository.BannerRepository
import gb.gongbaek.v1.backend.util.S3Uploader
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
@Transactional
class BannerServiceImpl(
        @Autowired private val bannerRepository: BannerRepository,
        @Autowired private val amazonS3Client: AmazonS3,
        @Value("\${aws.s3.img.banner.bucket}")
        private val bucketName: String,
        @Value("\${aws.s3.img.banner.dir}")
        private val dirName: String
): BannerService{

    fun getBanner(id: Long): Banner{
        return bannerRepository.findById(id).orElseThrow { throw Exception() }
    }

    override fun getBanners(): List<BannerDto.BannerRes>{

        val banners = bannerRepository.findAll()
        return banners.map{ banner -> banner.toDto() }
    }

    override fun getBannersByTab(tab: BannerTab): List<BannerDto.BannerRes>{
        val banners = bannerRepository.findBannersByTab(tab)
        return banners.map { banner -> banner.toDto() }
    }

    override fun createBanner(platform: Platform, bannerReq: BannerDto.BannerReq): BannerDto.BannerRes{

        val s3Uploader = S3Uploader(amazonS3Client, bucketName, dirName)
        try{
            val imageUrl: String? = s3Uploader.upload(bannerReq.image!!) ?: throw ImageUploadException("failed to upload image to s3.")
            val createdBanner = bannerRepository.save(bannerReq.toEntity(imageUrl!!, platform))
            return createdBanner.toDto()
        }
        catch(e: Exception){
            throw ImageUploadException("failed to upload image to s3.")
        }

    }
}