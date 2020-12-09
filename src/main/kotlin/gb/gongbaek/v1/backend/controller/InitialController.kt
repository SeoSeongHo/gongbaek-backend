package gb.gongbaek.v1.backend.controller

import gb.gongbaek.v1.backend.domain.hashtag.SearchCategory
import gb.gongbaek.v1.backend.domain.hashtag.SearchTab
import gb.gongbaek.v1.backend.dto.RecommendTab
import gb.gongbaek.v1.backend.dto.RecommendCategory
import gb.gongbaek.v1.backend.dto.RecommendationDto
import gb.gongbaek.v1.backend.dto.hashtag.CategoryDto
import gb.gongbaek.v1.backend.dto.hashtag.HashtagDto
import gb.gongbaek.v1.backend.dto.hashtag.TabDto
import gb.gongbaek.v1.backend.service.category.CategoryService
import gb.gongbaek.v1.backend.service.partner.hashtag.HashtagService
import gb.gongbaek.v1.backend.service.recommendation.RecommendationService
import gb.gongbaek.v1.backend.service.tab.TabService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/initial")
class InitialController(
        @Autowired private val recommendationService: RecommendationService,
        @Autowired private val tabService: TabService,
        @Autowired private val categoryService: CategoryService,
        @Autowired private val hashtagService: HashtagService
) {

    // 초기 값 세팅
    // recommendation, hashtag, category, tab
    @GetMapping
    fun initalize(){

        // set recommendation
        val recommendationReqs = listOf(
                RecommendationDto.RecommendationReq(RecommendTab.TODAY, RecommendCategory.AI_PICK, ""),
                RecommendationDto.RecommendationReq(RecommendTab.TODAY, RecommendCategory.PEER_PICK, ""),
                RecommendationDto.RecommendationReq(RecommendTab.TODAY, RecommendCategory.MD_PICK, ""),
                RecommendationDto.RecommendationReq(RecommendTab.TODAY, RecommendCategory.POPULAR_PICK, ""),
                RecommendationDto.RecommendationReq(RecommendTab.TODAY, RecommendCategory.BEST, ""),
                RecommendationDto.RecommendationReq(RecommendTab.TODAY, RecommendCategory.FAVORITE, ""),
                RecommendationDto.RecommendationReq(RecommendTab.ACADEMY, RecommendCategory.PREFERENCE, ""),
                RecommendationDto.RecommendationReq(RecommendTab.ACADEMY, RecommendCategory.NEIGHBORHOOD, ""),
                RecommendationDto.RecommendationReq(RecommendTab.ACADEMY, RecommendCategory.FEW_ELITE, ""),
                RecommendationDto.RecommendationReq(RecommendTab.ACADEMY, RecommendCategory.CLOSE_COACH, ""),
                RecommendationDto.RecommendationReq(RecommendTab.ACADEMY, RecommendCategory.SINGLE_SUBJECT, ""),
                RecommendationDto.RecommendationReq(RecommendTab.READING_ROOM, RecommendCategory.STUDY_CAFE, ""),
                RecommendationDto.RecommendationReq(RecommendTab.READING_ROOM, RecommendCategory.CHEAP, ""),
                RecommendationDto.RecommendationReq(RecommendTab.READING_ROOM, RecommendCategory.PREMIUM, ""),
                RecommendationDto.RecommendationReq(RecommendTab.READING_ROOM, RecommendCategory.MANAGEMENT, ""),
                RecommendationDto.RecommendationReq(RecommendTab.READING_ROOM, RecommendCategory.LOCAL_STUDY_ROOM, "")
        )
        
        recommendationService.createRecommendations(recommendationReqs)

        // set tab
        val tabReqs = listOf(
                TabDto.CreateTabReq(SearchTab.ACADEMY),
                TabDto.CreateTabReq(SearchTab.READING_ROOM),
                TabDto.CreateTabReq(SearchTab.CLASS)
        )

        tabService.createTabs(tabReqs)

        // set category
        val categoryReqs = listOf(
                CategoryDto.CreateCategoryReq(SearchTab.ACADEMY, SearchCategory.PLACE),
                CategoryDto.CreateCategoryReq(SearchTab.ACADEMY, SearchCategory.SUBJECT)
        )

        categoryService.createCategories(categoryReqs)

        // set hashtag
        val hashtagReqs = listOf(
                HashtagDto.CreateHashtagReq(SearchCategory.SUBJECT, "#수학"),
                HashtagDto.CreateHashtagReq(SearchCategory.SUBJECT, "#영어"),
                HashtagDto.CreateHashtagReq(SearchCategory.SUBJECT, "#국어"),
                HashtagDto.CreateHashtagReq(SearchCategory.SUBJECT, "#과학")
        )

        hashtagService.createHashtags(hashtagReqs)
    }
}