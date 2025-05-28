package com.example.Shwas.domain.model

sealed class Result<out T, out R> {
    data class Success<out T>(val data: T) : Result<T, Nothing>()
    data class Error<out R>(val error: R) : Result<Nothing, R>()
}

fun <T, R> Result<T, R>.onSuccess(action: (T) -> Unit): Result<T, R> {
    if (this is Result.Success) {
        action(data)
    }
    return this
}

fun <T, R> Result<T, R>.onError(action: (R) -> Unit): Result<T, R> {
    if (this is Result.Error) {
        action(error)
    }
    return this
}

sealed class Failure<out R> {
    data class NetworkError<out R>(val error: R) : Failure<R>()
    data class ServerError<out R>(val error: R) : Failure<R>()
    data class UnknownError<out R>(val error: R) : Failure<R>()
}