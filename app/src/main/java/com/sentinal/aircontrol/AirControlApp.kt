package com.sentinal.aircontrol

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.sentinal.aircontrol.model.SettingsState

class AirControlApp : Application() {

    lateinit var settingsState: SettingsState
    private lateinit var prefs: SharedPreferences
    private val gson = Gson()

    override fun onCreate() {
        super.onCreate()
        instance = this
        prefs = getSharedPreferences("aircontrol_settings", Context.MODE_PRIVATE)
        loadSettings()
    }

    private fun loadSettings() {
        val json = prefs.getString("settings", null)
        settingsState = if (json != null) {
            try {
                gson.fromJson(json, SettingsState::class.java)
            } catch (e: Exception) {
                SettingsState()
            }
        } else {
            SettingsState()
        }
    }

    fun saveSettings() {
        val json = gson.toJson(settingsState)
        prefs.edit().putString("settings", json).apply()
    }

    companion object {
        lateinit var instance: AirControlApp
            private set
    }
}
