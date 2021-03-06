package com.lacuc.pets.data.group

import com.lacuc.pets.data.Result
import com.lacuc.pets.data.group.entity.*
import okhttp3.MultipartBody
import javax.inject.Inject

class GroupRemoteDataSource @Inject constructor(
    private val groupService: GroupService
) : GroupDataSource {
    override suspend fun getMyGroups(): Result<List<GroupWrapper>> = groupService.getMyGroups()

    override suspend fun deleteGroup(gid: String): Result<Void> = groupService.deleteGroup(gid)

    override suspend fun setGroup(
        params: Map<String, String>,
        imageFile: MultipartBody.Part
    ): Result<Void> = groupService.setGroup(params, imageFile)

    override suspend fun getGroup(gid: String): Result<Group> = groupService.getGroup(gid)

    override suspend fun updateGroup(
        gid: String,
        params: Map<String, String>,
        imageFile: MultipartBody.Part?
    ): Result<Void> = imageFile?.let { groupService.updateGroup(gid, params, imageFile) }
        ?: groupService.updateGroup(gid, params)

    override suspend fun addGroupMember(
        gid: String,
        email: String,
        authority: String
    ): Result<Void> =
        groupService.addGroupMember(gid, email, authority)

    override suspend fun getGroupMembers(gid: String): Result<List<Member>> =
        groupService.getGroupMembers(gid)

    override suspend fun getProfile(): Result<Member> = groupService.getMemberInfo()

    override suspend fun updateProfile(
        name: String, email: String, nickName: String
    ): Result<Void> = groupService.updateMemberInfo(name, email, nickName)

    override suspend fun deleteGroupMember(gid: String): Result<Void> =
        groupService.deleteGroupMember(gid)

    override suspend fun getGroupImages(gid: String): Result<List<GroupImage>> =
        groupService.getGroupImages(gid)

    override suspend fun getGroupImage(gid: String, iid: String): Result<GroupImage> {
        TODO("Not yet implemented")
    }

    override suspend fun setGroupImage(
        imageParams: Map<String, String>,
        imageFile: MultipartBody.Part
    ): Result<Void> =
        groupService.setGroupImage(imageParams, imageFile)

    override suspend fun updateGroupImage(
        iid: String,
        tag: String,
        imageFile: MultipartBody.Part?
    ): Result<Void> = imageFile?.let { groupService.updateGroupImage(iid, tag, imageFile) }
        ?: groupService.updateGroupImage(iid, tag)

    override suspend fun getItems(gid: String): Result<List<ItemHistory>> =
        groupService.getItems(gid)

    override suspend fun getItem(hid: String): Result<ItemHistory> = groupService.getItem(hid)

    override suspend fun setItem(params: Map<String, String>): Result<Void> =
        groupService.setItem(params)

    override suspend fun updateItem(hid: String, params: Map<String, String>): Result<Void> =
        groupService.updateItem(hid, params)

    override suspend fun deleteItem(iid: String): Result<Void> = groupService.deleteItem(iid)
}