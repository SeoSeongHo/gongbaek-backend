package gb.gongbaek.v1.backend.repository

import gb.gongbaek.v1.backend.domain.hashtag.Hashtag
import org.springframework.data.jpa.repository.JpaRepository

interface HashtagRepository: JpaRepository<Hashtag, Long>{
}