package gb.gongbaek.v1.backend.service.impl

import gb.gongbaek.v1.backend.domain.User
import gb.gongbaek.v1.backend.domain.UserType
import gb.gongbaek.v1.backend.repository.UserRepository
import gb.gongbaek.v1.backend.service.UserService
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.annotation.Rollback
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.transaction.annotation.Transactional

@ExtendWith(SpringExtension::class)
@SpringBootTest
@Transactional
class UserServiceImplTest(

//        @Autowired private val entityManger: EntityManager,
        @Autowired private val userService: UserService,
        @Autowired private val userRepository: UserRepository
) {

    @Test
    @Rollback(false)
    fun createUserTest() {
        //give
        val user = createUser()

        //when
        userService.createUser(user)

        //then
        val findUser = userRepository.findById(user.id!!)
        Assertions.assertEquals(user, findUser.get())
    }

    @Test
    @Rollback(false)
    fun getUserTest() {
        //give
        val user = createUser()

        //when
        userRepository.save(user)

        //then
        val findUser = userService.findUser(user.id!!)
        Assertions.assertEquals(user, findUser)
    }

    @Test
    @Rollback(false)
    fun updateUserTest() {
        //give
        val user = createUser()
        val user2 = createUser2()
        userRepository.save(user)

        //when
        userService.updateUser(user.id!!, user2)

        //then
        val updatedUser = userService.findUser(user.id!!)
        Assertions.assertEquals(user2.name, updatedUser.name)
        Assertions.assertEquals(user2.email, updatedUser.email)
        Assertions.assertEquals(user2.password, updatedUser.password)
        Assertions.assertEquals(user2.avatar, updatedUser.avatar)
        Assertions.assertEquals(user2.type, updatedUser.type)
    }


    fun createUser(): User {

        return User(
                name = "seo",
                email =  "swa04246@naver.com",
                password =  "asd",
                avatar = "",
                type = UserType.STUDENT
        )
    }

    fun createUser2(): User {

        return User(
                name = "lee",
                email =  "sdada@naver.com",
                password =  "qwe",
                avatar = "",
                type = UserType.STUDENT
        )
    }
}