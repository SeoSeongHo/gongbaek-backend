package gb.gongbaek.v1.backend.dto

import javax.persistence.EnumType
import javax.persistence.Enumerated

class HomeCardDto {

    data class HomeCardRes(

            @Enumerated(EnumType.STRING)
            var title: RecommendTitle,
            var description: String,
            var cards: List<Card>
    )

    data class Card(
            @Enumerated(EnumType.STRING)
            var partnerType: PartnerType,
            var partnerId: Long,
            var imageUrl: String,
            var name: String,
            var location: String,
            var isLiked: Boolean,
            var totalLikes: Int
    )
}

enum class PartnerType {
    ACADEMY,
    //STUDY_ROOM,
    READING_ROOM,
    //STORE
}

enum class RecommendCategory{
    TODAY,
    ACADEMY,
    READING_ROOM,
}

enum class RecommendTitle{
    // TODAY -------
    AI_PICK,
    PEER_PICK,
    MD_PICK,
    POPULAR_PICK,
    BEST,
    FAVORITE,

    // ACADEMY --------
    PREFERENCE,
    NEIGHBORHOOD,
    FEW_ELITE,
    CLOSE_COACH,
    SINGLE_SUBJECT,

    // READING ROOM --------
    STUDY_CAFE,
    CHEAP,
    PREMIUM,
    MANAGERMENT,
    LOCAL_STUDYROOM
}