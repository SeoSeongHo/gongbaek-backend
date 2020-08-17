package gb.gongbaek.v1.backend.exception.handler

import gb.gongbaek.v1.backend.exception.DuplicateEmailException
import gb.gongbaek.v1.backend.exception.EmailNotFoundException
import gb.gongbaek.v1.backend.exception.JwtValidationException
import gb.gongbaek.v1.backend.exception.WrongPasswordException
import gb.gongbaek.v1.backend.exception.handler.ErrorResponseEntity.Companion.badRequest
import gb.gongbaek.v1.backend.exception.handler.ErrorResponseEntity.Companion.notFound
import gb.gongbaek.v1.backend.exception.handler.ErrorResponseEntity.Companion.serverError
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.MessageSource
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.servlet.config.annotation.EnableWebMvc
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import java.util.*

@RestControllerAdvice
@EnableWebMvc
class ExceptionHandler {

    @ExceptionHandler(DuplicateEmailException::class)
    fun duplicateEmailException(exception: DuplicateEmailException) =
            badRequest(exception.message!!)

    @ExceptionHandler(EmailNotFoundException::class)
    fun emailNotFoundException(exception: EmailNotFoundException) =
            notFound(exception.message!!)

    @ExceptionHandler(WrongPasswordException::class)
    fun wrongPasswordException(exception: WrongPasswordException) =
            badRequest(exception.message!!)

    @ExceptionHandler(JwtValidationException::class)
    fun jwtValidationException(exception: JwtValidationException) =
            badRequest(exception.message!!)
}