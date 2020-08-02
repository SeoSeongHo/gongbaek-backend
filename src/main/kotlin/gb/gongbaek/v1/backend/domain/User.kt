package gb.gongbaek.v1.backend.domain

import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.*

@Entity
@Table(name = "users")
data class User (
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long? = null,
        var name: String,

        var email: String,
        var password: String,

        var avatar: String,

        @Enumerated(EnumType.STRING)
        var type: UserType,

        // hashtag


        // post
        @JsonIgnore
        @OneToMany(mappedBy = "user")
        var posts: MutableList<Post> = mutableListOf()
): EntityAuditing() {

}

enum class UserType {
    STUDENT, PARENT, TEACHER
}
