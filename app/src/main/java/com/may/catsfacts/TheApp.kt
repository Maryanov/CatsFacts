package com.may.catsfacts

import android.app.Application
import com.may.catsfacts.di.Container

class TheApp : Application() {

    lateinit var container : Container

    override fun onCreate() {
        super.onCreate()
        container = Container()
    }
}