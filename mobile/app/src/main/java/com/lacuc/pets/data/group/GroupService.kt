package com.lacuc.pets.data.group

import com.lacuc.pets.data.Result
import com.lacuc.pets.data.group.entity.*
import okhttp3.MultipartBody
import retrofit2.http.*

interface GroupService {
    @GET("api/group")
    suspend fun getMyGroups(): Result<List<GroupWrapper>>

    @DELETE("api/group/{id}")
    suspend fun deleteGroup(@Path("id") gid: String): Result<Void>

    @Multipart
    @POST("api/group")
    suspend fun setGroup(
        @PartMap params: Map<String, String>,
        @Part imageFile: MultipartBody.Part
    ): Result<Void>

    @Multipart
    @PATCH("api/group/{id}")
    suspend fun updateGroup(
        @Path("id") gid: String,
        @PartMap params: Map<String, String>,
        @Part imageFile: MultipartBody.Part
    ): Result<Void>

    @Multipart
    @PATCH("api/group/{id}")
    suspend fun updateGroup(
        @Path("id") gid: String,
        @PartMap params: Map<String, String>,
    ): Result<Void>

    @GET("api/group/{id}")
    suspend fun getGroup(@Path("id") gid: String): Result<Group>

    @GET("api/user")
    suspend fun getMemberInfo(): Result<Member>

    @FormUrlEncoded
    @PATCH("api/user")
    suspend fun updateMemberInfo(
        @Field("name") name: String,
        @Field("email") email: String,
        @Field("nickName") nickName: String
    ): Result<Void>

    @FormUrlEncoded
    @POST("api/group/member/{id}")
    suspend fun addGroupMember(
        @Path("id") gid: String,
        @Field("email") email: String,
        @Field("authority") authority: String
    ): Result<Void>

    @GET("api/group/members/{id}")
    suspend fun getGroupMembers(@Path("id") gid: String): Result<List<Member>>

    @DELETE("api/group/members/{id}")
    suspend fun deleteGroupMember(@Path("id") gid: String): Result<Void>

    @GET("api/image/all/{id}")
    suspend fun getGroupImages(@Path("id") gid: String): Result<List<GroupImage>>
    // 그룹 단위 갤러리의 이미지를 가져오는 함수

    @Multipart
    @POST("api/image")
    suspend fun setGroupImage(
        @PartMap imageParams: Map<String, String>,
        @Part imageFile: MultipartBody.Part
    ): Result<Void>

    @Multipart
    @PATCH("api/image/{id}")
    suspend fun updateGroupImage(
        @Path("id") iid: String,
        @Part tag: String,
        @Part imageFile: MultipartBody.Part
    ): Result<Void>

    @Multipart
    @PATCH("api/image/{id}")
    suspend fun updateGroupImage(
        @Path("id") iid: String,
        @Part tag: String
    ): Result<Void>

    @GET("api/item/{id}")
    suspend fun getItems(@Path("id") gid: String): Result<List<ItemHistory>>

    @GET("api/item/{id}")
    suspend fun getItem(@Path("id") hid: String): Result<ItemHistory>

    @FormUrlEncoded
    @POST("api/item")
    suspend fun setItem(@FieldMap params: Map<String, String>): Result<Void>

    @FormUrlEncoded
    @PATCH("api/item/{id}")
    suspend fun updateItem(@Path("id") hid: String, params: Map<String, String>): Result<Void>

    @DELETE("api/item/{id}")
    suspend fun deleteItem(@Path("id") iid: String): Result<Void>
}