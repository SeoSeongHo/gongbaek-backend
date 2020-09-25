package gb.gongbaek.v1.backend.domain.partner

import gb.gongbaek.v1.backend.domain.Like
import gb.gongbaek.v1.backend.domain.Recommendation
import gb.gongbaek.v1.backend.dto.HomeCardDto
import gb.gongbaek.v1.backend.dto.PartnerType
import javax.persistence.DiscriminatorValue
import javax.persistence.Entity

@Entity
@DiscriminatorValue("S")
// 스토어
data class Store(
        override val id: Long? = null,
        override val name: String,
        override val address: Address,
        override var isConfirmed: Boolean,
        override var likes: List<Like>,

        var imageUrl: String
): Partner(id, PartnerType.STORE, name, address, isConfirmed, likes) {


    override fun toHomeCard(isLiked: Boolean) = HomeCardDto.Card(
            partnerType = PartnerType.STORE,
            partnerId = id!!,
            imageUrl = imageUrl,
            name = name,
            location = "",
            isLiked = isLiked,
            totalLikes = getTotalLikes()
    )
}