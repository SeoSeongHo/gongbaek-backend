package gb.gongbaek.v1.backend.service.partner.academy

import gb.gongbaek.v1.backend.domain.hashtag.PartnerHashtag
import gb.gongbaek.v1.backend.domain.partner.Academy
import gb.gongbaek.v1.backend.domain.partner.Partner
import gb.gongbaek.v1.backend.dto.PartnerType
import gb.gongbaek.v1.backend.dto.partner.academy.AcademyDetailDto
import gb.gongbaek.v1.backend.dto.partner.academy.AcademyDto
import gb.gongbaek.v1.backend.exception.AcademyNotFoundException
import gb.gongbaek.v1.backend.repository.AcademyRepository
import gb.gongbaek.v1.backend.service.partner.PartnerService
import gb.gongbaek.v1.backend.service.partner.hashtag.HashtagService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class AcademyServiceImpl(
        @Autowired private val academyRepository: AcademyRepository,
        @Autowired private val partnerService: PartnerService,
        @Autowired private val hashtagService: HashtagService
): AcademyService {

    override fun getAcademy(academyId: Long): Academy{
        return academyRepository.findById(academyId).orElseThrow { throw AcademyNotFoundException("can not find academy. id: $academyId") }
    }

    override fun getAcademies(): List<Partner> {
        return partnerService.getPartnersByType(PartnerType.ACADEMY)
    }


    override fun getAcademyDetail(academyId: Long): AcademyDetailDto.AcademyDetailRes{
        return getAcademy(academyId).toDetailDto()
    }

    override fun createAcademy(createAcademyReq: AcademyDto.CreateAcademyReq): Partner {

        val hashtags = hashtagService.getHashtagsByIds(createAcademyReq.hashtagIds)
        val partnerHashtags = hashtags.map { hashtag -> PartnerHashtag.createPartnerHashtag(hashtag) }.toMutableList()
        val academy = Academy.createAcademy(createAcademyReq, partnerHashtags)
        return academyRepository.save(academy)
    }

    override fun confirmAcademy(academyId: Long){
        partnerService.confirmPartner(academyId)
    }
}