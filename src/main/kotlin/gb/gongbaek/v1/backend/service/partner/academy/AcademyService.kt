package gb.gongbaek.v1.backend.service.partner.academy

import gb.gongbaek.v1.backend.domain.partner.Academy
import gb.gongbaek.v1.backend.domain.partner.Partner
import gb.gongbaek.v1.backend.dto.partner.academy.AcademyDetailDto
import gb.gongbaek.v1.backend.dto.partner.academy.AcademyDto

interface AcademyService {
    fun createAcademy(createAcademyReq: AcademyDto.CreateAcademyReq): Partner
    fun confirmAcademy(academyId: Long)
    fun getAcademy(academyId: Long): Academy
    fun getAcademies(): List<Partner>
    fun getAcademyDetail(academyId: Long): AcademyDetailDto.AcademyDetailRes
}