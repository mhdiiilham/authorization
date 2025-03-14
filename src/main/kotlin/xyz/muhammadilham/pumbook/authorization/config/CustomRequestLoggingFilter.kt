package xyz.muhammadilham.pumbook.authorization.config

import jakarta.servlet.http.HttpServletRequest
import org.springframework.web.filter.CommonsRequestLoggingFilter

class CustomRequestLoggingFilter : CommonsRequestLoggingFilter() {
    override fun beforeRequest(request: HttpServletRequest, message: String) {
        // No need to log request before handling
    }

    override fun afterRequest(request: HttpServletRequest, message: String) {
        // Mask sensitive fields before logging
        val sanitizedMessage = maskSensitiveData(message)
        logger.info("REQUEST: $sanitizedMessage")
    }

    private fun maskSensitiveData(requestBody: String): String {
        return requestBody.replace(
            Regex("\"password\"\\s*:\\s*\".*?\""),
            "\"password\":\"****\""
        )
    }
}