package com.lacuc.pets.domain.group

import com.lacuc.pets.data.group.DefaultGroupRepository
import com.lacuc.pets.data.group.FakeGroupRemoteDataSource
import com.lacuc.pets.data.group.Group
import com.lacuc.pets.data.group.GroupRepository
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class AddGroupUseCaseTest {

    lateinit var repository: GroupRepository
    lateinit var useCase: AddGroupUseCase

    @Before
    fun init_useCase() {
        repository = DefaultGroupRepository(FakeGroupRemoteDataSource())
        useCase = AddGroupUseCase(repository)
    }

    @Test
    fun addNewGroup() {
        val userEmail = "newUser@lacuc.com"
        val newGroup = Group("newGroup", "Info", "image", false)

        useCase(userEmail, newGroup)

        assertTrue(repository.loadGroup(userEmail).any { it == newGroup })
    }
}