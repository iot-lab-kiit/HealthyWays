package com.siddharthsinghbaghel.healthyways

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.siddharthsinghbaghel.healthyways.room.BMIHistoryEntity
import kotlinx.android.synthetic.main.bmi_history_item.view.*
import java.util.ArrayList

class BMIHistoryAdapter(private val context: Context, private val listener: IBMIHistoryRVAdapter):
                        RecyclerView.Adapter<BMIHistoryAdapter.BMIHistoryViewHolder>() {

     private val allBmiHistory = ArrayList<BMIHistoryEntity>()

     inner class BMIHistoryViewHolder(view: View): RecyclerView.ViewHolder(view){
         val bmiWeight: TextView = view.tvWeightValue
         val bmiHeight: TextView = view.tvHeightValue
         val bmiValue: TextView= view.tvBmiValue
         val bmiState: TextView = view.tvBmiState
         val btnDelete: ImageView = view.btnDelete
     }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BMIHistoryViewHolder {
        val viewHolder = BMIHistoryViewHolder(LayoutInflater.from(context).inflate(R.layout.bmi_history_item, parent, false))
        viewHolder.btnDelete.setOnClickListener {
            listener.onItemClicked(allBmiHistory[viewHolder.adapterPosition])
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: BMIHistoryViewHolder, position: Int) {


        val currentHistory = allBmiHistory[position]
        holder.bmiValue.text = currentHistory.bmiValue
        holder.bmiState.text = currentHistory.bmiState
        holder.bmiHeight.text = currentHistory.bmiHeight
        holder.bmiWeight.text = currentHistory.bmiWeight


    }

    override fun getItemCount(): Int {
        return allBmiHistory.size
    }

    /* To tell recycler view about the change of data due to viewModel*/
    fun updateList(newList: List<BMIHistoryEntity>){
        allBmiHistory.clear()
        allBmiHistory.addAll(newList)
        notifyDataSetChanged()
    }

interface IBMIHistoryRVAdapter{

        fun onItemClicked(bmiHistory: BMIHistoryEntity)
   }

}