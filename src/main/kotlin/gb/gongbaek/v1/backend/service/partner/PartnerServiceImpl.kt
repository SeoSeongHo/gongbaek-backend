package gb.gongbaek.v1.backend.service.partner

import gb.gongbaek.v1.backend.domain.partner.Partner
import gb.gongbaek.v1.backend.dto.PartnerType
import gb.gongbaek.v1.backend.exception.PartnerNotFoundException
import gb.gongbaek.v1.backend.repository.PartnerRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class PartnerServiceImpl(
        @Autowired private val partnerRepository: PartnerRepository
): PartnerService {

    override fun getPartnerById(partnerId: Long): Partner =
        partnerRepository.findById(partnerId).orElseThrow { throw PartnerNotFoundException("can not find partner. id: $partnerId") }

    override fun getPartnersByType(partnerType: PartnerType): List<Partner> =
        partnerRepository.findByType(partnerType)

    override fun createPartner(partner: Partner): Partner {

        // TODO fix which case to handle... ex) duplicate partner name
        return partnerRepository.save(partner)
    }

    override fun confirmPartner(partnerId: Long){
        val partner = getPartnerById(partnerId)
        partner.isConfirmed = true
    }
}