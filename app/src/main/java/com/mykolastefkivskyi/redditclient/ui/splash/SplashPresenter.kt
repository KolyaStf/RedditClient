package com.mykolastefkivskyi.redditclient.ui.splash

import android.content.Context
import android.os.Handler
import com.mykolastefkivskyi.redditclient.ui.base.BasePresenter
import javax.inject.Inject

class SplashPresenter @Inject
internal constructor(appContext: Context) : BasePresenter<SplashContract.Views>(appContext),
    SplashContract.Actions {

    @Inject
    lateinit var context: Context

    override fun makeDelayForLogo() {
        Handler().postDelayed({

            if (isViewAttached)
                view?.openMainActivity()

        }, SPLASH_SCREEN_DELAY_MS)
    }

    companion object {
        private const val SPLASH_SCREEN_DELAY_MS = 3000L
    }
}