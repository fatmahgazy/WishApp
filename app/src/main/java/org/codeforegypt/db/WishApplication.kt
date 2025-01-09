package org.codeforegypt.db

import android.app.Application

class WishApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        Graph.provide(this)
    }
}