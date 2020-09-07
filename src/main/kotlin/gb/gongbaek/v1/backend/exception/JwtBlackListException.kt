package gb.gongbaek.v1.backend.exception

import org.springframework.security.core.AuthenticationException

class JwtBlackListException(msg: String): AuthenticationException(msg) {
}