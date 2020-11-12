package gb.gongbaek.v1.backend.dto

class BannerDto {

    data class BannerRes(
            val title: String,
            val description: String,
            val imageUrl: String,
            val backgroundColor: String
    )
}