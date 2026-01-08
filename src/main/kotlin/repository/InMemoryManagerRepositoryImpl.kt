package org.example.repository

import org.example.model.Manager
import javax.inject.Inject

class InMemoryManagerRepositoryImpl @Inject constructor() : ManagerRepository {
    val managers: MutableList<Manager> = mutableListOf(
        Manager(
            id = "M 001",
            name = "Smith",
            dateOfBirth = "01/01/1988",
            gender = "Male",
            email = "smith@gmail.com",
            phoneNumber = "0173335257",
            address = "Dhaka"
        )
    )

    override fun createManager(manager: Manager): Manager {
        managers.add(manager)
        return manager
    }
}