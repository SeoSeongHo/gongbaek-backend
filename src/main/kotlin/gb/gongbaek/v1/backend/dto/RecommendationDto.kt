package gb.gongbaek.v1.backend.dto

import gb.gongbaek.v1.backend.domain.Recommendation

class RecommendationDto {

    data class RecommendationReq(
            var category: RecommendTab,
            var title: RecommendCategory,
            var description: String
    ){
        fun toEntity() = Recommendation(
                category = category,
                title = title,
                description = description
        )
    }
}