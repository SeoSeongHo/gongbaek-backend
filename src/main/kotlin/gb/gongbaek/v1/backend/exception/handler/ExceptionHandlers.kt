package gb.gongbaek.v1.backend.exception.handler

import gb.gongbaek.v1.backend.exception.DuplicateEmailException
import gb.gongbaek.v1.backend.exception.WrongPasswordException
import gb.gongbaek.v1.backend.exception.handler.ErrorResponseEntity.Companion.badRequest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.MessageSource
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import java.util.*

@RestControllerAdvice
class ExceptionHandlers @Autowired constructor(var messageSource: MessageSource) {

    @ExceptionHandler(DuplicateEmailException::class)
    fun duplicateEmailException(exception: DuplicateEmailException, locale: Locale) =
            badRequest(messageSource.getMessage(exception, locale))

    @ExceptionHandler(WrongPasswordException::class)
    fun wrongPasswordException(exception: WrongPasswordException, locale: Locale) =
            badRequest(messageSource.getMessage(exception, locale))
}