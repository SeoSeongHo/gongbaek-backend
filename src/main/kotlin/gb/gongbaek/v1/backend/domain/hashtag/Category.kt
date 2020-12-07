package gb.gongbaek.v1.backend.domain.hashtag

import com.fasterxml.jackson.annotation.JsonIgnore
import gb.gongbaek.v1.backend.domain.EntityAuditing
import javax.persistence.*

@Entity
data class Category(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long? = null,
        var name: String,

        @JsonIgnore
        @OneToMany
        var hashtags: MutableList<Hashtag> = mutableListOf()
): EntityAuditing()