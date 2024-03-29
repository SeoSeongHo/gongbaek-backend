package gb.gongbaek.v1.backend.config.jwt

import gb.gongbaek.v1.backend.exception.JwtBlackListException
import gb.gongbaek.v1.backend.exception.JwtValidationException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.GenericFilterBean
import javax.servlet.FilterChain
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class JwtTokenFilter(
        private val jwtTokenProvider: JwtTokenProvider,
        private val restAuthenticationEntryPoint: RestAuthenticationEntryPoint
): GenericFilterBean() {

    override fun doFilter(request: ServletRequest?, response: ServletResponse?, chain: FilterChain?) {
        request ?: throw Exception()

        try{
            val token: String? = jwtTokenProvider.resolveToken(request as HttpServletRequest)
            token?.let{
                if(jwtTokenProvider.validateToken(token)){
                    val auth: Authentication = jwtTokenProvider.getAuthentication(token)
                    SecurityContextHolder.getContext().authentication = auth
                }
            }
            chain?.doFilter(request, response)
        }
        catch(authenticationException: AuthenticationException){
            SecurityContextHolder.clearContext()
            restAuthenticationEntryPoint.commence(request as HttpServletRequest, response as HttpServletResponse, authenticationException)
        }
    }
}