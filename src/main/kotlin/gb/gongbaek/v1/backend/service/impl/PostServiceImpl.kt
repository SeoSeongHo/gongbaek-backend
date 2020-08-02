package gb.gongbaek.v1.backend.service.impl

import gb.gongbaek.v1.backend.domain.Category
import gb.gongbaek.v1.backend.domain.Post
import gb.gongbaek.v1.backend.dto.PostDto
import gb.gongbaek.v1.backend.dto.SearchType
import gb.gongbaek.v1.backend.exception.PostNotFoundException
import gb.gongbaek.v1.backend.exception.UserNotFoundException
import gb.gongbaek.v1.backend.repository.*
import gb.gongbaek.v1.backend.service.PostService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class PostServiceImpl(
        @Autowired private val postRepository: PostRepository,
        @Autowired private val userRepository: UserRepository,
        @Autowired private val scrapRepository: ScrapRepository,
        @Autowired private val scrapPostRepository: ScrapPostRepository,
        @Autowired private val commentRepository: CommentRepository
): PostService {

    override fun createPost(postReq: PostDto.CreatePostReq, userId: Long): PostDto.PostRes{

        val user = userRepository.findById(userId).orElseThrow { UserNotFoundException("can not create post. because can not find user. $userId") }
        val post = PostDto.create(postReq, user)
        postRepository.save(post)

        return PostDto.toDto(post)
    }

//    fun updatePost(postReq: PostDto.CreatePostReq, postId: Long): PostDto.PostRes{
//
//    }

    override fun getPosts(recent: Boolean, popular: Boolean, searchType: SearchType, category: Category, userId: Long): List<PostDto.PostRes>{

        val posts: MutableList<Post> = when (searchType){
            SearchType.ALL -> {
                postRepository.findAll()
            }
            SearchType.SCRAP -> {
                val scrap = scrapRepository.findByUserId(userId)
                val scrapPost = scrapPostRepository.findByScrapId(scrap.id!!)
                scrapPost.map{ sp -> sp.post}.toMutableList()
            }
            SearchType.MY_POST -> {
                postRepository.findByUserId(userId).toMutableList()
            }
            SearchType.MY_COMMENT -> {
                val comment = commentRepository.findByUserId(userId)
                comment.map { it.post }.toMutableList()
            }
            else -> {
                postRepository.findByCategory(category).toMutableList()
            }
        }

        if(recent){
            posts.sortByDescending { it.createdAt }
        }
        if(popular){
            posts.sortByDescending { it.likes.size }
        }

        return posts.map { p -> PostDto.toDto(p) }
    }

    override fun getPost(id: Long): PostDto.PostRes{
        return PostDto.toDto(postRepository.findById(id).orElseThrow{ PostNotFoundException("can not find post. $id") })
    }
}