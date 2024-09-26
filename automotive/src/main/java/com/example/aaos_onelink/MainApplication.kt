package com.example.aaos_onelink

import android.app.Application
import com.example.aaos_onelink.database.SerialInformationDatabase

class MainApplication : Application() {

    companion object {
        lateinit var SerialInformationDB: SerialInformationDatabase
    }

    override fun onCreate() {
        super.onCreate()
        SerialInformationDB = SerialInformationDatabase.getDatabase(this)
    }
}