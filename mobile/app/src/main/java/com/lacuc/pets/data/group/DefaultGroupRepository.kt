package com.lacuc.pets.data.group

import com.lacuc.pets.data.Result
import com.lacuc.pets.data.group.entity.Group
import javax.inject.Inject

class DefaultGroupRepository @Inject constructor(
    private val groupRemoteDataSource: GroupDataSource
) : GroupRepository {
    override suspend fun getMyGroups(): Result<List<Group>> = groupRemoteDataSource.getMyGroups()

    override suspend fun getGroupsNear(latitude: Double, longitude: Double): Result<List<Group>> {
        TODO("Not yet implemented")
    }

    override suspend fun deleteGroup(gid: Int): Result<Void> {
        TODO("Not yet implemented")
    }

    override suspend fun setGroup(group: Group): Result<Void> =
        groupRemoteDataSource.setGroup(group)

    override suspend fun getGroup(gid: Int): Result<Group> {
        TODO("Not yet implemented")
    }

    override suspend fun addGroupMember(memberParams: Map<String, Any>): Result<Void> {
        TODO("Not yet implemented")
    }

    override suspend fun getGroupMembers(gid: Int): Result<List<Unit>> {
        TODO("Not yet implemented")
    }

    override suspend fun deleteGroupMember(gid: Int): Result<Void> {
        TODO("Not yet implemented")
    }

    override suspend fun getGroupImages(gid: Int): Result<List<Unit>> {
        TODO("Not yet implemented")
    }

    override suspend fun setGroupImage(imageParams: Map<String, Any>): Result<List<Unit>> {
        TODO("Not yet implemented")
    }

    override suspend fun getItems(gid: Int): Result<List<Unit>> {
        TODO("Not yet implemented")
    }

    override suspend fun setItem(item: Any): Result<Void> {
        TODO("Not yet implemented")
    }

    override suspend fun updateItem(iid: Int, item: Any): Result<Void> {
        TODO("Not yet implemented")
    }

    override suspend fun deleteItem(iid: Int): Result<Void> {
        TODO("Not yet implemented")
    }
}