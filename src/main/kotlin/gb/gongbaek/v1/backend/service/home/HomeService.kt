package gb.gongbaek.v1.backend.service.home

import gb.gongbaek.v1.backend.dto.HomeCardDto
import gb.gongbaek.v1.backend.dto.RecommendCategory
import gb.gongbaek.v1.backend.dto.RecommendTitle

interface HomeService{
    fun getCards(category: RecommendCategory, userId: Long?): List<HomeCardDto.HomeCardRes>
    fun getCards(category: RecommendCategory, title: RecommendTitle, userId: Long?): HomeCardDto.HomeCardRes
}