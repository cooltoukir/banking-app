package org.example

import org.example.di.DaggerBankComponent
import org.example.exception.DepositException
import org.example.exception.InsufficientFundsException
import org.example.exception.WithdrawalException
import org.example.model.CheckingAccount
import org.example.model.SavingsAccount
import org.example.model.User
import org.example.service.BankService
import kotlin.system.exitProcess

fun main() {

    val bankService = DaggerBankComponent
        .create()
        .bankService()

    println("\n=== Welcome to Banking app ===")

    while (true) {
        println("\nMain login menu")
        println("1. Manager")
        println("2. User")
        println("3. Exit")
        print("\nSelect access level: ")

        when (readlnOrNull()) {
            "1" -> showManagerMenu(bankService)

            "2" -> showUserMenu(bankService)

            "3" -> return
        }
    }
}

fun showManagerMenu(bankService: BankService) {
    while (true) {
        println("\nManager menu")
        println("1. Create user")
        println("2. Create account")
        println("3. Search user")
        println("4. Search account")
        println("5. Back to login menu")
        println("6. Exit")
        print("\nSelect from manager menu: ")

        when (readlnOrNull()) {
            "1" -> createUser(bankService)

            "2" -> createAccount(bankService)

            "3" -> searchUser(bankService)

            "4" -> searchAccount(bankService)

            "5" -> return

            "6" -> exitProcess(0)
        }
    }
}

fun createUser(bankService: BankService) {
    println("\nCreate user")
    print("Enter user id: ")
    val userId = readln()
    val userExist = bankService.getUserById(userId)
    if (userExist != null) {
        print("$userId user is already exists!\n")
        return
    }
    print("Enter user name: ")
    val userName = readln()
    print("Enter user DOB: ")
    val dateOfBirth = readln()
    print("Enter user gender: ")
    val gender = readln()
    print("Enter user email: ")
    val email = readln()
    print("Enter user phone number: ")
    val phoneNumber = readln()
    print("Enter user address: ")
    val address = readln()

    val user = User(
        id = userId,
        name = userName,
        dateOfBirth = dateOfBirth,
        gender = gender,
        email = email,
        phoneNumber = phoneNumber,
        address = address
    )
    bankService.createUser(user)
}

fun createAccount(bankService: BankService) {
    println("\nCreate account")
    print("Enter account number: ")
    val accountNumber = readln()
    val account = bankService.getAccountByAccountNumber(accountNumber)
    if (account != null) {
        print("$accountNumber account is already exists!\n")
        return
    }
    print("Enter user id: ")
    val userId = readln()
    val userExist = bankService.getUserById(userId)
    if (userExist == null) {
        print("$userId user not exists!\n")
        return
    }
    print("Enter initial balance: ")
    val initialBalanceInput = readln()

    val initialBalance = initialBalanceInput.toDoubleOrNull() ?: run {
        println("Invalid balance entered. Please try again.")
        return
    }

    print("Select account type (1 for Checking, 2 for Savings): ")
    val accountTypeChoice = readln()

    val newAccount = when (accountTypeChoice) {
        "1" -> CheckingAccount(
            accountNumber = accountNumber,
            userId = userId,
            currentBalance = initialBalance
        )

        "2" -> SavingsAccount(
            accountNumber = accountNumber,
            userId = userId,
            currentBalance = initialBalance
        )

        else -> {
            println("Invalid account type selection. Please try again.")
            return
        }
    }

    bankService.createAccount(newAccount)
}

fun searchUser(bankService: BankService) {
    print("Enter user id: ")
    val userId = readln()
    val user = bankService.getUserById(userId)
    if (user != null) {
        print("\nUser found!\nName: ${user.name}\nDOB: ${user.dateOfBirth}\nGender: ${user.gender}\nEmail: ${user.email}\nNumber: ${user.phoneNumber}\nAddress: ${user.address}\n")
    } else {
        print("User not found!\n")
    }
}

fun searchAccount(bankService: BankService) {
    print("Enter account number: ")
    val accountNumber = readln()
    val account = bankService.getAccountByAccountNumber(accountNumber)
    if (account != null) {
        print("\nAccount found! \nAccount number: ${account.accountNumber}\nUser id: ${account.userId}\nCurrent balance: ${account.getBalance()}\nAccount Type: ${account.accountType.name}\n")
    } else {
        print("Account not found!\n")
    }
}

fun showUserMenu(bankService: BankService) {
    while (true) {
        println("\nUser menu")
        println("1. Check balance")
        println("2. Transfer balance")
        println("3. Back to login menu")
        println("4. Exit")
        print("\nSelect from user menu: ")

        when (readlnOrNull()) {
            "1" -> checkBalance(bankService)

            "2" -> transferBalance(bankService)

            "3" -> return

            "4" -> exitProcess(0)
        }
    }
}

fun checkBalance(bankService: BankService) {
    println("\nCheck balance")
    print("Enter your user id: ")
    val userId = readln()
    val userExist = bankService.getUserById(userId)
    if (userExist == null) {
        print("$userId user not exists!\n")
        return
    }

    print("Enter your account number: ")
    val accountNumber = readln()
    val account = bankService.getAccountByAccountNumber(accountNumber)
    if (account == null) {
        print("$accountNumber account not exists!\n")
        return
    }

    if (account.userId != userId) {
        print("This account is not yours!\n")
        return
    }

    val currentBalance = bankService.checkBalance(accountNumber)

    if (currentBalance != null) {
        println("\nCurrent balance(BDT): $currentBalance")
    }
}

fun transferBalance(bankService: BankService) {
    println("\nTransfer balance")
    print("Enter your user id: ")
    val senderUserId = readln()
    val userExist = bankService.getUserById(senderUserId)
    if (userExist == null) {
        print("$senderUserId user not exists!\n")
        return
    }
    print("Enter your account number: ")
    val senderAccountNumber = readln()
    val senderAccount = bankService.getAccountByAccountNumber(senderAccountNumber)
    if (senderAccount == null) {
        print("$senderAccountNumber account not exists!\n")
        return
    }
    if (senderAccount.userId != senderUserId) {
        print("This account is not yours!\n")
        return
    }
    print("Enter account number to transfer: ")
    val receiverAccountNumber = readln()
    val receiverAccount = bankService.getAccountByAccountNumber(receiverAccountNumber)
    if (receiverAccount == null) {
        print("$receiverAccountNumber account not exists!\n")
        return
    }
    if (senderAccountNumber == receiverAccountNumber) {
        println("Can't transfer to same account number!\n")
        return
    }
    print("Enter amount to transfer: ")
    val transferAmountInput = readln()

    val transferAmount = transferAmountInput.toDoubleOrNull() ?: run {
        println("Invalid amount entered. Please try again.")
        return
    }

    if (transferAmount <= 0) {
        println("Transfer amount must be greater than zero!\n")
        return
    }

    print("$transferAmount will we transfer to $receiverAccountNumber\n")
    print("Enter 1 for Yes and 2 for No: ")
    val transferConfirm = readln()

    when (transferConfirm) {
        "1" -> {
            try {
                val transfer =
                    bankService.transferMoney(senderAccountNumber, receiverAccountNumber, transferAmount)
                if (transfer) {
                    println("Transfer money(BDT) $transferAmount successful!\n")
                }
            } catch (e: InsufficientFundsException) {
                println(e.message)
            } catch (e: WithdrawalException) {
                println(e.message)
            } catch (e: DepositException) {
                println(e.message)
            }
        }

        "2" -> {
            println()
            return
        }

        else -> {
            println("Invalid input. Please try again.")
            return
        }
    }
}
