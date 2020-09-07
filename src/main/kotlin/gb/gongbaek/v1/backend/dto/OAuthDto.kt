package gb.gongbaek.v1.backend.dto

class OAuthDto {

    data class OAuthReq(
            val accessToken: String,
            val refreshToken: String
    )


    data class OAuthRes(
            val accessToken: String,
            val refreshToken: String
    )



}