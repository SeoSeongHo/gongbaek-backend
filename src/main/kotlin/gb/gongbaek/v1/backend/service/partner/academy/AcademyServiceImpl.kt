package gb.gongbaek.v1.backend.service.partner.academy

import gb.gongbaek.v1.backend.domain.partner.Academy
import gb.gongbaek.v1.backend.domain.partner.Partner
import gb.gongbaek.v1.backend.dto.PartnerType
import gb.gongbaek.v1.backend.dto.partner.academy.AcademyDetailDto
import gb.gongbaek.v1.backend.dto.partner.academy.AcademyDto
import gb.gongbaek.v1.backend.exception.AcademyNotFoundException
import gb.gongbaek.v1.backend.repository.AcademyRepository
import gb.gongbaek.v1.backend.service.partner.PartnerService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class AcademyServiceImpl(
        @Autowired private val partnerService: PartnerService,
        @Autowired private val academyRepository: AcademyRepository
): AcademyService {

    override fun getAcademy(academyId: Long): Academy{
        return academyRepository.findById(academyId).orElseThrow { throw AcademyNotFoundException("can not find academy. id: $academyId") }
    }

    override fun getAcademies(): List<Partner> {
        return partnerService.getPartnersByType(PartnerType.ACADEMY)
    }


    override fun getAcademyDetail(academyId: Long): AcademyDetailDto.AcademyDetailRes{
        return getAcademy(academyId).toDetailDto()
    }

    override fun createAcademy(createAcademyReq: AcademyDto.CreateAcademyReq) =
            partnerService.createPartner(createAcademyReq.toEntity())

    override fun confirmAcademy(academyId: Long){
        partnerService.confirmPartner(academyId)
    }
}