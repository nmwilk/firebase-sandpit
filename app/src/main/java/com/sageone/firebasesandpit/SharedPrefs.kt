package com.sageone.firebasesandpit

import android.content.Context
import android.content.SharedPreferences

class SharedPrefs(val context: Context) {

    fun setToolbarColor(argb: Int) = getPrefs().edit().putLong(KEY_TOOLBAR_COLOR, argb.toLong()).commit()
    fun getToolbarColor(): Int = getPrefs().getLong(KEY_TOOLBAR_COLOR, 0xFFFFFFFF).toInt()

    private fun getPrefs(): SharedPreferences {
        return context.getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE)
    }

    companion object {
        val SHARED_PREFS = "fkl4nkjnjkfef"
        val KEY_TOOLBAR_COLOR = "gtkjnkj3nk33eiu"
    }
}