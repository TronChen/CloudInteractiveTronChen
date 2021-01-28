package com.tron.cloudinteractivetronchen.second

import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import android.util.LruCache
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.tron.cloudinteractivetronchen.databinding.FragmentSecondBinding
import com.tron.cloudinteractivetronchen.ext.getVmFactory

class SecondFragment : Fragment() {

    private val viewModel by viewModels<SecondViewModel> { getVmFactory() }
    lateinit var cache: LruCache<String, Bitmap>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentSecondBinding.inflate(inflater, container, false)

        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        val adapter = SecondAdapter(SecondAdapter.PhotoOnItemClickListener{
            findNavController().navigate(SecondFragmentDirections.actionGlobalThirdFragment(it))
        }, viewModel)

        binding.recyclerView.adapter = adapter

        viewModel.photo.observe(viewLifecycleOwner, Observer { it ->
            it.forEach {
                viewModel.returnBitmap(it)
            Log.e("LLL", viewModel.list.toString())
            }
            adapter.submitList(it)
        })

        // Inflate the layout for this fragment
        return binding.root
    }

}