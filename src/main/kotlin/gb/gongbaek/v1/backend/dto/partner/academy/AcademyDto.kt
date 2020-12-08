package gb.gongbaek.v1.backend.dto.partner.academy

import gb.gongbaek.v1.backend.domain.OperationalCertification
import gb.gongbaek.v1.backend.domain.hashtag.Hashtag
import gb.gongbaek.v1.backend.domain.partner.Academy
import gb.gongbaek.v1.backend.domain.partner.AcademyDetail
import gb.gongbaek.v1.backend.domain.partner.AcademyType
import gb.gongbaek.v1.backend.domain.partner.Address
import gb.gongbaek.v1.backend.dto.PartnerType
import gb.gongbaek.v1.backend.dto.partner.PartnerDto
import org.springframework.web.multipart.MultipartFile

class AcademyDto {

    data class CreateAcademyReq(

            val name: String,

            val branchName: String?,
            val adminContact: String,
            val representativeContact: String,

            val businessRegistration: MultipartFile?,

            val operationalCertification: List<MultipartFile>?,
            val representativeImage: MultipartFile?,

            val academyType: AcademyType,

            val grade: String,
            val subject: String,
            val subjectDetail: String,
            val category: String,
            val webSiteUrl: String?,
            val address: Address,

            val hashtagIds: List<Long>
    )

    data class CreateAcademyImageReq(
            val businessRegistration: String?,
            val operationalCertification: MutableList<OperationalCertification>?,
            val representativeImage: String?
    )

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