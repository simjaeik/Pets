package com.lacuc.pets.data

import javax.inject.Inject

// TODO: 2021-04-04 레트로핏을 사용하여 쿼리하도록 변경해야 함.
class DefaultGroupRepository @Inject constructor() : GroupRepository {
    override fun loadGroup(email: String): List<Group> {
        TODO("Not yet implemented")
    }
}