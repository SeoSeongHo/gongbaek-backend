package gb.gongbaek.v1.backend.exception

import org.springframework.security.core.AuthenticationException
import java.lang.RuntimeException

class JwtValidationException(msg: String) : AuthenticationException(msg)
