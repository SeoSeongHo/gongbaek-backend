package gb.gongbaek.v1.backend.repository

import gb.gongbaek.v1.backend.domain.Like
import org.springframework.data.jpa.repository.JpaRepository

interface LikeRepository: JpaRepository<Like, Long> {
    fun existsByUserIdAndPartnerId(userId: Long, partnerId: Long): Boolean
}