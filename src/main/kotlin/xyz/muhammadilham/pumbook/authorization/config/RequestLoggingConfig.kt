package xyz.muhammadilham.pumbook.authorization.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class RequestLoggingConfig {

    @Bean
    fun logFilter(): CustomRequestLoggingFilter {
        val filter = CustomRequestLoggingFilter()
        filter.setIncludeQueryString(true)
        filter.setIncludePayload(true)
        filter.setMaxPayloadLength(10000)
        filter.setIncludeHeaders(true)
        filter.setAfterMessagePrefix("REQUEST DATA: ")
        return filter
    }
}