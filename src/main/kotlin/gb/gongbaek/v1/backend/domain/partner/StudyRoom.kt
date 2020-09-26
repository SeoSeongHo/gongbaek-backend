package gb.gongbaek.v1.backend.domain.partner

import gb.gongbaek.v1.backend.domain.Like
import gb.gongbaek.v1.backend.dto.HomeCardDto
import gb.gongbaek.v1.backend.dto.PartnerType
import gb.gongbaek.v1.backend.dto.partner.PartnerDto
import gb.gongbaek.v1.backend.dto.partner.StudyRoomDto
import javax.persistence.*

@Entity
@DiscriminatorValue("S")
// 독서실
data class StudyRoom(
        override val id: Long? = null,
        override val name: String,
        override val address: Address,
        override var isConfirmed: Boolean,
        override var likes: List<Like>,

        var validationImageUrl: String, // validationImageUrl
        var contact: String,
        var depositor: String
): Partner(id, PartnerType.STUDY_ROOM, name, address, isConfirmed, likes) {

    override fun toHomeCard(isLiked: Boolean) = HomeCardDto.Card(
            partnerType = PartnerType.ACADEMY,
            partnerId = id!!,
            imageUrl = validationImageUrl,
            name = name,
            location = address.roadAddress,
            isLiked = isLiked,
            totalLikes = getTotalLikes()
    )

    override fun toDto() = StudyRoomDto.StudyRoomRes(
            id = id,
            type = type,
            name = name,
            address = address,
            isConfirmed = isConfirmed,
            totalLikes = getTotalLikes()
    )
}