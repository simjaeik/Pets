package com.lacuc.pets.domain.group

import com.lacuc.pets.data.group.DefaultGroupRepository
import com.lacuc.pets.data.group.FakeGroupRemoteDataSource
import com.lacuc.pets.data.group.GroupRepository
import org.junit.Before
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
}