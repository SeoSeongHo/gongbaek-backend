//package gb.gongbaek.v1.backend.controller
//
//import gb.gongbaek.v1.backend.util.S3Uploader
//import org.springframework.beans.factory.annotation.Autowired
//import org.springframework.web.bind.annotation.*
//import org.springframework.web.multipart.MultipartFile
//
//@RestController
//@RequestMapping("/api/v1/test")
//class S3Controller(
//){
//
//    @GetMapping("/")
//    fun index(): String{
//        return "test"
//    }
//
//    @PostMapping("/upload")
//    fun upload(@RequestParam("data")multipartFile: MultipartFile){
//
//        multipartFile.let{s3Uploader.upload(multipartFile)}
//    }
//}