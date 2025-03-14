package xyz.muhammadilham.pumbook.authorization.model

import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
data class CreateUserRequest(
    @field:NotBlank(message = "First name is required!")
    val firstName: String,

    val lastName: String?,

    @field:Email
    val email: String,

    val phoneNumber: String?,

    @field:NotBlank
    val password: String,

    val role: String
)
