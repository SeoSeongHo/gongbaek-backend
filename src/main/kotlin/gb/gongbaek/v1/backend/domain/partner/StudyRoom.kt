package gb.gongbaek.v1.backend.domain.partner

import gb.gongbaek.v1.backend.domain.EntityAuditing
import javax.persistence.*

data class StudyRoom(

        var validationImageUrl: String, // url
        var contact: String,
        var depositor: String
): Partner()