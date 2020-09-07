package gb.gongbaek.v1.backend.dto

import gb.gongbaek.v1.backend.domain.UserType
import javax.persistence.EnumType
import javax.persistence.Enumerated

class UserInfoDto {

    data class UserInfoReq(

            @Enumerated(EnumType.STRING)
            var type: UserType,

            var nickname: String,
            var contact: String,

            // 이용약관 동의
            var isServiceTermsAgreed: Boolean,
            // SNS 등 알림 동의
            var isNotificationAgreed: Boolean
    )
}
