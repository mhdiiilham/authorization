package xyz.muhammadilham.pumbook.authorization.entity

import java.util.*

data class User(
    val id: Long = 0,
    val firstName: String,
    val lastName: String?,
    val role: UserRole,
    val email: String,
    val password: String,
    val countryCode: String?,
    val phoneNumber: String?,
    val createdAt: Date,
    val updatedAt: Date?,
)
