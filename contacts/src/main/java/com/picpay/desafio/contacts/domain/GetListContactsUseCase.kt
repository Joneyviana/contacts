package com.picpay.desafio.contacts.domain

import com.picpay.desafio.contacts.data.repository.ContactRepository
import kotlinx.coroutines.flow.flow

class GetListContactsUseCase(private val contactsRepository: ContactRepository) {
    private val contactListModel = ContactListModel()

    fun invoke() = flow {
        contactsRepository.getContacts(ContactModelMapper()).collect {
            it.exception?.let { exception ->
                emit(contactListModel.copy(exception = exception))
            } ?: emit(
                contactListModel.copy(contacts = it.contacts, isOffline = it.isOffline)
            )
        }

    }
}