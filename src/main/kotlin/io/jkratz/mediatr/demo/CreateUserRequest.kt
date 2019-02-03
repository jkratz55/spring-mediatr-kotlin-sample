package io.jkratz.mediatr.demo

import io.jkratz.mediator.core.Command
import io.jkratz.mediator.core.Request
import io.jkratz.mediator.core.RequestHandler
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.util.*
import javax.validation.constraints.NotBlank

data class CreateUserRequest(@field:NotBlank val userName: String,
                             @field:NotBlank val email: String,
                             @field:NotBlank val password: String): Request<UUID>

@Component
class CreateUserRequestHandler @Autowired constructor(val userRepository: UserRepository)
    : RequestHandler<CreateUserRequest,UUID> {

    private val logger: Logger = LoggerFactory.getLogger(javaClass)

    override fun handle(request: CreateUserRequest): UUID {
        logger.info("Request -> $request")
        val user = User(userName = request.userName,
                email = request.email,
                password = request.password)
        this.userRepository.save(user)
        return user.id
    }
}