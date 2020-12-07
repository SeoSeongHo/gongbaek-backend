package gb.gongbaek.v1.backend.dto.partner.academy

import gb.gongbaek.v1.backend.domain.partner.Address

class AcademyDetailDto {

    // 학원 상세 Dto
    data class AcademyDetailRes(

            val id: Long,
            val name: String,
            val grade: String,
            val subject: String, // 과목
            val subjectDetail: String, // 세부과목
            val category: String, // 분류
            val webSiteUrl: String?,
            val address: Address,
            val hashtags: List<String>
    )
}