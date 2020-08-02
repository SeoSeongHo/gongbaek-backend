package gb.gongbaek.v1.backend.domain

import javax.persistence.*

@Entity
data class Scrap(

        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long? = null,

        @OneToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "user_id")
        var user: User,

        @OneToMany(mappedBy = "scrap")
        var scrapPosts: MutableList<ScrapPost> = mutableListOf()
): EntityAuditing()