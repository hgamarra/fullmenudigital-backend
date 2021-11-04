package com.example.domain.usecases.category

import com.example.common.Either
import com.example.common.Failure
import com.example.domain.models.Category
import com.example.domain.repositories.CategoryRepository
import com.example.domain.usecases.UserCaseBase

class GetCategoriesUseCase(private val categoryRepository: CategoryRepository) :
    UserCaseBase<List<Category>, UserCaseBase.None>() {

    override suspend fun run(params: None): Either<Failure, List<Category>> = categoryRepository.getCategories()
}


