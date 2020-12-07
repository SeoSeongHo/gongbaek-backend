package gb.gongbaek.v1.backend.dto.partner.readingRoom

import gb.gongbaek.v1.backend.domain.hashtag.Hashtag
import gb.gongbaek.v1.backend.domain.partner.AcademyType
import gb.gongbaek.v1.backend.domain.partner.Address
import gb.gongbaek.v1.backend.domain.partner.ReadingRoom
import gb.gongbaek.v1.backend.domain.partner.ReadingRoomDetail
import gb.gongbaek.v1.backend.dto.PartnerType
import gb.gongbaek.v1.backend.dto.partner.PartnerDto

class ReadingRoomDto {

    data class CreateReadingRoomReq(
            val name: String,

            val branchName: String?,
            val adminContact: String,
            val representativeContact: String,

            val businessRegistration: String,

            val operationalCertification: String?,
            val representativeImage: String,

            val category: String,
            val webSiteUrl: String?,
            val address: Address,

            val hashtagIds: List<Long>
    ){
        fun toEntity() = ReadingRoom(
                name = name,
                isConfirmed = false,
                likes = mutableListOf(),
                branchName = branchName,
                adminContact = adminContact,
                representativeContact = representativeContact,
                businessRegistration = businessRegistration,
                operationalCertification = operationalCertification,
                representativeImage = representativeImage,

                detail = ReadingRoomDetail(
                        category = category,
                        webSiteUrl = webSiteUrl,
                        address = address
                )
                
        )
    }

    data class ReadingRoomRes(
            override val id: Long? = null,
            override val type: PartnerType,
            override val name: String,
            override val address: Address,
            override val isConfirmed: Boolean,
            override val totalLikes: Int
    ): PartnerDto(id, type, name, address, isConfirmed, totalLikes)

}