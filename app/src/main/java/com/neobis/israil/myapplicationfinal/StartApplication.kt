package com.neobis.israil.myapplicationfinal

import android.app.Application
import com.neobis.israil.myapplicationfinal.data.ForumService
import com.neobis.israil.myapplicationfinal.data.Network
import com.neobis.israil.myapplicationfinal.helpers.Helper

class StartApplication : Application() {
    companion object {
        @Volatile
        lateinit var INSTANCE: StartApplication
        lateinit var service: ForumService
        lateinit var weatherService: ForumService
    }

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
        service = Network.initService(Helper.BASE_URL)
        weatherService = Network.initService(Helper.WEATHER_URL)
    }

}