package org.example.repository

import org.example.model.User

interface UserRepository {
    fun createUser(user: User)
    fun findById(id: String): User?
}