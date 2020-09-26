package gb.gongbaek.v1.backend.dto.partner

import gb.gongbaek.v1.backend.domain.partner.Address
import gb.gongbaek.v1.backend.dto.PartnerType

abstract class PartnerDto(
        open val id: Long? = null,
        open val type: PartnerType,
        open val name: String,
        open val address: Address,
        open val isConfirmed: Boolean,
        open val totalLikes: Int
) {


}