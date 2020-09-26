package gb.gongbaek.v1.backend.repository

import gb.gongbaek.v1.backend.domain.partner.Partner
import gb.gongbaek.v1.backend.dto.PartnerType
import org.springframework.data.jpa.repository.JpaRepository

interface PartnerRepository: JpaRepository<Partner, Long> {
    
    fun findByType(type: PartnerType): List<Partner>
}