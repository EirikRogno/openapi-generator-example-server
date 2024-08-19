package no.webstep.openapi_example.api

import no.webstep.openapi_example.model.CreateUserRequest
import no.webstep.openapi_example.model.User
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component
import java.util.UUID.randomUUID

@Component
class UserApiImpl : UsersApiDelegate {

    val users: MutableMap<String, User> = mutableMapOf(
        "c07b8d6a-21dc-4d54-99d4-14084a0a42f0" to User(
            id = "c07b8d6a-21dc-4d54-99d4-14084a0a42f0",
            name = "Eirik",
            email = "a@b.no"
        ),
    )

    override fun createUsers(createUserRequest: CreateUserRequest): ResponseEntity<Unit> {
        val id = randomUUID().toString()
        users[id] = User(id = id, name = createUserRequest.name, email = createUserRequest.email)
        return ResponseEntity(HttpStatus.CREATED)
    }

    override fun getUserById(userId: String): ResponseEntity<User> {
        val user = users[userId] ?: return ResponseEntity.notFound().build()
        return ResponseEntity.ok(user)
    }

    override fun getUsers(limit: Int?): ResponseEntity<List<User>> {
        val users = users.values.toList()
        if (limit != null) {
            return ResponseEntity.ok(users.subList(0, limit))
        }
        return ResponseEntity.ok(users)
    }
}