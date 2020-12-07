package gb.gongbaek.v1.backend.domain.hashtag

import gb.gongbaek.v1.backend.domain.EntityAuditing
import gb.gongbaek.v1.backend.domain.partner.Partner
import javax.persistence.*

// partner - hash tag 간 many to many 관계를 위한 테이블
@Entity
data class PartnerHashtag (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "partner_id")
    var partner: Partner?,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hashtag_id")
    var hashtag: Hashtag
): EntityAuditing(){

    companion object{
        fun createPartnerHashtag(hashtag: Hashtag): PartnerHashtag{

            val partnerHashtag = PartnerHashtag(
                    id = null,
                    partner = null,
                    hashtag = hashtag
            )

            return partnerHashtag
        }
    }

}