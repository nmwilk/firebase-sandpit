package com.sageone.firebasesandpit

import android.app.Activity
import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.Menu
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var firebaseRemoteConfig: FirebaseRemoteConfig

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        transactionsList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        transactionsList.adapter = TransactionsAdapter(this)

        firebaseRemoteConfig = FirebaseRemoteConfig.getInstance()
        firebaseRemoteConfig.setConfigSettings(FirebaseRemoteConfigSettings.Builder().setDeveloperModeEnabled(BuildConfig.DEBUG).build())
    }

    override fun onResume() {
        super.onResume()

        firebaseRemoteConfig.fetch(0).addOnCompleteListener(this) {
            Log.d(TAG, "firebase remote config completion, success ${it.isSuccessful}")
            if (it.isSuccessful) {
                firebaseRemoteConfig.activateFetched()
                val colorHexString = firebaseRemoteConfig.getString("themeColor")
                val argb = java.lang.Long.parseLong(colorHexString, 16).toInt()
                supportActionBar?.setBackgroundDrawable(ColorDrawable(argb))
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.activity_main, menu)

        menu?.findItem(R.id.add_transaction)?.setOnMenuItemClickListener { newTransaction() }

        return true
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_CODE_NEW && resultCode == Activity.RESULT_OK && data != null) {
            val newTransaction = Transaction.fromIntent(data)
            Log.d(TAG, newTransaction.toString())
        }
    }

    private fun newTransaction(): Boolean {
        val intent = Intent(this, NewTransactionActivity::class.java)
        startActivityForResult(intent, REQUEST_CODE_NEW)
        return true
    }

    companion object {
        val REQUEST_CODE_NEW = 100

        val TAG: String = MainActivity::class.java.simpleName
    }
}
