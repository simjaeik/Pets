package com.lacuc.pets.util.retrofit

import com.lacuc.pets.data.*
import okhttp3.Request
import okio.Timeout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException


// https://github.com/mlegy/retrofit2-kotlin-coroutines-call-adapter/blob/master/app/src/main/java/com/melegy/retrofitcoroutines/remote/NetworkResponseCall.kt 참조
class ResultCall<T : Any>(private val call: Call<T>) : Call<Result<T>> {

    override fun clone(): Call<Result<T>> = ResultCall(call.clone())

    override fun execute(): Response<Result<T>> {
        throw UnsupportedOperationException("ResultCall doesn't support execute")
    }

    override fun enqueue(callback: Callback<Result<T>>) {
        call.enqueue(object : Callback<T> {
            override fun onResponse(call: Call<T>, response: Response<T>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        callback.onResponse(
                            this@ResultCall,
                            Response.success(Result.Success(it))
                        )
                    } ?: run {
                        callback.onResponse(
                            this@ResultCall,
                            Response.success(Result.Unexpected(null))
                        )
                    }
                } else {
                    response.errorBody()?.let {
                        callback.onResponse(
                            this@ResultCall,
                            Response.success(Result.Error(response.code(), it.string()))
                        )
                    } ?: run {
                        callback.onResponse(
                            this@ResultCall,
                            Response.success(Result.Unexpected(null))
                        )
                    }
                }
            }

            override fun onFailure(call: Call<T>, t: Throwable) {
                val networkResponse = when (t) {
                    is IOException -> Result.Failure(t)
                    else -> Result.Unexpected(t)
                }
                callback.onResponse(this@ResultCall, Response.success(networkResponse))
            }
        })
    }

    override fun isExecuted(): Boolean = call.isExecuted

    override fun cancel() = call.cancel()

    override fun isCanceled(): Boolean = call.isCanceled

    override fun request(): Request = call.request()

    override fun timeout(): Timeout = call.timeout()
}