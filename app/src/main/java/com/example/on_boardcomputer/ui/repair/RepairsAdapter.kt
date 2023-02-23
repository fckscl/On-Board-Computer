package com.example.on_boardcomputer.ui.repair

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.on_boardcomputer.R
import com.example.on_boardcomputer.database.Repair
import com.example.on_boardcomputer.util.convertLongToDateString

class RepairsAdapter: RecyclerView.Adapter<RepairsAdapter.ViewHolder> (){
    var data = listOf<Repair>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        holder.nameRepair.text = item.nameOfRepair
        holder.dateRepair.text = item.dateOfRepair
        holder.costRepair.text = item.costOfRepair.toString()
//TODO: Upgrade view holder
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater
            .inflate(R.layout.list_item_repair, parent, false)
        return ViewHolder(view)
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val nameRepair: TextView = itemView.findViewById(R.id.repairType)
        val dateRepair: TextView = itemView.findViewById(R.id.repairDate)
        val costRepair: TextView = itemView.findViewById(R.id.repairCost)
    }

}