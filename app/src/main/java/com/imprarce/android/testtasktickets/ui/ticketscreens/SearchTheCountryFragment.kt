package com.imprarce.android.testtasktickets.ui.ticketscreens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.imprarce.android.testtasktickets.R
import com.imprarce.android.testtasktickets.databinding.FragmentSearchTheCountryBinding


class SearchTheCountryFragment : Fragment() {

    private var _binding: FragmentSearchTheCountryBinding? = null
    private val binding get() = _binding!!

    private var cityName: String? = R.string.plug_text.toString()

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

        if(savedInstanceState != null) cityName = arguments?.getString("cityName")

        binding.chipSettings.setOnClickListener {
            findNavController().navigate(R.id.action_searchTheCountryFragment_to_filtersFragment)
        }

        binding.seeAllTicketsButton.setOnClickListener {
            findNavController().navigate(R.id.action_searchTheCountryFragment_to_allTicketFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}