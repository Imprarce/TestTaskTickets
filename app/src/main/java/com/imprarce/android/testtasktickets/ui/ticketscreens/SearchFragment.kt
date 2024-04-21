package com.imprarce.android.testtasktickets.ui.ticketscreens

import android.os.Bundle
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
import com.imprarce.android.testtasktickets.ui.ticketscreens.adapter.CountryAdapter

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

        setAdapter(countries)

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
        val cityName = country.name
        val bundle = Bundle().apply {
            putString("cityName", cityName)
        }
        findNavController().navigate(R.id.action_searchFragment_to_searchTheCountryFragment, bundle)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}