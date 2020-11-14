package com.ealmazan.marvelcodechallenge.framework

import android.app.Application
import com.ealmazan.marvelcodechallenge.framework.di.appModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MarvelCodeChallengeApp: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MarvelCodeChallengeApp)
            modules(appModules)
        }
    }

}