package gb.gongbaek.v1.backend.service.impl

import gb.gongbaek.v1.backend.domain.Category
import gb.gongbaek.v1.backend.domain.User
import gb.gongbaek.v1.backend.domain.UserType
import gb.gongbaek.v1.backend.dto.PostDto
import gb.gongbaek.v1.backend.repository.PostRepository
import gb.gongbaek.v1.backend.repository.ScrapPostRepository
import gb.gongbaek.v1.backend.repository.UserRepository
import gb.gongbaek.v1.backend.service.PostService
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.annotation.Rollback
import org.springframework.transaction.annotation.Transactional

@SpringBootTest
@Transactional
internal class PostServiceImplTest(
        @Autowired private val postService: PostService,
        @Autowired private val postRepository: PostRepository,
        @Autowired private val userRepository: UserRepository
){

    @Test
    @Rollback(false)
    fun createPostTest() {
        //give
        val user = createUser()
        userRepository.save(user)

        val post = PostDto.CreatePostReq(
                name = "seo",
                title = "테스트 제목입니다.",
                content = "테스트 본문입니다.",
                category = Category.FREE
        )

        //when
        val createdPost = postService.createPost(post, user.id!!)

        //then
        assertEquals(createdPost.name, post.name)
        assertEquals(createdPost.title, post.title)
        assertEquals(createdPost.content, post.content)
        assertEquals(createdPost.category, post.category)
        assertEquals(createdPost.user, user)
    }

    @Test
    fun getPostsTest() {
        //give

        //when

        //then
    }

    fun createUser(): User {

        return User(
                name = "seoseoseo",
                email =  "sdada@naver.com",
                password =  "qwe",
                avatar = "",
                type = UserType.STUDENT
        )
    }
}