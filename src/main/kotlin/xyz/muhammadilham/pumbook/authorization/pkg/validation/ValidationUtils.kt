package xyz.muhammadilham.pumbook.authorization.pkg.validation

import jakarta.validation.ConstraintViolationException
import jakarta.validation.Validator
import org.springframework.stereotype.Component

@Component
class ValidationUtils(
    private val validator: Validator
) {

    fun validate(any: Any) {
        val result = validator.validate(any)
        if (result.isNotEmpty()) throw ConstraintViolationException(result)
    }
}