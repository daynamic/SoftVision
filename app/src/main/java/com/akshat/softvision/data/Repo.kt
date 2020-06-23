package com.akshat.softvision.data

import com.akshat.softvision.model.Response

/**
 * Created by Akshat on 22/06/20.
 */
class Repo : SafeApiRequest() {
    suspend fun getService(): Response {
        return apiRequest { NetworkService().getData() }
    }

}