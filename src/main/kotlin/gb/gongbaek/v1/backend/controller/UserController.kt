package gb.gongbaek.v1.backend.controller

import gb.gongbaek.v1.backend.dto.*
import gb.gongbaek.v1.backend.dto.auth.AuthPrincipal
import gb.gongbaek.v1.backend.service.user.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid
import javax.xml.ws.Response

@RestController
@RequestMapping("/api/v1/users")
class UserController(
        @Autowired private val userService: UserService
) {
    @PostMapping("/register")
    fun signUp(@RequestBody @Valid signUpReq: SignUpDto.SignUpReq): ResponseEntity<SignUpDto.SignUpRes> {

        return ResponseEntity
                .ok()
                .header(HttpHeaders.CONTENT_TYPE, "application/json")
                .body(userService.signUp(signUpReq))
    }

    @PostMapping("/register/validate")
    fun validateForm(@RequestBody @Valid validateFormReq: ValidateFormDto.ValidateFormReq): ResponseEntity<ValidateFormDto.ValidateFormRes>{

        val isValid = when(validateFormReq.type){
            ValidateFormType.NICKNAME -> {
                userService.checkDuplicateNickname(validateFormReq.value)
            }
            ValidateFormType.EMAIL -> {
                userService.checkDuplicateEmail(validateFormReq.value)
            }
        }

        return ResponseEntity
                .ok()
                .header(HttpHeaders.CONTENT_TYPE, "application/json")
                .body(ValidateFormDto.ValidateFormRes(isValid = isValid, message = ""))
    }

    @PostMapping("/login")
    fun signIn(@RequestBody signInReq: SignInDto.SignInReq): ResponseEntity<SignInDto.SignInRes> {

        return ResponseEntity
                .ok()
                .header(HttpHeaders.CONTENT_TYPE, "application/json")
                .body(userService.signIn(signInReq))
    }

    @PostMapping("/update")
    fun update(@RequestBody userInfo: UserInfoDto.UserInfoReq, @AuthenticationPrincipal authPrincipal: AuthPrincipal): ResponseEntity<UserInfoDto.UserInfoRes>{

        return ResponseEntity
                .ok()
                .body(UserInfoDto.UserInfoRes(email = ""))
    }
}