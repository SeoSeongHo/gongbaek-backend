package gb.gongbaek.v1.backend.service.partner.academy

import gb.gongbaek.v1.backend.dto.PartnerType
import gb.gongbaek.v1.backend.dto.partner.AcademyDto
import gb.gongbaek.v1.backend.service.partner.PartnerService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class AcademyServiceImpl(
        @Autowired private val partnerService: PartnerService
): AcademyService {

    override fun createAcademy(createAcademyReq: AcademyDto.CreateAcademyReq) =
            partnerService.createPartner(createAcademyReq.toEntity())

    override fun confirmAcademy(academyId: Long) =
            partnerService.confirmPartner(academyId)

    override fun getAcademies() =
            partnerService.getPartnersByType(PartnerType.ACADEMY)

    override fun getAcademy(academyId: Long) =
            partnerService.getPartnerById(academyId)

    fun updateAcademy(academyId: Long, updateAcademyReq: AcademyDto.UpdateAcademyReq){

    }
}