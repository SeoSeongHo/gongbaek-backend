package gb.gongbaek.v1.backend.domain.partner

import gb.gongbaek.v1.backend.domain.Like
import gb.gongbaek.v1.backend.domain.OperationalCertification
import gb.gongbaek.v1.backend.domain.hashtag.PartnerHashtag
import gb.gongbaek.v1.backend.dto.HomeCardDto
import gb.gongbaek.v1.backend.dto.PartnerType
import gb.gongbaek.v1.backend.dto.partner.academy.AcademyDto
import gb.gongbaek.v1.backend.dto.partner.readingRoom.ReadingRoomDetailDto
import gb.gongbaek.v1.backend.dto.partner.readingRoom.ReadingRoomDto
import javax.persistence.*

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

        @OneToMany
        override var operationalCertification: MutableList<OperationalCertification>?, // 운영 인증, 유저 공개용
        override var representativeImage: String?, // 홈카드 대표 사진, 유저 공개용

        override var partnerHashtags: MutableList<PartnerHashtag> = mutableListOf(), // 해시태그

        @Embedded
        var detail: ReadingRoomDetail // 독서실 디테일

): Partner(id, PartnerType.ACADEMY, name, isConfirmed, likes, branchName, adminContact, representativeContact, businessRegistration, operationalCertification, representativeImage, partnerHashtags) {

    // 홈 카드 변환 메서드
    override fun toHomeCard(isLiked: Boolean) = HomeCardDto.Card(
            partnerType = PartnerType.ACADEMY,
            partnerId = id!!,
            imageUrl = representativeImage!!,
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
            address = detail.address,
            hashtags = partnerHashtags.map { ph -> ph.hashtag.name }
    )

    // 파트너 해시태그 추가 메서드
    fun addPartnerHashTag(partnerHashtag: PartnerHashtag){
        partnerHashtags.add(partnerHashtag)
        partnerHashtag.partner = this
    }

    companion object{

        // 독서실 생성 메서드
        fun createReadingRoom(req: ReadingRoomDto.CreateReadingRoomReq, imageReq: ReadingRoomDto.CreateReadingRoomImageReq, partnerHashtags: MutableList<PartnerHashtag>): ReadingRoom{

            val readingRoom = ReadingRoom(
                    name = req.name,
                    isConfirmed = false,
                    likes = mutableListOf(),
                    branchName = req.branchName,
                    adminContact = req.adminContact,
                    representativeContact = req.representativeContact,
                    businessRegistration = imageReq.businessRegistration,
                    operationalCertification = imageReq.operationalCertification,
                    representativeImage = imageReq.representativeImage,

                    detail = ReadingRoomDetail(
                            category = req.category,
                            webSiteUrl = req.webSiteUrl,
                            address = req.address
                    )
            )

            partnerHashtags.map { partnerHashtag -> readingRoom.addPartnerHashTag(partnerHashtag) }

            return readingRoom
        }

    }
}

@Embeddable
data class ReadingRoomDetail(

        var category: String, // 분류
        var webSiteUrl: String?,
        var address: Address
)