package gb.gongbaek.v1.backend.controller

import gb.gongbaek.v1.backend.config.jwt.JwtTokenProvider
import gb.gongbaek.v1.backend.dto.*
import gb.gongbaek.v1.backend.dto.auth.AuthPrincipal
import gb.gongbaek.v1.backend.exception.ExpiredRefreshTokenException
import gb.gongbaek.v1.backend.service.oauth.KaKaoOauthServiceImpl
import gb.gongbaek.v1.backend.service.user.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/oauth")
class OAuthController(
        @Autowired private val kaKaoOauthServiceImpl: KaKaoOauthServiceImpl,
        @Autowired private val userService: UserService
) {

    @PostMapping("/refresh")
    fun refreshToken(@RequestBody oAuthReq: OAuthDto.OAuthReq): ResponseEntity<OAuthDto.OAuthRes> {

        val tokens = userService.refresh(oAuthReq)

        return ResponseEntity
                .ok()
                .body(OAuthDto.OAuthRes(accessToken = tokens.accessToken, refreshToken = tokens.refreshToken))
    }


//
//    fun loginKaKao(@RequestBody kaKaoOauthReq: OAuthDto.KakaoOAuthReq){
//        val token = kaKaoOauthServiceImpl.getOauthToken(kaKaoOauthReq.code)
//    }
}