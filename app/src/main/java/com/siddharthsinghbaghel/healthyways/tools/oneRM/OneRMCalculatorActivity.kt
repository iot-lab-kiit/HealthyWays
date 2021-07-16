package com.siddharthsinghbaghel.healthyways.tools.oneRM

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.siddharthsinghbaghel.healthyways.R
import kotlinx.android.synthetic.main.activity_bmi.*
import kotlinx.android.synthetic.main.activity_one_r_m_calculater.*
import kotlinx.android.synthetic.main.activity_one_r_m_calculater.toolbar_oneRM_activity
import java.math.BigDecimal
import java.math.RoundingMode

class OneRMCalculatorActivity : AppCompatActivity() {

    val METRIC_UNITS_VIEW = "METRIC_UNITS_VIEW"
    val US_UNITS_VIEW = "US_UNITS_VIEW"
    var currentVisibleView: String = "METRIC_UNITS_VIEW"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_one_r_m_calculater)

        setSupportActionBar(toolbar_oneRM_activity)

        val actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
        actionBar!!.title = "Calculator 1RM"
        toolbar_oneRM_activity.setNavigationOnClickListener {

            onBackPressed()
        }





        btnCalculateUnitsRM.setOnClickListener {

            if(validateUnits()){

                when (currentVisibleView) {
                    METRIC_UNITS_VIEW -> {

                        val repValue = etMetricUnitRep.text.toString().toFloat()
                        val weightValue = etMetricUnitWeightRM.text.toString().toFloat()

                        calculateOneRM(repValue,weightValue)
                    }
                    US_UNITS_VIEW -> {

                        val repValue = etUsUnitRep.text.toString().toFloat()
                        val weightValue = etUsUnitWeightRM.text.toString().toFloat()

                        calculateOneRM(repValue,weightValue)
                    }
                }
            }else{
                Toast.makeText(
                        this,
                        "Please Enter valid values !!",
                        Toast.LENGTH_SHORT
                ).show()
            }

        }

        /* Initializing the calculator view */

            makeVisibleMetricUnitsView()
            setSpinner(spExerciseMetric)

        /* Initializing the calculator view */

        /* Radio btn check listener*/

        rgUnitsRM.setOnCheckedChangeListener { group, checkedId ->

            when (checkedId) {
                R.id.rdBtnMetricRM -> {
                    makeVisibleMetricUnitsView()

                    /* For setting up the image according to the exercise selected */

                    setSpinner(spExerciseMetric)

                    /* For setting up the image according to the exercise selected */

                }
                R.id.rdBtnUSUnitsRM -> {
                    makeVisibleUSUnitsView()

                    /* For setting up the image according to the exercise selected */

                    setSpinner(spExerciseUS)

                    /* For setting up the image according to the exercise selected */

                }
            }
        }
        /* Radio btn check listener*/
}



    private fun calculateOneRM(repValue: Float, weightValue: Float) {


        val resultOneRm = weightValue * 36/(37 - repValue)

        val speedRM = (60 * resultOneRm)/100
        val muscleRM = (80 * resultOneRm)/100
        val strengthRM = (95 * resultOneRm)/100

        llDisplayBFResult.visibility = View.VISIBLE
        llAdditionalInfo.visibility = View.VISIBLE

        val oneRMValue = BigDecimal(resultOneRm.toDouble()).setScale(2, RoundingMode.HALF_EVEN).toString()

        val speedRMValue = BigDecimal(speedRM.toDouble()).setScale(2, RoundingMode.HALF_EVEN).toString()
        val muscleRMValue = BigDecimal(muscleRM.toDouble()).setScale(2, RoundingMode.HALF_EVEN).toString()
        val strengthRMValue = BigDecimal(strengthRM.toDouble()).setScale(2, RoundingMode.HALF_EVEN).toString()

        tvRMValue.text = oneRMValue
        tvSpeedRM.text = speedRMValue
        tvStrengthRM.text = strengthRMValue
        tvMuscleRM.text = muscleRMValue

    }


    /* VISIBILITY OF UNITS VIEWS */
    private fun makeVisibleMetricUnitsView(){

        currentVisibleView = METRIC_UNITS_VIEW
        tilMetricUnitWeightRM.visibility = View.VISIBLE
        tilMetricUnitRep.visibility = View.VISIBLE
        spExerciseMetric.visibility = View.VISIBLE

        tilUsUnitWeightRM.visibility = View.GONE
        tilUsUnitRep.visibility = View.GONE
        spExerciseUS.visibility = View.GONE

        etMetricUnitWeightRM.text!!.clear()
        etMetricUnitRep.text!!.clear()


        llDisplayBFResult.visibility = View.GONE
        llAdditionalInfo.visibility = View.GONE
    }
    private fun makeVisibleUSUnitsView(){

        currentVisibleView = US_UNITS_VIEW
        tilUsUnitWeightRM.visibility = View.VISIBLE
        tilUsUnitRep.visibility = View.VISIBLE
        spExerciseUS.visibility = View.VISIBLE

        tilMetricUnitWeightRM.visibility = View.GONE
        tilMetricUnitRep.visibility = View.GONE
        spExerciseMetric.visibility = View.GONE

        etUsUnitWeightRM.text!!.clear()
        etUsUnitRep.text!!.clear()



        llDisplayBFResult.visibility = View.GONE
        llAdditionalInfo.visibility = View.GONE
    }

    /* VISIBILITY OF UNITS VIEWS */

    private fun validateUnits(): Boolean{

        var isValid = true

        when (currentVisibleView) {

            METRIC_UNITS_VIEW -> {

                when {
                    etMetricUnitWeightRM.text.toString().isEmpty() -> {
                        isValid = false
                    }
                    etMetricUnitRep.text.toString().isEmpty() -> {
                        isValid = false
                    }


                }
            }

            US_UNITS_VIEW -> {

                when {
                    etUsUnitWeightRM.text.toString().isEmpty() -> {
                        isValid = false
                    }
                    etUsUnitRep.text.toString().isEmpty() -> {
                        isValid = false
                    }
                }
            }
        }

        return isValid
    }


    private fun setSpinner(spExerciseToSet: Spinner?){

        spExerciseToSet?.onItemSelectedListener = object : AdapterView.OnItemClickListener, AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

                if(currentVisibleView == METRIC_UNITS_VIEW){
                    Toast.makeText(this@OneRMCalculatorActivity,
                            "US selected item is ${parent?.getItemAtPosition(position).toString()} and position $position",
                            Toast.LENGTH_SHORT).show()
                }else if(currentVisibleView == US_UNITS_VIEW){
                    Toast.makeText(this@OneRMCalculatorActivity,
                            "Metric selected item is ${parent?.getItemAtPosition(position).toString()} and position $position",
                            Toast.LENGTH_SHORT).show()
                }

                when (position) {
                    0 -> {
                        resultExerciseImage.setImageResource(R.drawable.ic_squats_1)
                    }
                    1 -> {
                        resultExerciseImage.setImageResource(R.drawable.ic_deadlift_1)
                    }
                    2 -> {
                        resultExerciseImage.setImageResource(R.drawable.ic_overhead_press)
                    }
                    3 -> {
                        resultExerciseImage.setImageResource(R.drawable.ic_benchpress_1)
                    }
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

                Toast.makeText(this@OneRMCalculatorActivity,
                        "Clicked item is ${parent?.getItemAtPosition(position).toString()}",
                        Toast.LENGTH_SHORT).show()
            }
        }
    }
}