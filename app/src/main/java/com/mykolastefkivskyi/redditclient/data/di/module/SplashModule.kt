package com.mykolastefkivskyi.redditclient.data.di.module


import com.mykolastefkivskyi.redditclient.data.di.scope.SplashScope
import com.mykolastefkivskyi.redditclient.ui.splash.SplashContract
import com.mykolastefkivskyi.redditclient.ui.splash.SplashPresenter

import dagger.Module
import dagger.Provides


@Module
class SplashModule {

    @Provides
    @SplashScope
    fun provideSplashActivityActions(splashActivityPresenter: SplashPresenter): SplashContract.Actions {
        return splashActivityPresenter
    }
}
