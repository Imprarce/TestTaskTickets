package com.imprarce.android.testtasktickets.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.imprarce.android.testtasktickets.R
import com.imprarce.android.testtasktickets.databinding.FragmentMainBinding
import com.imprarce.android.testtasktickets.ui.adapter.OffersAdapter
import com.imprarce.android.testtasktickets.utils.PreferenceHelper
import com.imprarce.android.ticket_api.entity.Offer
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment() {
    private val viewModel: MainViewModel by viewModels()
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.fetchOffers()
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val lastInput = PreferenceHelper.getLastInput(requireContext())
        binding.firstEditText.setText(lastInput)

        viewModel.offersLiveData.observe(viewLifecycleOwner){ offers ->
            if(offers != null) setAdapter(offers)
        }

        binding.firstEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                PreferenceHelper.saveLastInput(requireContext(), s.toString())
            }
        })

        binding.secondEditText.setOnClickListener {
            val cityNameFirst = binding.firstEditText.text.toString()
            val cityNameSecond = binding.secondEditText.text.toString()
            val bundle = Bundle().apply {
                putString("cityNameFirst", cityNameFirst)
                putString("cityNameSecond", cityNameSecond)
            }
            findNavController().navigate(R.id.action_mainFragment_to_nav_search, bundle)
        }
    }

    private fun setAdapter(offersList: List<Offer>){
        val adapter = OffersAdapter(offersList)
        binding.recyclerView.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}