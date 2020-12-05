package gb.gongbaek.v1.backend.exception

import java.lang.RuntimeException

class WrongRecommendTitleException(msg: String): RuntimeException(msg)