package com.imprarce.android.testtasktickets.ui.ticketscreens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.imprarce.android.testtasktickets.R
import com.imprarce.android.testtasktickets.databinding.FragmentAllTicketBinding

class AllTicketFragment : Fragment() {

    private var _binding: FragmentAllTicketBinding? = null
    private val binding get() = _binding!!

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

        binding.filter.setOnClickListener {
            findNavController().navigate(R.id.action_allTicketFragment_to_filtersTicketFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}