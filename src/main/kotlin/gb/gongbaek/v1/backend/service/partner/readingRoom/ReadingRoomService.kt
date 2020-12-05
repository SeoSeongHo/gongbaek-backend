package gb.gongbaek.v1.backend.service.partner.readingRoom

import gb.gongbaek.v1.backend.domain.partner.Partner
import gb.gongbaek.v1.backend.domain.partner.ReadingRoom
import gb.gongbaek.v1.backend.dto.partner.readingRoom.ReadingRoomDetailDto
import gb.gongbaek.v1.backend.dto.partner.readingRoom.ReadingRoomDto

interface ReadingRoomService {
    fun getReadingRoom(id: Long): ReadingRoom
    fun getReadingRooms(): List<Partner>
    fun getReadingRoomDetail(id: Long): ReadingRoomDetailDto.ReadingRoomDetailRes
    fun createReadingRoom(createReadingRoomReq: ReadingRoomDto.CreateReadingRoomReq): Partner
    fun confirmReadingRoom(id: Long)
}