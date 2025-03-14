package xyz.muhammadilham.pumbook.authorization.model

import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
data class AuthorizationResponse (
    val email: String,
    val accessToken: String,
    val expiresAt: String,
    val role: String
)