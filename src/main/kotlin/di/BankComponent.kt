package org.example.di

import dagger.Component
import org.example.service.BankService
import javax.inject.Singleton

@Singleton
@Component(modules = [BankModule::class])
interface BankComponent {
    fun bankService(): BankService
}