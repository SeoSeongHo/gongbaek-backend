package gb.gongbaek.v1.backend.repository

import gb.gongbaek.v1.backend.domain.hashtag.Category
import org.springframework.data.jpa.repository.JpaRepository

interface CategoryRepository: JpaRepository<Category, Long> {
}