package com.picpay.desafio.contacts.data.repository

import com.picpay.desafio.contacts.data.local.ContactDao
import com.picpay.desafio.contacts.data.local.ContactRoomModel
import com.picpay.desafio.contacts.data.network.ContactNetworkModel
import com.picpay.desafio.contacts.data.network.ContactService
import com.picpay.desafio.contacts.domain.ContactModel
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.Test


class ContactsRepositoryTest {
    private val api = mockk<ContactService>()
    private val dao = mockk<ContactDao>()
    private val contactMapper = mockk<IContactModelMapper>()
    private val repository = ContactRepository(api, dao)

    @Test
    fun `Should return contact list when api call success`(): Unit = runBlocking {
        coEvery { api.getContacts() } returns listOf(
            ContactNetworkModel(img = "img", name = "name", id = 1, username = "username")
        )
        coEvery { dao.getContacts() } returns listOf()

        val actual = repository.getContacts(contactMapper).toList()
        val expected = mutableListOf(ContactModel("img", "name", 3, "username"))
        assertEquals(expected, actual.last())
    }

    @Test
    fun `Should return contact list from room`(): Unit = runBlocking {
        val throwable = Throwable(message = "error")
        coEvery { api.getContacts() } throws throwable

        coEvery { dao.getContacts() } returns listOf(
            ContactRoomModel(3, "img", "name", "username")
        )

        val actual = repository.getContacts(contactMapper).toList()
        val expected = mutableListOf(ContactModel("img", "name", 3, "username"))
        assertEquals(expected, actual.last())
    }
}