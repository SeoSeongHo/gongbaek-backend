package gb.gongbaek.v1.backend.service.recommendation

import gb.gongbaek.v1.backend.domain.Recommendation
import gb.gongbaek.v1.backend.dto.PartnerType
import gb.gongbaek.v1.backend.dto.RecommendCategory
import gb.gongbaek.v1.backend.dto.RecommendationDto
import gb.gongbaek.v1.backend.dto.RecommendTitle
import gb.gongbaek.v1.backend.exception.RecommendationNotFoundException
import gb.gongbaek.v1.backend.repository.RecommendationRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class RecommendationServiceImpl(
        @Autowired private val recommendationRepository: RecommendationRepository
): RecommendationService {

    override fun getRecommendation(id: Long): Recommendation =
            recommendationRepository.findById(id).orElseThrow { throw RecommendationNotFoundException("can not find recommendation. id: $id") }

    override fun getRecommendationByTitle(title: RecommendTitle): Recommendation =
            recommendationRepository.findByTitle(title) ?: throw RecommendationNotFoundException("can not find recommendation. title: $title")

    override fun getRecommendationByCategory(category: RecommendCategory): List<Recommendation> =
            recommendationRepository.findByCategory(category)
    
    fun create(recommendationReq: RecommendationDto.RecommendationReq){

        recommendationRepository.save(recommendationReq.toEntity())
    }
}