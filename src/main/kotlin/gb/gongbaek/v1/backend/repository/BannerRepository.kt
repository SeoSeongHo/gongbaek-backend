package gb.gongbaek.v1.backend.repository

import gb.gongbaek.v1.backend.domain.Banner
import gb.gongbaek.v1.backend.domain.BannerTab
import org.springframework.data.jpa.repository.JpaRepository

interface BannerRepository: JpaRepository<Banner, Long> {

    fun findBannersByTab(tab: BannerTab): List<Banner>
}