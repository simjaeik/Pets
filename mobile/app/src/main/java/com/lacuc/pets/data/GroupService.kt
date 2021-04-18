package com.lacuc.pets.data

import com.lacuc.pets.data.group.Group
import retrofit2.http.*

interface GroupService {
    @GET("api/group")
    fun getMyGroups()

    @GET("api/group/near")
    fun getGroupsNear(@Query("latitude") latitude: Double, @Query("longitude") longitude: Double)

    @DELETE("api/group/{id}")
    fun deleteGroup(@Path("id") id: Int) // 그룹의 GID?

    @POST("api/group")
    fun setGroup(@Body group: Group)

    @GET("api/group/{id}")
    fun getGroup(@Path("id") id: Int)

    @FormUrlEncoded
    @POST("api/group/member")
    fun addGroupMember(@FieldMap memberParams: Map<String, Any>)

    @GET("api/group/member/{id}")
    fun getGroupMembers(@Path("id") id: Int)

    @DELETE("api/group/members/{id}")
    fun deleteGroupMember(@Path("id") id: Int)

    @GET("api/group/images/{id}")
    fun getGroupImages(@Path("id") id: Int)
    // 그룹 단위 갤러리의 이미지를 가져오는 함수

    @FormUrlEncoded
    @POST("api/group/images")
    fun setGroupImage(@FieldMap imageParams: Map<String, Any>)

    @GET("api/item/{id}")
    fun getItems(@Path("id") id: Int)

    @POST("api/item")
    fun setItem(@Body item: Any) // TODO: 2021-04-18 파라미터 타입을 Item으로 변경해야 함

    @PATCH("api/item/{id}")
    fun updateItem(@Path("id") id: Int, @Body item: Any)

    @DELETE("api/item/{id}")
    fun deleteItem(@Path("id") id: Int)
}