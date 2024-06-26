package com.example.a3200lab6

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class StationAdapter(private val stationNames: Array<String>, private val onItemClick: (Int) -> Unit) :
    RecyclerView.Adapter<StationAdapter.StationViewHolder>() {

    inner class StationViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val stationNameTextView: TextView = itemView.findViewById(R.id.viewStation)

        init {
            itemView.setOnClickListener {
                onItemClick(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StationViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.station, parent, false)
        return StationViewHolder(view)
    }

    override fun onBindViewHolder(holder: StationViewHolder, position: Int) {
        holder.stationNameTextView.text = stationNames[position]
    }

    override fun getItemCount(): Int {
        return stationNames.size
    }
}