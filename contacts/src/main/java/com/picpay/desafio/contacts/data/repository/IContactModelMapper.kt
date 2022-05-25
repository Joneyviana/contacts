package com.picpay.desafio.contacts.data.repository

import com.picpay.desafio.contacts.data.local.ContactRoomModel
import com.picpay.desafio.contacts.data.network.ContactNetworkModel
import com.picpay.desafio.contacts.domain.ContactListModel
import com.picpay.desafio.contacts.domain.ContactModel
import java.lang.Exception

interface IContactModelMapper {

    fun getContactListModelFromNetwork(
        contactNetworkModels: List<ContactNetworkModel>
    ): ContactListModel

    fun getContactListModelFromRoom(
        contactRoomModels: List<ContactRoomModel>
    ): ContactListModel

    fun getContactListModelFromException(exception: Exception): ContactListModel
}