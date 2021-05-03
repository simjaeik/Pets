package com.lacuc.pets.data.group

import com.lacuc.pets.data.Result
import com.lacuc.pets.data.group.entity.Group
import com.lacuc.pets.data.group.entity.GroupImage
import com.lacuc.pets.data.group.entity.ItemHistory
import com.lacuc.pets.data.group.entity.Member
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FakeGroupRemoteDataSource @Inject constructor() : GroupDataSource {

    private val tempToken = "tempToken"

    private val groupData = mutableMapOf<String, List<Group>>()

    private val memberData = mutableListOf<Pair<Int, Member>>()

    init {
        initGroup()
        initMember()
    }

    override suspend fun getMyGroups(): Result<List<Group>> = withContext(Dispatchers.IO) {
        delay(100)
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
        delay(100)
        groupData[tempToken] = groupData.getOrPut(tempToken) { listOf() } + group
        Result.Success(null)
    }

    override suspend fun getGroup(gid: Int): Result<Group> {
        TODO("Not yet implemented")
    }

    override suspend fun updateGroup(group: Group): Result<Void> = withContext(Dispatchers.IO) {
        delay(100)
        groupData[tempToken] = groupData.getValue(tempToken).filter { it.gid != group.gid } + group
        Result.Success(null)
    }

    override suspend fun addGroupMember(memberParams: Map<String, Any>): Result<Void> {
        TODO("Not yet implemented")
    }

    override suspend fun getGroupMembers(gid: Int): Result<List<Member>> =
        withContext(Dispatchers.IO) {
            delay(100)
            Result.Success(memberData.filter { it.first == gid }.map { it.second })
        }

    override suspend fun deleteGroupMember(gid: Int): Result<Void> {
        TODO("Not yet implemented")
    }

    override suspend fun getGroupImages(gid: Int): Result<List<GroupImage>> {
        TODO("Not yet implemented")
    }

    override suspend fun setGroupImage(imageParams: Map<String, Any>): Result<Void> {
        TODO("Not yet implemented")
    }

    override suspend fun getItems(gid: Int): Result<List<ItemHistory>> {
        TODO("Not yet implemented")
    }

    override suspend fun setItem(itemHistory: ItemHistory): Result<Void> {
        TODO("Not yet implemented")
    }

    override suspend fun updateItem(iid: Int, itemHistory: ItemHistory): Result<Void> {
        TODO("Not yet implemented")
    }

    override suspend fun deleteItem(iid: Int): Result<Void> {
        TODO("Not yet implemented")
    }

    private fun initGroup() {
        groupData[tempToken] = mutableListOf(
            Group(
                0,
                "Group1",
                "그룹 1 소개",
                "https://images.unsplash.com/photo-1593643946890-b5b85ade6451?ixid=MXwxMjA3fDF8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=2129&q=80",
                false
            ),
            Group(
                1,
                "Group2",
                "aco wqp awji  snl cnqwo c aslc wiz xlc  wia cwalwcia sic raltj wj cor fosajrw sa w skaj",
                "https://images.unsplash.com/photo-1617289749213-c2a7b44f6523?ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=1914&q=80",
                false
            ),
            Group(
                2,
                "Group3",
                "info \n info \n info",
                "https://images.unsplash.com/photo-1617286856618-f9411fcc1800?ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=1350&q=80",
                false
            )
        )
    }

    private fun initMember() {
        memberData.add(0 to Member(0, "Linux", "admin", "mail", "L"))
        memberData.add(0 to Member(1, "Ubuntu", "admin", "mail", "U"))
        memberData.add(0 to Member(2, "Arch", "admin", "mail", "A"))
    }

}