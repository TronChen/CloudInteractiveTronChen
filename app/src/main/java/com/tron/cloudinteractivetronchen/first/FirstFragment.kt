package com.tron.cloudinteractivetronchen.first

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.tron.cloudinteractivetronchen.R
import com.tron.cloudinteractivetronchen.databinding.FragmentFirstBinding
import com.tron.cloudinteractivetronchen.ext.getVmFactory


class FirstFragment : Fragment() {

    private val viewModel by viewModels<FirstViewModel> { getVmFactory() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentFirstBinding.inflate(inflater, container, false)

        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        binding.buttonAPI.setOnClickListener {
            findNavController().navigate(R.id.secondFragment)
        }

        // Inflate the layout for this fragment
        return binding.root
    }
}