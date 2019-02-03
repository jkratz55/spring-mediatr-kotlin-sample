package io.jkratz.mediatr.demo

import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface UserRepository: JpaRepository<User,UUID> {
}