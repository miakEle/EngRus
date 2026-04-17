package com.example.engrus.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.engrus.presentation.AddScreenViewModel
import com.example.engrus.presentation.ViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(AddScreenViewModel::class)
    abstract fun bindAddScreenViewModel(vm: AddScreenViewModel): ViewModel
}

