package com.example.on_boardcomputer.ui.history

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.on_boardcomputer.R
import com.example.on_boardcomputer.database.StatDatabase
import com.example.on_boardcomputer.databinding.FragmentHistoryBinding

class HistoryFragment : Fragment() {

//    private lateinit var viewModel: HistoryViewModel
    private lateinit var binding: FragmentHistoryBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val application = requireNotNull(this.activity).application
        val dataSource = StatDatabase.getInstance(application).statDatabaseDao
        val viewModelFactory = HistoryViewModelFactory(dataSource, application)
        val historyViewModel =
            ViewModelProvider(
                this, viewModelFactory).get(HistoryViewModel::class.java)
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_history,
            container,
            false
        )
        binding.lifecycleOwner = this
        binding.historyViewModel = historyViewModel

        val adapter =  AverageStatAdapter()
        binding.listStat.adapter = adapter

        historyViewModel.measurements.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)
            }
        })

        binding.btnClear.setOnClickListener {
            historyViewModel.onClear()
        }

        return binding.root
    }
}