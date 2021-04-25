package com.lacuc.pets.data.group

import com.lacuc.pets.data.Result
import com.lacuc.pets.data.group.entity.Group
import javax.inject.Inject

class DefaultGroupRepository @Inject constructor(
    private val groupRemoteDataSource: GroupDataSource
) : GroupRepository {
    override suspend fun loadGroup(): Result<List<Group>> = groupRemoteDataSource.loadGroup()

    override suspend fun saveGroup(group: Group) {
        groupRemoteDataSource.saveGroup(group)
    }
}