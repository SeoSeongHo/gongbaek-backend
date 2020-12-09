package gb.gongbaek.v1.backend.service.category

import gb.gongbaek.v1.backend.domain.hashtag.Category
import gb.gongbaek.v1.backend.domain.hashtag.SearchCategory
import gb.gongbaek.v1.backend.dto.hashtag.CategoryDto

interface CategoryService {

    fun createCategories(createCategoryReqs: List<CategoryDto.CreateCategoryReq>): List<Category>
    fun createCategory(createCategoryReq: CategoryDto.CreateCategoryReq): Category
    fun getCategory(id: Long): Category
    fun getCategoryByName(name: SearchCategory): Category
}