package xyz.muhammadilham.pumbook.authorization.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository: JpaRepository<UserDTO, String> {

    fun findByEmailAndDeletedAtIsNull(email: String): UserDTO?
}