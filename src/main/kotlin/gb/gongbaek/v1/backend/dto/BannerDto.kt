package gb.gongbaek.v1.backend.dto

import gb.gongbaek.v1.backend.domain.Banner
import gb.gongbaek.v1.backend.domain.BannerTab
import org.springframework.web.multipart.MultipartFile
import javax.persistence.EnumType
import javax.persistence.Enumerated

class BannerDto {

    data class BannerReq(
            var image: MultipartFile?,
            var tab: BannerTab,
            var backgroundColor: String
    ){

        fun toEntity(imageUrl: String) = Banner(
                imageUrl = imageUrl,
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