package gb.gongbaek.v1.backend.domain.partner

import com.fasterxml.jackson.annotation.JsonIgnore
import gb.gongbaek.v1.backend.domain.EntityAuditing
import gb.gongbaek.v1.backend.domain.Like
import gb.gongbaek.v1.backend.domain.OperationalCertification
import gb.gongbaek.v1.backend.domain.hashtag.PartnerHashtag
import gb.gongbaek.v1.backend.dto.HomeCardDto
import gb.gongbaek.v1.backend.dto.PartnerType
import gb.gongbaek.v1.backend.dto.partner.PartnerDto
import gb.gongbaek.v1.backend.dto.partner.readingRoom.ReadingRoomDto
import javax.persistence.*

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype")
abstract class Partner(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        open val id: Long?,
        @Enumerated(EnumType.STRING)
        open val type: PartnerType,
        open val name: String,
        open var isConfirmed: Boolean,

        @JsonIgnore
        @OneToMany(mappedBy = "partner")
        open var likes: MutableList<Like> = mutableListOf(),

        open var branchName: String?, // 지점명
        open var adminContact: String, // 연락처, 괸리자 컨택용
        open var representativeContact: String, // 대표 번호, 유저 공개용

        open var businessRegistration: String?, // 사업자 등록증

        @OneToMany
        open var operationalCertification: MutableList<OperationalCertification>?, // 운영 인증, 유저 공개용
        open var representativeImage: String?, // 홈카드 대표 사진, 유저 공개용

        @JsonIgnore
        @OneToMany(mappedBy = "partner")
        open var partnerHashtags: MutableList<PartnerHashtag>

): EntityAuditing() {

    fun getTotalLikes() = likes.size
    abstract fun toHomeCard(isLiked: Boolean): HomeCardDto.Card
    abstract fun toDto(): PartnerDto
}