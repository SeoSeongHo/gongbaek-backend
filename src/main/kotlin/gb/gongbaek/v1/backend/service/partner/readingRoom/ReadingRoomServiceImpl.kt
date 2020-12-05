package gb.gongbaek.v1.backend.service.partner.readingRoom

import gb.gongbaek.v1.backend.domain.partner.Partner
import gb.gongbaek.v1.backend.domain.partner.ReadingRoom
import gb.gongbaek.v1.backend.domain.partner.ReadingRoomDetail
import gb.gongbaek.v1.backend.dto.PartnerType
import gb.gongbaek.v1.backend.dto.partner.readingRoom.ReadingRoomDetailDto
import gb.gongbaek.v1.backend.dto.partner.readingRoom.ReadingRoomDto
import gb.gongbaek.v1.backend.exception.PartnerNotFoundException
import gb.gongbaek.v1.backend.repository.ReadingRoomRepository
import gb.gongbaek.v1.backend.service.partner.PartnerService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class ReadingRoomServiceImpl(
        @Autowired private val partnerService: PartnerService,
        @Autowired private val readingRoomRepository: ReadingRoomRepository
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
        return partnerService.createPartner(createReadingRoomReq.toEntity())
    }

    override fun confirmReadingRoom(id: Long){
        partnerService.confirmPartner(id)
    }
}