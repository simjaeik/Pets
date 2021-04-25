package com.lacuc.pets.data.group

import com.lacuc.pets.data.Result
import com.lacuc.pets.data.group.entity.Group
import retrofit2.http.*

interface GroupService {
    @GET("api/group")
    suspend fun getMyGroups(): Result<List<Group>>

    @GET("api/group/near")
    suspend fun getGroupsNear(
        @Query("latitude") latitude: Double,
        @Query("longitude") longitude: Double
    ): Result<List<Group>>

    @DELETE("api/group/{id}")
    suspend fun deleteGroup(@Path("id") gid: Int): Result<Void>

    @POST("api/group")
    suspend fun setGroup(@Body group: Group): Result<Void>

    @GET("api/group/{id}")
    suspend fun getGroup(@Path("id") gid: Int): Result<Group>

    @FormUrlEncoded
    @POST("api/group/member")
    suspend fun addGroupMember(@FieldMap memberParams: Map<String, Any>): Result<Void>

    @GET("api/group/member/{id}")
    suspend fun getGroupMembers(@Path("id") gid: Int): Result<List<Unit>> // TODO: 2021-04-25 리턴 타입: Member

    @DELETE("api/group/members/{id}")
    suspend fun deleteGroupMember(@Path("id") gid: Int): Result<Void>

    @GET("api/group/images/{id}")
    suspend fun getGroupImages(@Path("id") gid: Int): Result<List<Unit>> // TODO: 2021-04-25 리턴 타입: GroupImage
    // 그룹 단위 갤러리의 이미지를 가져오는 함수

    @FormUrlEncoded
    @POST("api/group/images")
    suspend fun setGroupImage(@FieldMap imageParams: Map<String, Any>): Result<List<Unit>>

    @GET("api/item/{id}")
    suspend fun getItems(@Path("id") gid: Int): Result<List<Unit>> // TODO: 2021-04-25 리턴 타입: Item

    @POST("api/item")
    suspend fun setItem(@Body item: Any): Result<Void> // TODO: 2021-04-18 파라미터 타입을 Item으로 변경해야 함

    @PATCH("api/item/{id}")
    suspend fun updateItem(@Path("id") iid: Int, @Body item: Any): Result<Void>

    @DELETE("api/item/{id}")
    suspend fun deleteItem(@Path("id") iid: Int): Result<Void>
}