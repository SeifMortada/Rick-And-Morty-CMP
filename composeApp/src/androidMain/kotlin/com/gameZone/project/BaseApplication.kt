package com.gameZone.project

import android.app.Application
import com.gameZone.project.di.initKoin

class BaseApplication: Application() {
    override fun onCreate() {
        super.onCreate()
       initKoin()
    }
}