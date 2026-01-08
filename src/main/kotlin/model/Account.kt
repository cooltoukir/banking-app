package org.example.model

abstract class Account(
    val accountNumber: String,
    val userId: String,
    protected var currentBalance: Double
) {
    abstract val accountType: AccountType

    fun getBalance(): Double = currentBalance

    fun debit(amount: Double): Boolean {
        if (currentBalance < amount) return false.also { println("Insufficient balance!") }

        currentBalance -= amount
        return true
    }

    fun credit(amount: Double): Boolean {
        currentBalance += amount
        return true
    }
}
