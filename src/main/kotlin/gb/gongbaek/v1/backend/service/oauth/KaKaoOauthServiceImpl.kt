package gb.gongbaek.v1.backend.service.oauth

import gb.gongbaek.v1.backend.service.oauth.OauthService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.getForObject
import org.springframework.web.util.UriComponentsBuilder
import java.nio.charset.Charset

@Service
class KaKaoOauthServiceImpl(
        @Autowired private val restTemplate: RestTemplate,

        @Value("\${oauth:kakao:clientId}")
        private val clientId: String,

        @Value("\${oauth:kakao:redirectUri}")
        private val redirectUri: String,

        @Value("\${oauth:kakao:clientSecret}")
        private val clientSecret: String

): OauthService {

    private val KAKAO_TOKEN_ENDPOINT = "https://kauth.kakao.com/oauth/token"
    private val KAKAO_USER_INFO_ENDPOINT = "https://kapi.kakao.com/v2/user/me"

    data class KaKaoGetOauthTokenRes(
            val token_type: String,
            val access_token: String,
            val expires_in: Int,
            val refresh_token: String,
            val refresh_token_expires_in: Int,
            val scope: String
    )

    data class KaKaoTokenRes(
            val access_token: String,
            val refresh_token: String
    )

//    data class KaKaoGetUserInfoRes(
//
//    )

    fun getOauthToken(code: String){

        val headers = HttpHeaders()
        headers.contentType = MediaType("application", "json", Charset.forName("UTF-8"))

        val builder = UriComponentsBuilder.fromHttpUrl(KAKAO_TOKEN_ENDPOINT)
                .queryParam("grant_type", "authorization_code")
                .queryParam("client_id", clientId)
                .queryParam("redirect_uri", redirectUri)
                .queryParam("code", code)
                .queryParam("client_secret", clientSecret)
                .build(false)

        val result = restTemplate.getForObject("https://kauth.kakao.com/oauth/token", KaKaoGetOauthTokenRes::class.java)

    }

    fun getUserInfo(accessToken: String){

        //restTemplate.getForObject("", )

    }
}