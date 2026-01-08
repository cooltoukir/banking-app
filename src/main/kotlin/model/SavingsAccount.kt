package org.example.model

class SavingsAccount(
    accountNumber: String,
    userId: String,
    currentBalance: Double
) : Account(accountNumber, userId, currentBalance) {
    override val accountType = AccountType(name = "SAVINGS", monthlyFee = 50.0, transferLimit = 20_000.0)
}