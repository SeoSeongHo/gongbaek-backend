package gb.gongbaek.v1.backend.domain.partner

import gb.gongbaek.v1.backend.domain.HashTag
import gb.gongbaek.v1.backend.domain.Like
import gb.gongbaek.v1.backend.dto.HomeCardDto
import gb.gongbaek.v1.backend.dto.PartnerType
import gb.gongbaek.v1.backend.dto.partner.AcademyDto
import javax.persistence.DiscriminatorValue
import javax.persistence.Embeddable
import javax.persistence.Embedded
import javax.persistence.Entity

@Entity
@DiscriminatorValue("A")
// 학원, 공부방, 그룹과외
data class Academy (
        override val id: Long? = null,
        override val name: String,
        override val address: Address,
        override var isConfirmed: Boolean,
        override var likes: MutableList<Like>,
        //override var hashTags: MutableList<HashTag>,

        var branchName: String? = null,
        var contact: String,
        var representativeContact: String,

        var businessRegistration: String,
        var operationalCertification: String,
        var webSiteUrl: String?
): Partner(id, PartnerType.ACADEMY, name, address, isConfirmed, likes) {

    override fun toHomeCard(isLiked: Boolean) = HomeCardDto.Card(
            partnerType = PartnerType.ACADEMY,
            partnerId = id!!,
            imageUrl = operationalCertification,
            name = name + branchName,
            location = address.roadAddress,
            isLiked = isLiked,
            totalLikes = getTotalLikes()
    )

    override fun toDto() = AcademyDto.AcademyRes(
            id = id,
            type = type,
            name = name,
            address = address,
            isConfirmed = isConfirmed,
            totalLikes = getTotalLikes()
    )

}

@Embeddable
data class Address(
     var roadAddress: String,
     var detailAddress: String
)