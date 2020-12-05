package gb.gongbaek.v1.backend.service.partner.readingRoom

import gb.gongbaek.v1.backend.domain.partner.ReadingRoom
import gb.gongbaek.v1.backend.dto.partner.readingRoom.ReadingRoomDetailDto

interface ReadingRoomService {
    fun getReadingRoom(id: Long): ReadingRoom
    fun getReadingRoomDetail(id: Long): ReadingRoomDetailDto.ReadingRoomDetailRes
}