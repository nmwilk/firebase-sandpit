package com.sageone.firebasesandpit

import android.os.Bundle
import android.support.v4.app.Fragment
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.Query

/**
 * non-UI fragment for Firebase Analytics
 */
class FirebaseDatabaseFragment : Fragment() {
    private lateinit var database: FirebaseDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        database = FirebaseDatabase.getInstance()
    }

    companion object {
        val KEY_TRANSACTIONS_LIST = "transactions"
    }

    fun transactionListReference(): Query {
        return database.getReference(FirebaseDatabaseFragment.KEY_TRANSACTIONS_LIST)
    }

    fun addTransaction(newTransaction: Transaction) {
        database.getReference(KEY_TRANSACTIONS_LIST).push().setValue(newTransaction)
    }
}