package com.rubylich.exmple

import arrow.data.*
import com.rubylich.exmple.Option.None
import com.rubylich.exmple.Option.Some
import java.io.IOException

/**
 * Created by rl98880 on 27/02/2018.
 */
class PresentationTry {
    private fun mayThrowException() {

    }

    fun consume() {

        when (Try { mayThrowException() }) {
            is Try.Success -> print("Success")
            is Try.Failure -> print("Failure")
        }

//        when (Try {}) {
//            is Try.Success -> print(lotteryNumbers.value)
//            is Try.Failure -> print(lotteryNumbers.exception)
//        }
    }

    open class GeneralException : Exception()

    class NoConnectionException : GeneralException()

    class AuthorizationException : GeneralException()

    fun checkPermissions() {
        throw AuthorizationException()
    }

    fun getLotteryNumbersFromCloud(): List<String> {
        throw NoConnectionException()
    }

    fun getLotteryNumbers(): List<String> {
        checkPermissions()

        return getLotteryNumbersFromCloud()
    }

    fun theTraditionalWay() {
        try {
            getLotteryNumbers()
        } catch (e: NoConnectionException) {
            //...
        } catch (e: AuthorizationException) {
            //...
        }
    }

    fun theFunctionalWay() {
        val lotteryNumbers = Try { getLotteryNumbers() }

        lotteryNumbers.getOrDefault { emptyList() }

        lotteryNumbers.getOrElse { ex: Throwable -> emptyList() }

        lotteryNumbers.filter {
            it.size < 4
        }

        lotteryNumbers.map {
            it.size + 1
        }

        lotteryNumbers.recover { exception ->
            emptyList()
        }

        Try { getLotteryNumbers(Source.NETWORK) }.recoverWith {
            Try { getLotteryNumbers(Source.CACHE) }
        }

//        val lotteryNumbers = Try { getLotteryNumbers() }

        lotteryNumbers.fold(
            { emptyList<String>() },
            { it.filter { it.toIntOrNull() != null } })

        lotteryNumbers.transform(
            { Try { it.map { it.toInt() } } },
            { Try.pure(emptyList<Int>()) })

        when (lotteryNumbers) {
            is Try.Success -> print(lotteryNumbers.value)
            is Try.Failure -> print(lotteryNumbers.exception)
        }
    }

    enum class Source {
        CACHE, NETWORK
    }

    fun getLotteryNumbers(source: Source): List<String> {
        checkPermissions()

        return getLotteryNumbersFromCloud()
    }
}

class TryExample() {
    data class Transaction(val toAccount: String, val amount: Double)

    private fun getToAccountsFromNetwork(): List<String> = TODO()
    private fun getToAccountsFromDisk(): List<String> = TODO()
    private fun getTransactionsFromNetwork(): List<Transaction> = TODO()
    private fun getTransactionsFromNetwork(accounts: List<String>): List<Transaction> = TODO()
    private fun getTransactionsFromFromDisk(): List<Transaction> = TODO()

    //show flat map
    //show usage of getOrElse
    private fun tryMapAndFlatMapExample() {

        val mapResult = Try { 1 / 0 }
            .map { it + 1 }

        when (mapResult) {
            is Try.Success -> print(mapResult.value)
            is Try.Failure -> print(mapResult.exception)
        }

        val flatMapResult = Try { 4 / 2 }
            .flatMap { Try { it + 1 } }

        when (flatMapResult) {
            is Try.Success -> print(flatMapResult.value)
            is Try.Failure -> print(flatMapResult.exception)
        }
    }

    fun functionalWayGetOrElse(): List<String> =
        Try { getTransactionsFromNetwork() }
            .recoverWith { Try { getTransactionsFromFromDisk() } }
            .getOrElse { exception ->
                when (exception) {
                    is IOException -> emptyList()
                    else           -> emptyList()
                }
            }
            .filter { it.amount > 1000 }
            .map { it.toAccount }

    fun functionalFlatMap(): List<Transaction> =
        Try { getToAccountsFromNetwork() }
            .recoverWith { Try { getToAccountsFromDisk() } }
            .flatMap { Try { getTransactionsFromNetwork(it) } }
            .getOrDefault { emptyList() }

    fun functionalWay(): List<String> =
        Try { getTransactionsFromNetwork() }
            .recoverWith { Try { getTransactionsFromFromDisk() } }
            .getOrDefault { emptyList() }
            .filter { it.amount > 1000 }
            .map { it.toAccount }

    fun theUglyWay(): List<String> {
        var transactions: List<Transaction>

        try {
            transactions = getTransactionsFromNetwork()
        } catch (e: Exception) {
            try {
                transactions = getTransactionsFromFromDisk()
            } catch (e: Exception) {
                transactions = emptyList()
            }
        }

        var toAccounts = mutableListOf<String>()

        for (transaction in transactions) {
            if (transaction.amount > 1000)
                toAccounts.add(transaction.toAccount)
        }

        return toAccounts
    }
}

class TryBoxingAndUnBoxing {
    //number 1
    //boxing

    fun sumThree(n: Int) = n + 3

    fun doMap() {
        Some(2).map(::sumThree)

        val none: Option<Int> = None
        none.map { it + 3 }
        // => None

        val option: Option<Int> = someCallThatMightReturnNone()
        option.map { it + 3 }
        // => None

//        val post = Post.findByID(1)
//        return post?.title
//
//        findPost(1).map(::getPostTitle)

        Some({ a: Int -> a + 3 }).apply(Some(2))

        Some(3).flatMap(::half)
        // None
        Some(4).flatMap(::half)
        // Some(2)
        None.flatMap(::half)
        // None

        Some(20) flatMap ::half flatMap ::half flatMap ::half
    }

    fun half(a: Int) = when {
        a % 2 == 0 -> Some(a / 2)
        else       -> None
    }


    private fun someCallThatMightReturnNone(): Option<Int> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    // => Some(5)
    //Option.Some(2).map(::sumThree)
}

//sealed class Try<out A> {
//    companion object {
//
//        inline operator fun <A> invoke(f: () -> A)
//                : Try<A> =
//            try {
//                Success(f())
//            } catch (e: Throwable) {
//                Failure(e)
//            }
//    }
//
//    data class Success<out A>(val value: A) : Try<A>()
//
//    data class Failure<out A>(val exception: Throwable) : Try<A>()
//}