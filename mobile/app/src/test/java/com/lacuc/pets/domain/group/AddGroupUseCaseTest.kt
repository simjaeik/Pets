package com.lacuc.pets.domain.group

import com.lacuc.pets.data.Result
import com.lacuc.pets.data.group.DefaultGroupRepository
import com.lacuc.pets.data.group.FakeGroupRemoteDataSource
import com.lacuc.pets.data.group.GroupRepository
import com.lacuc.pets.data.group.entity.Group
import kotlinx.coroutines.runBlocking
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

        runBlocking {
            useCase(newGroup)

            when (val result = repository.getMyGroups()) {
                is Result.Success -> assertTrue(result.body?.any { it == newGroup } ?: false)
                else -> assert(false)
            }
        }
    }
}