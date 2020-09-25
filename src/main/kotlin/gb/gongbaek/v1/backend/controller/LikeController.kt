package gb.gongbaek.v1.backend.controller

import gb.gongbaek.v1.backend.dto.LikeDto
import gb.gongbaek.v1.backend.dto.auth.AuthPrincipal
import gb.gongbaek.v1.backend.service.like.LikeService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/likes")
class LikeController(
        @Autowired private val likeService: LikeService
) {


    @PostMapping
    fun updateLikes(@AuthenticationPrincipal authPrincipal: AuthPrincipal, @RequestBody likeReq: LikeDto.LikeReq){


    }
}