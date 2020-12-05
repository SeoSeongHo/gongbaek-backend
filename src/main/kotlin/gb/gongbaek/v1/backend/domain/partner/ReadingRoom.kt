package gb.gongbaek.v1.backend.domain.partner

import gb.gongbaek.v1.backend.domain.Like
import gb.gongbaek.v1.backend.dto.HomeCardDto
import gb.gongbaek.v1.backend.dto.PartnerType
import gb.gongbaek.v1.backend.dto.partner.readingRoom.ReadingRoomDetailDto
import gb.gongbaek.v1.backend.dto.partner.readingRoom.ReadingRoomDto
import javax.persistence.DiscriminatorValue
import javax.persistence.Embeddable
import javax.persistence.Embedded
import javax.persistence.Entity

@Entity
@DiscriminatorValue("R")
// 독서실, 스터디카페
data class ReadingRoom(
        override val id: Long? = null,
        override val name: String, // 시설명
        override var isConfirmed: Boolean,
        override var likes: MutableList<Like>,

        override var branchName: String? = null,
        override var adminContact: String, // 연락처, 괸리자 컨택용
        override var representativeContact: String, // 대표 번호, 유저 공개용

        override var businessRegistration: String? = null, // 사업자 등록증

        override var operationalCertification: List<String>?, // 운영 인증, 유저 공개용
        override var representativeImage: String, // 홈카드 대표 사진, 유저 공개용

        @Embedded
        var detail: ReadingRoomDetail // 독서실 디테일

): Partner(id, PartnerType.ACADEMY, name, isConfirmed, likes, branchName, adminContact, representativeContact, businessRegistration, operationalCertification, representativeImage) {

    // 홈 카드 변환 메서드
    override fun toHomeCard(isLiked: Boolean) = HomeCardDto.Card(
            partnerType = PartnerType.ACADEMY,
            partnerId = id!!,
            imageUrl = representativeImage,
            name = name,
            location = detail.address.roadAddress,
            isLiked = isLiked,
            totalLikes = getTotalLikes()
    )
    
    // 기본 Dto 변환 메서드
    override fun toDto() = ReadingRoomDto.ReadingRoomRes(
            id = id,
            type = type,
            name = name,
            address = detail.address,
            isConfirmed = isConfirmed,
            totalLikes = getTotalLikes()
    )
    
    // 독서실 상세 Dto 변환 메서드
    fun toDetailDto() = ReadingRoomDetailDto.ReadingRoomDetailRes(
            id = id!!,
            name = name,
            category = detail.category,
            webSiteUrl = detail.webSiteUrl,
            address = detail.address
    )
}

@Embeddable
data class ReadingRoomDetail(

        var category: String, // 분류
        var webSiteUrl: String?,
        var address: Address
)