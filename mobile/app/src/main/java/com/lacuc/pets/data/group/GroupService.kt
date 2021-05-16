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

    @GET("api/group/{id}")
    suspend fun getGroup(@Path("id") gid: String): Result<Group>

    @FormUrlEncoded
    @POST("api/group/member/{id}")
    suspend fun addGroupMember(
        @Path("id") gid: String,
        @Field("email") email: String,
        @Field("authority") authority: String
    ): Result<Void>

    @GET("api/group/member/{id}")
    suspend fun getGroupMembers(@Path("id") gid: String): Result<List<Member>>

    @DELETE("api/group/members/{id}")
    suspend fun deleteGroupMember(@Path("id") gid: String): Result<Void>

    @GET("api/group/images/{id}")
    suspend fun getGroupImages(@Path("id") gid: String): Result<List<GroupImage>>
    // 그룹 단위 갤러리의 이미지를 가져오는 함수

    @FormUrlEncoded
    @POST("api/group/images")
    suspend fun setGroupImage(@FieldMap imageParams: Map<String, Any>): Result<Void>

    @GET("api/item/{id}")
    suspend fun getItems(@Path("id") gid: String): Result<List<ItemHistory>>

    @POST("api/item")
    suspend fun setItem(@Body itemHistory: ItemHistory): Result<Void>

    @PATCH("api/item/{id}")
    suspend fun updateItem(@Path("id") iid: String, @Body itemHistory: ItemHistory): Result<Void>

    @DELETE("api/item/{id}")
    suspend fun deleteItem(@Path("id") iid: String): Result<Void>
}