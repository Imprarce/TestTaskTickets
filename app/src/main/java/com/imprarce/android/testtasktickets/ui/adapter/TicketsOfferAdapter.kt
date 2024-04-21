package com.imprarce.android.testtasktickets.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.imprarce.android.testtasktickets.R
import com.imprarce.android.ticket_api.entity.TicketsOffer

class TicketsOfferAdapter(private val ticketsOfferList: List<TicketsOffer>) :
    RecyclerView.Adapter<TicketsOfferAdapter.TicketsOfferViewHolder>() {

    inner class TicketsOfferViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTextView: TextView = itemView.findViewById(R.id.name)
        val priceTextView: TextView = itemView.findViewById(R.id.price)
        val timeTextView: TextView = itemView.findViewById(R.id.time)
        val circle: ImageView = itemView.findViewById(R.id.circle)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TicketsOfferViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_recycler_view_tickets_offer, parent, false)
        return TicketsOfferViewHolder(view)
    }

    override fun onBindViewHolder(holder: TicketsOfferViewHolder, position: Int) {
        val ticketsOffer = ticketsOfferList[position]

        holder.nameTextView.text = ticketsOffer.title
        val priceString = ticketsOffer.price.value.toString().replace(Regex("(\\d)(?=(\\d{3})+\$)"), "$1 ")
        holder.priceTextView.text = "$priceString ₽"

        val time = ticketsOffer.timeRange.toString().replace("[", "").replace("]", "")
        holder.timeTextView.text = time

        val colors = arrayOf(
            R.color.red,
            R.color.blue,
            R.color.white
        )
        holder.circle.setColorFilter(
            ContextCompat.getColor(
                holder.itemView.context,
                colors[position % colors.size]
            )
        )
    }

    override fun getItemCount(): Int {
        return minOf(ticketsOfferList.size, 3)
    }
}