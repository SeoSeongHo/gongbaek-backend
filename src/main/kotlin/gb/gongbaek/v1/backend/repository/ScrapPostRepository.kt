package gb.gongbaek.v1.backend.repository

import gb.gongbaek.v1.backend.domain.ScrapPost
import org.springframework.data.jpa.repository.JpaRepository

interface ScrapPostRepository: JpaRepository<ScrapPost, Long> {
    fun findByScrapId(scarpId: Long): List<ScrapPost>
}