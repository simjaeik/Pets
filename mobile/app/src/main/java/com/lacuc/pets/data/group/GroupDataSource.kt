package com.lacuc.pets.data.group

import com.lacuc.pets.data.Result
import com.lacuc.pets.data.group.entity.Group
import com.lacuc.pets.data.group.entity.GroupImage
import com.lacuc.pets.data.group.entity.ItemHistory
import com.lacuc.pets.data.group.entity.Member

interface GroupDataSource {
    suspend fun getMyGroups(): Result<List<Group>>

    suspend fun getGroupsNear(latitude: Double, longitude: Double): Result<List<Group>>

    suspend fun deleteGroup(gid: Int): Result<Void>

    suspend fun setGroup(group: Group): Result<Void>

    suspend fun getGroup(gid: Int): Result<Group>

    suspend fun updateGroup(group: Group): Result<Void>

    suspend fun addGroupMember(memberParams: Map<String, Any>): Result<Void>

    suspend fun getGroupMembers(gid: Int): Result<List<Member>>

    suspend fun getGroupMember(gid: Int, uid: Int): Result<Member>

    suspend fun updateGroupMember(gid: Int, uid: Int, name: String, email: String): Result<Void>

    suspend fun deleteGroupMember(gid: Int): Result<Void>

    suspend fun getGroupImages(gid: Int): Result<List<GroupImage>>

    suspend fun getGroupImage(gid: Int, iid: Int): Result<GroupImage>

    suspend fun setGroupImage(imageParams: Map<String, Any>): Result<Void>

    suspend fun updateGroupImage(imageParams: Map<String, Any>): Result<Void>

    suspend fun getItems(gid: Int): Result<List<ItemHistory>>

    suspend fun setItem(itemHistory: ItemHistory): Result<Void>

    suspend fun updateItem(iid: Int, itemHistory: ItemHistory): Result<Void>

    suspend fun deleteItem(iid: Int): Result<Void>
}