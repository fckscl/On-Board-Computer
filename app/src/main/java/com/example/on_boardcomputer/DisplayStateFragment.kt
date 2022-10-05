package com.example.on_boardcomputer

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.on_boardcomputer.databinding.FragmentDisplayStateBinding
import com.jjoe64.graphview.DefaultLabelFormatter
import com.jjoe64.graphview.GraphView
import com.jjoe64.graphview.helper.DateAsXAxisLabelFormatter
import com.jjoe64.graphview.series.BarGraphSeries
import com.jjoe64.graphview.series.DataPoint
import com.jjoe64.graphview.series.LineGraphSeries
import java.text.DateFormat
import java.util.*

class DisplayStateFragment : Fragment() {

    private lateinit var binding: FragmentDisplayStateBinding
//    private lateinit var series: LineGraphSeries<DataPoint>

    companion object {
        fun newInstance() = DisplayStateFragment()
    }

    private val viewModel: DisplayStateViewModel by viewModels()

    @SuppressLint("SimpleDateFormat")
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
        val seriesEngine = LineGraphSeries<DataPoint>()
        val seriesVoltage = LineGraphSeries<DataPoint>()
        val seriesOnBoard = LineGraphSeries<DataPoint>()
        val graphes = listOf(binding.graphEngine, binding.graphOnBoard, binding.graphVoltage)
        val serieses = listOf(seriesEngine, seriesOnBoard, seriesVoltage)
        for (i in 0..2){
            graphes[i].gridLabelRenderer.numHorizontalLabels = 4
            graphes[i].gridLabelRenderer.labelFormatter = DateAsXAxisLabelFormatter(activity)
            graphes[i].viewport.isScalable = true
            graphes[i].addSeries(serieses[i])
        }

        binding.button.setOnClickListener{
            viewModel.voltageChange()
        }

        viewModel.voltage.observe(viewLifecycleOwner, Observer { newVoltage ->
            binding.valueVoltage.text = newVoltage.toString()
            seriesVoltage.appendData(
                DataPoint(Date(),
                viewModel.voltage.value!!),
                true,
                200)
            binding.graphVoltage.addSeries(seriesVoltage)
            Log.i("main", DateFormat.getTimeInstance().format(Date()))
        })

        return binding.root
    }
}