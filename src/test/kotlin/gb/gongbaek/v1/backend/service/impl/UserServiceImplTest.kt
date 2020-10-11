//package gb.gongbaek.v1.backend.service.impl
//
//import gb.gongbaek.v1.backend.domain.UserRole
//import gb.gongbaek.v1.backend.dto.SignUpDto
//import gb.gongbaek.v1.backend.repository.UserRepository
//import gb.gongbaek.v1.backend.service.user.UserService
//import org.junit.jupiter.api.Assertions
//import org.junit.jupiter.api.Test
//import org.junit.jupiter.api.extension.ExtendWith
//import org.junit.jupiter.api.fail
//
//import org.springframework.beans.factory.annotation.Autowired
//import org.springframework.boot.test.context.SpringBootTest
//import org.springframework.test.annotation.Rollback
//import org.springframework.test.context.junit.jupiter.SpringExtension
//import org.springframework.transaction.annotation.Transactional
//
//@ExtendWith(SpringExtension::class)
//@SpringBootTest
//@Transactional
//class UserServiceImplTest(
//
////        @Autowired private val entityManger: EntityManager,
//        @Autowired private val userService: UserService,
//        @Autowired private val userRepository: UserRepository
//) {
//
//    @Test
//    @Rollback(false)
//    fun signUp_Normal() {
//        //give
//        val signUpReq = getSampleSignUpReq()
//
//        //when
//        userService.signUp(signUpReq)
//
//        //then
//        val findUser = userRepository.findByUsername(signUpReq.username) ?: fail("")
//
//        Assertions.assertEquals(signUpReq.username, findUser.username)
//    }
//
////    @Test
////    @Rollback(false)
////    fun signUp_Duplicate() {
////        Assertions.assertThrows(DuplicateUsernameException::class.java, () ->  userService.)
////
////        fail("")
////    }
//
//    fun getSampleSignUpReq() = SignUpDto.SignUpReq(
//            username = "seo",
//            password = "asd",
//            firstName = "Seo",
//            lastName = "SeongHo",
//            avatar = "",
//            type = UserRole.STUDENT
//    )
//}