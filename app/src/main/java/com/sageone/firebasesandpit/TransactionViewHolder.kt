package com.sageone.firebasesandpit

import android.support.v7.widget.RecyclerView
import android.view.View

/**
 * RecyclerView ViewHolder for the item in the transaction list.
 */
class TransactionViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    var transactionView: TransactionView

    init {
        transactionView = view as TransactionView
    }
}