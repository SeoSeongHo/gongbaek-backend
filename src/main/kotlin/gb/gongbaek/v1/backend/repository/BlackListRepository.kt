package gb.gongbaek.v1.backend.repository

import gb.gongbaek.v1.backend.domain.BlackList
import org.springframework.data.jpa.repository.JpaRepository

interface BlackListRepository: JpaRepository<BlackList, Long> {
    fun existsByToken(token: String): Boolean
}