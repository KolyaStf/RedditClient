package com.mykolastefkivskyi.redditclient.data.di.module

import android.app.Application
import android.content.Context


import javax.inject.Singleton

import dagger.Module
import dagger.Provides

@Module
class AppModule(private val mApplication: Application) {

    @Provides
    @Singleton
    fun provideApplication(): Application {
        return mApplication
    }

    @Provides
    @Singleton
    fun provideAppContext(): Context {
        return mApplication.applicationContext
    }
}
