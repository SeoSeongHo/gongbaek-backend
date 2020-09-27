package gb.gongbaek.v1.backend.service.home

import gb.gongbaek.v1.backend.dto.HomeCardDto
import gb.gongbaek.v1.backend.dto.RecommendCategory

interface HomeService{
    fun getCards(category: RecommendCategory, userId: Long?): List<HomeCardDto.HomeCardRes>
}