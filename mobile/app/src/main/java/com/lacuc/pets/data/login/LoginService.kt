package com.lacuc.pets.data.login

import com.lacuc.pets.data.Result
import com.lacuc.pets.data.SimpleBoolResult
import retrofit2.http.*

interface LoginService {
    fun signIn(param: Map<String, String>): Boolean

    @FormUrlEncoded
    @POST("api/user/login")
    fun checkUserInfo(@Field("userID") userID: String, @Field("userPW") userPW: String)
    //: member_group table을 리턴 받는데 mapping table이다 보니 문제가 있지 않나? 유저 당 여러 개의 그룹에 속할 수 있는데 한 GID만 받는다던지, 로그인 성공 시 세션 ID나 토큰을 받는게 좋을지도?

    @GET("api/user/email/{email}")
    suspend fun isEmailExist(@Path("email") email: String): Result<SimpleBoolResult>

    @FormUrlEncoded
    @POST
    fun signUp(@FieldMap signUpParams: Map<String, String>)

    @GET("api/user/nickName/{nickName}")
    suspend fun isNickNameExist(@Path("nickName") nickName: String): Result<SimpleBoolResult>
}