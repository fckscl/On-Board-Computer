package com.example.on_boardcomputer.ui.repair

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import com.example.on_boardcomputer.R
import com.example.on_boardcomputer.database.Repair
import com.example.on_boardcomputer.database.StatDatabase
import com.example.on_boardcomputer.databinding.FragmentRepairsBinding
import com.example.on_boardcomputer.ui.history.HistoryViewModel
import com.example.on_boardcomputer.ui.history.HistoryViewModelFactory

class RepairsFragment : Fragment() {

    private lateinit var binding: FragmentRepairsBinding

    companion object {
        fun newInstance() = RepairsFragment()
    }

    private lateinit var viewModel: RepairsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val application = requireNotNull(this.activity).application
        val dataSource = StatDatabase.getInstance(application).repairDatabaseDao
        val viewModelFactory = RepairsViewModelFactory(dataSource, application)
        val viewModel =
            ViewModelProvider(
                this, viewModelFactory).get(RepairsViewModel::class.java)

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_repairs,
            container,
            false
        )
        binding.lifecycleOwner = this

        val adapter = RepairsAdapter()
        binding.listRepairs.adapter = adapter
//        adapter.data = viewModel.dataSource.getAll().value ?: listOf(Repair(nameOfRepair = "name", dateOfRepair = 123, costOfRepair = 11))
//        adapter.data = viewModel.dataSource.getAll()
        viewModel.measurements.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.data = it
            }
        })


        binding.btnAdd.setOnClickListener {
            it.findNavController().navigate(R.id.recordingFragment)
        }

        return binding.root
    }

}