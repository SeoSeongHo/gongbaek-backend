package gb.gongbaek.v1.backend.repository

import gb.gongbaek.v1.backend.domain.partner.Academy
import org.springframework.data.jpa.repository.JpaRepository

interface AcademyRepository: JpaRepository<Academy, Long> {
}