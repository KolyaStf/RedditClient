package com.mykolastefkivskyi.redditclient.ui.splash

import com.mykolastefkivskyi.redditclient.ui.base.BaseContract

class SplashContract : BaseContract() {
    interface Actions : BaseActions {
        fun makeDelayForLogo()
    }

    interface Views : BaseView {
        fun openMainActivity()
    }
}