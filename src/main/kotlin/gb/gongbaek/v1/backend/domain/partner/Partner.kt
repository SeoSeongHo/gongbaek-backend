package gb.gongbaek.v1.backend.domain.partner

import com.fasterxml.jackson.annotation.JsonIgnore
import gb.gongbaek.v1.backend.domain.EntityAuditing
import gb.gongbaek.v1.backend.domain.Like
import gb.gongbaek.v1.backend.domain.Recommendation
import gb.gongbaek.v1.backend.dto.HomeCardDto
import gb.gongbaek.v1.backend.dto.PartnerType
import javax.persistence.*

@MappedSuperclass
@DiscriminatorColumn(name = "dtype")
abstract class  Partner(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        open val id: Long? = null,
        open val type: PartnerType,
        open val name: String,
        @Embedded
        open val address: Address,
        open var isConfirmed: Boolean,
        @JsonIgnore
        @OneToMany(mappedBy = "partner")
        open var likes: List<Like> = mutableListOf()

): EntityAuditing() {

    fun getTotalLikes() = likes.size
    abstract fun toHomeCard(isLiked: Boolean): HomeCardDto.Card
}