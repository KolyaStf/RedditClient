package com.mykolastefkivskyi.redditclient.data.di.component


import com.mykolastefkivskyi.redditclient.data.di.module.SplashModule
import com.mykolastefkivskyi.redditclient.data.di.scope.SplashScope
import com.mykolastefkivskyi.redditclient.ui.splash.SplashActivity

import dagger.Subcomponent


@Subcomponent(modules = [SplashModule::class])
@SplashScope
interface SplashComponent {

    fun inject(activity: SplashActivity)
}
