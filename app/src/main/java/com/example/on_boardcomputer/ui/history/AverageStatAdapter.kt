package com.example.on_boardcomputer.ui.history

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
//import androidx.core.content.res.TypedArrayUtils.getString
import androidx.recyclerview.widget.RecyclerView
import com.example.on_boardcomputer.R
import com.example.on_boardcomputer.database.AverageStat
import com.example.on_boardcomputer.databinding.ListItemStatBinding
import com.example.on_boardcomputer.util.convertLongToDateString

//import com.example.on_boardcomputer.util.TextItemViewHolder

class AverageStatAdapter : ListAdapter<AverageStat, AverageStatAdapter.ViewHolder>(AverageStatDiffCallback()){

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)

//TODO: Upgrade view holder
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    class ViewHolder private constructor(val binding: ListItemStatBinding): RecyclerView.ViewHolder(binding.root){
        val startTime: TextView = binding.startTime
        val endTime: TextView = binding.endTime
        val averageOnBoard: TextView = binding.averageOnBoard
        val averageEngine: TextView = binding.averageEngine
        val averageVoltage: TextView = binding.averageVoltage

        fun bind(
            item: AverageStat
        ) {
            startTime.text = convertLongToDateString(item.startMeasuringMilli)
            endTime.text = convertLongToDateString(item.endMeasuringMilli)
            averageEngine.text = String.format("%.3f C", item.midEngine)
            averageOnBoard.text = String.format("%.3f C", item.midOnBoard)
            averageVoltage.text = String.format("%.3f V", item.midVoltage)
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ListItemStatBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }
}

class AverageStatListener(val clickListener: (statId: Long) -> Unit) {
    fun onClick(stat: AverageStat) = clickListener(stat.statId)
}

class AverageStatDiffCallback :
    DiffUtil.ItemCallback<AverageStat>() {
    override fun areItemsTheSame(oldItem: AverageStat, newItem: AverageStat): Boolean {
        return oldItem.statId == newItem.statId
    }

    override fun areContentsTheSame(oldItem: AverageStat, newItem: AverageStat): Boolean {
        return oldItem == newItem
    }
}