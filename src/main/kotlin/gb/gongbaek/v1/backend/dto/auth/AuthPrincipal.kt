package gb.gongbaek.v1.backend.dto.auth

import gb.gongbaek.v1.backend.domain.UserType
import io.jsonwebtoken.Claims
import javax.persistence.EnumType
import javax.persistence.Enumerated

data class AuthPrincipal (
     var userId: Long,
     @Enumerated(EnumType.STRING)
     var role: String
){
    companion object{
        fun of(claims: Claims) = AuthPrincipal(

                userId = claims.subject.toLong(),
                role = claims["role"] as String
        )
    }
}