package com.imprarce.android.ticket_api.additionalentity

import com.google.gson.annotations.SerializedName

data class Luggage(
    @SerializedName("has_luggage") val hasLuggage: Boolean,
    @SerializedName("price") val price: Price
)
