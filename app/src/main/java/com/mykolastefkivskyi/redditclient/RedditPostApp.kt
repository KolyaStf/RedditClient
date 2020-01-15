package com.mykolastefkivskyi.redditclient

import android.app.Application
import com.mykolastefkivskyi.redditclient.data.di.component.AppComponent
import com.mykolastefkivskyi.redditclient.data.di.component.DaggerAppComponent
import com.mykolastefkivskyi.redditclient.data.di.component.MainComponent
import com.mykolastefkivskyi.redditclient.data.di.component.SplashComponent
import com.mykolastefkivskyi.redditclient.data.di.module.*

class RedditPostApp : Application() {

    private lateinit var appComponent: AppComponent

    var mainComponent: MainComponent? = null
        get() {
            if (field == null) {
                mainComponent = appComponent.initMainComponent(MainModule())
            }
            return field
        }
        private set

    var splashComponent: SplashComponent? = null
        get() {
            if (field == null) {
                splashComponent = appComponent.initSplashComponent(SplashModule())
            }
            return field
        }
        private set

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .networkModule(NetworkModule())
            .build()
    }

    fun releaseMainComponent() {
        mainComponent = null
    }

    fun releaseSplashComponent() {
        splashComponent = null
    }
}