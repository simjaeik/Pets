package com.lacuc.pets.data.group

import com.lacuc.pets.data.Result
import com.lacuc.pets.data.group.entity.Group
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FakeGroupRemoteDataSource @Inject constructor() : GroupDataSource {

    private val tempToken = "tempToken"

    private val groupData =
        mutableMapOf<String, List<Group>>()

    init {
        groupData[tempToken] = mutableListOf(
            Group(
                "Group1",
                "그룹 1 소개",
                "https://images.unsplash.com/photo-1593643946890-b5b85ade6451?ixid=MXwxMjA3fDF8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=2129&q=80",
                false
            ),
            Group(
                "Group2",
                "aco wqp awji  snl cnqwo c aslc wiz xlc  wia cwalwcia sic raltj wj cor fosajrw sa w skaj",
                "https://images.unsplash.com/photo-1617289749213-c2a7b44f6523?ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=1914&q=80",
                false
            ),
            Group(
                "Group3",
                "info \n info \n info",
                "https://images.unsplash.com/photo-1617286856618-f9411fcc1800?ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=1350&q=80",
                false
            )
        )
    }

    override suspend fun getMyGroups(): Result<List<Group>> = withContext(Dispatchers.IO) {
        delay(1000)
        groupData[tempToken]?.let {
            Result.Success(it)
        } ?: Result.Success(emptyList())
    }

    override suspend fun getGroupsNear(latitude: Double, longitude: Double): Result<List<Group>> {
        TODO("Not yet implemented")
    }

    override suspend fun deleteGroup(gid: Int): Result<Void> {
        TODO("Not yet implemented")
    }

    override suspend fun setGroup(group: Group): Result<Void> = withContext(Dispatchers.IO) {
        delay(500)
        groupData[tempToken] = groupData.getOrPut(tempToken) { listOf() } + group
        Result.Success(null)
    }

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