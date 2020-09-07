package gb.gongbaek.v1.backend.dto

import gb.gongbaek.v1.backend.domain.User
import gb.gongbaek.v1.backend.domain.UserInfo

class UserDto {

    data class UserRes(
            var id: Long,
            var email: String,

            var userInfo: UserInfo
    )
}