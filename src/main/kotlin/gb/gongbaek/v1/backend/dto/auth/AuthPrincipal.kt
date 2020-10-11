package gb.gongbaek.v1.backend.dto.auth

import gb.gongbaek.v1.backend.domain.UserRole
import io.jsonwebtoken.Claims
import javax.persistence.EnumType
import javax.persistence.Enumerated

data class AuthPrincipal (
     var userId: Long,
     var userRole: String
){
    companion object{
        fun of(claims: Claims) = AuthPrincipal(

                userId = claims.subject.toLong(),
                userRole = claims["UserRole"] as String
        )
    }
}