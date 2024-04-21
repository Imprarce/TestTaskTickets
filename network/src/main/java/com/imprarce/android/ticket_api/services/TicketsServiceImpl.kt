package com.imprarce.android.ticket_api.services

import com.imprarce.android.ticket_api.NetworkService
import com.imprarce.android.ticket_api.Tickets

class TicketsServiceImpl : NetworkService(), TicketsService {

    private val ticketsService: TicketsService = createService("http://example.com/")
        .create(TicketsService::class.java)


    override suspend fun getTickets(): Tickets {
        return ticketsService.getTickets()
    }


}