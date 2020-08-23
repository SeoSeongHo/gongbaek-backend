package gb.gongbaek.v1.backend.controller

import gb.gongbaek.v1.backend.dto.*
import gb.gongbaek.v1.backend.service.oauth.KaKaoOauthServiceImpl
import gb.gongbaek.v1.backend.service.user.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("/api/v1/oauth")
class OAuthController(
        @Autowired private val kaKaoOauthServiceImpl: KaKaoOauthServiceImpl
) {

    fun loginKaKao(@RequestBody kaKaoOauthReq: OauthDto.KakaoOauthReq){
        val token = kaKaoOauthServiceImpl.getOauthToken(kaKaoOauthReq.code)
        val test = 2
    }
}