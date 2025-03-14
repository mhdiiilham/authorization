package xyz.muhammadilham.pumbook.authorization.entity

enum class UserRole {
    SuperAdmin,
    Organizer,
    Host,
    Guest;

    fun getStringValue(): String {
        return name.replace(Regex("([a-z])([A-Z])"), "$1_$2").lowercase()
    }

    companion object {
        fun parseUserRole(value: String): UserRole {
            return entries.first { it.getStringValue() == value }
        }
    }
}

