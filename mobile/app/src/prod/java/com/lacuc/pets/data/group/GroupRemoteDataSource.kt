package com.lacuc.pets.data.group

import com.lacuc.pets.data.Result
import com.lacuc.pets.data.group.entity.*
import okhttp3.MultipartBody
import javax.inject.Inject

class GroupRemoteDataSource @Inject constructor(
    private val groupService: GroupService
) : GroupDataSource {
    override suspend fun getMyGroups(): Result<List<GroupWrapper>> = groupService.getMyGroups()

    override suspend fun getGroupsNear(latitude: Double, longitude: Double): Result<List<Group>> =
        groupService.getGroupsNear(latitude, longitude)

    override suspend fun deleteGroup(gid: String): Result<Void> = groupService.deleteGroup(gid)

    override suspend fun setGroup(
        params: Map<String, String>,
        imageFile: MultipartBody.Part
    ): Result<Void> = groupService.setGroup(params, imageFile)

    override suspend fun getGroup(gid: String): Result<Group> = groupService.getGroup(gid)

    override suspend fun updateGroup(
        gid: String,
        params: Map<String, String>,
        imageFile: MultipartBody.Part
    ): Result<Void> = groupService.updateGroup(gid, params, imageFile)

    override suspend fun addGroupMember(memberParams: Map<String, Any>): Result<Void> =
        groupService.addGroupMember(memberParams)

    override suspend fun getGroupMembers(gid: String): Result<List<Member>> =
        groupService.getGroupMembers(gid)

    override suspend fun getGroupMember(gid: String, uid: String): Result<Member> {
        TODO("Not yet implemented")
    }

    override suspend fun updateProfile(
        name: String, email: String
    ): Result<Void> {
        TODO("Not yet implemented")
    }

    override suspend fun deleteGroupMember(gid: String): Result<Void> =
        groupService.deleteGroupMember(gid)

    override suspend fun getGroupImages(gid: String): Result<List<GroupImage>> =
        groupService.getGroupImages(gid)

    override suspend fun getGroupImage(gid: String, iid: String): Result<GroupImage> {
        TODO("Not yet implemented")
    }

    override suspend fun setGroupImage(imageParams: Map<String, Any>): Result<Void> =
        groupService.setGroupImage(imageParams)

    override suspend fun updateGroupImage(imageParams: Map<String, Any>): Result<Void> {
        TODO("Not yet implemented")
    }

    override suspend fun getItems(gid: String): Result<List<ItemHistory>> =
        groupService.getItems(gid)

    override suspend fun setItem(itemHistory: ItemHistory): Result<Void> =
        groupService.setItem(itemHistory)

    override suspend fun updateItem(iid: String, itemHistory: ItemHistory): Result<Void> =
        groupService.updateItem(iid, itemHistory)

    override suspend fun deleteItem(iid: String): Result<Void> = groupService.deleteItem(iid)
}