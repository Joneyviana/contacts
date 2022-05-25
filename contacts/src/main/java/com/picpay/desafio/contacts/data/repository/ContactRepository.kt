package com.picpay.desafio.contacts.data.repository

import com.picpay.desafio.contacts.data.local.ContactDao
import com.picpay.desafio.contacts.data.local.ContactRoomModel
import com.picpay.desafio.contacts.data.network.ContactService
import kotlinx.coroutines.flow.flow

class ContactRepository(
    private val contactService: ContactService,
    private val contactDao: ContactDao,
) {

    fun getContacts(contactMapper: IContactModelMapper) = flow {
        emit(contactMapper.getContactListModelFromRoom(contactDao.getContacts()))
        try {
            val contacts = contactService.getContacts()
            contactDao.insertAll(contacts.map {
                ContactRoomModel(
                    it.id, it.name, it.username, it.img
                )
            })
            emit(contactMapper.getContactListModelFromNetwork(contacts))
        } catch (exception: Exception) {
            emit(contactMapper.getContactListModelFromException(exception))
        }
    }

}