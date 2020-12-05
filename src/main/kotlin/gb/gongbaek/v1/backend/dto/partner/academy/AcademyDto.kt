package gb.gongbaek.v1.backend.dto.partner.academy

import gb.gongbaek.v1.backend.domain.partner.Academy
import gb.gongbaek.v1.backend.domain.partner.AcademyDetail
import gb.gongbaek.v1.backend.domain.partner.AcademyType
import gb.gongbaek.v1.backend.domain.partner.Address
import gb.gongbaek.v1.backend.dto.PartnerType
import gb.gongbaek.v1.backend.dto.partner.PartnerDto

class AcademyDto {

    data class CreateAcademyReq(

            val name: String,

            val branchName: String?,
            val adminContact: String,
            val representativeContact: String,

            val businessRegistration: String,

            val operationalCertification: List<String>?,
            val representativeImage: String,

            val academyType: AcademyType,

            val grade: String,
            val subject: String,
            val subjectDetail: String,
            val category: String,
            val webSiteUrl: String?,
            val address: Address

            //val hashTags: MutableList<HashTag>
    ){
        fun toEntity() = Academy(
                name = name,
                isConfirmed = false,
                likes = mutableListOf(),
                branchName = branchName,
                adminContact = adminContact,
                representativeContact = representativeContact,
                businessRegistration = businessRegistration,
                //hashTags = hashTags,
                operationalCertification = operationalCertification,
                representativeImage = representativeImage,
                academyType = academyType,

                detail = AcademyDetail(
                        grade = grade,
                        subject = subject,
                        subjectDetail = subjectDetail,
                        category = category,
                        webSiteUrl = webSiteUrl,
                        address = address
                )
        )
    }

    data class AcademyRes(
            override val id: Long? = null,
            override val type: PartnerType,
            override val name: String,
            override val address: Address,
            override val isConfirmed: Boolean,
            override val totalLikes: Int
    ): PartnerDto(id, type, name, address, isConfirmed, totalLikes){

    }

}