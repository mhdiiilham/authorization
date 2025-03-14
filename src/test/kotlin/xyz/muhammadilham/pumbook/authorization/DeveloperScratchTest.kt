package xyz.muhammadilham.pumbook.authorization

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.springframework.data.jpa.repository.JpaRepository
import xyz.muhammadilham.pumbook.authorization.pkg.security.TokenUtils
import xyz.muhammadilham.pumbook.authorization.repository.UserDTO
import xyz.muhammadilham.pumbook.authorization.repository.UserRepository
import xyz.muhammadilham.pumbook.authorization.service.AuthorizationService
import xyz.muhammadilham.pumbook.authorization.service.impl.AuthorizationServiceImpl
import kotlin.test.Ignore

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class DeveloperScratchTest {

    @Test
    fun accessToken() {
        val tokenUtils = TokenUtils()
        val accessToken = tokenUtils.generateToken("1", "hi@muhammadilham.xyz", "super_admin")
        val tokenClaims = tokenUtils.decodeToken(accessToken)
        println("accessToken: $accessToken")
        println("token claims: $tokenClaims")
    }
}