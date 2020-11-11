package gb.gongbaek.v1.backend.domain

import javax.persistence.Entity

//@Entity
data class Category(
        var id: Long? = null,
        var name: String
) {
}