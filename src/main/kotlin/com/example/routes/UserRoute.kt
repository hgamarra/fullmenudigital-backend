package com.example.routes

import com.example.data.model.User
import com.example.repository.LocalUsersRepository
import com.example.repository.UserRepository
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*

val repository: UserRepository = LocalUsersRepository()

fun Route.users() {

    get("/users") {
        val users = repository.getUsers()
        call.respond(
            HttpStatusCode.OK,
            users
        )
    }

    get("/users/{id}") {

        val id = call.parameters["id"]?.toIntOrNull()

        if(id == null){
            call.respond(HttpStatusCode.BadRequest, "Id parameter has to be a number")
            return@get
        }
        val user = repository.getUserById(id)

        if(user == null){
            call.respond(HttpStatusCode.NotFound, "Not found user for the id : $id")
            return@get
        }

        call.respond(HttpStatusCode.OK, user)

    }

    post("/users") {

        val userRequest = call.receive<User>()
        val user = repository.createUser(userRequest)
        call.respond(HttpStatusCode.Created, user)

    }

    put("/users/{id}"){

        val userId = call.parameters["id"]?.toIntOrNull()

        if(userId == null){
            call.respond(HttpStatusCode.BadGateway , "Id parameter has to be a number")
            return@put
        }

        val user = repository.getUserById(userId)


        if(user == null){
            call.respond(HttpStatusCode.NotFound , "user nor found with id : $userId")
            return@put
        }

        call.respond(HttpStatusCode(205, "Updated Successfully") , user)

    }

    delete("/users/{id}"){

        val userId = call.parameters["id"]?.toIntOrNull()

        if (userId == null){
            call.respond(HttpStatusCode.BadRequest , "id parameter has to be a number")
            return@delete
        }

        val deleted = repository.deleteUser(userId)

        if (deleted){
            call.respond(HttpStatusCode.OK)
        }else{
            call.respond(HttpStatusCode.NotFound, "found no user with the id : $userId")
        }

    }

}