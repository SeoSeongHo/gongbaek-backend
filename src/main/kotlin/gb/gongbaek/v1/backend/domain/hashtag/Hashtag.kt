package gb.gongbaek.v1.backend.domain.hashtag

import com.fasterxml.jackson.annotation.JsonIgnore
import gb.gongbaek.v1.backend.domain.EntityAuditing
import javax.persistence.*

@Entity
data class Hashtag(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long? = null,
        @Column(unique = true)
        var name: String,

        @JsonIgnore
        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "category_id")
        var category: Category
): EntityAuditing()