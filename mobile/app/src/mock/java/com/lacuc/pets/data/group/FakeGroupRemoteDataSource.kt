package com.lacuc.pets.data.group

import javax.inject.Inject

class FakeGroupRemoteDataSource @Inject constructor() : GroupDataSource {
    override fun loadGroup(email: String): List<Group> {
        TODO("Not yet implemented")
    }
}