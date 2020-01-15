package com.mykolastefkivskyi.redditclient.data.di.module


import com.mykolastefkivskyi.redditclient.data.interactors.implementations.RedditPostInteractorImpl
import com.mykolastefkivskyi.redditclient.data.interactors.interfaces.RedditPostInteractor
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class InteractorModule {

    @Provides
    @Singleton
    fun provideRedditPostInteractor(redditPostInteractor: RedditPostInteractorImpl): RedditPostInteractor {
        return redditPostInteractor
    }
}
