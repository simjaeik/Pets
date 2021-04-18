package com.lacuc.pets.data

import retrofit2.http.*

interface PostService {
    // TODO: 2021-04-18 Post와 이미지 관련 토의가 필요할 듯 함. 지금은 각각 따로 보내는 것 같은데 멀티 파트 타입으로 보내야 하지 않을까?

    @GET("api/post/category/{category}")
    fun getPostByCategory(@Path("category") category: String) // TODO: 2021-04-18 리턴 타입으로 Post 리스트를 받아야 함

    @GET("api/post")
    fun getPostAll()

    @GET("api/post/best")
    fun getBestPosts()

    @GET("api/post/favorite/{id}")
    fun getPostsMyLike(@Path("id") id: Int)

    @GET("api/post/my/{id}")
    fun getMyPosts(@Path("id") id: Int)

    @GET("api/post/{id}")
    fun getPost(@Path("id") id: Int)

    @POST("api/post")
    fun setPost(@Body post: Any) // TODO: 2021-04-18 파라미터 타입 변경 필요

    @PUT("api/post/{id}")
    fun updatePost(@Body post: Any)

    @DELETE("api/post/{id}")
    fun deletePost(@Path("id") id: Int)

    @FormUrlEncoded
    @POST("api/post/favorite")
    fun likePost(@Field("GID") gid: Int, @Field("PID") pid: Int)

    @DELETE("api/post/favorite/{id}")
    fun unlikePost(@Path("id") id: Int)

    @FormUrlEncoded
    @POST("api/post/image")
    fun setPostImages(
        @Field("PID") pid: Int,
        @Field("url") url: String
    ) // TODO: 2021-04-18 이미지를 url 타입으로 보낼 게 아니라 포스트와 함께 Multipart를 써야하지 않을까

    @GET("api/post/image/{id}")
    fun getPostImages(@Path("id") id: Int)

    @GET("api/comment/{id}")
    fun getComments(@Path("id") id: Int)

    @FormUrlEncoded
    @POST("api/comment")
    fun setComment(
        @Field("PID") pid: Int,
        @Field("UID") uid: Int,
        @Field("content") content: String
    )

    @PATCH("api/comment/{id}")
    fun updateComment(@Path("id") id: Int)

    @DELETE("api/comment/{id}")
    fun deleteComment(@Path("id") id: Int)
}