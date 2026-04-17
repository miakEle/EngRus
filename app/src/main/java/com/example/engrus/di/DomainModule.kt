package com.example.engrus.di

import com.example.engrus.data.WordCardRepositoryImp
import com.example.engrus.domain.repository.WordCardRepository
import com.example.engrus.domain.usecases.AddWordCardUseCase
import dagger.Binds
import dagger.Module
import dagger.Provides


@Module
interface DomainModule {

    @Binds
    fun bindWordCardRepository(impl: WordCardRepositoryImp): WordCardRepository

}