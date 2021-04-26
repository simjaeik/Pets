package com.lacuc.pets.domain.animal

import com.lacuc.pets.data.Result
import com.lacuc.pets.data.animal.AnimalRepository
import com.lacuc.pets.data.animal.entity.Animal
import com.lacuc.pets.domain.animal.animal.GetAnimalUseCase
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers
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
    fun loadAnimal_empty() = runBlocking {
        `when`(repository.getAnimalByGroup(anyInt())).thenReturn(Result.Success(emptyList()))

        val result = useCase(0) {}

        if (result is Result.Success) {
            assertNotNull(result.body)
            assertTrue(result.body?.isEmpty() ?: false)
            verify(repository).getAnimalByGroup(anyInt())
        }
    }

    @Test
    fun loadAnimals() = runBlocking {
        val gid = 1
        `when`(repository.getAnimalByGroup(gid)).thenReturn(
            Result.Success(
                listOf(
                    Animal(gid, "", "", 0, "", "", "", 0.0, ""),
                    Animal(gid, "", "", 0, "", "", "", 0.0, ""),
                    Animal(gid, "", "", 0, "", "", "", 0.0, ""),
                )
            )
        )

        val result = useCase(1) {}

        if (result is Result.Success) {
            assertNotNull(result.body)
            assertEquals(result.body?.size, 3)
            verify(repository).getAnimalByGroup(1)
        }
    }

    @Test
    fun loadAnimals_wrongGid() = runBlocking {
        val gid = 1
        `when`(repository.getAnimalByGroup(ArgumentMatchers.anyInt())).thenAnswer{
            val param = it.arguments[0] as Int
            if (param == 1)
                Result.Success(
                    listOf(
                        Animal(gid, "", "", 0, "", "", "", 0.0, ""),
                        Animal(gid, "", "", 0, "", "", "", 0.0, ""),
                        Animal(gid, "", "", 0, "", "", "", 0.0, ""),
                    )
                )
            else
                Result.Success(emptyList())
        }

        val result = useCase(0) {}

        if (result is Result.Success) {
            assertNotNull(result.body)
            assertEquals(result.body?.size, 0)
            verify(repository).getAnimalByGroup(0)
        }
    }
}