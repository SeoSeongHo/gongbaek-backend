package gb.gongbaek.v1.backend.dto

import gb.gongbaek.v1.backend.domain.Banner

class BannerDto {

    data class BannerReq(
            val title: String,
            val description: String,
            val imageUrl: String,
            val backgroundColor: String
    ){
        fun toEntity() = Banner(
                title = title,
                description = description,
                imageUrl = imageUrl,
                backgroundColor = backgroundColor
        )
    }

    data class BannerRes(
            val title: String,
            val description: String,
            val imageUrl: String,
            val backgroundColor: String
    )
}