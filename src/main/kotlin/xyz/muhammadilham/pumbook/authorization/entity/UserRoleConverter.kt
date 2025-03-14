package xyz.muhammadilham.pumbook.authorization.entity

import jakarta.persistence.AttributeConverter
import jakarta.persistence.Converter

@Converter(autoApply = true)
class UserRoleConverter : AttributeConverter<UserRole, String> {

    override fun convertToDatabaseColumn(attribute: UserRole?): String {
        return attribute?.getStringValue() ?: throw IllegalArgumentException("UserRole cannot be null")
    }

    override fun convertToEntityAttribute(dbData: String?): UserRole {
        return dbData?.let { UserRole.parseUserRole(it) } ?: throw IllegalArgumentException("Invalid user role value")
    }
}
