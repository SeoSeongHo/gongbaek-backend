package gb.gongbaek.v1.backend.domain.partner

import gb.gongbaek.v1.backend.domain.EntityAuditing
import gb.gongbaek.v1.backend.domain.Like
import gb.gongbaek.v1.backend.domain.Recommendation
import gb.gongbaek.v1.backend.dto.AcademyDto
import gb.gongbaek.v1.backend.dto.HomeCardDto
import gb.gongbaek.v1.backend.dto.PartnerType
import org.jetbrains.annotations.NotNull
import javax.persistence.*

@Entity
@DiscriminatorValue("A")
// 학원, 공부방, 그룹과외
data class Academy (
        override val id: Long? = null,
        override val name: String,
        override val address: Address,
        override var isConfirmed: Boolean,
        override var likes: List<Like>,

        var branchName: String? = null,
        @Embedded
        var contact: String,
        var businessRegistration: String,
        var imageUrl: String
): Partner(id, PartnerType.ACADEMY, name, address, isConfirmed, likes) {

    override fun toHomeCard(isLiked: Boolean) = HomeCardDto.Card(
            partnerType = PartnerType.ACADEMY,
            partnerId = id!!,
            imageUrl = imageUrl,
            name = name + branchName,
            location = address.roadAddress,
            isLiked = isLiked,
            totalLikes = getTotalLikes()
    )

}

@Embeddable
data class Address(
     var roadAddress: String,
     var detailAddress: String
)