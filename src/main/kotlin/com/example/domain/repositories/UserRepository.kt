package com.example.repository

import com.example.data.model.User

interface UserRepository {

    fun getUsers() : List<User>

    fun getUserById(id : Int) : User?

    fun createUser(user : User) : User

    fun deleteUser(userId : Int) : Boolean

    fun editUser(userId : Int) : User?


}