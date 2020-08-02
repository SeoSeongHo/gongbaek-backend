package gb.gongbaek.v1.backend.dto

import gb.gongbaek.v1.backend.domain.*

class PostDto{

    data class CreatePostReq(
            var name: String,
            var title: String,
            var content: String,
            var category: Category

            // poster
    )

    data class PostRes(
            var name: String,
            var title: String,
            var content: String,
            var category: Category,
            var user: User,
            var likes: MutableList<Like>,
            var comments: MutableList<Comment>
    )

    data class UpdatePostReq(
            var name: String,
            var title: String,
            var content: String,
            var category: Category
    )

    companion object{
        fun create(postReq: CreatePostReq, user: User): Post {
            return Post(
                    title = postReq.title,
                    content = postReq.content,
                    name = postReq.name,
                    category = postReq.category,
                    user = user
            )
        }

        //fun update(postReq: UpdatePostReq, user: User, )

        fun toDto(post: Post): PostRes{
            return PostRes(
                    name = post.name,
                    title = post.title,
                    content = post.content,
                    category = post.category,
                    user = post.user,
                    likes = post.likes,
                    comments = post.comments
            )
        }
    }
}

enum class SearchType{
    ALL, SCRAP, MY_POST, MY_COMMENT, CATEGORY
}