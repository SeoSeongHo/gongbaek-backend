package gb.gongbaek.v1.backend.domain.hashtag

import com.fasterxml.jackson.annotation.JsonIgnore
import gb.gongbaek.v1.backend.domain.EntityAuditing
import gb.gongbaek.v1.backend.dto.RecommendCategory
import javax.persistence.*

@Entity
data class Category(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long? = null,
        @Enumerated(EnumType.STRING)
        @Column(unique = true)
        var name: SearchCategory,

        @JsonIgnore
        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "tab_id")
        var tab: Tab
): EntityAuditing()

enum class SearchCategory{
        PLACE, SUBJECT
}