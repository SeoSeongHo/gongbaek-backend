package gb.gongbaek.v1.backend.dto.partner

import gb.gongbaek.v1.backend.domain.partner.Address
import gb.gongbaek.v1.backend.dto.PartnerType

class StoreDto {

    data class StoreRes(

            override val id: Long? = null,
            override val type: PartnerType,
            override val name: String,
            override val address: Address,
            override val isConfirmed: Boolean,
            override val totalLikes: Int
    ): PartnerDto(id, type, name, address, isConfirmed, totalLikes){

    }
}