package com.imprarce.android.ticket_api.additionalentity

import com.google.gson.annotations.SerializedName
import java.util.*

data class Departure(
    @SerializedName("town") val town: String,
    @SerializedName("date") val date: Date,
    @SerializedName("airport") val airport: String
)
