package gb.gongbaek.v1.backend.service.partner.hashtag

import gb.gongbaek.v1.backend.domain.hashtag.Hashtag
import gb.gongbaek.v1.backend.exception.HashtagNotFoundException
import gb.gongbaek.v1.backend.repository.HashtagRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class HashtagServiceImpl(
        @Autowired private val hashtagRepository: HashtagRepository
): HashtagService {

    override fun getHashtag(id: Long): Hashtag {
        return hashtagRepository.findById(id).orElseThrow { throw HashtagNotFoundException("can not find hashtag. id: $id") }
    }

    override fun getHashtags(): List<Hashtag> {
        return hashtagRepository.findAll()
    }

    override fun getHashtagsByIds(ids: List<Long>): List<Hashtag> {
        return hashtagRepository.findAllById(ids)
    }
}