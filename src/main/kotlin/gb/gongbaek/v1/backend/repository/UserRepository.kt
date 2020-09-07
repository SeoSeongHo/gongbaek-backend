package gb.gongbaek.v1.backend.repository

import gb.gongbaek.v1.backend.domain.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository: JpaRepository<User, Long>{
    fun findByEmail(email: String): User?
    fun existsByEmail(email: String): Boolean
    fun existsByUserInfoNickname(nickname: String): Boolean
}