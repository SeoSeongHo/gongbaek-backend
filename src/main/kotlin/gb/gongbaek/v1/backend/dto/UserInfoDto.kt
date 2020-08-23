package gb.gongbaek.v1.backend.dto

import gb.gongbaek.v1.backend.domain.UserType
import javax.persistence.EnumType
import javax.persistence.Enumerated

class UserInfoDto {

    data class UserInfoReq(

            @Enumerated(EnumType.STRING)
            val type: UserType
    )

    data class UserInfoRes(

            val email: String
    ){

    }
}
