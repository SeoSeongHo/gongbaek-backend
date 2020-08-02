package gb.gongbaek.v1.backend.repository

import gb.gongbaek.v1.backend.domain.Category
import gb.gongbaek.v1.backend.domain.Post
import org.springframework.data.jpa.repository.JpaRepository

interface PostRepository: JpaRepository<Post, Long>{
    fun findByUserId(userId: Long): List<Post>
    fun findByCategory(category: Category): List<Post>
}