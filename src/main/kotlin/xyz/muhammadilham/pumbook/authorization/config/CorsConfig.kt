package xyz.muhammadilham.pumbook.authorization.config

import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class CorsConfig: WebMvcConfigurer {
    override fun addCorsMappings(registry: CorsRegistry) {
        registry.addMapping("/**")
            .allowedOrigins("http://localhost:3000", "https://yourdomain.com")
            .allowedMethods("*")
            .allowedHeaders("*")
            .allowCredentials(true) // Only works with specific origins
            .maxAge(3600)
    }
}