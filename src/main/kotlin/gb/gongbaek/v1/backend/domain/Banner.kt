package gb.gongbaek.v1.backend.domain

import gb.gongbaek.v1.backend.dto.BannerDto
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
data class Banner (
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long? = null,
        val platform: Platform,
        val tab: BannerTab,
        val imageUrl: String,
        val backgroundColor: String
): EntityAuditing() {

    fun toDto(): BannerDto.BannerRes{
        return BannerDto.BannerRes(
                id = id!!,
                imageUrl = toHttp(imageUrl),
                backgroundColor = backgroundColor
        )
    }
    
    // TODO s3 endpoint 에 ssl 처리하면 사라질 메서드
    fun toHttp(preStr: String): String{
        return preStr.replace("https", "http")
    }
}

enum class BannerTab {
    TODAY,
    ACADEMY,
    STUDY_ROOM,
    CLASS
}

enum class Platform {
    MOBILE,
    WEB,
    UNKNOWN
}
