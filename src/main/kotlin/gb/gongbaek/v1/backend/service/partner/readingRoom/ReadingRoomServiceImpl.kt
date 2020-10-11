package gb.gongbaek.v1.backend.service.partner.readingRoom

import gb.gongbaek.v1.backend.service.partner.PartnerService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class ReadingRoomServiceImpl(
        @Autowired private val partnerService: PartnerService
): ReadingRoomService {

    
}