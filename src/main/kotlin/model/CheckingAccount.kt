package org.example.model

class CheckingAccount(
    accountNumber: String,
    userId: String,
    currentBalance: Double
) : Account(accountNumber, userId, currentBalance) {
    override val accountType = AccountType(name = "CHECKING", monthlyFee = 100.0, transferLimit = 50_000.0)
}