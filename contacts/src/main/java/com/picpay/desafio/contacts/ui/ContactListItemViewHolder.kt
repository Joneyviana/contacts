package com.picpay.desafio.contacts.ui

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.picpay.desafio.contacts.data.network.ContactNetworkModel
import com.picpay.desafio.contacts.R
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.list_item_user.view.*

class ContactListItemViewHolder(
    itemView: View
) : RecyclerView.ViewHolder(itemView) {

    fun bind(contact: ContactPresentationModel) {
        itemView.name.text = contact.name
        itemView.username.text = contact.username
        itemView.progressBar.visibility = View.VISIBLE
        Picasso.get()
            .load(contact.image)
            .error(R.drawable.ic_round_account_circle)
            .into(itemView.picture, object : Callback {
                override fun onSuccess() {
                    itemView.progressBar.visibility = View.GONE
                }

                override fun onError(e: Exception?) {
                    itemView.progressBar.visibility = View.GONE
                }
            })
    }
}