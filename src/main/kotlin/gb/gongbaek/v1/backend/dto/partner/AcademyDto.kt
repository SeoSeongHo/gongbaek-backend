package gb.gongbaek.v1.backend.dto.partner

import gb.gongbaek.v1.backend.domain.Like
import gb.gongbaek.v1.backend.domain.partner.Academy
import gb.gongbaek.v1.backend.domain.partner.Address
import gb.gongbaek.v1.backend.dto.PartnerType

class AcademyDto {

    data class CreateAcademyReq(

            val name: String,
            val address: Address,
            val branchName: String?,
            val contact: String,
            val imageUrl: String,
            // TODO How to get this...
            val businessRegistration: String
    ){
        fun toEntity() = Academy(
                name = name,
                address = address,
                isConfirmed = false,
                likes = mutableListOf(),
                branchName = branchName,
                contact = contact,
                imageUrl = imageUrl,
                businessRegistration = businessRegistration
        )
    }

    data class UpdateAcademyReq(
            val name: String
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