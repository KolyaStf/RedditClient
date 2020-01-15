package com.mykolastefkivskyi.redditclient.data.di.module


import com.mykolastefkivskyi.redditclient.data.di.scope.MainScope
import com.mykolastefkivskyi.redditclient.data.usecase.RedditUseCase
import com.mykolastefkivskyi.redditclient.ui.main.MainContract
import com.mykolastefkivskyi.redditclient.ui.main.MainPresenter
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class MainModule {

    @Provides
    @MainScope
    fun provideMainActivityActions(mainActivityPresenter: MainPresenter): MainContract.Actions {
        return mainActivityPresenter
    }
}
