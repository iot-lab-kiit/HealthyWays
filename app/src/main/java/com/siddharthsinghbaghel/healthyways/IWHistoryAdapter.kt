package com.siddharthsinghbaghel.healthyways

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.siddharthsinghbaghel.healthyways.room.iWHistory.IWHistoryEntity
import kotlinx.android.synthetic.main.iw_history_item.view.*
import java.util.*

class IWHistoryAdapter(private val context: Context, private val listener: IIWHistoryRVAdapter):
                        RecyclerView.Adapter<IWHistoryAdapter.IWHistoryViewHolder>() {

     private val allIwHistory = ArrayList<IWHistoryEntity>()

     inner class IWHistoryViewHolder(view: View): RecyclerView.ViewHolder(view){
         val iwValue: TextView = view.txtIWValue
         val iwHeight: TextView = view.txtHeightValue
         val iwWeight: TextView= view.txtWeightValue
         val btnDelete: ImageView = view.btnIWDelete
     }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IWHistoryViewHolder {
        val viewHolder = IWHistoryViewHolder(LayoutInflater.from(context).inflate(R.layout.iw_history_item, parent, false))
        viewHolder.btnDelete.setOnClickListener {
            listener.onItemClicked(allIwHistory[viewHolder.adapterPosition])
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: IWHistoryViewHolder, position: Int) {


        val currentHistory = allIwHistory[position]
        holder.iwHeight.text = currentHistory.iwHeight
        holder.iwValue.text = currentHistory.IWValue
        holder.iwWeight.text = currentHistory.iwWeight



    }

    override fun getItemCount(): Int {
        return allIwHistory.size
    }

    /* To tell recycler view about the change of data due to viewModel*/
    fun updateList(newList: List<IWHistoryEntity>){
        allIwHistory.clear()
        allIwHistory.addAll(newList)
        notifyDataSetChanged()
    }

interface IIWHistoryRVAdapter{

        fun onItemClicked(iwHistory: IWHistoryEntity)
   }

}