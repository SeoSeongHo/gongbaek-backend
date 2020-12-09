package gb.gongbaek.v1.backend.repository

import gb.gongbaek.v1.backend.domain.hashtag.SearchTab
import gb.gongbaek.v1.backend.domain.hashtag.Tab
import org.springframework.data.jpa.repository.JpaRepository

interface TabRepository: JpaRepository<Tab, Long> {
    fun findByName(name: SearchTab): Tab?
}