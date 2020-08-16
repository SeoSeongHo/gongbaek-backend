package gb.gongbaek.v1.backend.exception

import org.springframework.context.MessageSourceResolvable

abstract class BadRequestException(msg: String): RuntimeException(msg), MessageSourceResolvable


class DuplicateEmailException(msg: String): BadRequestException(msg){

    override fun getCodes(): Array<String>? =
            arrayOf("duplicateEmailException")
}

class WrongPasswordException(msg: String): BadRequestException(msg){

    override fun getCodes(): Array<String>? =
            arrayOf("wrongPasswordException")
}
