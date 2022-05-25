package com.picpay.desafio.contacts.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.picpay.desafio.contacts.databinding.FragmentContactsBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel


class ContactFragment : Fragment() {

    private var binding: FragmentContactsBinding? = null
    private val viewModel: ContactViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = ContactListAdapter()
        binding?.recyclerView?.adapter = adapter
        binding?.recyclerView?.layoutManager = LinearLayoutManager(activity)

        binding?.userListProgressBar?.visibility = View.VISIBLE

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state.collect {
                    binding?.userListProgressBar?.isVisible = it.isLoading
                    adapter.contacts = it.contacts
                }
            }
        }
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