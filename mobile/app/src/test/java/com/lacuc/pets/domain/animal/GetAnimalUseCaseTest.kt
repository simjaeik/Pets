package com.lacuc.pets.domain.animal

import com.lacuc.pets.data.animal.Animal
import com.lacuc.pets.data.animal.AnimalRepository
import org.junit.Assert.assertEquals
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
        val result = useCase(0) {}

        assertTrue(result.isEmpty())
        verify(repository).loadAnimal(anyInt())
    }

    @Test
    fun loadAnimals() {
        val gid = 1
        `when`(repository.loadAnimal(gid)).thenReturn(
            listOf(
                Animal(gid, "", "", 0, "", "", "", 0.0, ""),
                Animal(gid, "", "", 0, "", "", "", 0.0, ""),
                Animal(gid, "", "", 0, "", "", "", 0.0, ""),
            )
        )

        val result = useCase(1) {}

        assertEquals(result.size, 3)
        verify(repository).loadAnimal(1)
    }

    @Test
    fun loadAnimals_wrongGid() {
        val gid = 1
        `when`(repository.loadAnimal(gid)).thenReturn(
            listOf(
                Animal(gid, "", "", 0, "", "", "", 0.0, ""),
                Animal(gid, "", "", 0, "", "", "", 0.0, ""),
                Animal(gid, "", "", 0, "", "", "", 0.0, ""),
            )
        )

        val result = useCase(0) {}

        assertEquals(result.size, 0)
        verify(repository).loadAnimal(0)
    }
}