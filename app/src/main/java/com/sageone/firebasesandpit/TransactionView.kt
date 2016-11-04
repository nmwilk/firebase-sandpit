package com.sageone.firebasesandpit

import android.content.Context
import android.util.AttributeSet
import android.widget.RelativeLayout
import kotlinx.android.synthetic.main.transaction_row.view.*

/**
 * Item in the RecyclerView
 */
class TransactionView(context: Context, attributeSet: AttributeSet) : RelativeLayout(context, attributeSet) {

    fun setDetails(title: String, amount: String, date: String) {
        this.title.text = title
        this.amount.text = amount
        this.date.text = date
    }
}