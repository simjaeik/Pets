package com.lacuc.pets.domain.group

import com.lacuc.pets.data.Result
import com.lacuc.pets.data.group.GroupRepository
import com.lacuc.pets.data.group.entity.Group
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GetGroupsUseCaseTest {

    lateinit var repository: GroupRepository
    lateinit var useCase: GetGroupsUseCase

    @Before
    fun init_useCase() {
        repository = mock(GroupRepository::class.java)

        useCase = GetGroupsUseCase(repository)
    }

    @Test
    fun loadGroup_empty(): Unit = runBlocking {
        `when`(repository.getMyGroups()).thenReturn(Result.Success(emptyList()))

        when (val result = useCase {}) {
            is Result.Success -> {
                assertNotNull(result.body)
                assertTrue(result.body?.isEmpty() ?: false)
                verify(repository).getMyGroups()
            }
            else -> assert(false)
        }
    }

    @Test
    fun loadGroup(): Unit = runBlocking {
        `when`(repository.getMyGroups()).thenReturn(
            Result.Success(
                listOf(
                    Group("", "", "", false),
                    Group("", "", "", false),
                    Group("", "", "", false)
                )
            )
        )

        when (val result = useCase {}) {
            is Result.Success -> {
                assertNotNull(result.body)
                assertEquals(result.body?.size, 3)
                verify(repository).getMyGroups()
            }
            else -> assert(false)
        }

    }

}