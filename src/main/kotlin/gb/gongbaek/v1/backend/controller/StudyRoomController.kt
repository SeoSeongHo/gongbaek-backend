package gb.gongbaek.v1.backend.controller

import gb.gongbaek.v1.backend.service.partner.studyRoom.StudyRoomService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/api/v1/studyRoom")
class StudyRoomController(
        @Autowired private val studyRoomService: StudyRoomService
) {


    @GetMapping
    fun getStudyRooms(){

    }

    @GetMapping("/{id}")
    fun getStudyRoom(@PathVariable id: Long){

    }

    @PostMapping
    fun createStudyRoom(){

    }

    @PostMapping("/confirm/{id}")
    fun confirmStudyRoom(@PathVariable id: Long){

    }

    @PostMapping("/{id}")
    fun updateStudyRoom(){

    }
}