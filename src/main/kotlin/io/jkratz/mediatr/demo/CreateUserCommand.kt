package io.jkratz.mediatr.demo

import io.jkratz.mediator.core.Command
import io.jkratz.mediator.core.CommandHandler
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import javax.validation.constraints.NotBlank

data class CreateUserCommand(@field:NotBlank val userName: String,
                             @field:NotBlank val email: String,
                             @field:NotBlank val password: String): Command

@Component
class CreateUserCommandHandler @Autowired constructor(private val userRepository: UserRepository)
    : CommandHandler<CreateUserCommand> {

    private val logger: Logger = LoggerFactory.getLogger(javaClass)

    override fun handle(command: CreateUserCommand) {
        logger.info("Command -> $command")
        val user = User(userName = command.userName,
                email = command.email,
                password = command.password)
        this.userRepository.save(user)
    }
}