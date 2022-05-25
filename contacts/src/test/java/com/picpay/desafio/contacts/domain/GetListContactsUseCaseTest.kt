package com.picpay.desafio.contacts.domain

import com.picpay.desafio.contacts.data.local.ContactRoomModel
import com.picpay.desafio.contacts.data.network.ContactNetworkModel
import junit.framework.Assert.assertEquals
import org.junit.Test


class GetListContactsUseCaseTest {
    private val contactModelMapper = ContactModelMapper()

    @Test
    fun `From the network should return contact list `() {
        val contactsFromNetwork = mutableListOf(
            ContactNetworkModel
                ("img", "name", 3, "username")
        )
        val result = contactModelMapper.getContactListModelFromNetwork(contactsFromNetwork)

        assertEquals(
            ContactListModel(
                contactsFromNetwork.map { ContactModel() },
                isOffline = false
            ), result
        )
    }

    @Test
    fun `From the database should return contact list`() {
        val contactsFromRoom = mutableListOf(
            ContactRoomModel("img", "name", 3, "username")
        )
        val result = contactModelMapper.getContactListModelFromRoom(contactsFromRoom)

        assertEquals(
            ContactListModel(
                contactsFromRoom.map { ContactModel() },
                isOffline = true
            ), result
        )
    }

    @Test
    fun `Should return contact list from failure`() {

    }
}

