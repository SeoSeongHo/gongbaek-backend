package gb.gongbaek.v1.backend.config.jwt

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.config.annotation.SecurityConfigurerAdapter
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.web.DefaultSecurityFilterChain
import org.springframework.security.web.access.ExceptionTranslationFilter
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

class JwtConfig(
        private val jwtTokenProvider: JwtTokenProvider,
        private val restAuthenticationEntryPoint: RestAuthenticationEntryPoint
): SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity>() {

    override fun configure(builder: HttpSecurity?) {
        val filter = JwtTokenFilter(jwtTokenProvider, restAuthenticationEntryPoint)
        builder?.addFilterBefore(filter, UsernamePasswordAuthenticationFilter::class.java)
    }
}