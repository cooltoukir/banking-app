package org.example.di

import dagger.Module
import dagger.Provides
import org.example.repository.AccountRepository
import org.example.repository.InMemoryAccountRepositoryImpl
import org.example.repository.InMemoryManagerRepositoryImpl
import org.example.repository.InMemoryUserRepositoryImpl
import org.example.repository.ManagerRepository
import org.example.repository.UserRepository
import javax.inject.Singleton

@Module
class BankModule {

    @Provides
    @Singleton
    fun provideUserRepository(): UserRepository {
        return InMemoryUserRepositoryImpl()
    }

    @Provides
    @Singleton
    fun provideManagerRepository(): ManagerRepository {
        return InMemoryManagerRepositoryImpl()
    }

    @Provides
    @Singleton
    fun provideAccountRepository(): AccountRepository {
        return InMemoryAccountRepositoryImpl()
    }
}