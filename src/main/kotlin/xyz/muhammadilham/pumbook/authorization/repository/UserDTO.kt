package xyz.muhammadilham.pumbook.authorization.repository

import jakarta.persistence.*
import xyz.muhammadilham.pumbook.authorization.entity.User
import xyz.muhammadilham.pumbook.authorization.entity.UserRole
import xyz.muhammadilham.pumbook.authorization.entity.UserRoleConverter
import java.util.*

@Entity
@Table(name="users")
data class UserDTO(
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(name = "first_name")
    val firstName: String,

    @Column(name = "last_name")
    val lastName: String?,

    @Column(nullable = false, name = "role")
    @Convert(converter = UserRoleConverter::class)
    val role: UserRole,

    @Column(name = "email")
    val email: String,

    @Column(name = "password")
    val password: String,

    @Column(name = "country_code")
    val countryCode: String?,

    @Column(name = "phone_number")
    val phoneNumber: String?,

    @Column(name = "created_at")
    val createdAt: Date,

    @Column(name = "updated_at")
    val updatedAt: Date? = null,

    @Column(name = "deleted_at")
    val deletedAt: Date? = null
) {
    fun toEntityUser(): User {
        return User(
            id = this.id,
            firstName = this.firstName,
            lastName = this.lastName,
            role = this.role,
            email = this.email,
            password = this.password,
            countryCode = this.countryCode,
            phoneNumber = this.phoneNumber,
            createdAt = this.createdAt,
            updatedAt = this.updatedAt
        )
    }

    companion object {

        fun fromEntityUser(user: User): UserDTO {
            return UserDTO(
                id = user.id,
                firstName = user.firstName,
                lastName = user.lastName,
                role = user.role,
                email = user.email,
                password = user.password,
                countryCode = user.countryCode,
                phoneNumber = user.phoneNumber,
                createdAt = user.createdAt,
                updatedAt = user.updatedAt,
            )
        }
    }
}
