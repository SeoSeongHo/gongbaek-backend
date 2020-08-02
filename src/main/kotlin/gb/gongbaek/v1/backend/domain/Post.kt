package gb.gongbaek.v1.backend.domain

import javax.persistence.*

@Entity
@Table(name = "posts")
data class Post (

        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long? = null,

        var title: String,
        var content: String,
        var name: String,

        @Enumerated(EnumType.STRING)
        var category: Category,

        // TODO poster S3 upload

        // user
        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "user_id")
        var user: User,

        // like
        @OneToMany(mappedBy = "post", cascade = [(CascadeType.ALL)])
        var likes: MutableList<Like> = mutableListOf(),

        // comment
        @OneToMany(mappedBy = "post", cascade = [(CascadeType.ALL)])
        var comments: MutableList<Comment> = mutableListOf(),

        @OneToMany(mappedBy = "post", cascade = [(CascadeType.ALL)])
        var scrapPosts: MutableList<ScrapPost> = mutableListOf()
): EntityAuditing() {


}

enum class Category{
        FREE, ENTRANCE, QA, EXAM_REVIEW, LECTURE_REVIEW
}