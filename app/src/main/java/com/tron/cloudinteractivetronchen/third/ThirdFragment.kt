package com.tron.cloudinteractivetronchen.third

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.tron.cloudinteractivetronchen.R
import com.tron.cloudinteractivetronchen.databinding.FragmentSecondBinding
import com.tron.cloudinteractivetronchen.databinding.FragmentThirdBinding
import com.tron.cloudinteractivetronchen.ext.getVmFactory
import com.tron.cloudinteractivetronchen.first.FirstViewModel

class ThirdFragment : Fragment() {

    private val viewModel by viewModels<ThirdViewModel> { getVmFactory(
        ThirdFragmentArgs.fromBundle(
            requireArguments()
        ).photoProperty)
     }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentThirdBinding.inflate(inflater, container, false)

        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        binding.imageView2.setImageBitmap(viewModel.photo.value?.bitmap)

        binding.imageView2.setOnClickListener {
            findNavController().navigateUp()
        }

        // Inflate the layout for this fragment
        return binding.root
    }
}