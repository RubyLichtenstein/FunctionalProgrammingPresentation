package com.rubylich.exmple

import arrow.core.Either
import arrow.core.flatMap
import arrow.core.getOrElse
import arrow.core.getOrHandle
import com.rubylich.exmple.PresentationEither.Error.*
import com.rubylich.exmple.PresentationEither.PresentationError.InvalidInput
import com.rubylich.exmple.PresentationEither.PresentationError.LoginFailed

/**
 * Created by rl98880 on 27/02/2018.
 */
class PresentationEither {
    init {
        login("", "")
        InvalidEmail
        InvalidPassword
        EmailNotExist
        WrongPassword
    }

    private fun login(email: String, password: String)
            : Either<Error, AccessToken> {
        TODO()
    }

    fun eitherMapFlatMap() {
//        var either: Either<String, String>
//
//        either = Either.right("Kotlin is awesome")
//            .map { it.toUpperCase() }
//
//        when (either) {
//            is Either.Left  -> print(either.a)
//            is Either.Right -> print(either.b)
//        }

        val either = Either.right("Kotlin is awesome")
            .flatMap {
                Either.cond(
                    !it.contains("Kotlin"),
                    { "Nice" },
                    { "Booo" }
                )
            }

        when (either) {
            is Either.Left  -> print(either.a)
            is Either.Right -> print(either.b)
        }
    }

    private fun getTokenOrNull(): String? =
        login("my@gmail.com", "password")
            .map { it.token }
            .getOrElse { null }

    private fun printLoginResult(): Any =
        login("my@gmail.com", "password")
            .map { "Token: " + it.token }
            .getOrHandle { error ->
                when (error) {
                    InvalidEmail    -> "Invalid email"
                    InvalidPassword -> "Invalid password"
                    EmailNotExist   -> "Email not exist"
                    WrongPassword   -> "Wrong password"
                }
            }.also { print(it) }

    sealed class PresentationError {
        object InvalidInput : PresentationError()
        object LoginFailed : PresentationError()
    }

    private fun mapBothSides() {
        val mappedLoginResult = login("my@gmail.com", "password")
            .map { it.token }
            .mapLeft { error ->
                when (error) {
                    InvalidEmail, InvalidPassword -> InvalidInput
                    EmailNotExist, WrongPassword  -> LoginFailed
                }
            }

        when (mappedLoginResult) {
            is Either.Left  -> when (mappedLoginResult.a) {
                InvalidInput -> showErrorMessageInvalidInput()
                LoginFailed  -> showErrorMessageLoginFaild()
            }
            is Either.Right -> showLoginSuccess(mappedLoginResult.b)
        }
    }

    private fun showErrorMessageLoginFaild() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun showErrorMessageInvalidInput() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    private fun loginPresenter() {
        val loginResult = login("me@gmail.com", "my_secret")
        when (loginResult) {
            is Either.Right -> showLoginSuccess(loginResult.b)
            is Either.Left  -> when (loginResult.a) {
                InvalidEmail    -> showErrorMessageInvalidEmail()
                InvalidPassword -> showErrorMessageInvalidPassword()
                EmailNotExist   -> showErrorMessageEmailNotExist()
                WrongPassword   -> showErrorMessageWrongPassword()
            }
        }
    }

    private fun showErrorMessageWrongPassword() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun showErrorMessageEmailNotExist() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun showErrorMessageInvalidPassword() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun showErrorMessageInvalidEmail() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun showLoginSuccess(b: AccessToken): Nothing {
        TODO()
    }

    private fun showLoginSuccess(b: String): Nothing {
        TODO()
    }

    data class AccessToken(val token: String)

    sealed class Error {
        object InvalidEmail : Error()
        object InvalidPassword : Error()
        object EmailNotExist : Error()
        object WrongPassword : Error()
    }
}