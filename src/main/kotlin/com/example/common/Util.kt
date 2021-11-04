package com.example.common

import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*
import org.slf4j.Logger
import org.slf4j.LoggerFactory

 class Util {

    companion object  {
        const val SERVER_ERROR_MESSAGE = "Bad Request"
        const val SERVER_ERROR_CODE = "500"
        const val BAD_REQUEST_ERROR_CODE = "401"

    }

     suspend fun handleError(failure: Failure, call: ApplicationCall, logger: Logger) {
        when (failure) {
            is Failure.ServerError -> {
                logger.error(failure.e.message)
                call.respond(
                    HttpStatusCode.InternalServerError,
                    ErrorDTO(
                        SERVER_ERROR_CODE,
                        failure.e.message ?: ""
                    )
                )
            }
            is Failure.BadRequest -> {
                logger.error(failure.message)
                call.respond(
                    HttpStatusCode.InternalServerError,
                    ErrorDTO(
                        BAD_REQUEST_ERROR_CODE,
                        failure.message
                    )
                )
            }

        }
    }

}