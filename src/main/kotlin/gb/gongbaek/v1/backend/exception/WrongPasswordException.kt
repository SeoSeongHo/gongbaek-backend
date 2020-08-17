package gb.gongbaek.v1.backend.exception

import java.lang.RuntimeException

class WrongPasswordException(msg: String): RuntimeException(msg)