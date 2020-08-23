package gb.gongbaek.v1.backend.dto

import javax.persistence.EnumType
import javax.persistence.Enumerated

class ValidateFormDto {

    data class ValidateFormReq(

            @Enumerated(EnumType.STRING)
            val type: ValidateFormType,
            val value: String
    )

    data class ValidateFormRes(
            val isValid: Boolean,
            val message: String
    )
}

enum class ValidateFormType {
    NICKNAME, EMAIL
}
