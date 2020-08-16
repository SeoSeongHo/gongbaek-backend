package gb.gongbaek.v1.backend.config.jwt

import gb.gongbaek.v1.backend.dto.auth.AuthPrincipal
import gb.gongbaek.v1.backend.exception.JwtValidationException
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component
import java.util.*
import javax.servlet.http.HttpServletRequest

@Component
class JwtTokenProvider(
        @Value("\${jwt.secretKey}") private val secretKey: String,
        @Value("\${jwt.expiredTime}") private val expiredTime: Long
) {

    fun createToken(userId: Long): String{
        val claims: Claims = Jwts.claims().setSubject(userId.toString())
        val validity = Date(Date().time + expiredTime)

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(Date())
                .setExpiration(validity)
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .compact()
    }

    fun getAuthentication(token: String): Authentication{
        val principal = AuthPrincipal(getClaims(token).subject.toLong())
        return UsernamePasswordAuthenticationToken(principal, "", mutableListOf())
    }

    fun resolveToken(req: HttpServletRequest): String? {
        val token: String? = req.getHeader("Authorization")
        if(token == null || !token.startsWith("Bearer ")) return null

        return token.substring(7, token.length)
    }

    fun validateToken(token: String): Boolean{
        try{
            if(getClaims(token).expiration.before(Date())) return false
            return true
        }
        catch(e: Exception) {
            throw JwtValidationException("failed to validate jwt.")
        }
    }

    private fun getClaims(token: String): Claims {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).body
    }
}