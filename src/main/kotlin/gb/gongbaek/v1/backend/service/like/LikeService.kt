package gb.gongbaek.v1.backend.service.like

import gb.gongbaek.v1.backend.domain.Like
import org.springframework.data.jpa.repository.JpaRepository

interface LikeService{
    fun existsLikeByUserIdAndPartnerId(userId: Long, partnerId: Long): Boolean
}