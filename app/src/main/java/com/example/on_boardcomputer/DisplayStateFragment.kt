package com.example.on_boardcomputer

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.on_boardcomputer.databinding.FragmentDisplayStateBinding

class DisplayStateFragment : Fragment() {

    private lateinit var binding: FragmentDisplayStateBinding

    companion object {
        fun newInstance() = DisplayStateFragment()
    }

    private val viewModel: DisplayStateViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_display_state,
            container,
            false
        )

        binding.button.setOnClickListener{
            viewModel.voltageChange()
        }

        viewModel.voltage.observe(viewLifecycleOwner, Observer { newVoltage ->
            binding.voltage.text = newVoltage.toString()
        })

        return binding.root
    }
}