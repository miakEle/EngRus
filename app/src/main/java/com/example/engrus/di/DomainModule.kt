package com.example.engrus.di

import com.example.engrus.data.WordCardRepositoryImp
import com.example.engrus.domain.repository.WordCardRepository
import dagger.Binds
import dagger.Module


@Module
interface DomainModule {

    @Binds
    fun bindWordCardRepository(impl: WordCardRepositoryImp): WordCardRepository
}