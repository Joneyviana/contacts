package com.picpay.desafio.contacts.domain

import com.picpay.desafio.contacts.data.local.ContactRoomModel
import com.picpay.desafio.contacts.data.network.ContactNetworkModel
import com.picpay.desafio.contacts.data.repository.IContactModelMapper
import java.lang.Exception

class ContactModelMapper : IContactModelMapper {

    override fun getContactListModelFromNetwork(
        contactNetworkModels: List<ContactNetworkModel>
    ): ContactListModel {
        return ContactListModel(contactNetworkModels.map {
            ContactModel(it.img, it.name, it.id, it.username) } , isOffline = false)
    }

    override fun getContactListModelFromRoom(
        contactRoomModels: List<ContactRoomModel>
    ): ContactListModel {
        return ContactListModel(contactRoomModels.map {
            ContactModel(it.image, it.name, it.id, it.userName) })
    }

    override fun getContactListModelFromException(exception: Exception): ContactListModel {
        return ContactListModel(exception = exception)
    }

}