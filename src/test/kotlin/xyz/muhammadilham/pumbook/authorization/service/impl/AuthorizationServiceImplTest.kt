package xyz.muhammadilham.pumbook.authorization.service.impl

import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import xyz.muhammadilham.pumbook.authorization.entity.User
import xyz.muhammadilham.pumbook.authorization.entity.UserRole
import xyz.muhammadilham.pumbook.authorization.model.AuthorizationResponse
import xyz.muhammadilham.pumbook.authorization.model.CreateUserRequest
import xyz.muhammadilham.pumbook.authorization.pkg.security.Bcrypt
import xyz.muhammadilham.pumbook.authorization.pkg.security.TokenUtils
import xyz.muhammadilham.pumbook.authorization.repository.UserDTO
import xyz.muhammadilham.pumbook.authorization.repository.UserRepository
import java.util.*


class AuthorizationServiceImplTest {
    private lateinit var authorizationService: AuthorizationServiceImpl
    private val userRepository: UserRepository = mockk()
    private val security: Bcrypt = mockk()
    private val tokenUtils: TokenUtils = mockk()


    @BeforeEach
    fun setup() {
        authorizationService = AuthorizationServiceImpl(userRepository, security, tokenUtils)
    }

    @Test
    fun `should create super_admin user and return authorization response`() {

        // Given
        val request = CreateUserRequest(
            firstName = "John",
            lastName = "Doe",
            email = "john.doe@example.com",
            password = "password123",
            phoneNumber = "6289658876167",
            role = "super_admin"
        )

        val hashedPassword = "hashedPassword123"
        val user = User(
            firstName = request.firstName,
            lastName = request.lastName,
            role = UserRole.SuperAdmin,
            email = request.email,
            password = hashedPassword,
            countryCode = null,
            phoneNumber = null,
            createdAt = Date(),
            updatedAt = Date()
        )

        val userDTO = UserDTO.fromEntityUser(user)
        val savedUserDTO = userDTO.copy(id = 1) // Simulate database save
        val savedUser = savedUserDTO.toEntityUser()

        val expectedToken = "mockedJwtToken"

        every { security.hashPassword(request.password) } returns hashedPassword
        every { userRepository.save(match {
            it.firstName == userDTO.firstName &&
                    it.lastName == userDTO.lastName &&
                    it.email == userDTO.email &&
                    it.role == userDTO.role &&
                    it.password == userDTO.password &&
                    it.countryCode == userDTO.countryCode &&
                    it.phoneNumber == userDTO.phoneNumber
        }) } returns savedUserDTO
        every { tokenUtils.generateToken(1, savedUser.email, savedUser.role.getStringValue()) } returns expectedToken

        // When
        val response: AuthorizationResponse = authorizationService.createUser(request)

        // Then
        assertEquals(savedUser.email, response.email)
        assertEquals(savedUser.role.getStringValue(), response.role)
        assertEquals(expectedToken, response.accessToken)

        // verify mock were called
        verify { security.hashPassword(request.password) }
        every { userRepository.save(match {
            it.firstName == userDTO.firstName &&
                    it.lastName == userDTO.lastName &&
                    it.email == userDTO.email &&
                    it.role == userDTO.role &&
                    it.password == userDTO.password &&
                    it.countryCode == userDTO.countryCode &&
                    it.phoneNumber == userDTO.phoneNumber
        }) } returns savedUserDTO
        verify { tokenUtils.generateToken(1, savedUser.email, savedUser.role.getStringValue()) }
    }
}