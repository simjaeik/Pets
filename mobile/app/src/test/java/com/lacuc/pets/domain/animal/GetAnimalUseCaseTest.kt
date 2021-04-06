package com.lacuc.pets.domain.animal

import com.lacuc.pets.data.animal.AnimalRepository
import com.lacuc.pets.data.animal.DefaultAnimalRepository
import com.lacuc.pets.data.animal.FakeAnimalRemoteDataSource
import org.junit.Before

class GetAnimalUseCaseTest {

    lateinit var repository: AnimalRepository

    lateinit var useCase: GetAnimalUseCase

    @Before
    fun init_useCase() {
        repository = DefaultAnimalRepository(FakeAnimalRemoteDataSource())
        useCase = GetAnimalUseCase(repository)
    }
}