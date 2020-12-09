package gb.gongbaek.v1.backend.service.tab

import gb.gongbaek.v1.backend.domain.hashtag.SearchTab
import gb.gongbaek.v1.backend.domain.hashtag.Tab
import gb.gongbaek.v1.backend.dto.hashtag.TabDto
import gb.gongbaek.v1.backend.exception.TabNotFoundException
import gb.gongbaek.v1.backend.repository.TabRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class TabServiceImpl(
        @Autowired private val tabRepository: TabRepository
): TabService {

    override fun createTabs(createTabReqs: List<TabDto.CreateTabReq>): List<Tab>{

        return createTabReqs.map { createTabReq -> tabRepository.save(createTabReq.toEntity()) }
    }

    override fun createTab(createTabReq: TabDto.CreateTabReq): Tab{

        return tabRepository.save(createTabReq.toEntity())
    }

    override fun getTab(id: Long): Tab{

        return tabRepository.findById(id).orElseThrow { throw TabNotFoundException("can not find tab. id: $id") }
    }

    override fun getTabByName(name: SearchTab): Tab{

        return tabRepository.findByName(name) ?: throw TabNotFoundException("can not find tab. name: $name")
    }
}