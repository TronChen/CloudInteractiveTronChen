package com.tron.cloudinteractivetronchen.second

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.tron.cloudinteractivetronchen.data.local.Cache
import com.tron.cloudinteractivetronchen.databinding.FragmentSecondBinding
import com.tron.cloudinteractivetronchen.ext.getVmFactory


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
            findNavController().navigate(SecondFragmentDirections.actionGlobalThirdFragment(it))
        }, viewModel)

        binding.recyclerView.adapter = adapter

        viewModel.photo.observe(viewLifecycleOwner, Observer { it ->
            it.forEach {
//                it.id?.let { it1 -> viewModel.loadURLToBitmapReturnPhoto(it, it1) }
                it.bitmap?.let { it1 ->
                    Cache.instance.saveBitmapToCache(it.thumbnailUrl.toString(),
                        it1
                    )
                Log.e("WWWWW",
                    Cache.instance.retrieveBitmapFromCache(it.thumbnailUrl.toString()).toString()
                )
                }
            }
            adapter.submitList(it)
        })

        viewModel.updateBitmap.observe(viewLifecycleOwner, Observer {
            adapter.notifyItemChanged(it)
        })


        // Inflate the layout for this fragment
        return binding.root
    }
}