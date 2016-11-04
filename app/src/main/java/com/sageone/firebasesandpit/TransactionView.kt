package com.sageone.firebasesandpit

import android.content.Context
import android.view.View
import android.widget.FrameLayout
import kotlinx.android.synthetic.main.transaction_row.view.*
import java.text.NumberFormat

/**
 * Item in the RecyclerView
 */
class TransactionView(context: Context) : FrameLayout(context) {

    init {
        View.inflate(context, R.layout.transaction_row, this)
    }

    fun setDetails(title: String, amount: Double) {
        this.title.text = title
        this.amount.text = NumberFormat.getCurrencyInstance().format(amount)
    }
}