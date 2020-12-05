package gb.gongbaek.v1.backend.controller

import gb.gongbaek.v1.backend.dto.partner.academy.AcademyDto
import gb.gongbaek.v1.backend.dto.partner.PartnerDto
import gb.gongbaek.v1.backend.dto.partner.academy.AcademyDetailDto
import gb.gongbaek.v1.backend.service.partner.academy.AcademyService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/academies")
class AcademyController(
        @Autowired private val academyService: AcademyService
) {

    @GetMapping
    fun getAcademies(): ResponseEntity<List<PartnerDto>>{

        val academies = academyService.getAcademies()

        return ResponseEntity
                .ok()
                .body(academies.map { academy -> academy.toDto() })
    }

    @GetMapping("/{id}")
    fun getAcademy(@PathVariable id: Long): ResponseEntity<PartnerDto>{

        val academy = academyService.getAcademy(id)

        return ResponseEntity
                .ok()
                .body(academy.toDto())
    }

    @PostMapping
    fun createAcademy(@RequestBody academyReq: AcademyDto.CreateAcademyReq): ResponseEntity<PartnerDto>{
        val academy = academyService.createAcademy(academyReq)

        return ResponseEntity
                .ok()
                .body(academy.toDto())
    }

    @GetMapping("/{id}/detail")
    fun getAcademyDetail(@PathVariable id: Long): ResponseEntity<AcademyDetailDto.AcademyDetailRes>{

        val academyDetail = academyService.getAcademyDetail(id)

        return ResponseEntity
                .ok()
                .body(academyDetail)
    }


    @PostMapping("/confirm/{id}")
    fun confirmAcademy(@PathVariable id: Long){
        academyService.confirmAcademy(id)
    }
}