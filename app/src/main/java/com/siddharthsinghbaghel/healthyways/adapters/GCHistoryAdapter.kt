package com.siddharthsinghbaghel.healthyways.adapters

import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.siddharthsinghbaghel.healthyways.R

import com.siddharthsinghbaghel.healthyways.room.history.entities.GCHistoryEntity

import kotlinx.android.synthetic.main.gc_history_item.view.*
import java.util.*

class GCHistoryAdapter(private val context: Context, private val listener: IGCHistoryRVAdapter):
                        RecyclerView.Adapter<GCHistoryAdapter.GCHistoryViewHolder>() {

     private val allGCHistory = ArrayList<GCHistoryEntity>()

     inner class GCHistoryViewHolder(view: View): RecyclerView.ViewHolder(view){

         val currWeight: TextView = view.txtWeightValue
         val targetWeight: TextView = view.txtTargetWeightValue
         val weekValue: TextView= view.txtWeekValue
         val gcDateT: TextView = view.dateGC
         val perDayCal: TextView = view.txtCalValue
         val btnDelete: ImageView = view.btnGCDelete
     }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GCHistoryViewHolder {
        val viewHolder = GCHistoryViewHolder(LayoutInflater.from(context).inflate(R.layout.gc_history_item, parent, false))
        viewHolder.btnDelete.setOnClickListener {
            listener.onItemClicked(allGCHistory[viewHolder.adapterPosition])
        }
        return viewHolder
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: GCHistoryViewHolder, position: Int) {


        val currentHistory = allGCHistory[position]
        holder.currWeight.text = currentHistory.currWeight
        holder.targetWeight.text = currentHistory.targetWeight
        holder.weekValue.text = currentHistory.weekValue
        holder.gcDateT.text = currentHistory.gcDateT
        holder.perDayCal.text = currentHistory.perDayCal

    }

    override fun getItemCount(): Int {
        return allGCHistory.size
    }

    /* To tell recycler view about the change of data due to viewModel*/
    fun updateList(newList: List<GCHistoryEntity>){
        allGCHistory.clear()
        allGCHistory.addAll(newList)
        notifyDataSetChanged()
    }

interface IGCHistoryRVAdapter{

        fun onItemClicked(gcHistory: GCHistoryEntity)
   }

}