package gb.gongbaek.v1.backend.repository

import gb.gongbaek.v1.backend.domain.Scrap
import org.springframework.data.jpa.repository.JpaRepository

interface ScrapRepository: JpaRepository<Scrap, Long>{
    fun findByUserId(userId: Long): Scrap
}