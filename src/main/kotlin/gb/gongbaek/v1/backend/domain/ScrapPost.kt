package gb.gongbaek.v1.backend.domain

import javax.persistence.*

@Entity
data class ScrapPost (

        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long? = null,

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "scarp_id")
        var scrap: Scrap,

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "post_id")
        var post: Post

): EntityAuditing()