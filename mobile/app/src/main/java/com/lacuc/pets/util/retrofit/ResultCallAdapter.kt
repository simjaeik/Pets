package com.lacuc.pets.util.retrofit

import com.lacuc.pets.data.Result
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Retrofit
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

class ResultCallAdapter<R : Any>(private val responseType: Type) : CallAdapter<R, Call<Result<R>>> {
    override fun responseType(): Type {
        return responseType
    }

    override fun adapt(call: Call<R>): Call<Result<R>> {
        return ResultCall(call)
    }

    class Factory : CallAdapter.Factory() {
        override fun get(
            returnType: Type,
            annotations: Array<out Annotation>,
            retrofit: Retrofit
        ): CallAdapter<*, *>? {
            if (getRawType(returnType) != Call::class.java) {
                return null
            }
            check(returnType is ParameterizedType) {
                "return type must be parameterized as Call<Result<Foo>> or Call<Result<out Foo>>"
            }
            val responseType = getParameterUpperBound(0, returnType)

            if (getRawType((responseType)) != Result::class.java) {
                return null
            }

            check(responseType is ParameterizedType) {
                "Response must be parameterized as Result<Foo> or Result<out Foo>"
            }

            val successBodyType = getParameterUpperBound(0, responseType)

            return ResultCallAdapter<Any>(successBodyType)
        }
    }
}