package com.example.on_boardcomputer.ui.repair

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.on_boardcomputer.R

class RepairsFragment : Fragment() {

    companion object {
        fun newInstance() = RepairsFragment()
    }

    private lateinit var viewModel: RepairsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_repairs, container, false)
    }

}