package com.example.on_boardcomputer.ui.history

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
//import androidx.core.content.res.TypedArrayUtils.getString
import androidx.recyclerview.widget.RecyclerView
import com.example.on_boardcomputer.R
import com.example.on_boardcomputer.database.AverageStat
import com.example.on_boardcomputer.util.convertLongToDateString

//import com.example.on_boardcomputer.util.TextItemViewHolder

class AverageStatAdapter(val clickListener:AverageStatListener): RecyclerView.Adapter<AverageStatAdapter.ViewHolder> (){
    var data = listOf<AverageStat>()
    set(value) {
        field = value
        notifyDataSetChanged()
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        holder.bind(item)

//TODO: Upgrade view holder
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    class ViewHolder private constructor(itemView: View): RecyclerView.ViewHolder(itemView){
        val startTime: TextView = itemView.findViewById(R.id.startTime)
        val endTime: TextView = itemView.findViewById(R.id.endTime)
//        val duration: TextView = itemView.findViewById(R.id.duration)
        val averageOnBoard: TextView = itemView.findViewById(R.id.averageOnBoard)
        val averageEngine: TextView = itemView.findViewById(R.id.averageEngine)
        val averageVoltage: TextView = itemView.findViewById(R.id.averageVoltage)

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
                val view = layoutInflater
                    .inflate(R.layout.list_item_stat, parent, false)
                return ViewHolder(view)
            }
        }
    }



}

class AverageStatListener(val clickListener: (statId: Long) -> Unit) {
    fun onClick(stat: AverageStat) = clickListener(stat.statId)
}