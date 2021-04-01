package com.lacuc.pets

import com.lacuc.pets.data.GroupRepository
import com.lacuc.pets.domain.GetGroupUseCase
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GetGroupUseCaseTest {

    lateinit var repository: GroupRepository
    lateinit var useCase: GetGroupUseCase

    @Before
    fun init_useCase() {
        repository = mock(GroupRepository::class.java)
        useCase = GetGroupUseCase(repository)
    }

    @Test
    fun loadGroup_empty() {
        val result = useCase()

        assertTrue(result.isEmpty())
    }

}