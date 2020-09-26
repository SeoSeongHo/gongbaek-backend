package gb.gongbaek.v1.backend.service.partner.academy

import gb.gongbaek.v1.backend.domain.partner.Partner
import gb.gongbaek.v1.backend.dto.partner.AcademyDto

interface AcademyService {
    fun createAcademy(createAcademyReq: AcademyDto.CreateAcademyReq): Partner
    fun confirmAcademy(academyId: Long)
    fun getAcademies(): List<Partner>
    fun getAcademy(academyId: Long): Partner
}