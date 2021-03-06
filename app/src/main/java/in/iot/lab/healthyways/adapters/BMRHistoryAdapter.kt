package `in`.iot.lab.healthyways.adapters

import `in`.iot.lab.healthyways.R
import `in`.iot.lab.healthyways.room.history.entities.BMRCalcHistoryEntity
import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView

import kotlinx.android.synthetic.main.bmr_history_item.view.*
import java.text.DecimalFormat
import java.util.*

class BMRHistoryAdapter(private val context: Context, private val listener: IBMRHistoryRVAdapter):
                        RecyclerView.Adapter<BMRHistoryAdapter.BMRHistoryViewHolder>() {

     private val allBmrHistory = ArrayList<BMRCalcHistoryEntity>()

     inner class BMRHistoryViewHolder(view: View): RecyclerView.ViewHolder(view){

         val exerExtent: TextView = view.txtExerciseValue
         val weightValue: TextView = view.txtWeightValue
         val heightValue: TextView= view.txtHeightValue
         val bmrDateT: TextView= view.dateBMR
         val bmrValue: TextView = view.txtBmrValue
         val tdeeValue: TextView = view.txtTdeeValue
         val btnBMRDelete: ImageView = view.btnBMRDelete
     }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BMRHistoryAdapter.BMRHistoryViewHolder {
        val viewHolder = BMRHistoryViewHolder(LayoutInflater.from(context).inflate(R.layout.bmr_history_item, parent, false))
        viewHolder.btnBMRDelete.setOnClickListener {
            listener.onItemClicked(allBmrHistory[viewHolder.adapterPosition])
        }
        return viewHolder
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: BMRHistoryAdapter.BMRHistoryViewHolder, position: Int) {


        val currentHistory = allBmrHistory[position]
        holder.exerExtent.text = currentHistory.exerExtent
        holder.weightValue.text = currentHistory.weightValue
        holder.heightValue.text = currentHistory.heightValue
        val formatter = DecimalFormat("#.##")
        currentHistory.bmrValue.toDoubleOrNull()?.let {
            holder.bmrValue.text = formatter.format(it)
        }
        currentHistory.tdeeValue.toDoubleOrNull()?.let {
            holder.tdeeValue.text = formatter.format(it)
        }
        holder.bmrDateT.text = currentHistory.bmrDateT

    }

    override fun getItemCount(): Int {
        return allBmrHistory.size
    }

    /* To tell recycler view about the change of data due to viewModel*/
    fun updateList(newList: List<BMRCalcHistoryEntity>){
        allBmrHistory.clear()
        allBmrHistory.addAll(newList)
        notifyDataSetChanged()
    }

interface IBMRHistoryRVAdapter{

        fun onItemClicked(bmrHistory:BMRCalcHistoryEntity)
   }

}