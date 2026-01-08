package org.example.repository

import org.example.model.User
import javax.inject.Inject

class InMemoryUserRepositoryImpl @Inject constructor() : UserRepository {
    private val users: MutableList<User> = mutableListOf(
        User(
            id = "U 001",
            name = "Toukir",
            dateOfBirth = "01/01/1995",
            gender = "Male",
            email = "toukir@gmail.com",
            phoneNumber = "01732435257",
            address = "Cumilla"
        ),
        User(
            id = "U 002",
            name = "Ahmed",
            dateOfBirth = "01/01/1998",
            gender = "Male",
            email = "ahmed@gmail.com",
            phoneNumber = "01730085257",
            address = "Chittagong"
        )
    )

    override fun createUser(user: User) {
        if (findById(user.id) != null) {
            println("${user.id} user already exists!")
        } else {
            users.add(user)
            println("${user.name} user created successfully!")
        }
    }

    override fun findById(id: String): User? {
        return users.find { it.id == id }
    }
}