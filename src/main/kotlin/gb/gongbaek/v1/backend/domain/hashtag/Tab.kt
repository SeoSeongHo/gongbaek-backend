package gb.gongbaek.v1.backend.domain.hashtag

import com.fasterxml.jackson.annotation.JsonIgnore
import gb.gongbaek.v1.backend.domain.EntityAuditing
import gb.gongbaek.v1.backend.dto.RecommendCategory
import gb.gongbaek.v1.backend.dto.RecommendTab
import javax.persistence.*

@Entity
data class Tab (
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long? = null,
        @Enumerated(EnumType.STRING)
        @Column(unique = true)
        var name: SearchTab
): EntityAuditing()

enum class SearchTab{
        ACADEMY, READING_ROOM, CLASS
}