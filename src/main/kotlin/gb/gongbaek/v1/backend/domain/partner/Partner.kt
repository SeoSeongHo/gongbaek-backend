package gb.gongbaek.v1.backend.domain.partner

import com.fasterxml.jackson.annotation.JsonIgnore
import gb.gongbaek.v1.backend.domain.Like
import javax.persistence.*

// TODO 다중상속 처리하기
@Entity
abstract class Partner(
){
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
    lateinit var name: String
    @Embedded
    lateinit var address: Address
    var isConfirmed: Boolean? = false

    @JsonIgnore
    @OneToMany(mappedBy = "partner")
    var likes: List<Like> = mutableListOf()


    fun getLikesCount() = likes.size
}