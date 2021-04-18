package com.lacuc.pets.data.group

import com.lacuc.pets.data.group.entity.Group
import javax.inject.Inject

class DefaultGroupRepository @Inject constructor(
    private val groupRemoteDataSource: GroupDataSource
) : GroupRepository {
    override fun loadGroup(email: String): List<Group> = groupRemoteDataSource.loadGroup(email)

    override fun saveGroup(email: String, group: Group) {
        groupRemoteDataSource.saveGroup(email, group)
    }
}