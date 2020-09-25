package gb.gongbaek.v1.backend.service.partner

import gb.gongbaek.v1.backend.domain.partner.Partner
import gb.gongbaek.v1.backend.dto.PartnerType

interface PartnerService {

    fun getPartnerById(partnerId: Long): Partner
    fun getPartnerByType(partnerType: PartnerType): List<Partner>
}