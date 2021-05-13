package com.lacuc.pets.data.group

import com.lacuc.pets.data.Result
import com.lacuc.pets.data.group.entity.*

interface GroupDataSource {
    suspend fun getMyGroups(): Result<List<GroupWrapper>>

    suspend fun getGroupsNear(latitude: Double, longitude: Double): Result<List<Group>>

    suspend fun deleteGroup(gid: String): Result<Void>

    suspend fun setGroup(params: Map<String, String>): Result<Void>

    suspend fun getGroup(gid: String): Result<Group>

    suspend fun updateGroup(group: Group): Result<Void>

    suspend fun addGroupMember(memberParams: Map<String, Any>): Result<Void>

    suspend fun getGroupMembers(gid: String): Result<List<Member>>

    suspend fun getGroupMember(gid: String, uid: String): Result<Member>

    suspend fun updateGroupMember(
        gid: String,
        uid: String,
        name: String,
        email: String
    ): Result<Void>

    suspend fun deleteGroupMember(gid: String): Result<Void>

    suspend fun getGroupImages(gid: String): Result<List<GroupImage>>

    suspend fun getGroupImage(gid: String, iid: String): Result<GroupImage>

    suspend fun setGroupImage(imageParams: Map<String, Any>): Result<Void>

    suspend fun updateGroupImage(imageParams: Map<String, Any>): Result<Void>

    suspend fun getItems(gid: String): Result<List<ItemHistory>>

    suspend fun setItem(itemHistory: ItemHistory): Result<Void>

    suspend fun updateItem(iid: String, itemHistory: ItemHistory): Result<Void>

    suspend fun deleteItem(iid: String): Result<Void>
}