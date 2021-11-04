package com.example.data.model.repository

import com.example.data.model.User
import com.example.domain.repositories.UserRepository

class LocalUsersRepository : UserRepository {



    private val users = listOf(
        User(1,"jhoan", "jhoan@gmail.com", "123"),
        User(2,"carolina", "carolina@gmail.com", "123"),
        User(3,"juan", "juan@gmail.com", "123"),
        User(4,"andrea", "andrea@gmail.com", "123")
    )

    override fun getUsers(): List<User> {
        return users
    }

    override fun getUserById(id : Int): User? {
        return users.firstOrNull{it.id == id}
    }

    override fun createUser(user: User) : User{
        TODO("Not yet implemented")
    }

    override fun deleteUser(userId: Int) : Boolean {
        return true
    }

    override fun editUser(userId: Int): User? {
        TODO("Not yet implemented")
    }


}