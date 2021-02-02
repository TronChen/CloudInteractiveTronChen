package com.tron.cloudinteractivetronchen.second

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.tron.cloudinteractivetronchen.CloudApplication
import com.tron.cloudinteractivetronchen.R
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
            if (it.bitmap == null){
                Toast.makeText(CloudApplication.instance.applicationContext,
                    R.string.do_not_touch,Toast.LENGTH_SHORT).show()
            }else {
                findNavController().navigate(SecondFragmentDirections.actionGlobalThirdFragment(it))
            }
        }, viewModel)

        binding.recyclerView.adapter = adapter

        viewModel.photo.observe(viewLifecycleOwner, Observer { it ->
            adapter.submitList(it)
        })

        viewModel.updateBitmap.observe(viewLifecycleOwner, Observer {
            Handler().post(Runnable {
                adapter.notifyItemChanged(it)
            })
        })


        // Inflate the layout for this fragment
        return binding.root
    }
}