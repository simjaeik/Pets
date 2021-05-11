package com.lacuc.pets.data.group

import com.lacuc.pets.data.Result
import com.lacuc.pets.data.group.entity.Group
import com.lacuc.pets.data.group.entity.GroupImage
import com.lacuc.pets.data.group.entity.ItemHistory
import com.lacuc.pets.data.group.entity.Member
import javax.inject.Inject

class GroupRemoteDataSource @Inject constructor(
    private val groupService: GroupService
) : GroupDataSource {
    override suspend fun getMyGroups(): Result<List<Group>> = groupService.getMyGroups()

    override suspend fun getGroupsNear(latitude: Double, longitude: Double): Result<List<Group>> =
        groupService.getGroupsNear(latitude, longitude)

    override suspend fun deleteGroup(gid: Int): Result<Void> = groupService.deleteGroup(gid)

    override suspend fun setGroup(group: Group): Result<Void> = groupService.setGroup(group)

    override suspend fun getGroup(gid: Int): Result<Group> = groupService.getGroup(gid)

    override suspend fun updateGroup(group: Group): Result<Void> {
        TODO("Not yet implemented")
    }

    override suspend fun addGroupMember(memberParams: Map<String, Any>): Result<Void> =
        groupService.addGroupMember(memberParams)

    override suspend fun getGroupMembers(gid: Int): Result<List<Member>> =
        groupService.getGroupMembers(gid)

    override suspend fun getGroupMember(gid: Int, uid: Int): Result<Member> {
        TODO("Not yet implemented")
    }

    override suspend fun updateGroupMember(
        gid: Int, uid: Int, name: String, email: String
    ): Result<Void> {
        TODO("Not yet implemented")
    }

    override suspend fun deleteGroupMember(gid: Int): Result<Void> =
        groupService.deleteGroupMember(gid)

    override suspend fun getGroupImages(gid: Int): Result<List<GroupImage>> =
        groupService.getGroupImages(gid)

    override suspend fun getGroupImage(gid: Int, iid: Int): Result<GroupImage> {
        TODO("Not yet implemented")
    }

    override suspend fun setGroupImage(imageParams: Map<String, Any>): Result<Void> =
        groupService.setGroupImage(imageParams)

    override suspend fun updateGroupImage(imageParams: Map<String, Any>): Result<Void> {
        TODO("Not yet implemented")
    }

    override suspend fun getItems(gid: Int): Result<List<ItemHistory>> = groupService.getItems(gid)

    override suspend fun setItem(itemHistory: ItemHistory): Result<Void> =
        groupService.setItem(itemHistory)

    override suspend fun updateItem(iid: Int, itemHistory: ItemHistory): Result<Void> =
        groupService.updateItem(iid, itemHistory)

    override suspend fun deleteItem(iid: Int): Result<Void> = groupService.deleteItem(iid)
}