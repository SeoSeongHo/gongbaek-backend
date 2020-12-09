package gb.gongbaek.v1.backend.service.tab

import gb.gongbaek.v1.backend.domain.hashtag.SearchTab
import gb.gongbaek.v1.backend.domain.hashtag.Tab
import gb.gongbaek.v1.backend.dto.hashtag.TabDto

interface TabService {
    fun createTabs(createTabReqs: List<TabDto.CreateTabReq>): List<Tab>
    fun createTab(createTabReq: TabDto.CreateTabReq): Tab
    fun getTab(id: Long): Tab
    fun getTabByName(name: SearchTab): Tab
}