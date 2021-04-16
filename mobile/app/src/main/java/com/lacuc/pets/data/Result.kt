package com.lacuc.pets.data

import java.io.IOException

sealed class Result<out T : Any> {

    data class Success<T : Any>(val body: T) : Result<T>()

    data class Error<T : Any>(val code: Int, val error: String) : Result<T>()

    data class Failure(val exception: IOException) : Result<Nothing>()

    data class Unexpected(val t: Throwable?) : Result<Nothing>()

}

