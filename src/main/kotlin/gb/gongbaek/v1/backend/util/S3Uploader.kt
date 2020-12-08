package gb.gongbaek.v1.backend.util

import com.amazonaws.services.s3.AmazonS3
import com.amazonaws.services.s3.model.CannedAccessControlList
import com.amazonaws.services.s3.model.GetObjectRequest
import com.amazonaws.services.s3.model.PutObjectRequest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import org.springframework.web.multipart.MultipartFile
import java.io.*
import java.util.*

class S3Uploader(
        private val amazonS3Client: AmazonS3
) {

    @Throws(IOException::class)
    fun upload(multipartFile: MultipartFile, bucketName: String, dirName: String): String? {
        val uploadFile: File = convert(multipartFile)
                .orElseThrow { IllegalArgumentException("Converting MultipartFile to File failed") }
        return upload(uploadFile, bucketName, dirName)
    }

    private fun upload(uploadFile: File, bucketName: String, dirName: String): String? {
        val fileName = dirName + "/" + uploadFile.name
        val uploadImageUrl = putS3(uploadFile, bucketName, fileName)
        removeNewFile(uploadFile)
        return uploadImageUrl
    }

    private fun putS3(uploadFile: File?, bucketName: String, fileName: String?): String? {
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