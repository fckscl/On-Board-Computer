package com.example.on_boardcomputer.util

import android.annotation.SuppressLint
import android.content.res.Resources
import android.text.Html
import android.text.Spanned
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.on_boardcomputer.R
import com.example.on_boardcomputer.database.AverageStat
import java.text.SimpleDateFormat

@SuppressLint("SimpleDateFormat")
fun convertLongToDateString(systemTime: Long): String {
    return SimpleDateFormat("MMM-dd-yyyy' 'HH:mm")
        .format(systemTime).toString()
}

fun formatMeasurements(nights: List<AverageStat>, resources: Resources): Spanned {
    val sb = StringBuilder()
    sb.apply {
        append(resources.getString(R.string.last))
        nights.forEach {
            append("<br>")
            append(resources.getString(R.string.start_measuring))
            append("\t${convertLongToDateString(it.startMeasuringMilli)}<br>")
            if (it.endMeasuringMilli != it.startMeasuringMilli) {
                append(resources.getString(R.string.end_measuring))
                append("\t${convertLongToDateString(it.endMeasuringMilli)}<br>")
                append(resources.getString(R.string.average_on_board))
                append("\t${it.midOnBoard}<br>")
                append(resources.getString(R.string.average_engine))
                append("\t${it.midEngine}<br>")
                append(resources.getString(R.string.average_voltage))
                append("\t${it.midVoltage}<br>")
                append(resources.getString(R.string.time_measuring))
                // Hours
                append("\t ${it.endMeasuringMilli.minus(it.startMeasuringMilli) / 1000 / 60 / 60}:")
                // Minutes
                append("${it.endMeasuringMilli.minus(it.startMeasuringMilli) / 1000 / 60}:")
                // Seconds
                append("${it.endMeasuringMilli.minus(it.startMeasuringMilli) / 1000}<br><br>")
            }
        }
    }
    return Html.fromHtml(sb.toString(), Html.FROM_HTML_MODE_LEGACY)
}

//class TextItemViewHolder(val textView: TextView): RecyclerView.ViewHolder(textView)
