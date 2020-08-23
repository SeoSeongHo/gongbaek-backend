package gb.gongbaek.v1.backend.util

import com.amazonaws.services.s3.AmazonS3
import com.amazonaws.services.s3.model.CannedAccessControlList
import com.amazonaws.services.s3.model.PutObjectRequest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import org.springframework.web.multipart.MultipartFile
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.util.*

@Component
class S3Uploader(
        @Autowired
        private val amazonS3Client: AmazonS3,
        @Value("\${aws.s3.academy.img.bucket}")
        private val bucketName: String,
        @Value("\${aws.s3.academy.img.dir}")
        private val dirName: String
) {

    @Throws(IOException::class)
    fun upload(multipartFile: MultipartFile): String? {
        val uploadFile: File = convert(multipartFile)
                .orElseThrow { IllegalArgumentException("Converting MultipartFile to File failed") }
        return upload(uploadFile)
    }

    private fun upload(uploadFile: File): String? {
        val fileName = dirName + "/" + uploadFile.name
        val uploadImageUrl = putS3(uploadFile, fileName)
        removeNewFile(uploadFile)
        return uploadImageUrl
    }

    private fun putS3(uploadFile: File?, fileName: String?): String? {
        amazonS3Client.putObject(PutObjectRequest(bucketName, fileName, uploadFile).withCannedAcl(CannedAccessControlList.PublicRead))
        return amazonS3Client.getUrl(bucketName, fileName).toString()
    }

    private fun removeNewFile(targetFile: File) {
        targetFile.delete()
    }

    @Throws(IOException::class)
    private fun convert(file: MultipartFile): Optional<File> {
        val convertFile = File(file.originalFilename!!)
        if (convertFile.createNewFile()) {
            FileOutputStream(convertFile).use { it.write(file.bytes) }
            return Optional.of(convertFile)
        }
        return Optional.empty()
    }
}