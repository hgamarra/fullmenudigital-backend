package com.example.domain.usecases.category

import com.example.common.Either
import com.example.common.Failure
import com.example.domain.models.Category
import com.example.domain.repositories.CategoryRepository
import com.example.domain.usecases.UserCaseBase

class CreateCategoryUseCase(
    private val categoryRepository: CategoryRepository
) : UserCaseBase<Boolean, CreateCategoryUseCase.Params>() {


    data class Params(val category: Category)

    override suspend fun run(params: Params): Either<Failure, Boolean> =
        categoryRepository.createCategory(params.category)

}