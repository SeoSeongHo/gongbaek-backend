package gb.gongbaek.v1.backend.domain.partner

import gb.gongbaek.v1.backend.domain.Like
import gb.gongbaek.v1.backend.dto.HomeCardDto
import gb.gongbaek.v1.backend.dto.PartnerType
import gb.gongbaek.v1.backend.dto.partner.PartnerDto
import javax.persistence.DiscriminatorValue
import javax.persistence.Entity

@Entity
@DiscriminatorValue("R")
data class ReadingRoom(
        override val id: Long? = null,
        override val name: String,
        override val address: Address,
        override var isConfirmed: Boolean,
        override var likes: MutableList<Like>,

        var contact: String

): Partner(id, PartnerType.ACADEMY, name, address, isConfirmed, likes) {

    override fun toHomeCard(isLiked: Boolean): HomeCardDto.Card {
        TODO("Not yet implemented")
    }

    override fun toDto(): PartnerDto {
        TODO("Not yet implemented")
    }
}