package io.jkratz.mediatr.demo

import io.jkratz.mediator.core.Mediator
import io.jkratz.mediator.spring.SpringMediator
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.Bean

@SpringBootApplication
class SpringMediatrKotlinSampleApplication @Autowired constructor(val applicationContext: ApplicationContext) {

    @Bean
    fun mediator(): Mediator {
        return SpringMediator(applicationContext)
    }
}

fun main(args: Array<String>) {
    runApplication<SpringMediatrKotlinSampleApplication>(*args)
}

