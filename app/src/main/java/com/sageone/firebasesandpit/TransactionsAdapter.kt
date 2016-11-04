package com.sageone.firebasesandpit

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup

/**
 * Adapter for transaction list.
 */
class TransactionsAdapter(val context: Context) : RecyclerView.Adapter<TransactionsAdapter.TransactionViewHolder>() {

    private val rowHeight: Int

    init {
        rowHeight = context.resources.getDimensionPixelSize(R.dimen.row_height)
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): TransactionViewHolder {
        val view = TransactionView(context)
        view.layoutParams = RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT, rowHeight)
        return TransactionViewHolder(view)
    }

    override fun getItemCount(): Int {
        return 40
    }

    override fun onBindViewHolder(holder: TransactionViewHolder?, position: Int) {
        holder?.view!!.setDetails("${position+1}", position.toDouble() + 0.99)
    }

    inner class TransactionViewHolder(val view: TransactionView) : RecyclerView.ViewHolder(view) {
    }
}
