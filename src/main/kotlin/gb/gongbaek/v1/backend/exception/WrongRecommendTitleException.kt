package gb.gongbaek.v1.backend.exception

import java.lang.RuntimeException

class WrongRecommendCategoryException(msg: String): RuntimeException(msg)