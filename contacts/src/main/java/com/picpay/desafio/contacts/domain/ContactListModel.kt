package com.picpay.desafio.contacts.domain

import java.lang.Exception

data class ContactListModel(
    val contacts : List<ContactModel> = emptyList(),
    val isOffline : Boolean = true,
    val exception: Exception? = null,
) {
    fun isFailedToLoad(): Boolean {
        return contacts.isEmpty() && exception != null
    }
}