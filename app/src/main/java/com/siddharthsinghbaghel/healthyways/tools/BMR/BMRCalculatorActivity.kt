package com.siddharthsinghbaghel.healthyways.tools.BMR

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.AdapterView
import android.widget.ScrollView
import android.widget.Spinner
import android.widget.Toast
import com.siddharthsinghbaghel.healthyways.R
import kotlinx.android.synthetic.main.activity_b_m_r_calculator.*
import java.math.BigDecimal
import java.math.RoundingMode

class BMRCalculatorActivity : AppCompatActivity() {

    var mGender = 0;
    var mExerciseIndex = 0;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_b_m_r_calculator)

        setSupportActionBar(toolbar_bmr_activity)

        val actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
        actionBar!!.title = "BMR & TDEE"
        toolbar_bmr_activity.setNavigationOnClickListener {

            onBackPressed()
        }

        setSpinnerExercise(spExtentExercise)
        setSpinnerGen(spGenderBMR)

        btnCalculateBMR.setOnClickListener {

            bmrSv.fullScroll(ScrollView.FOCUS_DOWN);
            hideKeyboard(it)

            if(validateUnits()) {

                val ageValue = etMetricBMRAge.text.toString().toFloat()
                val heightValue = etMetricBMRHeight.text.toString().toFloat()
                val weightValue = etMetricBMRWeight.text.toString().toFloat()

                calculateBMR(ageValue,heightValue,weightValue)
            }else{
                Toast.makeText(this, "Invalid Entries", Toast.LENGTH_SHORT).show()
            }

        }
    }

    private fun calculateBMR(ageValue: Float, heightValue: Float, weightValue: Float) {

        var bmrResult = (66 + (13.7 * weightValue) + (5 * heightValue) - (6.8 * ageValue))
        var tdeeResult = bmrResult * 1.2

        when (mGender) {
            0 -> {
                 bmrResult = (66 + (13.7 * weightValue) + (5 * heightValue) - (6.8 * ageValue))
                 tdeeResult = bmrResult * 1.2
                when (mExerciseIndex) {
                    0 -> {
                        tdeeResult = bmrResult * 1.2
                    }
                    1 -> {
                        tdeeResult = bmrResult * 1.375
                    }
                    2 -> {
                        tdeeResult = bmrResult * 1.55
                    }
                    3 -> {
                        tdeeResult = bmrResult * 1.725
                    }
                    4 -> {
                        tdeeResult = bmrResult * 1.9
                    }
                }
            }
            1 -> {
                bmrResult = 655 + (9.6 * weightValue) + (1.8 * heightValue) - (4.7 * ageValue)
                tdeeResult = bmrResult * 1.2
                when (mExerciseIndex) {
                    0 -> {
                        tdeeResult = bmrResult * 1.2
                    }
                    1 -> {
                        tdeeResult = bmrResult * 1.375
                    }
                    2 -> {
                        tdeeResult = bmrResult * 1.55
                    }
                    3 -> {
                        tdeeResult = bmrResult * 1.725
                    }
                    4 -> {
                        tdeeResult = bmrResult * 1.9
                    }
                }

            }
        }

        val resultBMRValue = BigDecimal(bmrResult).setScale(2, RoundingMode.HALF_EVEN).toString()
        val resultTDEEValue = BigDecimal(tdeeResult).setScale(2, RoundingMode.HALF_EVEN).toString()

        llResultBMR.visibility =View.VISIBLE

        tvTDEEValue.text = resultTDEEValue
        tvBMRValue.text = resultBMRValue

    }


    private fun validateUnits(): Boolean {

        val isValid = true

        if(etMetricBMRAge.text!!.isEmpty()){
            !isValid
            Toast.makeText(this, " Here Age", Toast.LENGTH_SHORT).show()
        }
        if(etMetricBMRHeight.text!!.isEmpty()){
            !isValid
            Toast.makeText(this, " Here BMI", Toast.LENGTH_SHORT).show()
        }
        if(etMetricBMRWeight.text!!.isEmpty()){
            !isValid
            Toast.makeText(this, " Here BMI", Toast.LENGTH_SHORT).show()
        }
        return isValid
    }




    private fun setSpinnerGen(spGender: Spinner?){

        llResultBMR.visibility = View.GONE

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


            }
        }
    }
    private fun setSpinnerExercise(spExercise: Spinner?){

        spExercise?.onItemSelectedListener = object : AdapterView.OnItemClickListener, AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {


                when (position) {
                    0 -> {
                       mExerciseIndex = 0
                    }
                    1 -> {
                        mExerciseIndex = 1
                    }
                    2 -> {
                        mExerciseIndex = 2
                    }
                    3 -> {
                        mExerciseIndex = 3
                    }
                    4 -> {
                        mExerciseIndex = 4
                    }
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {


            }
        }
    }

    fun referenceCalled(view: View) {

        val intent = Intent("android.intent.action.VIEW", Uri.parse("https://steelfitusa.com/blogs/health-and-wellness/calculate-tdee"));
        startActivity(intent)
    }


    private fun hideKeyboard(view: View) {
        view.apply {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

}