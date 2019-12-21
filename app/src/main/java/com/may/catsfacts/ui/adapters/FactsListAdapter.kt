package com.may.catsfacts.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.may.catsfacts.R
import com.may.catsfacts.models.FactItem

class FactsListAdapter(private val items: ArrayList<FactItem>) : RecyclerView.Adapter<FactsListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(viewGroup.context).inflate(R.layout.item_facts_list, viewGroup, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.statement = viewHolder.itemView.findViewById(R.id.statementTextView) as TextView
        viewHolder.statement.text =items[position].Statement
        viewHolder.data = viewHolder.itemView.findViewById(R.id.createAtTextView) as TextView
        viewHolder.data.text =items[position].CreateAt
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        lateinit var statement: TextView
        lateinit var data: TextView
    }

}