package com.picpay.desafio.contacts.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.picpay.desafio.contacts.domain.GetListContactsUseCase
import com.picpay.desafio.contacts.ui.message.Message
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

data class ContactUIState(
    val isLoading: Boolean = false,
    val contacts: List<ContactPresentationModel> = emptyList()
)



class ContactViewModel(private val contactsUseCase: GetListContactsUseCase) : ViewModel() {

    private val _events = MutableSharedFlow<Message>()
    val events = _events.asSharedFlow()

    private val _state = MutableStateFlow(ContactUIState())
    val state = _state.asStateFlow()

    init {
        loadContacts()
    }

    fun loadContacts() = viewModelScope.launch {
        _state.update { it.copy(isLoading = true) }
        contactsUseCase.invoke().collect { contactList ->
            _state.update {
                it.copy(
                    contacts = contactList.contacts.map { contact ->
                        ContactPresentationModel(
                            contact.image,
                            contact.name,
                            contact.id,
                            contact.username
                        )
                    },
                    isLoading = contactList.contacts.isEmpty() && contactList.isOffline
                )
            }
        }
    }

}