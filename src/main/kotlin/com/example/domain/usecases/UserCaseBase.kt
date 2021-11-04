package com.example.domain.usecases

import com.example.common.Either
import com.example.common.Failure

abstract class UserCaseBase<out Type , in Params> where Type : Any{

    abstract suspend fun  run(params: Params) : Either<Failure, Type>

    suspend fun execute(params: Params, onResult: suspend (Either<Failure, Type>) -> Unit = {}) =
        onResult(run(params))

    class  None

}