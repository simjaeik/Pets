package com.lacuc.pets.data.group

import com.lacuc.pets.data.Result
import com.lacuc.pets.data.group.entity.*
import okhttp3.MultipartBody
import javax.inject.Inject

class DefaultGroupRepository @Inject constructor(
    private val groupRemoteDataSource: GroupDataSource
) : GroupRepository {
    override suspend fun getMyGroups(): Result<List<GroupWrapper>> =
        groupRemoteDataSource.getMyGroups()

    override suspend fun deleteGroup(gid: String): Result<Void> {
        TODO("Not yet implemented")
    }

    override suspend fun setGroup(
        params: Map<String, String>,
        imageFile: MultipartBody.Part
    ): Result<Void> =
        groupRemoteDataSource.setGroup(params, imageFile)

    override suspend fun getGroup(gid: String): Result<Group> = groupRemoteDataSource.getGroup(gid)

    override suspend fun updateGroup(
        gid: String,
        params: Map<String, String>,
        imageFile: MultipartBody.Part?
    ): Result<Void> =
        groupRemoteDataSource.updateGroup(gid, params, imageFile)

    override suspend fun addGroupMember(
        gid: String,
        email: String,
        authority: String
    ): Result<Void> = groupRemoteDataSource.addGroupMember(gid, email, authority)

    override suspend fun getGroupMembers(gid: String): Result<List<Member>> =
        groupRemoteDataSource.getGroupMembers(gid)

    override suspend fun getProfile(): Result<Member> = groupRemoteDataSource.getProfile()

    override suspend fun updateProfile(
        name: String, email: String, nickName: String
    ): Result<Void> = groupRemoteDataSource.updateProfile(name, email, nickName)

    override suspend fun deleteGroupMember(gid: String): Result<Void> {
        TODO("Not yet implemented")
    }

    override suspend fun getGroupImages(gid: String): Result<List<GroupImage>> =
        groupRemoteDataSource.getGroupImages(gid)

    override suspend fun getGroupImage(gid: String, iid: String): Result<GroupImage> =
        groupRemoteDataSource.getGroupImage(gid, iid)

    override suspend fun setGroupImage(
        imageParams: Map<String, String>,
        imageFile: MultipartBody.Part
    ): Result<Void> =
        groupRemoteDataSource.setGroupImage(imageParams, imageFile)

    override suspend fun updateGroupImage(
        iid: String,
        tag: String,
        imageFile: MultipartBody.Part?
    ): Result<Void> =
        groupRemoteDataSource.updateGroupImage(iid, tag, imageFile)

    override suspend fun getItems(gid: String): Result<List<ItemHistory>> =
        groupRemoteDataSource.getItems(gid)

    override suspend fun getItem(hid: String): Result<ItemHistory> =
        groupRemoteDataSource.getItem(hid)

    override suspend fun setItem(params: Map<String, String>): Result<Void> =
        groupRemoteDataSource.setItem(params)

    override suspend fun updateItem(hid: String, params: Map<String, String>): Result<Void> =
        groupRemoteDataSource.updateItem(hid, params)

    override suspend fun deleteItem(iid: String): Result<Void> =
        groupRemoteDataSource.deleteItem(iid)
}