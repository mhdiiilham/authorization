package xyz.muhammadilham.pumbook.authorization.model

import org.springframework.http.HttpStatusCode

data class WebResponse<T, E>(
    val statusCode: HttpStatusCode,
    val message: String?,
    val data: T?,
    val error: E? = null
)
