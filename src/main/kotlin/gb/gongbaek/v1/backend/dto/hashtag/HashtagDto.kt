package gb.gongbaek.v1.backend.dto.hashtag

import gb.gongbaek.v1.backend.domain.hashtag.Category
import gb.gongbaek.v1.backend.domain.hashtag.Hashtag
import gb.gongbaek.v1.backend.domain.hashtag.SearchCategory
import gb.gongbaek.v1.backend.dto.RecommendCategory

class HashtagDto {

    data class CreateHashtagReq(
            var categoryName: SearchCategory,
            var name: String
    ){
        fun toEntity(category: Category) = Hashtag(
                name = name,
                category = category
        )
    }
}