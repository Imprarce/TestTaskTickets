package com.imprarce.android.ticket_api.entity

import com.google.gson.annotations.SerializedName
import com.imprarce.android.ticket_api.additionalentity.Price

data class OffersTickets(
    @SerializedName("tickets_offers") val ticketsOffers: List<TicketsOffer>
)

data class TicketsOffer(
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("time_range") val timeRange: List<String>,
    @SerializedName("price") val price: Price
)