package xyz.muhammadilham.pumbook.authorization.service

import xyz.muhammadilham.pumbook.authorization.entity.User
import xyz.muhammadilham.pumbook.authorization.model.AuthorizationResponse
import xyz.muhammadilham.pumbook.authorization.model.CreateUserRequest

interface AuthorizationService {
    fun createUser(createUserRequest: CreateUserRequest) : AuthorizationResponse

//    fun getUserByEmail(email: String): User
}