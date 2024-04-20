package com.imprarce.android.ticket_api.additionalentity

import com.google.gson.annotations.SerializedName

data class HandLuggage(
    @SerializedName("has_hand_luggage") val hasHandLuggage: Boolean,
    @SerializedName("size") val size: String?
)
