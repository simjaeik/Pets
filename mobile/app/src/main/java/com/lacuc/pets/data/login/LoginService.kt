package com.lacuc.pets.data.login

import com.lacuc.pets.data.Result
import com.lacuc.pets.data.SimpleBoolResult
import com.lacuc.pets.data.TokenResponse
import retrofit2.http.*

interface LoginService {
    @FormUrlEncoded
    @POST("api/user/login")
    suspend fun signIn(
        @Field("email") userID: String,
        @Field("password") userPW: String
    ): Result<TokenResponse>

    @GET("api/user/email/{email}")
    suspend fun isEmailExist(@Path("email") email: String): Result<SimpleBoolResult>

    @FormUrlEncoded
    @POST("api/user/signUp")
    suspend fun signUp(@FieldMap signUpParams: Map<String, String>): Result<SimpleBoolResult>

    @GET("api/user/nickName/{nickName}")
    suspend fun isNickNameExist(@Path("nickName") nickName: String): Result<SimpleBoolResult>
}