package com.siddharthsinghbaghel.healthyways.tools.idealWeight

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
import kotlinx.android.synthetic.main.activity_bmi.*
import kotlinx.android.synthetic.main.activity_ideal_weight.*
import java.math.BigDecimal
import java.math.RoundingMode

class IdealWeightActivity : AppCompatActivity() {
    var mGender = 0;
    var mExerciseIndex = 0;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ideal_weight)

        setSupportActionBar(toolbar_iw_activity)

        val actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
        actionBar!!.title = "Ideal Weight Calc"
        toolbar_iw_activity.setNavigationOnClickListener {

            onBackPressed()
        }

        setSpinner(spGenderIw)

        btnCalculateIW.setOnClickListener {

            svIw.fullScroll(ScrollView.FOCUS_DOWN);
            hideKeyboard(it)

            if(validateUnits()) {

                        val heightValue = etMetricIWHeight.text.toString().toFloat()
                        val weightValue = etMetricIWWeight.text.toString().toFloat()

                        calculateIW(heightValue,weightValue)
            }else{
                Toast.makeText(this, "Invalid Entries", Toast.LENGTH_SHORT).show()
            }

        }

        tvConverterRedirect.setOnClickListener {
            val intent = Intent("android.intent.action.VIEW", Uri.parse("https://www.unitconverters.net/"));
            startActivity(intent)
        }


    }

    private fun calculateIW(heightValue: Float,weightValue: Float) {


        if(heightValue <= 153)
        {
            Toast.makeText(this, "Height Should be Greater than 153 cm for accurate measurement!!!", Toast.LENGTH_LONG).show()
            llResultIW.visibility = View.GONE
        }
        else {

            val inch = heightValue * 0.393701
            val remainingInch = inch - 60

            var robinsonValue = 52 + 1.9 * remainingInch
            var millerValue = 56.2 + 1.41 * remainingInch
            var hamwiValue = 48 + 2.7 * remainingInch
            var devineValue = 50 + 2.3 * remainingInch

                    when (mGender) {
                        0 -> {
                             robinsonValue = 52 + 1.9 * remainingInch
                             millerValue = 56.2 + 1.41 * remainingInch
                             hamwiValue = 48 + 2.7 * remainingInch
                             devineValue = 50 + 2.3 * remainingInch

                        }
                        1 -> {
                            robinsonValue = 49 + 1.7 * remainingInch
                            millerValue = 53.1 + 1.36 * remainingInch
                            hamwiValue = 45.5 + 2.2 * remainingInch
                            devineValue = 45.5 + 2.3 * remainingInch

                        }
                    }
            val robinsonResult = BigDecimal(robinsonValue).setScale(2, RoundingMode.HALF_EVEN).toString()
            val millerResult = BigDecimal(millerValue).setScale(2, RoundingMode.HALF_EVEN).toString()
            val hamwiResult = BigDecimal(hamwiValue).setScale(2, RoundingMode.HALF_EVEN).toString()
            val devineResult = BigDecimal(devineValue).setScale(2, RoundingMode.HALF_EVEN).toString()


           tvRobinValue.text = robinsonResult
           tvMillerValue.text = millerResult
           tvHamwiValue.text = hamwiResult
           tvDevineValue.text = devineResult

            llResultIW.visibility = View.VISIBLE

        }


    }


    private fun validateUnits(): Boolean{

        var isValid = true

               when {
                    etMetricIWHeight.text.toString().isEmpty() -> {
                        isValid = false
                    }
                    etMetricIWWeight.text.toString().isEmpty() -> {
                        isValid = false
                    }

                }

        return isValid
    }

    private fun setSpinner(spGender: Spinner?){

        llResultIW.visibility = View.GONE

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

    fun referenceCalled(view: View) {

        val intent = Intent("android.intent.action.VIEW", Uri.parse("https://www.medicalnewstoday.com/articles/323446#_noHeaderPrefixedContent"));
        startActivity(intent)
    }


    private fun hideKeyboard(view: View) {
        view.apply {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }
}