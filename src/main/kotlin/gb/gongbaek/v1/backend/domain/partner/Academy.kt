package gb.gongbaek.v1.backend.domain.partner

import gb.gongbaek.v1.backend.domain.Like
import gb.gongbaek.v1.backend.domain.hashtag.PartnerHashtag
import gb.gongbaek.v1.backend.dto.HomeCardDto
import gb.gongbaek.v1.backend.dto.PartnerType
import gb.gongbaek.v1.backend.dto.partner.academy.AcademyDetailDto
import gb.gongbaek.v1.backend.dto.partner.academy.AcademyDto
import javax.persistence.DiscriminatorValue
import javax.persistence.Embeddable
import javax.persistence.Embedded
import javax.persistence.Entity

@Entity
@DiscriminatorValue("A")
// 학원, 교습소
data class Academy (
        override val id: Long? = null,
        override val name: String, // 시설명
        override var isConfirmed: Boolean,
        override var likes: MutableList<Like>,

        override var branchName: String? = null, // 지점명
        override var adminContact: String, // 연락처, 괸리자 컨택용
        override var representativeContact: String, // 대표 번호, 유저 공개용

        override var businessRegistration: String? = null, // 사업자 등록증

        override var operationalCertification: String?, // 운영 인증, 유저 공개용
        override var representativeImage: String, // 홈카드 대표 사진, 유저 공개용

        override var partnerHashtags: MutableList<PartnerHashtag> = mutableListOf(), // 해시태그

        var academyType: AcademyType, // 학원인지 공부방인지

        @Embedded
        var detail: AcademyDetail // 학원 디테일

): Partner(id, PartnerType.ACADEMY, name, isConfirmed, likes, branchName, adminContact, representativeContact, businessRegistration, operationalCertification, representativeImage, partnerHashtags) {


    // 홈 카드 변환 메서드
    override fun toHomeCard(isLiked: Boolean) = HomeCardDto.Card(
            partnerType = type,
            partnerId = id!!,
            imageUrl = representativeImage,
            name = name + branchName,
            location = detail.address.roadAddress,
            isLiked = isLiked,
            totalLikes = getTotalLikes()
    )

    // 기본 Dto 변환 메서드
    override fun toDto() = AcademyDto.AcademyRes(
            id = id,
            type = type,
            name = name,
            address = detail.address,
            isConfirmed = isConfirmed,
            totalLikes = getTotalLikes()
    )

    // 학원 상세 Dto 변환 메서드
    fun toDetailDto() = AcademyDetailDto.AcademyDetailRes(
            id = id!!,
            name = name,
            grade = detail.grade,
            subject = detail.subject,
            subjectDetail = detail.subjectDetail,
            category = detail.category,
            webSiteUrl = detail.webSiteUrl,
            address = detail.address,
            hashtags = partnerHashtags.map { ph -> ph.hashtag.name }
    )

    // 파트너 해시태그 추가 메서드
    fun addPartnerHashtag(partnerHashtag: PartnerHashtag){
        partnerHashtags.add(partnerHashtag)
        partnerHashtag.partner = this
    }

    companion object{

        // 학원 생성 메서드
        fun createAcademy(req: AcademyDto.CreateAcademyReq, partnerHashtags: MutableList<PartnerHashtag>): Academy{

            val academy = Academy(
                    name = req.name,
                    isConfirmed = false,
                    likes = mutableListOf(),
                    branchName = req.branchName,
                    adminContact = req.adminContact,
                    representativeContact = req.representativeContact,
                    businessRegistration = req.businessRegistration,
                    operationalCertification = req.operationalCertification,
                    representativeImage = req.representativeImage,

                    academyType = AcademyType.ACADEMY,
                    detail = AcademyDetail(
                            grade = req.grade,
                            subject = req.subject,
                            subjectDetail = req.subjectDetail,
                            category = req.category,
                            webSiteUrl = req.webSiteUrl,
                            address = req.address
                    )
            )

            partnerHashtags.map { partnerHashtag -> academy.addPartnerHashtag(partnerHashtag) }

            return academy
        }
    }


}

enum class AcademyType{

    ACADEMY,
    STUDY_ROOM
}

@Embeddable
data class Address(
     var roadAddress: String, // 도로명 주소
     var detailAddress: String // 상세 주소
)

@Embeddable
// 학원 상세 클래스
data class AcademyDetail(

        var grade: String,
        var subject: String,
        var subjectDetail: String, // 세부과목
        var category: String, // 분류
        var webSiteUrl: String?,
        var address: Address
)