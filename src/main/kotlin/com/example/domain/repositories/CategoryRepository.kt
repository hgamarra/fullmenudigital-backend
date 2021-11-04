package com.example.domain.repositories

import com.example.common.Either
import com.example.common.Failure
import com.example.domain.models.Category
import javax.swing.text.ElementIterator

interface CategoryRepository {

    suspend fun createCategory(category: Category) : Either<Failure, Boolean>
    suspend fun getCategories() : Either<Failure, List<Category>>


}