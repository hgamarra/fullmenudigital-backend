package com.example.data.model

import com.example.domain.models.Category
import com.google.cloud.firestore.Firestore
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine


class LocalDataSource(private val db: Firestore) {


    suspend fun createCategory() : Boolean {
        return try {
            true
        }catch (e: Exception){
            false
        }
    }

    suspend fun getCategories() : List<Category> {
        try {
            return  listOf()
        }catch (e :Exception){
            return emptyList()
        }
    }

}