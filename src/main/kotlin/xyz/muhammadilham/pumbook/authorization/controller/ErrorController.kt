package xyz.muhammadilham.pumbook.authorization.controller

import jakarta.validation.ConstraintViolationException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import xyz.muhammadilham.pumbook.authorization.model.WebResponse

@RestControllerAdvice
class ErrorController {

    @ExceptionHandler(value = [ConstraintViolationException::class])
    fun validationHandler(
        constraintViolationException: ConstraintViolationException
    ): ResponseEntity<WebResponse<Any, Any>> {
        val response = WebResponse<Any, Any>(
            statusCode = HttpStatus.BAD_REQUEST,
            message = constraintViolationException.message,
            data = null,
            error = constraintViolationException
        )
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response)
    }
}