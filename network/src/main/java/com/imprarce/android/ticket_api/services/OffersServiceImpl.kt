package com.imprarce.android.ticket_api.services

import com.imprarce.android.ticket_api.NetworkService
import com.imprarce.android.ticket_api.OffersService
import com.imprarce.android.ticket_api.entity.Offers

class OffersServiceImpl : NetworkService(), OffersService {

    private val offersService: OffersService = createService("http://example.com/")
        .create(OffersService::class.java)

    override suspend fun getOffers(): Offers {
        return offersService.getOffers()
    }
}