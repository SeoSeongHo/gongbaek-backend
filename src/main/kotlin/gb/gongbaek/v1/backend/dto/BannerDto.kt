package gb.gongbaek.v1.backend.dto

import gb.gongbaek.v1.backend.domain.Banner
import org.springframework.web.multipart.MultipartFile

class BannerDto {

    data class BannerReq(
            var title: String,
            var description: String,
            var image: MultipartFile?,
            var backgroundColor: String
    ){

        fun toEntity(imageUrl: String) = Banner(
                title = title,
                description = description,
                imageUrl = imageUrl,
                backgroundColor = backgroundColor
        )
    }


    data class BannerRes(
            val id: Long,
            val title: String,
            val description: String,
            val imageUrl: String,
            val backgroundColor: String
    )
}