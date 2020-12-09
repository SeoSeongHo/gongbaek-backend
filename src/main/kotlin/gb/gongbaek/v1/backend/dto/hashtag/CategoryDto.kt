package gb.gongbaek.v1.backend.dto.hashtag

import gb.gongbaek.v1.backend.domain.hashtag.Category
import gb.gongbaek.v1.backend.domain.hashtag.SearchCategory
import gb.gongbaek.v1.backend.domain.hashtag.SearchTab
import gb.gongbaek.v1.backend.domain.hashtag.Tab
import gb.gongbaek.v1.backend.dto.RecommendCategory
import gb.gongbaek.v1.backend.dto.RecommendTab

class CategoryDto{

    data class CreateCategoryReq(
            val tabName: SearchTab,
            val name: SearchCategory
    ){
        fun toEntity(tab: Tab) = Category(
                name = name,
                tab = tab
        )
    }
}