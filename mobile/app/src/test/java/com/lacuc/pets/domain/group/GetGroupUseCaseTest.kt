package com.lacuc.pets.domain.group

import com.lacuc.pets.data.Group
import com.lacuc.pets.data.GroupRepository
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.*
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
        val result = useCase("user@lacuc.com") {}

        assertTrue(result.isEmpty())
        verify(repository).loadGroup(anyString())
    }

    @Test
    fun loadGroup() {
        `when`(repository.loadGroup("user@lacuc.com")).thenReturn(
            listOf(
                Group("", "", "", false),
                Group("", "", "", false),
                Group("", "", "", false)
            )
        )

        val result = useCase("user@lacuc.com") {}

        assertEquals(result.size, 3)
        verify(repository).loadGroup(anyString())
    }

}