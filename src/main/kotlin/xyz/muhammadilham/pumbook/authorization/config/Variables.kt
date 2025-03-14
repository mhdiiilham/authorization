package xyz.muhammadilham.pumbook.authorization.config

import io.github.cdimascio.dotenv.Dotenv

val dotEnv: Dotenv = Dotenv.load()
val jwtSecret: String = dotEnv["JWT_SECRET"]