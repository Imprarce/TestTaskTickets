package com.imprarce.android.testtasktickets.ui.plugscreens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.imprarce.android.testtasktickets.R
import com.imprarce.android.testtasktickets.databinding.FragmentHotelsBinding

class HotelsFragment : Fragment() {
    private var _binding: FragmentHotelsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHotelsBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}