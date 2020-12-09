package gb.gongbaek.v1.backend.service.home

import gb.gongbaek.v1.backend.dto.HomeCardDto
import gb.gongbaek.v1.backend.dto.RecommendTab
import gb.gongbaek.v1.backend.dto.RecommendCategory

interface HomeService{
    fun getCards(category: RecommendTab, userId: Long?): List<HomeCardDto.HomeCardRes>
    fun getCards(category: RecommendTab, title: RecommendCategory, userId: Long?): HomeCardDto.HomeCardRes
}