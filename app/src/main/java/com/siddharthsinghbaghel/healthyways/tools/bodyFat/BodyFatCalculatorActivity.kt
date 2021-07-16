package com.siddharthsinghbaghel.healthyways.tools.bodyFat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Spinner
import android.widget.Toast
import com.siddharthsinghbaghel.healthyways.R
import com.siddharthsinghbaghel.healthyways.tools.BMI.BMICalculatorActivity
import kotlinx.android.synthetic.main.activity_b_m_r_calculator.*
import kotlinx.android.synthetic.main.activity_bmi.*
import kotlinx.android.synthetic.main.activity_body_fat_calculator.*
import kotlinx.android.synthetic.main.activity_body_fat_calculator.btnCalculateBFC
import kotlinx.android.synthetic.main.activity_body_fat_calculator.etMetricBFCAge
import kotlinx.android.synthetic.main.activity_body_fat_calculator.etMetricBFCBMI
import kotlinx.android.synthetic.main.activity_body_fat_calculator.etMetricBFCWeight
import kotlinx.android.synthetic.main.activity_body_fat_calculator.llResultBFC
import kotlinx.android.synthetic.main.activity_body_fat_calculator.spGender
import kotlinx.android.synthetic.main.activity_body_fat_calculator.tvFatMassBFCValue
import kotlinx.android.synthetic.main.activity_body_fat_calculator.tvFatPercBFCValue
import kotlinx.android.synthetic.main.activity_one_r_m_calculater.*
import java.math.BigDecimal
import java.math.RoundingMode

class BodyFatCalculatorActivity : AppCompatActivity() {

    var mGender = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_body_fat_calculator)

        setSupportActionBar(toolbar_body_fat_activity)

        val actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
        actionBar!!.title = "Body Fat Percentage"
        toolbar_body_fat_activity.setNavigationOnClickListener {

            onBackPressed()
        }



        setSpinner(spGender)

        btnCalculateBFC.setOnClickListener {
            Toast.makeText(this, " Here btn", Toast.LENGTH_SHORT).show()
            val result = validateUnits()
            Toast.makeText(this, " $result", Toast.LENGTH_SHORT).show()
                 if(result) {

                              val ageValue = etMetricBFCAge.text.toString().toFloat()
                              val bmiValue = etMetricBFCBMI.text.toString().toFloat()
                              val weightValue = etMetricBFCWeight.text.toString().toFloat()

                            calculateBF(ageValue,bmiValue,weightValue)
                  }else{
                     Toast.makeText(this, "Invalid Entries", Toast.LENGTH_SHORT).show()
                  }

              }
          }

    private fun calculateBF(ageValue: Float, bmiValue: Float,weightValue: Float) {

        llResultBFC.visibility = View.VISIBLE
        when (mGender) {
            0 -> {
                print(mGender)
                val resultBF =  (1.20 * bmiValue) + (0.23 * ageValue) - 16.2
                val resultBFValue = BigDecimal(resultBF).setScale(2, RoundingMode.HALF_EVEN).toString()
                val massFat = (resultBF * weightValue)/100
                val massFatValue = BigDecimal(massFat).setScale(2, RoundingMode.HALF_EVEN).toString()
                tvFatPercBFCValue.text = resultBFValue
                tvFatMassBFCValue.text = massFatValue
            }
            1 -> {
                print(mGender)
                val resultBF = (1.20 * bmiValue) + (0.23 * ageValue) - 5.4
                val resultBFValue = BigDecimal(resultBF).setScale(2, RoundingMode.HALF_EVEN).toString()
                val massFat = (resultBF * weightValue)/100
                val massFatValue = BigDecimal(massFat).setScale(2, RoundingMode.HALF_EVEN).toString()
                tvFatPercBFCValue.text = resultBFValue
                tvFatMassBFCValue.text = massFatValue
            }
        }
    }


    private fun setSpinner(spGender: Spinner?){

        llResultBFC.visibility = View.GONE

        spGender?.onItemSelectedListener = object : AdapterView.OnItemClickListener, AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {


                when (position) {
                    0 -> {
                       mGender = 0
                    }
                    1 -> {
                        mGender = 1
                    }
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

                Toast.makeText(this@BodyFatCalculatorActivity,
                        "Clicked item is ${parent?.getItemAtPosition(position).toString()}",
                        Toast.LENGTH_SHORT).show()
            }
        }
    }



    private fun validateUnits(): Boolean {

        val isValid: Boolean = true

        if(etMetricBFCAge.text!!.isEmpty()){
            !isValid
            Toast.makeText(this, " Here Age", Toast.LENGTH_SHORT).show()
        }
        if(etMetricBFCBMI.text!!.isEmpty()){
            !isValid
            Toast.makeText(this, " Here BMI", Toast.LENGTH_SHORT).show()
        }
        if(etMetricBFCWeight.text!!.isEmpty()){
            !isValid
            Toast.makeText(this, " Here BMI", Toast.LENGTH_SHORT).show()
        }
        return isValid
    }

    fun bmiCalled(view: View) {

        val intent = Intent(this@BodyFatCalculatorActivity, BMICalculatorActivity::class.java)
        startActivity(intent)


    }
}




