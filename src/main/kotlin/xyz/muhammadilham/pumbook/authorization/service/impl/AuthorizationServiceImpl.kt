package xyz.muhammadilham.pumbook.authorization.service.impl

import org.springframework.stereotype.Service
import xyz.muhammadilham.pumbook.authorization.entity.User
import xyz.muhammadilham.pumbook.authorization.entity.UserRole
import xyz.muhammadilham.pumbook.authorization.model.AuthorizationResponse
import xyz.muhammadilham.pumbook.authorization.model.CreateUserRequest
import xyz.muhammadilham.pumbook.authorization.repository.UserRepository
import xyz.muhammadilham.pumbook.authorization.pkg.security.Bcrypt
import xyz.muhammadilham.pumbook.authorization.pkg.security.TokenUtils
import xyz.muhammadilham.pumbook.authorization.repository.UserDTO
import xyz.muhammadilham.pumbook.authorization.service.AuthorizationService
import java.util.Date

@Service
class AuthorizationServiceImpl(
    private val userRepository: UserRepository,
    private val security: Bcrypt,
    private val tokenUtils: TokenUtils,
): AuthorizationService {
    override fun createUser(createUserRequest: CreateUserRequest): AuthorizationResponse {

        val user = User(
            firstName = createUserRequest.firstName,
            lastName = createUserRequest.lastName,
            role = UserRole.SuperAdmin,
            email = createUserRequest.email,
            password = security.hashPassword(createUserRequest.password),
            countryCode = null,
            phoneNumber = null,
            createdAt = Date(),
            updatedAt = Date(),
        )

        val createdUser = userRepository
            .save(UserDTO.fromEntityUser(user))
            .toEntityUser()

        val accessToken = tokenUtils
            .generateToken(
                userId = createdUser.id,
                email = createdUser.email,
                userRole = createdUser.role.getStringValue()
            )

        return AuthorizationResponse(
            email = createdUser.email,
            accessToken = accessToken,
            expiresAt = "",
            role = createdUser.role.getStringValue()
        )
    }
}