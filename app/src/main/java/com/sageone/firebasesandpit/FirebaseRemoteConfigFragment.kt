package com.sageone.firebasesandpit

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings

/**
 * non-UI fragment for Firebase Remote Config
 */
class FirebaseRemoteConfigFragment : Fragment() {
    lateinit var firebaseRemoteConfig: FirebaseRemoteConfig

    var updateListener: UpdateListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        firebaseRemoteConfig = FirebaseRemoteConfig.getInstance()
        firebaseRemoteConfig.setConfigSettings(FirebaseRemoteConfigSettings.Builder().setDeveloperModeEnabled(BuildConfig.DEBUG).build())
    }

    override fun onResume() {
        super.onResume()

        updateRemoteConfig()
    }

    fun updateRemoteConfig() {
        firebaseRemoteConfig.fetch(0).addOnCompleteListener(activity) {
            Log.d(MainActivity.TAG, "firebase remote config completion, success ${it.isSuccessful}")
            if (it.isSuccessful) {
                firebaseRemoteConfig.activateFetched()
                val colorHexString = firebaseRemoteConfig.getString("themeColor")
                val argb = java.lang.Long.parseLong(colorHexString, 16).toInt()
                updateListener?.actionBarColorChanged(argb)
            }
        }
    }

    interface UpdateListener {
        fun actionBarColorChanged(argb: Int)
    }
}