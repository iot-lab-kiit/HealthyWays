package `in`.iot.lab.healthyways.adapters

import `in`.iot.lab.healthyways.R
import `in`.iot.lab.healthyways.room.history.entities.FatCalcHistoryEntity
import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView


import kotlinx.android.synthetic.main.fat_history_item.view.*
import java.text.DecimalFormat
import java.util.*

class FatHistoryAdapter(private val context: Context, private val listener: FatHistoryAdapter.IGCHistoryRVAdapter):
                        RecyclerView.Adapter<FatHistoryAdapter.FatHistoryViewHolder>() {

     private val allFatHistory = ArrayList<FatCalcHistoryEntity>()

     inner class FatHistoryViewHolder(view: View): RecyclerView.ViewHolder(view){

         val fatPerc: TextView = view.txtPercValue
         val fatMass: TextView = view.txtFatMassValue
         val age: TextView= view.txtAgeValue
         val bmiValue: TextView = view.txtBMIValue
         val fatDateT: TextView = view.dateFat
         val btnDelete: ImageView = view.btnFatDelete
     }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FatHistoryAdapter.FatHistoryViewHolder {
        val viewHolder = FatHistoryViewHolder(LayoutInflater.from(context).inflate(R.layout.fat_history_item, parent, false))
        viewHolder.btnDelete.setOnClickListener {
            listener.onItemClicked(allFatHistory[viewHolder.adapterPosition])
        }
        return viewHolder
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: FatHistoryAdapter.FatHistoryViewHolder, position: Int) {


        val currentHistory = allFatHistory[position]
        holder.age.text = currentHistory.ageValue
        holder.bmiValue.text = currentHistory.bmiValue
        holder.fatDateT.text = currentHistory.fatDateT
        holder.fatPerc.text = currentHistory.fatPerc
        holder.fatMass.text = currentHistory.fatMass

        val formatter = DecimalFormat("#.##")
        currentHistory.fatPerc.toDoubleOrNull()?.let {
            holder.fatPerc.text = formatter.format(it)
        }
        currentHistory.fatMass.toDoubleOrNull()?.let {
            holder.fatMass.text = formatter.format(it)
        }
    }

    override fun getItemCount(): Int {
        return allFatHistory.size
    }

    /* To tell recycler view about the change of data due to viewModel*/
    fun updateList(newList: List<FatCalcHistoryEntity>){
        allFatHistory.clear()
        allFatHistory.addAll(newList)
        notifyDataSetChanged()
    }

interface IGCHistoryRVAdapter{

        fun onItemClicked(fatHistory: FatCalcHistoryEntity)
   }

}
