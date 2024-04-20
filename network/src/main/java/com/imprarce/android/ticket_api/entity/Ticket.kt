package com.imprarce.android.ticket_api

import com.google.gson.annotations.SerializedName
import com.imprarce.android.ticket_api.additionalentity.*

data class Tickets(
    val tickets: List<Ticket>
)

data class Ticket(
    @SerializedName("id") val id: Int,
    @SerializedName("badge") val badge: String?,
    @SerializedName("price") val price: Price,
    @SerializedName("provider_name") val providerName: String,
    @SerializedName("company") val company: String,
    @SerializedName("departure") val departure: Departure,
    @SerializedName("arrival") val arrival: Arrival,
    @SerializedName("has_transfer") val hasTransfer: Boolean,
    @SerializedName("has_visa_transfer") val hasVisaTransfer: Boolean,
    @SerializedName("luggage") val luggage: Luggage?,
    @SerializedName("hand_luggage") val handLuggage: HandLuggage,
    @SerializedName("is_returnable") val isReturnable: Boolean,
    @SerializedName("is_exchangable") val isExchangable: Boolean
)