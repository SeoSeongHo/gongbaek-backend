package gb.gongbaek.v1.backend.exception.handler

import gb.gongbaek.v1.backend.exception.*
import gb.gongbaek.v1.backend.exception.handler.ErrorResponseEntity.Companion.badRequest
import gb.gongbaek.v1.backend.exception.handler.ErrorResponseEntity.Companion.notFound
import gb.gongbaek.v1.backend.exception.handler.ErrorResponseEntity.Companion.serverError
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.MessageSource
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.security.core.AuthenticationException
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
    fun handleDuplicateEmailException(exception: DuplicateEmailException) =
            badRequest(exception.message!!)

    @ExceptionHandler(DuplicateNicknameException::class)
    fun handleDuplicateNicknameException(exception: DuplicateNicknameException) =
            badRequest(exception.message!!)

    @ExceptionHandler(UserNotFoundException::class)
    fun handleUserNotFoundException(exception: UserNotFoundException) =
            notFound(exception.message!!)

    @ExceptionHandler(WrongPasswordException::class)
    fun handleWrongPasswordException(exception: WrongPasswordException) =
            badRequest(exception.message!!)

    @ExceptionHandler(ExpiredRefreshTokenException::class)
    fun handleExpiredRefreshTokenException(exception: ExpiredRefreshTokenException) =
            badRequest(exception.message!!)

    @ExceptionHandler(RefreshTokenNotFoundException::class)
    fun handleRefreshTokenNotFoundException(exception: RefreshTokenNotFoundException) =
            badRequest(exception.message!!)

    @ExceptionHandler(DataIntegrityViolationException::class)
    fun handleDataIntegrityViolationException(exception: DataIntegrityViolationException) =
            badRequest("database exception: duplicate field. message: ${exception.message!!}")
}