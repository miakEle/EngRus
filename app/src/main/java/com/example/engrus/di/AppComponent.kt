package com.example.engrus.di

import android.content.Context
import com.example.engrus.presentation.AddScreenActivity
import com.example.engrus.presentation.MainActivity
import com.example.engrus.presentation.WordCardWorker
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [DataModule::class, DomainModule::class, ViewModelModule::class])
interface AppComponent {

    fun inject(mainActivity: MainActivity)

    fun inject(addScreenActivity: AddScreenActivity)

    fun inject(worker: WordCardWorker)

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }


}