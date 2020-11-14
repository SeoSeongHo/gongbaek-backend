package gb.gongbaek.v1.backend.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/health")
class HealthCheckController {


    // 로드 밸런서가 상태 검사하는 api
    @GetMapping
    fun checkHealth(): ResponseEntity<Unit>{
        
        return ResponseEntity.ok().build()
    }
}