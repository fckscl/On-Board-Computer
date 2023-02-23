package com.example.on_boardcomputer.ui.display

import android.annotation.SuppressLint
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.preference.PreferenceManager
import com.example.on_boardcomputer.R
import com.example.on_boardcomputer.database.StatDatabase
import com.example.on_boardcomputer.databinding.FragmentDisplayStateBinding
import com.jjoe64.graphview.helper.DateAsXAxisLabelFormatter

class DisplayStateFragment : Fragment() {

    private lateinit var binding: FragmentDisplayStateBinding
//    private lateinit var series: LineGraphSeries<DataPoint>

    private lateinit var viewModel: DisplayStateViewModel
    private lateinit var sharedPreferences: SharedPreferences

    @SuppressLint("SimpleDateFormat")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val application = requireNotNull(this.activity).application
        val dataSource = StatDatabase.getInstance(application).statDatabaseDao
        val viewModelFactory = DisplayStateViewModelFactory(dataSource, application)
        viewModel =
            ViewModelProvider(
                this, viewModelFactory).get(DisplayStateViewModel::class.java)
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_display_state,
            container,
            false
        )
        binding.displayStateViewModel = viewModel
        binding.lifecycleOwner = this

        val graphes = listOf(binding.graphEngine, binding.graphOnBoard, binding.graphVoltage)
        val serieses = listOf(viewModel.seriesEngine, viewModel.seriesOnBoard, viewModel.seriesVoltage)
        val thresholds = listOf(
            listOf(viewModel.seriesEngineMax, viewModel.seriesEngineMin),
            listOf(viewModel.seriesOnBoardMax, viewModel.seriesOnBoardMin),
            listOf(viewModel.seriesVoltageMax, viewModel.seriesVoltageMin))

        for (i in 0..2){
            graphes[i].gridLabelRenderer.numHorizontalLabels = 10
            graphes[i].gridLabelRenderer.labelFormatter = DateAsXAxisLabelFormatter(activity)
            graphes[i].viewport.isScalable = true
            graphes[i].viewport.isScrollable = true
            thresholds[i][0].value?.color = Color.RED
            thresholds[i][1].value?.color = Color.YELLOW
            graphes[i].addSeries(serieses[i].value)
            graphes[i].addSeries(thresholds[i][0].value)
            graphes[i].addSeries(thresholds[i][1].value)
        }

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(requireContext())

        binding.btnStart.setOnClickListener{
            viewModel.onStartTracking()
        }

        binding.btnEnd.setOnClickListener {
            viewModel.onStopTracking()
        }

        binding.valueVoltage.setOnClickListener{
            val min = sharedPreferences.getString("min_voltage", "")?.toDouble()
            val max = sharedPreferences.getString("max_voltage", "")?.toDouble()
            viewModel.voltageChange(min!!, max!!)
        }

        binding.valueEngine.setOnClickListener {
            val min = sharedPreferences.getString("min_engine", "")?.toDouble()
            val max = sharedPreferences.getString("max_engine", "")?.toDouble()
            viewModel.engineChange(min!!, max!!)
        }

        binding.valueOnBoard.setOnClickListener {
            val min = sharedPreferences.getString("min_on_board", "")?.toDouble()
            val max = sharedPreferences.getString("max_on_board", "")?.toDouble()
            viewModel.onBoardChange(min!!, max!!)
        }

        viewModel.voltage.observe(viewLifecycleOwner, Observer { newVoltage ->
            binding.valueVoltage.text = newVoltage.toString()
        })

        viewModel.tEngine.observe(viewLifecycleOwner, Observer { newVoltage ->
            binding.valueEngine.text = newVoltage.toString()
        })

        viewModel.tOnBoard.observe(viewLifecycleOwner, Observer { newVoltage ->
            binding.valueOnBoard.text = newVoltage.toString()
        })

        return binding.root
    }
}