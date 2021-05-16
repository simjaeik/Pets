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
        imageFile: MultipartBody.Part
    ): Result<Void> =
        groupRemoteDataSource.updateGroup(gid, params, imageFile)

    override suspend fun addGroupMember(memberParams: Map<String, Any>): Result<Void> {
        TODO("Not yet implemented")
    }

    override suspend fun getGroupMembers(gid: String): Result<List<Member>> =
        groupRemoteDataSource.getGroupMembers(gid)

    override suspend fun getGroupMember(gid: String, uid: String): Result<Member> =
        groupRemoteDataSource.getGroupMember(gid, uid)

    override suspend fun updateProfile(
        name: String, email: String
    ): Result<Void> = groupRemoteDataSource.updateProfile(name, email)

    override suspend fun deleteGroupMember(gid: String): Result<Void> {
        TODO("Not yet implemented")
    }

    override suspend fun getGroupImages(gid: String): Result<List<GroupImage>> =
        groupRemoteDataSource.getGroupImages(gid)

    override suspend fun getGroupImage(gid: String, iid: String): Result<GroupImage> =
        groupRemoteDataSource.getGroupImage(gid, iid)

    override suspend fun setGroupImage(imageParams: Map<String, Any>): Result<Void> =
        groupRemoteDataSource.setGroupImage(imageParams)

    override suspend fun updateGroupImage(imageParams: Map<String, Any>): Result<Void> =
        groupRemoteDataSource.updateGroupImage(imageParams)

    override suspend fun getItems(gid: String): Result<List<ItemHistory>> =
        groupRemoteDataSource.getItems(gid)

    override suspend fun setItem(itemHistory: ItemHistory): Result<Void> {
        TODO("Not yet implemented")
    }

    override suspend fun updateItem(iid: String, itemHistory: ItemHistory): Result<Void> {
        TODO("Not yet implemented")
    }

    override suspend fun deleteItem(iid: String): Result<Void> {
        TODO("Not yet implemented")
    }
}