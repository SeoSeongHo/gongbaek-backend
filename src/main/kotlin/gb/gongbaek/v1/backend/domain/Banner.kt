package gb.gongbaek.v1.backend.domain

import gb.gongbaek.v1.backend.dto.BannerDto
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

// TODO RDS 외에 다른 DB 에 저장
@Entity
data class Banner (
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long? = null,
        val title: String,
        val description: String,
        val imageUrl: String,
        val backgroundColor: String
): EntityAuditing() {

    fun toDto(): BannerDto.BannerRes{
        return BannerDto.BannerRes(
                id = id!!,
                title = title,
                description = description,
                imageUrl = imageUrl,
                backgroundColor = backgroundColor
        )
    }
}