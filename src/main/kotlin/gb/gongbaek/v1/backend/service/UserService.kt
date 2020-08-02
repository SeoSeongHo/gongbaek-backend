package gb.gongbaek.v1.backend.service

import gb.gongbaek.v1.backend.domain.User

interface UserService {
    fun findUsers(): MutableList<User>
    fun findUser(id: Long): User
    fun createUser(user: User): Long
    fun updateUser(id: Long, user: User): Long
}