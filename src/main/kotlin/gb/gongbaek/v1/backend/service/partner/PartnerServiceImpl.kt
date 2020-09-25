package gb.gongbaek.v1.backend.service.partner

import gb.gongbaek.v1.backend.domain.partner.Partner
import gb.gongbaek.v1.backend.dto.HomeCardDto
import gb.gongbaek.v1.backend.dto.PartnerType
import gb.gongbaek.v1.backend.exception.PartnerNotFoundException
import gb.gongbaek.v1.backend.repository.PartnerRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class PartnerServiceImpl(
        @Autowired private val partnerRepository: PartnerRepository,
): PartnerService {

    override fun getPartnerById(partnerId: Long): Partner =
        partnerRepository.findById(partnerId).orElseThrow { throw PartnerNotFoundException("") }

    override fun getPartnerByType(partnerType: PartnerType): List<Partner> =
        partnerRepository.findByType(partnerType)
}