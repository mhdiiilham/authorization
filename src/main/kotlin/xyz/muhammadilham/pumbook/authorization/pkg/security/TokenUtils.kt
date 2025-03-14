package xyz.muhammadilham.pumbook.authorization.pkg.security

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.exceptions.JWTVerificationException
import org.springframework.stereotype.Component
import xyz.muhammadilham.pumbook.authorization.config.jwtSecret
import java.time.Instant
import java.util.*

@Component
class TokenUtils {
    private val secretKey = jwtSecret
    private val algorithm = Algorithm.HMAC256(secretKey)
    private val issuer = "authorization-service"

    fun generateToken(userId: Long, email: String, userRole: String): String {
        val now = Instant.now()

        return JWT.create()
            .withSubject(userId.toString())
            .withIssuer(issuer)
            .withClaim("id", userId)
            .withClaim("email", email)
            .withClaim("role", userRole)
            .withIssuedAt(Date.from(now))
            .withExpiresAt(Date.from(now.plusSeconds(604800))) // 7 days
            .sign(algorithm)
    }

    fun decodeToken(token: String): TokenClaims {
        return try {
            val verifier = JWT.require(algorithm)
                .withIssuer(issuer)
                .build()

            val decodedJWT = verifier.verify(token)

            TokenClaims(
                userId = decodedJWT.getClaim("id").asString(),
                email = decodedJWT.getClaim("email").asString(),
                role = decodedJWT.getClaim("role").asString()
            )
        } catch (e: JWTVerificationException) {
            throw RuntimeException("Invalid access token", e)
        }
    }
}
