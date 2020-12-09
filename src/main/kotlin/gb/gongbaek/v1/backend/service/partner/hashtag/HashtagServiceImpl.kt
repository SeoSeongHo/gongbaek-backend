package gb.gongbaek.v1.backend.service.partner.hashtag

import gb.gongbaek.v1.backend.domain.hashtag.Hashtag
import gb.gongbaek.v1.backend.dto.hashtag.HashtagDto
import gb.gongbaek.v1.backend.exception.HashtagNotFoundException
import gb.gongbaek.v1.backend.repository.HashtagRepository
import gb.gongbaek.v1.backend.service.category.CategoryService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class HashtagServiceImpl(
        @Autowired private val hashtagRepository: HashtagRepository,
        @Autowired private val categoryService: CategoryService
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

    override fun createHashtags(createHashtags: List<HashtagDto.CreateHashtagReq>): List<Hashtag>{

        return createHashtags.map { createHashtagReq -> createHashtag(createHashtagReq) }
    }

    override fun createHashtag(createHashtag: HashtagDto.CreateHashtagReq): Hashtag{

        val category = categoryService.getCategoryByName(createHashtag.categoryName)

        return hashtagRepository.save(createHashtag.toEntity(category))
    }
}