package xyz.muhammadilham.pumbook.authorization.pkg.security

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Component

@Component
class Bcrypt(
    private val encoder: BCryptPasswordEncoder
) {

    fun hashPassword(plainPassword: String): String {
        return encoder.encode(plainPassword)
    }

}