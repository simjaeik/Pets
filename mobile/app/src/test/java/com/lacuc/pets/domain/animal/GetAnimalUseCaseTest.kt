package com.lacuc.pets.domain.animal

import com.lacuc.pets.data.animal.AnimalRepository
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.*

class GetAnimalUseCaseTest {

    lateinit var repository: AnimalRepository

    lateinit var useCase: GetAnimalUseCase

    @Before
    fun init_useCase() {
        repository = mock(AnimalRepository::class.java)
        useCase = GetAnimalUseCase(repository)
    }

    @Test
    fun loadAnimal_empty() {
        val result = useCase(0)

        assertTrue(result.isEmpty())
        verify(repository).loadAnimal(anyInt())
    }
}