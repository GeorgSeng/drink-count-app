package com.example.drinkcountapp.viewmodels

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.drinkcountapp.R
import com.example.drinkcountapp.data.Drink
import com.example.drinkcountapp.dummy.DummyContent.DummyItem
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

/**
 * [RecyclerView.Adapter] that can display a [DummyItem].
 * TODO: Replace the implementation with code for your data type.
 */
class MyDrinkHistoryRecyclerViewAdapter(
    private val values: List<Drink>
) : RecyclerView.Adapter<MyDrinkHistoryRecyclerViewAdapter.ViewHolder>() {

    companion object {

        private var formatter: DateTimeFormatter =
            DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm")
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_drink, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.idView.text = LocalDateTime.parse(item.localDateTime).format(formatter)
        holder.contentView.text = String.format("%s: %d Cups", item.drinkType, item.amount)
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val idView: TextView = view.findViewById(R.id.item_number)
        val contentView: TextView = view.findViewById(R.id.content)

        override fun toString(): String {
            return super.toString() + " '" + contentView.text + "'"
        }
    }
}