package org.example.service

import org.example.model.Account
import org.example.model.User
import org.example.repository.AccountRepository
import org.example.repository.ManagerRepository
import org.example.repository.UserRepository
import javax.inject.Inject

class BankService @Inject constructor(
    private val userRepository: UserRepository,
    private val managerRepository: ManagerRepository,
    private val accountRepository: AccountRepository
) {

    fun createUser(user: User) = userRepository.createUser(user)

    fun getUserById(id: String): User? = userRepository.findById(id)

    fun createAccount(account: Account) = accountRepository.createAccount(account)

    fun getAccountByAccountNumber(accountNumber: String): Account? =
        accountRepository.findAccount(accountNumber)

    fun checkBalance(accountNumber: String): Double? = accountRepository.findBalance(accountNumber)

    fun transferMoney(senderAccountNumber: String, receiverAccountNumber: String, transferAmount: Double): Boolean {
        val withdraw = accountRepository.withdraw(senderAccountNumber, transferAmount)
        if (withdraw) {
            val deposit = accountRepository.deposit(receiverAccountNumber, transferAmount)
            return deposit
        } else {
            return false
        }
    }
}