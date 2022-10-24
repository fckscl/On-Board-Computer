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

class AverageStatAdapter: RecyclerView.Adapter<AverageStatAdapter.ViewHolder> (){
    var data = listOf<AverageStat>()
    set(value) {
        field = value
        notifyDataSetChanged()
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        holder.startTime.text = convertLongToDateString(item.startMeasuringMilli)
        holder.endTime.text = convertLongToDateString(item.endMeasuringMilli)
        val diff = item.endMeasuringMilli.minus(item.startMeasuringMilli)
        holder.duration.text = String.format("%d:%d:%d", diff / 1000 / 60 / 60, diff / 1000 / 60, diff / 1000)
        holder.averageEngine.text = String.format("Engine: %d", item.midEngine)
        holder.averageOnBoard.text = String.format("OnBoard: %d", item.midOnBoard)
        holder.averageVoltage.text = String.format("Voltage: %d", item.midVoltage)
//TODO: Upgrade view holder
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater
            .inflate(R.layout.list_item_stat, parent, false)
        return ViewHolder(view)
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val startTime: TextView = itemView.findViewById(R.id.startTime)
        val endTime: TextView = itemView.findViewById(R.id.endTime)
        val duration: TextView = itemView.findViewById(R.id.duration)
        val averageOnBoard: TextView = itemView.findViewById(R.id.averageOnBoard)
        val averageEngine: TextView = itemView.findViewById(R.id.averageEngine)
        val averageVoltage: TextView = itemView.findViewById(R.id.averageVoltage)

    }

}