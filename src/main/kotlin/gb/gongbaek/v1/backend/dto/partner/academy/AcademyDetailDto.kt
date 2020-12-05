package gb.gongbaek.v1.backend.dto.partner.academy

import gb.gongbaek.v1.backend.domain.partner.Address

class AcademyDetailDto {

    // 학원 상세 Dto
    data class AcademyDetailRes(

            var id: Long,
            var name: String,
            var grade: String,
            var subject: String, // 과목
            var subjectDetail: String, // 세부과목
            var category: String, // 분류
            var webSiteUrl: String?,
            var address: Address

    )
}