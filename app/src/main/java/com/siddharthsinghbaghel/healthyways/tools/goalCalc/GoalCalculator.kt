package com.siddharthsinghbaghel.healthyways.tools.goalCalc

import android.content.Context
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ScrollView
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import com.siddharthsinghbaghel.healthyways.R
import kotlinx.android.synthetic.main.activity_goal_calculator.*
import kotlinx.android.synthetic.main.activity_goal_calculator.svIw
import kotlinx.android.synthetic.main.activity_ideal_weight.*
import java.math.BigDecimal
import java.math.RoundingMode

class GoalCalculator : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_goal_calculator)

        setSupportActionBar(toolbar_GC_activity)



        val actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
        actionBar!!.title = "Body Fat Percentage"
        toolbar_GC_activity.setNavigationOnClickListener {

            onBackPressed()
        }

        llResultGC.visibility = View.GONE

        btnCalculateGC.setOnClickListener {



            hideKeyboard(it)
            svIw.fullScroll(ScrollView.FOCUS_DOWN)


            if(validateUnits()) {

                val targetWeight = etGCTargetBodyWeight.text.toString().toFloat()
                val currentWeight = etGCCurrentBodyWeight.text.toString().toFloat()
                val tee = etGCTEE.text.toString().toFloat()
                val weeks = etGCWeeks.text.toString().toInt()

                calculateGC(currentWeight,targetWeight,tee,weeks,it)
            }else{
                Toast.makeText(this, "Invalid Entries", Toast.LENGTH_SHORT).show()
            }

        }
    }

    private fun calculateGC(currentWeight: Float, targetWeight: Float, tee: Float, weeks: Int, view: View) {




        when {
            currentWeight > targetWeight -> {


            }
            currentWeight < targetWeight -> {

                val totalWeightGain = targetWeight - currentWeight
                val totalCaloriesToGain = totalWeightGain * 7700
                val totalDays = weeks * 7
                val caloriesGainPerDay = totalCaloriesToGain / totalDays
                val caloriesGainPerDayFormatted = BigDecimal(caloriesGainPerDay.toDouble()).setScale(2, RoundingMode.HALF_EVEN).toString()

                if (caloriesGainPerDayFormatted.toFloat() > 1000 || caloriesGainPerDayFormatted.toFloat() < 0) {

                    val snackBar = Snackbar.make(
                        view, "Replace with your own action",
                        Snackbar.LENGTH_LONG
                    ).setAction("Action", null)
                    snackBar.setActionTextColor(Color.BLUE)

                }else{

                    llResultGC.visibility = View.VISIBLE
                    Toast.makeText(this, "$tee  $caloriesGainPerDay", Toast.LENGTH_SHORT).show()
                     val goalCalorieResult = tee + caloriesGainPerDayFormatted.toFloat()
                     val surplusCaloriesValue = caloriesGainPerDayFormatted.toFloat()

                     tvKcalPerDayValue.text = goalCalorieResult.toString()
                     tvSurplus.text = surplusCaloriesValue.toString()
                }

            }
            else -> {
                Toast.makeText(this, "Nice try but I got you 😉! Target Weight = Current Weight ", Toast.LENGTH_LONG).show()
            }
        }

    }





    private fun validateUnits(): Boolean{

        var isValid = true

        when {
            etGCTEE.text.toString().isEmpty() -> {
                isValid = false
            }
            etGCCurrentBodyWeight.text.toString().isEmpty() -> {
                isValid = false
            }
            etGCTargetBodyWeight.text.toString().isEmpty() -> {
                isValid = false
            }
            etGCWeeks.text.toString().isEmpty() -> {
                isValid = false
            }

        }

        return isValid
    }

    private fun hideKeyboard(view: View) {
        view.apply {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }
}


