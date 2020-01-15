package com.mykolastefkivskyi.redditclient.data.di.component


import com.mykolastefkivskyi.redditclient.data.di.module.MainModule
import com.mykolastefkivskyi.redditclient.data.di.scope.MainScope
import com.mykolastefkivskyi.redditclient.ui.main.MainActivity
import com.mykolastefkivskyi.redditclient.ui.splash.SplashActivity

import dagger.Subcomponent

@Subcomponent(modules = [MainModule::class])
@MainScope
interface MainComponent {

    fun inject(mainActivity: MainActivity)
}
