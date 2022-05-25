package com.picpay.desafio.contacts.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.picpay.desafio.contacts.data.network.RetrofitModule.service
import com.picpay.desafio.contacts.data.network.ContactNetworkModel
import com.picpay.desafio.contacts.R
import com.picpay.desafio.contacts.databinding.FragmentContactsBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ContactFragment : Fragment() {

    private var binding: FragmentContactsBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = ContactListAdapter()
        binding?.recyclerView?.adapter = adapter
        binding?.recyclerView?.layoutManager = LinearLayoutManager(activity)

        binding?.userListProgressBar?.visibility = View.VISIBLE
        /*service.getContacts()
            .enqueue(object : Callback<List<ContactNetworkModel>> {
                override fun onFailure(call: Call<List<ContactNetworkModel>>, t: Throwable) {
                    val message = getString(R.string.error)

                    binding?.userListProgressBar?.visibility = View.GONE
                    binding?.recyclerView?.visibility = View.GONE

                    Toast.makeText(activity, message, Toast.LENGTH_SHORT)
                        .show()
                }

                override fun onResponse(call: Call<List<ContactNetworkModel>>, response: Response<List<ContactNetworkModel>>) {
                    binding?.userListProgressBar?.visibility = View.GONE

                    adapter.users = response.body()!!
                }
            })
            */

    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentContactsBinding.inflate(inflater, container, false)
        return binding?.root
    }
}