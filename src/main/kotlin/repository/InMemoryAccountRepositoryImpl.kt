package org.example.repository

import org.example.exception.DepositException
import org.example.exception.WithdrawalException
import org.example.model.Account
import org.example.model.SavingsAccount
import javax.inject.Inject

class InMemoryAccountRepositoryImpl @Inject constructor() : AccountRepository {
//    val accounts = mutableListOf<Account>()

    private val accounts: MutableList<Account> = mutableListOf(
        SavingsAccount(
            accountNumber = "A 001",
            userId = "U 001",
            currentBalance = 50000.0
        ),
        SavingsAccount(
            accountNumber = "A 002",
            userId = "U 002",
            currentBalance = 6000.0
        )
    )

    override fun createAccount(account: Account) {
        if (findAccount(account.accountNumber) != null) {
            println("${account.accountNumber} account already exists!")
        } else {
            accounts.add(account)
            println("${account.accountNumber} account created successfully!")
        }
    }

    override fun findAccount(accountNumber: String): Account? {
        return accounts.find { it.accountNumber == accountNumber }
    }

    override fun findBalance(accountNumber: String): Double? {
        val foundAccount = findAccount(accountNumber)
        if (foundAccount != null) {
            return foundAccount.getBalance()
        } else {
            println("No account found for $accountNumber!")
            return null
        }
    }

    override fun withdraw(senderAccountNumber: String, transferAmount: Double): Boolean {
        val account = findAccount(senderAccountNumber)
            ?: return false

        if (transferAmount > account.accountType.transferLimit) {
            println("Transfer limit(${account.accountType.transferLimit}) exceeded!")
            return false
        }

        return if (account.debit(transferAmount)) {
            true
        } else {
            throw WithdrawalException("Withdrawal failed!")
        }
    }

    override fun deposit(receiverAccountNumber: String, transferAmount: Double): Boolean {
        val account = findAccount(receiverAccountNumber)
            ?: return false

        return if (account.credit(transferAmount)) {
            true
        } else {
            throw DepositException("Deposit failed!")
        }
    }
}