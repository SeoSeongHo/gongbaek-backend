package gb.gongbaek.v1.backend.exception

import java.lang.RuntimeException

class JwtValidationException(msg: String) : RuntimeException(msg)
