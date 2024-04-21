package com.imprarce.android.testtasktickets.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.imprarce.android.testtasktickets.R
import com.imprarce.android.ticket_api.entity.Offer

class OffersAdapter(private val offersList: List<Offer>) :
    RecyclerView.Adapter<OffersAdapter.OfferViewHolder>() {

    inner class OfferViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image: ImageView = itemView.findViewById(R.id.image)
        val name: TextView = itemView.findViewById(R.id.name)
        val nameCity: TextView = itemView.findViewById(R.id.name_city)
        val price: TextView = itemView.findViewById(R.id.price)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OfferViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_recycler_view_main, parent, false)
        return OfferViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: OfferViewHolder, position: Int) {
        val currentItem = offersList[position]
        val imageResourceId = R.drawable::class.java.getField("img_tape_${position + 1}").getInt(null)

        holder.image.setImageResource(imageResourceId)
        holder.name.text = currentItem.title
        holder.nameCity.text = currentItem.town
        holder.price.text = "от "+ currentItem.price.value.toString() + " ₽"
    }

    override fun getItemCount(): Int {
        return offersList.size
    }
}