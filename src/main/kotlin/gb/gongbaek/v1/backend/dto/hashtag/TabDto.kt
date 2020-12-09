package gb.gongbaek.v1.backend.dto.hashtag

import gb.gongbaek.v1.backend.domain.hashtag.SearchTab
import gb.gongbaek.v1.backend.domain.hashtag.Tab
import gb.gongbaek.v1.backend.dto.RecommendTab

class TabDto {

    data class CreateTabReq(
            val name: SearchTab
    ){
        fun toEntity() = Tab(
                name = name
        )
    }
}