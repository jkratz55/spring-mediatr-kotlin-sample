package io.jkratz.mediatr.demo

import java.util.*
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "USER")
class User {

    @Id
    val id: UUID = UUID.randomUUID()
    val userName: String
    val email: String
    val password: String

    protected constructor() {
        this.userName = ""
        this.email = ""
        this.password = ""
    }

    constructor(userName: String, email: String, password: String) {
        this.userName = userName
        this.email = email
        this.password = password
    }

    override fun toString(): String {
        return "User(id=$id, userName='$userName', email='$email', password='$password')"
    }
}