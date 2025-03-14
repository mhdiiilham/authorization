package xyz.muhammadilham.pumbook.authorization.pkg.security

data class TokenClaims(
    val userId: String,
    val email: String,
    val role: String,
)
