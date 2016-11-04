package com.sageone.firebasesandpit

import android.content.Intent

/**
 * Item model
 */
data class Transaction(val title: String, val amount: String) {

    fun fillIntent(intent: Intent): Intent {
        intent.putExtra(TITLE, title)
        intent.putExtra(AMOUNT, amount)
        return intent
    }

    companion object {
        val TITLE = "sgskfsd"
        val AMOUNT = "t4iokjfnkef"

        fun fromIntent(intent: Intent) = Transaction(intent.getStringExtra(TITLE), intent.getStringExtra(AMOUNT))
    }
}