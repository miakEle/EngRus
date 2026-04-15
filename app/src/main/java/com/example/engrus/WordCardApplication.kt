package com.example.engrus

import android.app.Application
import com.example.engrus.di.AppComponent
import com.example.engrus.di.DaggerAppComponent
import dagger.android.DaggerApplication

class WordCardApplication: Application() {

    lateinit var appComponent : AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.factory().create(this)

    }

}