package org.example.model

import org.example.exception.InsufficientFundsException

abstract class Account(
    val accountNumber: String,
    val userId: String,
    protected var currentBalance: Double
) {
    abstract val accountType: AccountType

    fun getBalance(): Double = currentBalance

    fun debit(amount: Double): Boolean {
        if (currentBalance < amount) throw InsufficientFundsException("Insufficient funds for the withdrawal!")

        currentBalance -= amount
        return true
    }

    fun credit(amount: Double): Boolean {
        currentBalance += amount
        return true
    }
}
