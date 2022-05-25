package com.picpay.desafio.contacts.ui

import androidx.recyclerview.widget.DiffUtil
import com.picpay.desafio.contacts.data.network.ContactNetworkModel

class ContactListDiffCallback(
    private val oldList: List<ContactNetworkModel>,
    private val newList: List<ContactNetworkModel>
) : DiffUtil.Callback() {

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].username.equals(newList[newItemPosition].username)
    }

    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return true
    }
}