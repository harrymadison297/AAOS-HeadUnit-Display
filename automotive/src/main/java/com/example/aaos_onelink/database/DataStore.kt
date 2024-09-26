package com.example.aaos_onelink.database

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.map

const val PORT_DATASTORE = "port_datastore"

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = PORT_DATASTORE)

class DataStoreManager(val context: Context) {
    companion object {
        val PORT_KEY = stringPreferencesKey("serial_port")
    }

    suspend fun saveToDataStore(context: Context, port: String) {
        context.dataStore.edit {
            it[PORT_KEY] = port
        }
    }

    fun getFromDataStore() = context.dataStore.data.map {
        it[PORT_KEY] ?: "/dev/ttyS5"
    }

    suspend fun clearDataStore() = context.dataStore.edit {
        it.clear()
    }
}