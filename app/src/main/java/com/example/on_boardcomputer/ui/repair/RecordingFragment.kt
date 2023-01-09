package com.example.on_boardcomputer.ui.repair

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.on_boardcomputer.R
import com.example.on_boardcomputer.database.RepairDatabaseDao
import com.example.on_boardcomputer.database.StatDatabase
import com.example.on_boardcomputer.databinding.FragmentRecordingBinding
import com.example.on_boardcomputer.ui.history.HistoryViewModel
import com.example.on_boardcomputer.ui.history.HistoryViewModelFactory
import java.util.*

class RecordingFragment : Fragment() {

    companion object {
        fun newInstance() = RecordingFragment()
    }

    private lateinit var viewModel: RecordingViewModel
    private lateinit var binding: FragmentRecordingBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val application = requireNotNull(this.activity).application
        val dataSource = StatDatabase.getInstance(application).repairDatabaseDao
        val viewModelFactory = RecordingViewModelFactory(dataSource, application)
        val viewModel =
            ViewModelProvider(
                this, viewModelFactory).get(RecordingViewModel::class.java)

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_recording,
            container,
            false
        )
        binding.lifecycleOwner = this

        binding.btnUpdate.setOnClickListener {
            val today = Calendar.getInstance()
            val datePicker = binding.datePicker

            viewModel.add(binding.editTextTextRepairName.text.toString(),
                datePicker.dayOfMonth,
                datePicker.month,
                datePicker.year,
                binding.editTextCost.text.toString().toInt())

            it.findNavController().navigate(R.id.repairsFragment)
        }

        return binding.root
    }

}