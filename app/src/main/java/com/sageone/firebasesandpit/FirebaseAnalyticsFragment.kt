package com.sageone.firebasesandpit

import android.os.Bundle
import android.support.v4.app.Fragment
import com.google.firebase.analytics.FirebaseAnalytics

/**
 * non-UI fragment for Firebase Analytics
 */
class FirebaseAnalyticsFragment: Fragment() {
    lateinit var firebaseAnalytics: FirebaseAnalytics

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        firebaseAnalytics = FirebaseAnalytics.getInstance(activity)
    }
}