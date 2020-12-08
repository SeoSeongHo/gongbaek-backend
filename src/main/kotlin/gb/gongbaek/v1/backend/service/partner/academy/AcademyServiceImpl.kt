package gb.gongbaek.v1.backend.service.partner.academy

import com.amazonaws.services.s3.AmazonS3
import gb.gongbaek.v1.backend.domain.OperationalCertification
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
import gb.gongbaek.v1.backend.util.S3Uploader
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class AcademyServiceImpl(
        @Autowired private val academyRepository: AcademyRepository,
        @Autowired private val partnerService: PartnerService,
        @Autowired private val hashtagService: HashtagService,
        @Autowired private val amazonS3Client: AmazonS3

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

        val s3Uploader = S3Uploader(amazonS3Client)

        // TODO null 일 때 default 사진 url 맵핑
        val createAcademyImageReq = AcademyDto.CreateAcademyImageReq(
                businessRegistration = s3Uploader.upload(createAcademyReq.businessRegistration!!, "gongbaek.static.io", "partners/academies/${createAcademyReq.name}"),
                operationalCertification = createAcademyReq.operationalCertification?.map { oc ->  OperationalCertification(null, s3Uploader.upload(oc, "gongbaek.static.io", "partners/academies/${createAcademyReq.name}")) }?.toMutableList(),
                representativeImage = s3Uploader.upload(createAcademyReq.representativeImage!!, "gongbaek.static.io", "partners/academies/${createAcademyReq.name}")
        )

        val hashtags = hashtagService.getHashtagsByIds(createAcademyReq.hashtagIds)
        val partnerHashtags = hashtags.map { hashtag -> PartnerHashtag.createPartnerHashtag(hashtag) }.toMutableList()
        val academy = Academy.createAcademy(createAcademyReq, createAcademyImageReq, partnerHashtags)
        return academyRepository.save(academy)
    }

    override fun confirmAcademy(academyId: Long){
        partnerService.confirmPartner(academyId)
    }
}