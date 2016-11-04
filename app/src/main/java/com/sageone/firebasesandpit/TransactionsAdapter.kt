package com.sageone.firebasesandpit

import android.content.Context
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.google.firebase.database.Query

/**
 * Adapter for transaction list.
 */
class TransactionsAdapter(val context: Context, val reference: Query) :
        FirebaseRecyclerAdapter<Transaction, TransactionViewHolder>(Transaction::class.java, R.layout.transaction_row, TransactionViewHolder::class.java, reference) {
    override fun populateViewHolder(viewHolder: TransactionViewHolder?, model: Transaction?, position: Int) {
        viewHolder!!.transactionView.setDetails(model!!.title, model.amount, model.datestamp)
    }

    private val rowHeight: Int

    init {
        rowHeight = context.resources.getDimensionPixelSize(R.dimen.row_height)
    }
}
