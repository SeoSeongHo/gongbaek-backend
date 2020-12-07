package gb.gongbaek.v1.backend.domain

import gb.gongbaek.v1.backend.domain.partner.Partner
import javax.persistence.*

@Entity
@Table(name = "likes")
data class Like (

        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long? = null,

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "user_id")
        var user: User,

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "partner_id")
        var partner: Partner
): EntityAuditing() {
        companion object{
                fun toLike(user: User, partner: Partner) = Like(
                        user = user,
                        partner = partner
                )
        }
}