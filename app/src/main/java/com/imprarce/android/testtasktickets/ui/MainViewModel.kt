package com.imprarce.android.testtasktickets.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.imprarce.android.ticket_api.ReadFromAssets
import com.imprarce.android.ticket_api.Ticket
import com.imprarce.android.ticket_api.entity.Offer
import com.imprarce.android.ticket_api.entity.TicketsOffer
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val readFromAssets: ReadFromAssets
) : ViewModel() {

    private val _offersLiveData = MutableLiveData<List<Offer>?>()

    val offersLiveData: LiveData<List<Offer>?> get() = _offersLiveData

    private val _ticketsOffersLiveData = MutableLiveData<List<TicketsOffer>?>()

    val ticketsOffersLiveData: LiveData<List<TicketsOffer>?> get() = _ticketsOffersLiveData

    private val _ticketsLiveData = MutableLiveData<List<Ticket>?>()

    val ticketsLiveData: LiveData<List<Ticket>?> get() = _ticketsLiveData

    fun fetchOffers() {
        viewModelScope.launch {
            try {
                val offers = readFromAssets.readFromAssets<Offer>("offers.json", "offers")
                _offersLiveData.value = offers
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun fetchTicketsOffers() {
        viewModelScope.launch {
            try {
                val offers_tickets = readFromAssets.readFromAssets<TicketsOffer>(
                    "offers_tickets.json",
                    "tickets_offers"
                )
                _ticketsOffersLiveData.value = offers_tickets
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun fetchTickets() {
        viewModelScope.launch {
            try {
                val tickets = readFromAssets.readFromAssets<Ticket>("tickets.json", "tickets")
                _ticketsLiveData.value = tickets
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }


}