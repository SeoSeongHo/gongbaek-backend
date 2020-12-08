package gb.gongbaek.v1.backend.service.partner.readingRoom

import com.amazonaws.services.s3.AmazonS3
import gb.gongbaek.v1.backend.domain.OperationalCertification
import gb.gongbaek.v1.backend.domain.hashtag.PartnerHashtag
import gb.gongbaek.v1.backend.domain.partner.Partner
import gb.gongbaek.v1.backend.domain.partner.ReadingRoom
import gb.gongbaek.v1.backend.dto.PartnerType
import gb.gongbaek.v1.backend.dto.partner.readingRoom.ReadingRoomDetailDto
import gb.gongbaek.v1.backend.dto.partner.readingRoom.ReadingRoomDto
import gb.gongbaek.v1.backend.exception.PartnerNotFoundException
import gb.gongbaek.v1.backend.repository.ReadingRoomRepository
import gb.gongbaek.v1.backend.service.partner.PartnerService
import gb.gongbaek.v1.backend.service.partner.hashtag.HashtagService
import gb.gongbaek.v1.backend.util.S3Uploader
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class ReadingRoomServiceImpl(
        @Autowired private val readingRoomRepository: ReadingRoomRepository,
        @Autowired private val partnerService: PartnerService,
        @Autowired private val hashtagService: HashtagService,
        @Autowired private val amazonS3Client: AmazonS3
): ReadingRoomService {

    override fun getReadingRoom(id: Long): ReadingRoom {
        return readingRoomRepository.findById(id).orElseThrow { PartnerNotFoundException("can not find reading room. id: $id") }
    }

    override fun getReadingRooms(): List<Partner>{
        return partnerService.getPartnersByType(PartnerType.READING_ROOM)
    }

    override fun getReadingRoomDetail(id: Long): ReadingRoomDetailDto.ReadingRoomDetailRes{
        return getReadingRoom(id).toDetailDto()
    }

    override fun createReadingRoom(createReadingRoomReq: ReadingRoomDto.CreateReadingRoomReq): Partner{

        val s3Uploader = S3Uploader(amazonS3Client)

        // TODO null 일 때 default 사진 url 맵핑
        val createReadingRoomImageReq = ReadingRoomDto.CreateReadingRoomImageReq(
                businessRegistration = s3Uploader.upload(createReadingRoomReq.businessRegistration!!, "gongbaek.static.io", "partners/readingRooms/${createReadingRoomReq.name}"),
                operationalCertification = createReadingRoomReq.operationalCertification?.map { oc ->  OperationalCertification(null, s3Uploader.upload(oc, "gongbaek.static.io", "partners/academies/${createReadingRoomReq.name}")) }?.toMutableList(),
                representativeImage = s3Uploader.upload(createReadingRoomReq.representativeImage!!, "gongbaek.static.io", "partners/readingRooms/${createReadingRoomReq.name}")
        )

        val hashtags = hashtagService.getHashtagsByIds(createReadingRoomReq.hashtagIds)
        val partnerHashtags = hashtags.map { hashtag -> PartnerHashtag.createPartnerHashtag(hashtag) }.toMutableList()
        val readingRoom = ReadingRoom.createReadingRoom(createReadingRoomReq, createReadingRoomImageReq, partnerHashtags)
        return partnerService.createPartner(readingRoom)
    }

    override fun confirmReadingRoom(id: Long){
        partnerService.confirmPartner(id)
    }
}