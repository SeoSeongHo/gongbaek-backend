package gb.gongbaek.v1.backend.domain

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
data class OperationalCertification (

        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long? = null,

        // s3 image url
        val image: String?
){
}