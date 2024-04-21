package com.imprarce.android.testtasktickets.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.imprarce.android.testtasktickets.R
import com.imprarce.android.ticket_api.Ticket
import java.text.SimpleDateFormat
import java.util.*

class TicketsAdapter(private val tickets: List<Ticket>) :
    RecyclerView.Adapter<TicketsAdapter.TicketsViewHolder>() {

    inner class TicketsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val badgeTextView: TextView = itemView.findViewById(R.id.badgeTextView)
        val priceTextView: TextView = itemView.findViewById(R.id.priceTextView)
        val timeTextView1: TextView = itemView.findViewById(R.id.timeTextView_1)
        val timeTextView2: TextView = itemView.findViewById(R.id.timeTextView_2)
        val slashTextView: TextView = itemView.findViewById(R.id.slashTextView)
        val airportTextView1: TextView = itemView.findViewById(R.id.airport_1)
        val airportTextView2: TextView = itemView.findViewById(R.id.airport_2)
        val timeInFlyTextView: TextView = itemView.findViewById(R.id.timeInFlyTextView)
        val transferTextView: TextView = itemView.findViewById(R.id.transferTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TicketsViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_recycler_view_tickets, parent, false)
        return TicketsViewHolder(view)
    }

    override fun onBindViewHolder(holder: TicketsViewHolder, position: Int) {
        val ticket = tickets[position]
        if(ticket.badge != null) {
            holder.badgeTextView.visibility = View.VISIBLE
        }
        holder.badgeTextView.text = ticket.badge ?: ""
        val priceString = ticket.price.value.toString().replace(Regex("(\\d)(?=(\\d{3})+\$)"), "$1 ")
        holder.priceTextView.text = "$priceString ₽"
        holder.timeTextView1.text = formatTime(ticket.departure.date)
        holder.timeTextView2.text = formatTime(ticket.arrival.date)
        holder.airportTextView1.text = ticket.departure.airport
        holder.airportTextView2.text = ticket.arrival.airport
        holder.timeInFlyTextView.text = "${calculateFlightTime(ticket.departure.date, ticket.arrival.date)} ч в пути"
        if(!ticket.hasTransfer){
            holder.transferTextView.text = "Без пересадки"
        } else {
            holder.transferTextView.visibility = View.GONE
            holder.slashTextView.visibility = View.GONE
        }
    }

    override fun getItemCount(): Int {
        return tickets.size
    }

    private fun formatTime(date: Date): String {
        val dateFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
        return dateFormat.format(date)
    }

    private fun calculateFlightTime(departureDate: Date, arrivalDate: Date): Float {
        val millisecondsInHour = 1000 * 60 * 60
        val difference = arrivalDate.time - departureDate.time
        val hours = difference / millisecondsInHour
        val minutes = (difference % millisecondsInHour) / (1000 * 60)
        val totalHours = hours.toFloat() + (minutes.toFloat() / 60)

        return if (totalHours % 1 <= 0.25) {
            totalHours.toInt().toFloat()
        } else if (totalHours % 1 >= 0.75) {
            (totalHours.toInt() + 1).toFloat()
        } else {
            (totalHours.toInt() + 0.5f)
        }
    }
}