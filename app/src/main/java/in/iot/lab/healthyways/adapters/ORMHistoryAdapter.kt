package `in`.iot.lab.healthyways.adapters

import `in`.iot.lab.healthyways.R
import `in`.iot.lab.healthyways.room.history.entities.OneRMCalcHistoryEntity
import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView

import kotlinx.android.synthetic.main.orm_history_item.view.*
import java.text.DecimalFormat
import java.util.*

class ORMHistoryAdapter(private val context: Context, private val listener: ORMHistoryAdapter.IORMHistoryRVAdapter):
                        RecyclerView.Adapter<ORMHistoryAdapter.ORMHistoryViewHolder>() {

     private val allORMHistory = ArrayList<OneRMCalcHistoryEntity>()

     inner class ORMHistoryViewHolder(view: View): RecyclerView.ViewHolder(view){

         val ormValue: TextView = view.txt1RMValue
         val exerciseUsed: TextView = view.txtExercise
         val weightUsed: TextView= view.txtWeightUsed
         val rep: TextView= view.txtRepValue
         val btnDelete: ImageView = view.btnORMDelete
         val dateTime: TextView = view.dateORM
     }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ORMHistoryAdapter.ORMHistoryViewHolder {
        val viewHolder = ORMHistoryViewHolder(LayoutInflater.from(context).inflate(R.layout.orm_history_item, parent, false))
        viewHolder.btnDelete.setOnClickListener {
            listener.onItemClicked(allORMHistory[viewHolder.adapterPosition])
        }
        return viewHolder
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: ORMHistoryAdapter.ORMHistoryViewHolder, position: Int) {


        val currentHistory = allORMHistory[position]
       
        holder.exerciseUsed.text = currentHistory.exerChoosed
        holder.weightUsed.text = currentHistory.weightValue
        holder.rep.text = currentHistory.repValue
        holder.dateTime.text = currentHistory.ormDateT

        val deciFormatter = DecimalFormat("#.##")
        currentHistory.oneRMValue.toDoubleOrNull()?.let {
            holder.ormValue.text = deciFormatter.format(it)
        }

    }

    override fun getItemCount(): Int {
        return allORMHistory.size
    }

    /* To tell recycler view about the change of data due to viewModel*/
    fun updateList(newList: List<OneRMCalcHistoryEntity>){
        allORMHistory.clear()
        allORMHistory.addAll(newList)
        notifyDataSetChanged()
    }

interface IORMHistoryRVAdapter{

        fun onItemClicked(ormHistory:OneRMCalcHistoryEntity)
   }

}
