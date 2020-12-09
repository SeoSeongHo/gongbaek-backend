package gb.gongbaek.v1.backend.service.partner.hashtag

import gb.gongbaek.v1.backend.domain.hashtag.Hashtag
import gb.gongbaek.v1.backend.dto.hashtag.HashtagDto

interface HashtagService {

    fun getHashtag(id: Long): Hashtag
    fun getHashtags(): List<Hashtag>
    fun getHashtagsByIds(ids: List<Long>): List<Hashtag>
    fun createHashtags(createHashtags: List<HashtagDto.CreateHashtagReq>): List<Hashtag>
    fun createHashtag(createHashtag: HashtagDto.CreateHashtagReq): Hashtag
}