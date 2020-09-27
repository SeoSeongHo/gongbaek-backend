package gb.gongbaek.v1.backend.config

import gb.gongbaek.v1.backend.config.jwt.JwtConfig
import gb.gongbaek.v1.backend.config.jwt.JwtTokenProvider
import gb.gongbaek.v1.backend.config.jwt.RestAuthenticationEntryPoint
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter

@Configuration
@EnableWebSecurity
class SecurityConfig(
        @Autowired private val jwtTokenProvider: JwtTokenProvider,
        @Autowired private val restAuthenticationEntryPoint: RestAuthenticationEntryPoint
): WebSecurityConfigurerAdapter() {

    override fun configure(http: HttpSecurity) {

        http
                .cors().disable()
                .csrf().disable()
                .antMatcher("/api/**").authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                .antMatchers("/api/v1/academy/**").hasAuthority("BUSINESS")
                .antMatchers("/api/v1/users/register/**").anonymous()
                .antMatchers("/api/v1/oauth/refresh").anonymous()
                .anyRequest().authenticated()
                .and()
                .apply(JwtConfig(jwtTokenProvider, restAuthenticationEntryPoint))
    }
}