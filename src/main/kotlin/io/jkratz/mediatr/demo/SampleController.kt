package io.jkratz.mediatr.demo

import io.jkratz.mediator.core.Mediator
import io.jkratz.mediator.spring.SpringMediator
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.util.UriComponentsBuilder
import java.util.concurrent.CompletableFuture

@RestController
class SampleController @Autowired constructor(private val mediator: Mediator) {

    @PostMapping("/command")
    fun runCommand(@RequestBody command: CreateUserCommand): ResponseEntity<Unit> {
        this.mediator.dispatch(command)
        return ResponseEntity.accepted().build()
    }

    @PostMapping("/request")
    fun runRequest(@RequestBody request: CreateUserRequest): ResponseEntity<Unit> {
        val id = this.mediator.dispatch(request)
        val uriBuilder = UriComponentsBuilder.fromPath("/user/{id}").buildAndExpand(id)
        return ResponseEntity.created(uriBuilder.toUri()).build()
    }

    @PostMapping("/commandAsync")
    fun runCommandAsync(@RequestBody command: CreateUserCommand): CompletableFuture<ResponseEntity<Void>> {
        return this.mediator.dispatchAsync(command)
                .thenApply { ResponseEntity<Void>(HttpStatus.ACCEPTED) }
                .exceptionally { ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR) }
    }

    @PostMapping("/requestAsync")
    fun runRequestAsync(@RequestBody request: CreateUserRequest): CompletableFuture<ResponseEntity<*>> {
        return this.mediator.dispatchAsync(request)
                .thenApply { id ->
                    val uriBuilder = UriComponentsBuilder.fromPath("/user/{id}").buildAndExpand(id)
                    ResponseEntity.created(uriBuilder.toUri()).build<Void>()
                }
    }
}