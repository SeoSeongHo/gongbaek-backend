package gb.gongbaek.v1.backend.service.like

import gb.gongbaek.v1.backend.domain.Like
import gb.gongbaek.v1.backend.dto.LikeDto
import gb.gongbaek.v1.backend.exception.PartnerNotFoundException
import gb.gongbaek.v1.backend.exception.UserNotFoundException
import gb.gongbaek.v1.backend.repository.LikeRepository
import gb.gongbaek.v1.backend.repository.PartnerRepository
import gb.gongbaek.v1.backend.repository.UserRepository
import gb.gongbaek.v1.backend.service.partner.PartnerService
import gb.gongbaek.v1.backend.service.user.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class LikeServiceImpl(
        @Autowired private val likeRepository: LikeRepository,
        @Autowired private val userService: UserService,
        @Autowired private val partnerService: PartnerService
): LikeService {

    override fun existsLikeByUserIdAndPartnerId(userId: Long, partnerId: Long): Boolean =
        likeRepository.existsByUserIdAndPartnerId(userId, partnerId)

    fun updateLikes(userId: Long, likeReq: LikeDto.LikeReq){

        val user = userService.getUserById(userId)
        val partner = partnerService.getPartnerById(likeReq.partnerId)

        likeRepository.save(Like.toLike(user, partner))
    }
}