package com.sageone.firebasesandpit

import android.content.Intent

/**
 * Item model
 */
data class Transaction(var title: String, var amount: String, var datestamp: String) {

    constructor() : this("", "", "")

    fun fillIntent(intent: Intent): Intent {
        intent.putExtra(TITLE, title)
        intent.putExtra(AMOUNT, amount)
        intent.putExtra(DATESTAMP, datestamp)
        return intent
    }

    companion object {
        val TITLE = "sgskfsd"
        val AMOUNT = "t4iokjfnkef"
        val DATESTAMP = "glk3lk2"

        fun fromIntent(intent: Intent) = Transaction(intent.getStringExtra(TITLE), intent.getStringExtra(AMOUNT), intent.getStringExtra(DATESTAMP))
    }
}