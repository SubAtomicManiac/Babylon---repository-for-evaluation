package com.example.babylon.di.app

import com.example.babylon.MyApp
import com.example.babylon.ui.PostsActivity
import com.example.babylon.di.post.PostsModule
import com.example.babylon.repository.Repository
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = [NetworkModule::class,RepositoryModule::class,PostsModule::class])
interface AppComponent {

    fun inject(myApp: MyApp)

    fun repository():Repository
}