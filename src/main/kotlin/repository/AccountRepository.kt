package org.example.repository

import org.example.model.Account

interface AccountRepository {
    fun createAccount(account: Account)
    fun findAccount(accountNumber: String): Account?
    fun findBalance(accountNumber: String): Double?
    fun deposit(receiverAccountNumber: String, transferAmount: Double): Boolean
    fun withdraw(senderAccountNumber: String, transferAmount: Double): Boolean
}