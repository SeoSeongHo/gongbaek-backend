package gb.gongbaek.v1.backend.controller

import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/readingRoom")
class ReadingRoomController {


    @GetMapping
    fun getReadingRooms(){

    }

    @GetMapping("/{id}")
    fun getReadingRoom(@PathVariable id: Long){

    }

    @PostMapping
    fun createReadingRoom(){

    }

    @PostMapping("/confirm/{id}")
    fun confirmReadingRoom(@PathVariable id: Long){

    }

    @PostMapping("/{id}")
    fun updateReadingRoom(@PathVariable id: Long){

    }
}