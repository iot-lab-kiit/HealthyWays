package com.siddharthsinghbaghel.healthyways.exercise

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.siddharthsinghbaghel.healthyways.R
import kotlinx.android.synthetic.main.item_exercise_status.view.*

class ExerciseStatusAdapter(private val items: ArrayList<ExerciseModel>, private val context:Context): RecyclerView.Adapter<ExerciseStatusAdapter.ExerciseViewHolder>() {


   class ExerciseViewHolder(view : View): RecyclerView.ViewHolder(view) {

       val tvItem: TextView = view.tvStatusItem

   }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExerciseViewHolder {

        return ExerciseViewHolder(LayoutInflater.from(context)
                .inflate(R.layout.item_exercise_status
                        ,parent,false))
    }

    override fun onBindViewHolder(holder: ExerciseViewHolder, position: Int) {
         val currentExerciseModel: ExerciseModel = items[position]
         holder.tvItem.text = currentExerciseModel.getId().toString()

        when {
            currentExerciseModel.getIsSelected() -> {
                holder.tvItem.background = ContextCompat.getDrawable(context, R.drawable.item_circular_color_grey_bg_current)
                holder.tvItem.setTextColor(Color.parseColor("#212121"))
            }
            currentExerciseModel.getIsCompleted() -> {
                holder.tvItem.background = ContextCompat.getDrawable(context, R.drawable.item_circular_color_grey_bg_completed)
                holder.tvItem.setTextColor(Color.parseColor("#FFFFFF"))
            }
            else -> {
                holder.tvItem.background = ContextCompat.getDrawable(context, R.drawable.item_circular_color_grey_bg)
                holder.tvItem.setTextColor(Color.parseColor("#FFFFFF"))

            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }
}