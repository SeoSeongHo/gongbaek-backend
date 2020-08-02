package gb.gongbaek.v1.backend.service

import gb.gongbaek.v1.backend.domain.Category
import gb.gongbaek.v1.backend.dto.PostDto
import gb.gongbaek.v1.backend.dto.SearchType

interface PostService {
    fun createPost(postReq: PostDto.CreatePostReq, userId: Long): PostDto.PostRes
    fun getPosts(recent: Boolean, popular: Boolean, searchType: SearchType, category: Category, userId: Long): List<PostDto.PostRes>
    fun getPost(id: Long): PostDto.PostRes
}