package gb.gongbaek.v1.backend.domain.partner

import gb.gongbaek.v1.backend.domain.EntityAuditing
import gb.gongbaek.v1.backend.dto.AcademyDto
import org.jetbrains.annotations.NotNull
import javax.persistence.*

@Entity
data class Academy (

       var branchName: String? = null,
       @Embedded
       var contact: String,
       var businessRegistration: String,
       var imageUrl: String
): Partner() {

       fun toHomeAcademyDto(): AcademyDto.HomeAcademyRes{
              return AcademyDto.HomeAcademyRes(
                      name = name + branchName,
                      imageUrl = imageUrl,
                      location = "${address.roadAddress} ${address.detailAddress}"
              )
       }
}

@Embeddable
data class Address(
     var roadAddress: String,
     var detailAddress: String
)