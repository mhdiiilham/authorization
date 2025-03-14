package xyz.muhammadilham.pumbook.authorization.controller

import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import xyz.muhammadilham.pumbook.authorization.model.AuthorizationResponse
import xyz.muhammadilham.pumbook.authorization.model.CreateUserRequest
import xyz.muhammadilham.pumbook.authorization.model.WebResponse
import xyz.muhammadilham.pumbook.authorization.service.AuthorizationService
import xyz.muhammadilham.pumbook.authorization.pkg.validation.ValidationUtils

@RestController
class AuthorizationController(
    val authorizationService: AuthorizationService,
    val validationUtils: ValidationUtils,
) {
    @GetMapping(value = ["/api/v1"], produces = ["application/json"])
    fun rootHandler(): ResponseEntity<WebResponse<String, Any>> {
        val response = WebResponse<String, Any>(
            statusCode = HttpStatus.OK,
            message = "ok",
            data = "ok",
            error = null
        )

        return ResponseEntity.status(200).body(response)
    }

    @PostMapping(
        value = ["/api/v1/auth/signup"],
        consumes = [MediaType.APPLICATION_JSON_VALUE],
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    fun registerUser(
        @RequestBody body: CreateUserRequest
    ): ResponseEntity<WebResponse<AuthorizationResponse, Any>> {
        validationUtils.validate(body)
        val response = authorizationService.createUser(body)

        return ResponseEntity.status(201).body(WebResponse(
            statusCode = HttpStatus.CREATED,
            message = "created",
            data = response,
        ))
    }
}