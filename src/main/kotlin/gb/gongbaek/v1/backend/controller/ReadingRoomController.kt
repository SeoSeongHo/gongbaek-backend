package gb.gongbaek.v1.backend.controller

import gb.gongbaek.v1.backend.dto.partner.PartnerDto
import gb.gongbaek.v1.backend.dto.partner.readingRoom.ReadingRoomDetailDto
import gb.gongbaek.v1.backend.dto.partner.readingRoom.ReadingRoomDto
import gb.gongbaek.v1.backend.service.partner.readingRoom.ReadingRoomService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/readingRoom")
class ReadingRoomController(
        @Autowired private val readingRoomService: ReadingRoomService
) {


    @GetMapping
    fun getReadingRooms(){

    }

    @GetMapping("/{id}")
    fun getReadingRoom(@PathVariable id: Long): ResponseEntity<PartnerDto> {

        val readingRoom = readingRoomService.getReadingRoom(id)
        
        return ResponseEntity
                .ok()
                .body(readingRoom.toDto())
    }

    @GetMapping("/{id}/detail")
    fun getReadingRoomDetail(@PathVariable id: Long): ResponseEntity<ReadingRoomDetailDto.ReadingRoomDetailRes>{

        val readingRoomDetail = readingRoomService.getReadingRoomDetail(id)

        return ResponseEntity
                .ok()
                .body(readingRoomDetail)
    }

    @PostMapping
    fun createReadingRoom(){

    }

    @PostMapping("/confirm/{id}")
    fun confirmReadingRoom(@PathVariable id: Long){

    }
}