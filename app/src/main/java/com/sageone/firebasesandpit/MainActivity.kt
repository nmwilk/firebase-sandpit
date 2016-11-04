package com.sageone.firebasesandpit

import android.app.Activity
import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.Menu
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), FirebaseRemoteConfigFragment.UpdateListener {

    lateinit var firebaseDatabaseFragment: FirebaseDatabaseFragment
    lateinit var sharedPrefs: SharedPrefs

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sharedPrefs = SharedPrefs(this)

        transactionsList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        transactionsList.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.VERTICAL))

        val firebaseRemoteConfigFragment = FirebaseRemoteConfigFragment()
        firebaseRemoteConfigFragment.updateListener = this

        firebaseDatabaseFragment = FirebaseDatabaseFragment()

        supportFragmentManager.beginTransaction()
                .add(firebaseRemoteConfigFragment, "FirebaseRemoteConfig")
                .add(firebaseDatabaseFragment, "FirebaseDatabase")
                .add(FirebaseAnalyticsFragment(), "FirebaseAnalytics")
                .commit()

        actionBarColorChanged(sharedPrefs.getToolbarColor())
    }

    override fun onStart() {
        super.onStart()

        val transactionsAdapter = TransactionsAdapter(this, firebaseDatabaseFragment.transactionListReference())
        transactionsList.adapter = transactionsAdapter
        transactionsList.setHasFixedSize(true)
    }

    override fun actionBarColorChanged(argb: Int) {
        sharedPrefs.setToolbarColor(argb)
        supportActionBar?.setBackgroundDrawable(ColorDrawable(argb))
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
            firebaseDatabaseFragment.addTransaction(newTransaction)
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
