package com.example.babylon

import android.app.Application
import com.example.babylon.di.app.DaggerAppComponent
import com.example.babylon.di.app.NetworkModule
import com.example.babylon.di.app.RepositoryModule

class MyApp:Application() {

    override fun onCreate() {
        super.onCreate()
      component()
    }

   fun component() = DaggerAppComponent.builder().networkModule(NetworkModule()).repositoryModule(
       RepositoryModule()
   ).build()
}