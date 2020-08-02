package gb.gongbaek.v1.backend.repository

import gb.gongbaek.v1.backend.domain.Comment
import org.springframework.data.jpa.repository.JpaRepository

interface CommentRepository: JpaRepository<Comment, Long>{
    fun findByUserId(userId: Long) : List<Comment>
}