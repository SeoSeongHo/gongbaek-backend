package gb.gongbaek.v1.backend.service.category

import gb.gongbaek.v1.backend.domain.hashtag.Category
import gb.gongbaek.v1.backend.domain.hashtag.SearchCategory
import gb.gongbaek.v1.backend.dto.hashtag.CategoryDto
import gb.gongbaek.v1.backend.exception.CategoryNotFoundException
import gb.gongbaek.v1.backend.repository.CategoryRepository
import gb.gongbaek.v1.backend.service.tab.TabService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class CategoryServiceImpl(
        @Autowired private val categoryRepository: CategoryRepository,
        @Autowired private val tabService: TabService
): CategoryService {

    override fun createCategories(createCategoryReqs: List<CategoryDto.CreateCategoryReq>): List<Category>{

        return createCategoryReqs.map { createCategoryReq -> createCategory(createCategoryReq)}
    }

    override fun createCategory(createCategoryReq: CategoryDto.CreateCategoryReq): Category{

        val tab = tabService.getTabByName(createCategoryReq.tabName)

        return categoryRepository.save(createCategoryReq.toEntity(tab))
    }

    override fun getCategory(id: Long): Category{

        return categoryRepository.findById(id).orElseThrow { throw CategoryNotFoundException("can not find category. id: $id") }
    }

    override fun getCategoryByName(name: SearchCategory): Category{

        return categoryRepository.findByName(name) ?: throw CategoryNotFoundException("can not find category. name: $name")
    }
}