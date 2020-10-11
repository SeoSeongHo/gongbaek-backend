package gb.gongbaek.v1.backend.repository

import gb.gongbaek.v1.backend.domain.partner.ReadingRoom
import org.springframework.data.jpa.repository.JpaRepository

interface ReadingRoomRepository: JpaRepository<ReadingRoom, Long> {
}