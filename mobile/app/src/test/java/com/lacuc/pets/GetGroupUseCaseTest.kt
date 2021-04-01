package com.lacuc.pets

import com.lacuc.pets.data.GroupRepository
import com.lacuc.pets.domain.GetGroupUseCase
import com.lacuc.pets.domain.GroupItem
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GetGroupUseCaseTest {

    lateinit var repository: GroupRepository
    lateinit var clickListener: GroupItem.ClickListener
    lateinit var useCase: GetGroupUseCase

    @Before
    fun init_useCase() {
        repository = mock(GroupRepository::class.java)
        clickListener = mock(GroupItem.ClickListener::class.java)

        useCase = GetGroupUseCase(repository, clickListener)
    }

    @Test
    fun loadGroup_empty() {
        val result = useCase("user@lacuc.com")

        assertTrue(result.isEmpty())
        verify(repository).loadGroup(anyString())
    }

}