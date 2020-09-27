package gb.gongbaek.v1.backend.domain.partner

import com.fasterxml.jackson.annotation.JsonIgnore
import gb.gongbaek.v1.backend.domain.EntityAuditing
import gb.gongbaek.v1.backend.domain.Like
import gb.gongbaek.v1.backend.dto.HomeCardDto
import gb.gongbaek.v1.backend.dto.PartnerType
import gb.gongbaek.v1.backend.dto.partner.PartnerDto
import javax.persistence.*

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype")
abstract class Partner(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        open val id: Long? = null,
        @Enumerated(EnumType.STRING)
        open val type: PartnerType,
        open val name: String,
        @Embedded
        open val address: Address,
        open var isConfirmed: Boolean,
        @JsonIgnore
        @OneToMany(mappedBy = "partner")
        open var likes: MutableList<Like> = mutableListOf()

): EntityAuditing() {

    fun getTotalLikes() = likes.size
    abstract fun toHomeCard(isLiked: Boolean): HomeCardDto.Card
    abstract fun toDto(): PartnerDto
}