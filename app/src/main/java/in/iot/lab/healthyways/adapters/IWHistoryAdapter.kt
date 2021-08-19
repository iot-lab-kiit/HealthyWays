package `in`.iot.lab.healthyways.adapters

import `in`.iot.lab.healthyways.R
import `in`.iot.lab.healthyways.room.history.entities.IWHistoryEntity
import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.iw_history_item.view.*
import java.util.*

class IWHistoryAdapter(private val context: Context, private val listener: IWHistoryAdapter.IIWHistoryRVAdapter):
                        RecyclerView.Adapter<IWHistoryAdapter.IWHistoryViewHolder>() {

     private val allIwHistory = ArrayList<IWHistoryEntity>()

     inner class IWHistoryViewHolder(view: View): RecyclerView.ViewHolder(view){
         val iwValue: TextView = view.txtIWValue
         val iwHeight: TextView = view.txtHeightValue
         val iwWeight: TextView= view.txtWeightValue
         val btnDelete: ImageView = view.btnIWDelete
         val dateTime: TextView = view.date
     }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IWHistoryAdapter.IWHistoryViewHolder {
        val viewHolder = IWHistoryViewHolder(LayoutInflater.from(context).inflate(R.layout.iw_history_item, parent, false))
        viewHolder.btnDelete.setOnClickListener {
            listener.onItemClicked(allIwHistory[viewHolder.adapterPosition])
        }
        return viewHolder
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: IWHistoryAdapter.IWHistoryViewHolder, position: Int) {


        val currentHistory = allIwHistory[position]
        holder.iwHeight.text = currentHistory.iwHeight
        holder.iwValue.text = currentHistory.IWValue
        holder.iwWeight.text = currentHistory.iwWeight
        holder.dateTime.text = currentHistory.iwDateT



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