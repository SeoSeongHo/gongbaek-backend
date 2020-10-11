package gb.gongbaek.v1.backend.dto.partner

import gb.gongbaek.v1.backend.domain.partner.Address
import gb.gongbaek.v1.backend.dto.PartnerType

class ReadingRoomDto {

    data class CreateReadingRoomReq(
            val name: String
    )

    data class ReadingRoomRes(
            override val id: Long? = null,
            override val type: PartnerType,
            override val name: String,
            override val address: Address,
            override val isConfirmed: Boolean,
            override val totalLikes: Int
    ): PartnerDto(id, type, name, address, isConfirmed, totalLikes)

}