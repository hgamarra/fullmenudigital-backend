package com.example.data.model.repository

import com.example.common.Either
import com.example.common.Failure
import com.example.data.model.LocalDataSource
import com.example.domain.models.Category
import com.example.domain.repositories.CategoryRepository

class CategoryRepositoryImpl(private val localDataSource: LocalDataSource) : CategoryRepository {


    override suspend fun createCategory(category: Category): Either<Failure, Boolean> =
        try {
            Either.Right(localDataSource.createCategory())
        }catch (e : Exception){
            Either.Left(Failure.ServerError(e))
        }


    override suspend fun getCategories(): Either<Failure, List<Category>> =
        try {
            Either.Right(localDataSource.getCategories())
        }catch (e :Exception){
            Either.Left(Failure.ServerError(e))
        }


}