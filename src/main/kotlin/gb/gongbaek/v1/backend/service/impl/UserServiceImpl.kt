package gb.gongbaek.v1.backend.service.impl

import gb.gongbaek.v1.backend.domain.User
import gb.gongbaek.v1.backend.exception.UserNotFoundException
import gb.gongbaek.v1.backend.repository.UserRepository
import gb.gongbaek.v1.backend.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class UserServiceImpl(
        @Autowired private val userRepository: UserRepository
): UserService{

    override fun findUsers(): MutableList<User>{
        return userRepository.findAll()
    }

    override fun findUser(id: Long): User {
        return userRepository.findById(id).orElseThrow{ UserNotFoundException("can not find user. user_id: $id") }
    }

    override fun createUser(user: User): Long {
        userRepository.save(user)
        return user.id!!
    }

    override fun updateUser(id: Long, user: User): Long {
        val findUser = userRepository.findById(id).orElseThrow{ UserNotFoundException("can not find user. user_id: ${id}") }
        findUser.name = user.name
        findUser.email = user.email
        findUser.password = user.password
        findUser.avatar = user.avatar
        findUser.type = user.type

        return findUser.id!!
    }
}