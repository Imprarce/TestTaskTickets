package com.imprarce.android.testtasktickets.ui.ticketscreens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.imprarce.android.testtasktickets.R
import com.imprarce.android.testtasktickets.databinding.FragmentAllTicketBinding
import com.imprarce.android.testtasktickets.ui.MainViewModel
import com.imprarce.android.testtasktickets.ui.adapter.OffersAdapter
import com.imprarce.android.testtasktickets.ui.adapter.TicketsAdapter
import com.imprarce.android.ticket_api.Ticket
import com.imprarce.android.ticket_api.entity.Offer
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AllTicketFragment : Fragment() {

    private val viewModel: MainViewModel by viewModels()

    private var _binding: FragmentAllTicketBinding? = null
    private val binding get() = _binding!!

    private var cities : String? = R.string.plug_text.toString()
    private var date : String? = R.string.plug_text.toString()
    private var passengers : Int? = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.fetchTickets()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAllTicketBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        cities = arguments?.getString("cities")
        date = arguments?.getString("date")
        passengers = arguments?.getInt("passengers")

        binding.cities.text = cities ?: ""
        binding.dateAndPassengers.text = date + ", " + passengers + " " + getString(R.string.passenger)

        binding.filter.setOnClickListener {
            findNavController().navigate(R.id.action_allTicketFragment2_to_filtersTicketFragment2)
        }

        binding.back.setOnClickListener {
            findNavController().popBackStack()
        }

        viewModel.ticketsLiveData.observe(viewLifecycleOwner){tickets ->
            if(tickets != null) setAdapter(tickets)
        }
    }

    private fun setAdapter(tickets: List<Ticket>){
        val adapter = TicketsAdapter(tickets)
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}