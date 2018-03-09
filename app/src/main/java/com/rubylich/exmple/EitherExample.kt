package com.rubylich.exmple

import arrow.core.*
import arrow.core.Either
import arrow.syntax.either.left
import arrow.syntax.either.right

/**
 * Created by rl98880 on 02/03/2018.
 */
class EitherExample {
//    val throwsSomeStuff: (Int) -> Double = {x -> x.toDouble()}
//    val throwsOtherThings: (Double) -> String = {x -> x.toString()}
//    val moreThrowing: (String) -> List<String> = {x -> listOf(x) }
//    val magic = throwsSomeStuff
//        .andThen(throwsOtherThings)
//        .andThen(moreThrowing)

    fun additionalSyntax() {
        val r: Either<Int, Int> = Either.Right(7)
        r.mapLeft { it + 1 }

        val l: Either<Int, Int> = Either.Left(7)
        l.mapLeft { it + 1 }

        val rm: Either<Int, Int> = Either.Right(7)
        rm.map { it + 1 }

        val lm: Either<Int, Int> = Either.Left(7)
        lm.map { it + 1 }

        val right: Either<String, Int> = Either.Right(5)
        right.flatMap { Either.Right(it + 1) }

        val left: Either<String, Int> = Either.Left("Something went wrong")
        left.flatMap { Either.Right(it + 1) }

        val x = "hello".left()
        x.getOrElse { 7 }
        // 7

        val le = "hello".left()
        le.getOrHandle { s ->  7 }
        // 7

        val xr = 7.right()
        xr.contains(7)
        // true
    }

    fun additionalSyntxGetOrHandle(){
        val r: Either<Throwable, Int> = Either.Left(NumberFormatException())
        val httpStatusCode = r.getOrHandle {
            when(it) {
                is NumberFormatException -> 400
                else -> 500
            }
        } // 400
    }

    fun usingEitherInstedOfException() {

        val either: Either<String, Int> = Either.Right(5)
//        val either: Either<String, Int> = Either.Left("Something went wrong")

        when (either) {
            is Either.Left  -> print(either.a)
            is Either.Right -> print(either.b)
        }
    }

    fun a() {
        val right: Either<String, Int> = Either.Right(5)
        val left: Either<String, Int> = Either.Left("Something went wrong")
    }

    fun b() {
        val right: Either<String, Int> = Either.Right(5)
        right.flatMap { Either.Right(it + 1) }

        val left: Either<String, Int> = Either.Left("Something went wrong")
        left.flatMap { Either.Right(it + 1) }
    }

    // Exception Style
    class ExceptionStyle {
        fun parse(s: String): Int =
            if (s.matches(Regex("-?[0-9]+"))) s.toInt()
            else throw NumberFormatException("$s is not a valid integer.")

        fun reciprocal(i: Int): Double =
            if (i == 0) throw IllegalArgumentException("Cannot take reciprocal of 0.")
            else 1.0 / i

        fun stringify(d: Double): String = d.toString()
    }

    // Either with ADT Style

    class EitherwithADTStyle {
        sealed class Error {
            object NotANumber : Error()
            object NoZeroReciprocal : Error()
        }

        fun parse(s: String): Either<Error, Int> =
            if (s.matches(Regex("-?[0-9]+"))) Either.Right(s.toInt())
            else Either.Left(Error.NotANumber)

        fun reciprocal(i: Int): Either<Error, Double> =
            if (i == 0) Either.Left(Error.NoZeroReciprocal)
            else Either.Right(1.0 / i)

        fun stringify(d: Double): String = d.toString()

        fun magic(s: String): Either<Error, String> =
            parse(s).flatMap { reciprocal(it) }.map { stringify(it) }

        fun consume() {
            val x = magic("2")
            when (x) {
                is Either.Left  -> when (x.a) {
                    Error.NotANumber       -> "Not a number!"
                    Error.NoZeroReciprocal -> "Can't take reciprocal of 0!"
                }
                is Either.Right -> "Got reciprocal: ${x.b}"
            }
        }
    }
}