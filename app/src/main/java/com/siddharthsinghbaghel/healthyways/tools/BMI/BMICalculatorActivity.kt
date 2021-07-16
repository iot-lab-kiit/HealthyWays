package com.siddharthsinghbaghel.healthyways.tools.BMI

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.siddharthsinghbaghel.healthyways.R
import com.siddharthsinghbaghel.healthyways.room.BMIHistoryEntity
import com.siddharthsinghbaghel.healthyways.room.BMIHistoryViewModel
import kotlinx.android.synthetic.main.activity_bmi.*
import java.math.BigDecimal
import java.math.RoundingMode
import kotlin.math.pow

class BMICalculatorActivity : AppCompatActivity() {


    lateinit var viewModel: BMIHistoryViewModel
    val METRIC_UNITS_VIEW = "METRIC_UNITS_VIEW"
    val US_UNITS_VIEW = "US_UNITS_VIEW"
    var currentVisibleView: String = "METRIC_UNITS_VIEW"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bmi)

        setSupportActionBar(toolbar_bmi_activity)

        val actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
        actionBar!!.title = "Calculator BMI"
        toolbar_bmi_activity.setNavigationOnClickListener {

            onBackPressed()
        }

        viewModel = ViewModelProvider(this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(BMIHistoryViewModel::class.java)

        btnCalculateUnits.setOnClickListener {

            if(validateUnits()){

                when (currentVisibleView) {
                    METRIC_UNITS_VIEW -> {

                        val heightValue = etMetricUnitHeight.text.toString().toFloat() / 100
                        val weightValue = etMetricUnitWeight.text.toString().toFloat()

                        calculateBMI(heightValue,weightValue)
                    }
                    US_UNITS_VIEW -> {

                         val heightValue = etUsUnitHeightFeet.text.toString().toFloat() * 12 + etUsUnitHeightInch.text.toString().toFloat()
                         val weightValue = etUsUnitWeight.text.toString().toFloat()

                         calculateBMI(heightValue,weightValue)
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
         makeVisibleMetricUnitsView()

        /* Radio btn check listener*/

                    rgUnitsBMI.setOnCheckedChangeListener { group, checkedId ->

                        when (checkedId) {
                            R.id.rdBtnMetricBMI -> {
                                makeVisibleMetricUnitsView()
                            }
                            R.id.rdBtnUSUnitsBMI -> {
                                makeVisibleUSUnitsView()
                            }
                        }
                     }
        /* Radio btn check listener*/

             
    }



 /* VISIBILITY OF UNITS VIEWS */
                    private fun makeVisibleMetricUnitsView(){

                        currentVisibleView = METRIC_UNITS_VIEW
                        tilMetricUnitWeight.visibility = View.VISIBLE
                        tilMetricUnitHeight.visibility = View.VISIBLE

                        tilUsUnitWeight.visibility = View.GONE
                        llUsUnitsHeight.visibility = View.GONE

                         etMetricUnitWeight.text!!.clear()
                         etMetricUnitHeight.text!!.clear()

                        llDisplayBMIResult.visibility = View.GONE
                    }
                    private fun makeVisibleUSUnitsView(){

                        currentVisibleView = US_UNITS_VIEW
                        tilUsUnitWeight.visibility = View.VISIBLE
                        llUsUnitsHeight.visibility = View.VISIBLE

                        tilMetricUnitWeight.visibility = View.GONE
                        tilMetricUnitHeight.visibility = View.GONE

                        etUsUnitHeightFeet.text!!.clear()
                        etUsUnitHeightInch.text!!.clear()
                        etUsUnitWeight.text!!.clear()



                        llDisplayBMIResult.visibility = View.GONE
                    }

    /* VISIBILITY OF UNITS VIEWS */




         private fun calculateBMI(heightValue: Float, weightValue: Float){

             var resultBMI: Double = 0.0

             when (currentVisibleView) {
                 METRIC_UNITS_VIEW -> {
                     resultBMI = (weightValue / heightValue.toDouble().pow(2.0))
                 }
                 US_UNITS_VIEW -> {
                     resultBMI = (weightValue / heightValue.toDouble().pow(2.0)) * 703
                 }
             }


             val bmiLabel: String
             val bmiDescription: String

            if (resultBMI.compareTo(15f) <= 0) {
                bmiLabel = "Very severely underweight"
                bmiDescription = "Oops! You really need to take better care of yourself! Eat more!"
            } else if (resultBMI.compareTo(15f) > 0 && resultBMI.compareTo(16f) <= 0) {
                bmiLabel = "Severely underweight"
                bmiDescription = "Oops!You really need to take better care of yourself! Eat more!"
            } else if (resultBMI.compareTo(16f) > 0 && resultBMI.compareTo(18.5f) <= 0) {
                bmiLabel = "Underweight"
                bmiDescription = "Oops! You really need to take better care of yourself! Eat more!"
            } else if (resultBMI.compareTo(18.5f) > 0 && resultBMI.compareTo(25f) <= 0) {
                bmiLabel = "Normal"
                bmiDescription = "Congratulations! You are in a good shape!"
            } else if (resultBMI.compareTo(25f) > 0 && resultBMI.compareTo(30f) <=0 ) {
                bmiLabel = "Overweight"
                bmiDescription = "Oops! You really need to take care of your yourself! Workout maybe!"
            } else if (resultBMI.compareTo(30f) > 0 && resultBMI.compareTo(35f) <= 0) {
                bmiLabel = "Obese Class | (Moderately obese)"
                bmiDescription = "Oops! You really need to take care of your yourself! Workout maybe!"
            } else if (resultBMI.compareTo(35f) > 0 && resultBMI.compareTo(40f) <= 0) {
                bmiLabel = "Obese Class || (Severely obese)"
                bmiDescription = "OMG! You are in a very dangerous condition! Act now!"
            } else {
                bmiLabel = "Obese Class ||| (Very Severely obese)"
                bmiDescription = "OMG! You are in a very dangerous condition! Act now!"
            }

            llDisplayBMIResult.visibility = View.VISIBLE

            val bmiValue = BigDecimal(resultBMI.toDouble()).setScale(2, RoundingMode.HALF_EVEN).toString()

             tvBMIValue.text = bmiValue
             tvBMIType.text = bmiLabel
             tvBMIDescription.text = bmiDescription

            val bmiHeight: String = heightValue.toString()
            val bmiWeight: String = weightValue.toString()


             if(bmiValue.isNotEmpty() && bmiLabel.isNotEmpty()) {
                 viewModel.insertBMIHistory(BMIHistoryEntity(bmiValue,bmiLabel,bmiWeight,bmiHeight))
                 Toast.makeText(this, "$bmiValue Inserted", Toast.LENGTH_SHORT).show()
             }else{
                 Toast.makeText(this, "Invalid Entry", Toast.LENGTH_SHORT).show()
             }

         }

    private fun validateUnits(): Boolean{

        var isValid = true

        when (currentVisibleView) {

            METRIC_UNITS_VIEW -> {

                when {
                    etMetricUnitWeight.text.toString().isEmpty() -> {
                        isValid = false
                    }
                    etMetricUnitHeight.text.toString().isEmpty() -> {
                        isValid = false
                    }
                }
            }

            US_UNITS_VIEW -> {

                when {
                    etUsUnitWeight.text.toString().isEmpty() -> {
                        isValid = false
                    }
                    etUsUnitHeightFeet.text.toString().isEmpty() -> {
                        isValid = false
                    }
                    etUsUnitHeightInch.text.toString().isEmpty() -> {
                        isValid = false
                    }
                }
            }
        }

      return isValid
    }
}