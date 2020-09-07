package gb.gongbaek.v1.backend.repository

import gb.gongbaek.v1.backend.domain.RefreshTokenJob
import org.springframework.data.jpa.repository.JpaRepository

interface RefreshTokenJobRepository: JpaRepository<RefreshTokenJob, Long> {

    fun findByRefreshToken(refreshToken: String): RefreshTokenJob?
    fun deleteByRefreshToken(refreshToken: String)
}