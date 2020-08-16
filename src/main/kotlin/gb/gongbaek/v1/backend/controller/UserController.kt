package gb.gongbaek.v1.backend.controller

import gb.gongbaek.v1.backend.dto.SignInDto
import gb.gongbaek.v1.backend.dto.SignUpDto
import gb.gongbaek.v1.backend.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/oauth")
class UserController(
        @Autowired private val userService: UserService
) {

    @PostMapping("/signUp")
    fun signUp(@RequestBody signUpReq: SignUpDto.SignUpReq): ResponseEntity<Any>{

        userService.signUp(signUpReq)

        return ResponseEntity
                .ok()
                .build()
    }

    @PostMapping("/signIn")
    fun signIn(@RequestBody signInReq: SignInDto.SignInReq): ResponseEntity<SignInDto.SignInRes>{

        return ResponseEntity
                .ok()
                .header(HttpHeaders.CONTENT_TYPE, "application/json")
                .body(userService.signIn(signInReq))
    }
}