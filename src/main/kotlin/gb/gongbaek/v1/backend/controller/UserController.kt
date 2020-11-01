package gb.gongbaek.v1.backend.controller

import gb.gongbaek.v1.backend.domain.UserRole
import gb.gongbaek.v1.backend.dto.*
import gb.gongbaek.v1.backend.dto.auth.AuthPrincipal
import gb.gongbaek.v1.backend.service.user.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

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
                !userService.isDuplicateNickname(validateFormReq.value)
            }
            ValidateFormType.EMAIL -> {
                !userService.isDuplicateEmail(validateFormReq.value)
            }
        }

        return ResponseEntity
                .ok()
                .header(HttpHeaders.CONTENT_TYPE, "application/json")
                .body(ValidateFormDto.ValidateFormRes(isValid = isValid, message = ""))
    }

    @PostMapping("/login")
    fun login(@RequestBody signInReq: SignInDto.SignInReq): ResponseEntity<SignInDto.SignInRes>{

        return ResponseEntity
                .ok()
                .header(HttpHeaders.CONTENT_TYPE, "application/json")
                .body(userService.signIn(signInReq))
    }
    
    @PostMapping("/logout")
    fun logout(@RequestBody oAuthReq: OAuthDto.OAuthReq){

        userService.logout(oAuthReq)
    }


    @PostMapping("/update/info")
    fun update(@AuthenticationPrincipal authPrincipal: AuthPrincipal, @RequestBody userInfoReq: UserInfoDto.UserInfoReq): ResponseEntity<UserDto.UserRes>{

        val updatedUser = userService.updateUserInfo(userInfoReq, authPrincipal.userId)
        return ResponseEntity
                .ok()
                .body(updatedUser)
    }

    @PostMapping("/update/role")
    fun updateUserRole(@AuthenticationPrincipal authPrincipal: AuthPrincipal, @RequestBody UserRoleReq: UserInfoDto.UserRoleReq): ResponseEntity<SignInDto.SignInRes>{
        val updatedUser = userService.updateUserRole(UserRoleReq, authPrincipal.userId)
        return ResponseEntity
                .ok()
                .body(updatedUser)
    }

    @GetMapping
    fun getUser(@AuthenticationPrincipal authPrincipal: AuthPrincipal): ResponseEntity<UserDto.UserRes>{

        val user = userService.getUserById(authPrincipal.userId)

        return ResponseEntity
                .ok()
                .body(user.toDto())
    }
}