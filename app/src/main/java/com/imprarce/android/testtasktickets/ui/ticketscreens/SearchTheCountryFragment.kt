package com.imprarce.android.testtasktickets.ui.ticketscreens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.imprarce.android.testtasktickets.R
import com.imprarce.android.testtasktickets.databinding.FragmentSearchTheCountryBinding
import com.imprarce.android.testtasktickets.ui.MainViewModel
import com.imprarce.android.testtasktickets.ui.adapter.TicketsOfferAdapter
import com.imprarce.android.testtasktickets.utils.DateFormatter
import com.imprarce.android.testtasktickets.utils.DatePickerManager
import com.imprarce.android.ticket_api.entity.TicketsOffer
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SearchTheCountryFragment : Fragment() {
    private val viewModel: MainViewModel by viewModels()

    private var _binding: FragmentSearchTheCountryBinding? = null
    private val binding get() = _binding!!

    private var cityName: String? = R.string.plug_text.toString()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.fetchTicketsOffers()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSearchTheCountryBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val cityNameFirst = arguments?.getString("cityNameFirst")
        val cityNameSecond = arguments?.getString("cityNameSecond")

        binding.firstCountry.setText(cityNameFirst)
        binding.secondCountry.setText(cityNameSecond)

        binding.chipSettings.setOnClickListener {
            findNavController().navigate(R.id.action_searchTheCountryFragment2_to_filtersFragment2)
        }

        binding.arrowBack.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.imageSwap.setOnClickListener {
            val firstCountryText = binding.firstCountry.text
            binding.firstCountry.text = binding.secondCountry.text
            binding.secondCountry.text = firstCountryText
        }

        binding.imageDelete.setOnClickListener {
            binding.secondCountry.setText("")
        }

        binding.chipDate.text = DateFormatter.formatCurrentDate()

        val datePickerManager = DatePickerManager(requireContext())

        binding.chipDate.setOnClickListener {
            datePickerManager.showDatePickerDialog { selectedDate ->
                binding.chipDate.text = DateFormatter.formatDate(selectedDate)
            }
        }

        binding.chipBack.setOnClickListener {
            datePickerManager.showDatePickerDialog {}
        }

        viewModel.ticketsOffersLiveData.observe(viewLifecycleOwner) { ticketsOffer ->
            if (ticketsOffer != null) {
                setAdapter(ticketsOffer)
                binding.showMoreTextView.visibility = View.GONE
            }
        }

        binding.seeAllTicketsButton.setOnClickListener {
            navigateToAllTicketsFragment()
        }
    }

    private fun navigateToAllTicketsFragment() {
        val cities =
            binding.firstCountry.text.toString() + "-" + binding.secondCountry.text.toString()
        val date = DateFormatter.formatDateForBundle(binding.chipDate.text.toString())
        val passengers = binding.chipPersons.text.toString().toIntOrNull() ?: 1
        val bundle = Bundle().apply {
            putString("cities", cities)
            putString("date", date)
            putInt("passengers", passengers)
        }
        findNavController().navigate(
            R.id.action_searchTheCountryFragment2_to_allTicketFragment2,
            bundle
        )
    }

    private fun setAdapter(ticketsOfferList: List<TicketsOffer>) {
        val adapter = TicketsOfferAdapter(ticketsOfferList)
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}