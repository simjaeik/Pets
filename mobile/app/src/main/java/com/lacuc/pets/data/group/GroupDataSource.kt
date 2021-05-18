package com.lacuc.pets.data.group

import com.lacuc.pets.data.Result
import com.lacuc.pets.data.group.entity.*
import okhttp3.MultipartBody

interface GroupDataSource {
    suspend fun getMyGroups(): Result<List<GroupWrapper>>

    suspend fun deleteGroup(gid: String): Result<Void>

    suspend fun setGroup(params: Map<String, String>, imageFile: MultipartBody.Part): Result<Void>

    suspend fun getGroup(gid: String): Result<Group>

    suspend fun updateGroup(
        gid: String,
        params: Map<String, String>,
        imageFile: MultipartBody.Part? = null
    ): Result<Void>

    suspend fun addGroupMember(
        gid: String,
        email: String,
        authority: String
    ): Result<Void>

    suspend fun getGroupMembers(gid: String): Result<List<Member>>

    suspend fun getProfile(): Result<Member>

    suspend fun updateProfile(
        name: String,
        email: String,
        nickName: String
    ): Result<Void>

    suspend fun deleteGroupMember(gid: String): Result<Void>

    suspend fun getGroupImages(gid: String): Result<List<GroupImage>>

    suspend fun getGroupImage(gid: String, iid: String): Result<GroupImage>

    suspend fun setGroupImage(
        imageParams: Map<String, String>,
        imageFile: MultipartBody.Part
    ): Result<Void>

    suspend fun updateGroupImage(
        iid: String,
        tag: String,
        imageFile: MultipartBody.Part? = null
    ): Result<Void>

    suspend fun getItems(gid: String): Result<List<ItemHistory>>

    suspend fun setItem(itemHistory: ItemHistory): Result<Void>

    suspend fun updateItem(iid: String, itemHistory: ItemHistory): Result<Void>

    suspend fun deleteItem(iid: String): Result<Void>
}