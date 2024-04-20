package com.imprarce.android.ticket_api.entity

import com.google.gson.annotations.SerializedName
import com.imprarce.android.ticket_api.additionalentity.Price

data class Offers(
    @SerializedName("offers") val offers: List<Offer>
)

data class Offer(
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("town") val town: String,
    @SerializedName("price") val price: Price
)