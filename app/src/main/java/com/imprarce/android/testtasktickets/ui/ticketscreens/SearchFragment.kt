package com.imprarce.android.testtasktickets.ui.ticketscreens

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.imprarce.android.testtasktickets.R
import com.imprarce.android.testtasktickets.databinding.FragmentSearchBinding
import com.imprarce.android.testtasktickets.model.CountryItem
import com.imprarce.android.testtasktickets.ui.adapter.CountryAdapter
import com.imprarce.android.testtasktickets.ui.ticketscreens.helper.OnCountryClickListener

class SearchFragment : BottomSheetDialogFragment(), OnCountryClickListener {

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val countries = listOf(
            CountryItem(R.drawable.image_country_1, getString(R.string.Istanbul), getString(R.string.a_popular_destination)),
            CountryItem(R.drawable.image_country_2, getString(R.string.Sochi), getString(R.string.a_popular_destination)),
            CountryItem(R.drawable.image_country_3, getString(R.string.Phuket), getString(R.string.a_popular_destination))
        )

        val cityNameFirst = arguments?.getString("cityNameFirst")
        val cityNameSecond = arguments?.getString("cityNameSecond")

        binding.firstCountry.setText(cityNameFirst)
        binding.secondCountry.setText(cityNameSecond)

        if(cityNameSecond != ""){
            binding.clearButton.visibility = View.VISIBLE
        }

        binding.secondCountry.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(s: Editable?) {
                binding.clearButton.visibility = if (s.isNullOrEmpty()) View.GONE else View.VISIBLE
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })

        binding.clearButton.setOnClickListener {
            binding.secondCountry.text.clear()
        }

        setAdapter(countries)
        setBottomSheet()

        binding.square1.setOnClickListener {
            findNavController().navigate(R.id.action_searchFragment2_to_fragmentPlug2)
        }

        binding.square2.setOnClickListener {
            navigateToSearchTheCountryFragment("Куда угодно")
        }

        binding.square3.setOnClickListener {
            findNavController().navigate(R.id.action_searchFragment2_to_fragmentPlug2)
        }

        binding.square4.setOnClickListener {
            findNavController().navigate(R.id.action_searchFragment2_to_fragmentPlug2)
        }
    }

    private fun setBottomSheet(){
        val bottomSheet : FrameLayout = dialog?.findViewById(com.google.android.material.R.id.design_bottom_sheet)!!

        bottomSheet.layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT

        val behavior = BottomSheetBehavior.from(bottomSheet)
        behavior.apply {
            peekHeight = resources.displayMetrics.heightPixels
            state = BottomSheetBehavior.STATE_EXPANDED

            addBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
                override fun onStateChanged(bottomSheet: View, newState: Int) {
                }

                override fun onSlide(bottomSheet: View, slideOffset: Float) {}
            })
        }
    }

    private fun setAdapter(countryList: List<CountryItem>){
        val adapter = CountryAdapter(countryList, this)
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
    }

    override fun onCountryClick(country: CountryItem) {
        val cityNameFirst = binding.firstCountry.text.toString()
        val cityNameSecond = country.name
        val bundle = Bundle().apply {
            putString("cityNameFirst", cityNameFirst)
            putString("cityNameSecond", cityNameSecond)
        }
        findNavController().navigate(R.id.action_searchFragment2_to_searchTheCountryFragment2, bundle)
    }

    private fun navigateToSearchTheCountryFragment(somewhere: String){
        val cityNameFirst = binding.firstCountry.text.toString()
        val bundle = Bundle().apply {
            putString("cityNameFirst", cityNameFirst)
            putString("cityNameSecond", somewhere)
        }
        findNavController().navigate(R.id.action_searchFragment2_to_searchTheCountryFragment2, bundle)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}