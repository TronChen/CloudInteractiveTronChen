package com.tron.cloudinteractivetronchen.second

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.tron.cloudinteractivetronchen.R
import com.tron.cloudinteractivetronchen.databinding.FragmentFirstBinding
import com.tron.cloudinteractivetronchen.databinding.FragmentSecondBinding
import com.tron.cloudinteractivetronchen.ext.getVmFactory
import com.tron.cloudinteractivetronchen.first.FirstViewModel

class SecondFragment : Fragment() {

    private val viewModel by viewModels<SecondViewModel> { getVmFactory() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentSecondBinding.inflate(inflater, container, false)

        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        val adapter = SecondAdapter(SecondAdapter.PhotoOnItemClickListener{

        })

        binding.recyclerView.adapter = adapter

        viewModel.photo.observe(viewLifecycleOwner, Observer {
            it.forEach {
                Log.e("Tron", it.toString())
            }
            adapter.submitList(it)

        })

        // Inflate the layout for this fragment
        return binding.root
    }
}