package gb.gongbaek.v1.backend.dto

import gb.gongbaek.v1.backend.domain.Recommendation

class RecommendationDto {

    data class RecommendationReq(
            var category: RecommendCategory,
            var title: RecommendTitle,
            var description: String
    ){
        fun toEntity() = Recommendation(
                category = category,
                title = title,
                description = description
        )
    }
}