package com.imprarce.android.testtasktickets.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.imprarce.android.testtasktickets.R
import com.imprarce.android.testtasktickets.model.CountryItem
import com.imprarce.android.testtasktickets.ui.ticketscreens.helper.OnCountryClickListener

class CountryAdapter(
    private val countries: List<CountryItem>,
    private val clickListener: OnCountryClickListener
) :
    RecyclerView.Adapter<CountryAdapter.CountryViewHolder>() {

    inner class CountryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        private val imageCountry: ImageView = itemView.findViewById(R.id.image_country)
        private val nameCountry: TextView = itemView.findViewById(R.id.name_country)
        private val descriptionCountry: TextView = itemView.findViewById(R.id.description_country)

        fun bind(country: CountryItem) {
            imageCountry.setImageResource(country.imageResource)
            nameCountry.text = country.name
            descriptionCountry.text = country.description
        }

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                val country = countries[position]
                clickListener.onCountryClick(country)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_recycler_view_search, parent, false)
        return CountryViewHolder(view)
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        val country = countries[position]
        holder.bind(country)
    }

    override fun getItemCount(): Int {
        return countries.size
    }
}
