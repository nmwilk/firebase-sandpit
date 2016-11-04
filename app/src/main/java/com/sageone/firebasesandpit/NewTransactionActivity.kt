package com.sageone.firebasesandpit

import android.app.Activity
import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_new_transaction.*
import java.text.SimpleDateFormat
import java.util.*

/**
 * Screen to enter a new transaction.
 */
class NewTransactionActivity : AppCompatActivity() {
    private var saveButton: MenuItem? = null
    private val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_new_transaction)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setBackgroundDrawable(ColorDrawable(SharedPrefs(this).getToolbarColor()))

        titleEntry.addTextChangedListener(TitleTextWatcher())
        amountEntry.addTextChangedListener(AmountTextWatcher())

        supportFragmentManager.beginTransaction()
                .add(FirebaseAnalyticsFragment(), "FirebaseAnalytics")
                .add(FirebaseRemoteConfigFragment(), "FirebaseRemoteConfig")
                .commit()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.activity_new_transaction, menu)
        saveButton = menu?.findItem(R.id.done)
        saveButton?.setOnMenuItemClickListener { saveTransaction() }

        enableSaveButton()
        return true
    }

    private fun saveTransaction(): Boolean {
        val transaction = Transaction(titleEntry.text.toString(), amountEntry.text.toString(), dateFormat.format(Date()))
        setResult(Activity.RESULT_OK, transaction.fillIntent(Intent()))
        finish()
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == android.R.id.home) {
            setResult(Activity.RESULT_CANCELED)
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun enableSaveButton() {
        val enabled = amountEntry.text.toString().isNotEmpty() && titleEntry.text.toString().isNotEmpty()
        saveButton?.isEnabled = enabled
        saveButton?.icon?.alpha = if (enabled) 255 else 120
    }

    inner class AmountTextWatcher : TextWatcher {
        override fun afterTextChanged(p0: Editable?) {
        }

        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            enableSaveButton()
        }

    }

    inner class TitleTextWatcher : TextWatcher {
        override fun afterTextChanged(p0: Editable?) {
        }

        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            enableSaveButton()
        }
    }
}

