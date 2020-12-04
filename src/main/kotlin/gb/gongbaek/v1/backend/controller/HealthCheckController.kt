package gb.gongbaek.v1.backend.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HealthCheckController {


    // ALB 로드 밸런서가 상태 검사하는 api
    @GetMapping("/api/v1/health")
    fun checkHealth(): ResponseEntity<Unit>{
        
        return ResponseEntity.ok().build()
    }

    // CLB
    @GetMapping("/")
    fun checkHealthForCLB(): ResponseEntity<Unit>{

        return ResponseEntity.ok().build()
    }
}