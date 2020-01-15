package com.mykolastefkivskyi.redditclient.ui.splash

import android.os.Bundle
import com.mykolastefkivskyi.redditclient.R
import com.mykolastefkivskyi.redditclient.RedditPostApp
import com.mykolastefkivskyi.redditclient.ui.base.BaseActivity
import com.mykolastefkivskyi.redditclient.ui.main.MainActivity
import com.mykolastefkivskyi.redditclient.ui.main.MainContract

class SplashActivity : BaseActivity<SplashContract.Actions>(), SplashContract.Views {

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as RedditPostApp).splashComponent?.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        actions?.makeDelayForLogo()
    }

    override fun onDestroy() {
        (application as RedditPostApp).releaseSplashComponent()
        super.onDestroy()
    }

    override fun openMainActivity() {
        MainActivity.start(this)
    }

}
