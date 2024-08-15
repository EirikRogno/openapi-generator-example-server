package no.webstep.openapi_example.api

import no.webstep.openapi_example.model.User
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component

@Component
class UserApiImpl : UsersApiDelegate {

    val users: MutableMap<Long, User> = mutableMapOf(
        1L to User(id = 1L, name = "Eirik", email = "a@b.no"),
    )

    override fun createUsers(user: User): ResponseEntity<Unit> {
        users[user.id] = user
        return ResponseEntity(HttpStatus.CREATED)
    }

    override fun getUserById(userId: String): ResponseEntity<User> {
        val user = users[userId.toLong()] ?: return ResponseEntity.notFound().build()
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