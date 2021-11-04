package com.example.routes

import com.example.common.Failure
import com.example.common.SuccessfulDTO
import com.example.common.Util
import com.example.data.model.User
import com.example.data.repository.LocalUsersRepository
import com.example.domain.models.Category
import com.example.domain.repositories.UserRepository
import com.example.domain.usecases.UserCaseBase
import com.example.domain.usecases.category.CreateCategoryUseCase
import com.example.domain.usecases.category.GetCategoriesUseCase
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import org.slf4j.Logger
import org.slf4j.LoggerFactory

val repository: UserRepository = LocalUsersRepository()
private val logger: Logger = LoggerFactory.getLogger("UserRoute ----->")
private val util: Util = Util()


fun Route.categoryRouting(
    createCategoryUseCase: CreateCategoryUseCase,
    getCategoriesUseCase: GetCategoriesUseCase
) {


    get("/categories") {
        getCategoriesUseCase.execute(
           UserCaseBase.None()
        )
        {
            it.either({ failure ->
                util.handleError(failure = failure, call, logger)
            }, { categories ->
                call.respond(HttpStatusCode.OK, categories)
            })
        }
    }

    post("/categories") {

        try {
            val categoryRequest: Category? = call.receiveOrNull()
            categoryRequest?.let { category ->
                createCategoryUseCase.execute(CreateCategoryUseCase.Params(category)) {
                    it.either({ failure ->
                        util.handleError(failure = failure, call, logger)
                    }, { response ->
                        call.respond(HttpStatusCode.OK, SuccessfulDTO(response))
                    })
                }
            } ?: util.handleError(Failure.BadRequest(Util.SERVER_ERROR_MESSAGE), call, logger)
        } catch (e: Exception) {
            util.handleError(Failure.BadRequest(e.message ?: ""), call, logger)
        }

    }

    get("/users") {
        val users = repository.getUsers()
        call.respond(
            HttpStatusCode.OK,
            users
        )
    }

    get("/users/{id}") {

        val id = call.parameters["id"]?.toIntOrNull()

        if (id == null) {
            call.respond(HttpStatusCode.BadRequest, "Id parameter has to be a number")
            return@get
        }
        val user = repository.getUserById(id)

        if (user == null) {
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

    put("/users/{id}") {

        val userId = call.parameters["id"]?.toIntOrNull()

        if (userId == null) {
            call.respond(HttpStatusCode.BadGateway, "Id parameter has to be a number")
            return@put
        }

        val user = repository.getUserById(userId)


        if (user == null) {
            call.respond(HttpStatusCode.NotFound, "user nor found with id : $userId")
            return@put
        }

        call.respond(HttpStatusCode(205, "Updated Successfully"), user)

    }

    delete("/users/{id}") {

        val userId = call.parameters["id"]?.toIntOrNull()

        if (userId == null) {
            call.respond(HttpStatusCode.BadRequest, "id parameter has to be a number")
            return@delete
        }

        val deleted = repository.deleteUser(userId)

        if (deleted) {
            call.respond(HttpStatusCode.OK)
        } else {
            call.respond(HttpStatusCode.NotFound, "found no user with the id : $userId")
        }

    }

}