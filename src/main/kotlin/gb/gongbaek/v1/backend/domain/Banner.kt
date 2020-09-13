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
        val name: String,
        val imageUrl: String
): EntityAuditing() {

    fun toDto(): BannerDto.BannerRes{
        return BannerDto.BannerRes(
                imageUrl = imageUrl
        )
    }
}