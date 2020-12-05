package gb.gongbaek.v1.backend.dto.partner.readingRoom

import gb.gongbaek.v1.backend.domain.partner.Address

class ReadingRoomDetailDto{

    // 독서실 상세 Dto
    data class ReadingRoomDetailRes(

            val id: Long,
            val name: String,
            var category: String, // 분류
            var webSiteUrl: String?,
            var address: Address
    )
}