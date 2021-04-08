package com.lacuc.pets.data.group

import javax.inject.Inject

class DefaultGroupRepository @Inject constructor(
    private val groupRemoteDataSource: GroupDataSource
) : GroupRepository {
    override fun loadGroup(email: String): List<Group> = groupRemoteDataSource.loadGroup(email)

    override fun saveGroup(email: String, group: Group) {
        groupRemoteDataSource.saveGroup(email, group)
    }
}