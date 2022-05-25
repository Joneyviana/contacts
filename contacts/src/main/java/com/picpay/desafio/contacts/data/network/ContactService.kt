package com.picpay.desafio.contacts.data.network

import retrofit2.Call
import retrofit2.http.GET


interface ContactService {

    @GET("users")
    suspend fun getContacts(): List<ContactNetworkModel>
}