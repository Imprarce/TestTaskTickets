package com.imprarce.android.ticket_api.services

import com.imprarce.android.ticket_api.NetworkService
import com.imprarce.android.ticket_api.entity.OffersTickets

class OffersTicketsServiceImpl : NetworkService(), OffersTicketsService {

    private val offersTicketsService: OffersTicketsService = createService("http://example.com/")
        .create(OffersTicketsService::class.java)

    override suspend fun getOffersTickets(): OffersTickets {
        return offersTicketsService.getOffersTickets()
    }

}