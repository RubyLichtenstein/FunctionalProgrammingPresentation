package com.rubylich.exmple


/**
 * Created by rl98880 on 04/03/2018.
 */
sealed class Either<B, A> {

    companion object {
        fun <L> left(left: L): Either<L, Nothing> = Either.Left(left)

        fun <R> right(right: R): Either<Nothing, R> = Either.Right(right)
    }

    data class Left<A, B>(val a: A) : Either<A, B>()

    data class Right<A, B>(val b: B) : Either<A, B>()
}