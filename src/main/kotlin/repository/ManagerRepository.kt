package org.example.repository

import org.example.model.Manager

interface ManagerRepository {
    fun createManager(manager: Manager): Manager
}