package com.mykolastefkivskyi.redditclient.data.di.component


import com.mykolastefkivskyi.redditclient.data.di.module.*

import javax.inject.Singleton

import dagger.Component


@Component(modules = [AppModule::class, NetworkModule::class, InteractorModule::class])
@Singleton
interface AppComponent {

    fun initMainComponent(mainModule: MainModule): MainComponent

    fun initSplashComponent(splashModule: SplashModule): SplashComponent
}
