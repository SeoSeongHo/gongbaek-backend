package gb.gongbaek.v1.backend.service.partner.hashtag

import gb.gongbaek.v1.backend.domain.hashtag.Hashtag

interface HashtagService {

    fun getHashtag(id: Long): Hashtag
    fun getHashtags(): List<Hashtag>
    fun getHashtagsByIds(ids: List<Long>): List<Hashtag>
}