package gb.gongbaek.v1.backend.dto

import gb.gongbaek.v1.backend.domain.Banner
import gb.gongbaek.v1.backend.domain.BannerTab
import gb.gongbaek.v1.backend.domain.Platform
import org.springframework.web.multipart.MultipartFile

class BannerDto {

    data class BannerReq(
            var image: MultipartFile?,
            var tab: BannerTab,
            var backgroundColor: String
    ){

        fun toEntity(imageUrl: String, platform: Platform) = Banner(
                imageUrl = imageUrl,
                platform = platform,
                tab = tab,
                backgroundColor = backgroundColor
        )
    }


    data class BannerRes(
            val id: Long,
            val imageUrl: String,
            val backgroundColor: String
    )
}