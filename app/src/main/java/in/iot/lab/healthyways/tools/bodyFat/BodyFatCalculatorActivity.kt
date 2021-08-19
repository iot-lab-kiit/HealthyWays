package `in`.iot.lab.healthyways.tools.bodyFat

import `in`.iot.lab.healthyways.room.history.HistoryViewModel
import `in`.iot.lab.healthyways.room.history.entities.FatCalcHistoryEntity
import `in`.iot.lab.healthyways.tools.BMI.BMICalculatorActivity
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.AdapterView
import android.widget.Spinner
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProvider
import com.iot.lab.healthyways.R
import kotlinx.android.synthetic.main.activity_body_fat_calculator.*
import kotlinx.android.synthetic.main.activity_body_fat_calculator.btnCalculateBFC
import kotlinx.android.synthetic.main.activity_body_fat_calculator.etMetricBFCAge
import kotlinx.android.synthetic.main.activity_body_fat_calculator.etMetricBFCBMI
import kotlinx.android.synthetic.main.activity_body_fat_calculator.etMetricBFCWeight
import kotlinx.android.synthetic.main.activity_body_fat_calculator.llResultBFC
import kotlinx.android.synthetic.main.activity_body_fat_calculator.spGender
import kotlinx.android.synthetic.main.activity_body_fat_calculator.tvFatMassBFCValue
import kotlinx.android.synthetic.main.activity_body_fat_calculator.tvFatPercBFCValue
import java.math.BigDecimal
import java.math.RoundingMode
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class BodyFatCalculatorActivity : AppCompatActivity() {

    var mGender = 0

    lateinit var viewModel : HistoryViewModel

    @RequiresApi(Build.VERSION_CODES.O)
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


        viewModel = ViewModelProvider(this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(
            HistoryViewModel::class.java)


        setSpinner(spGender)

        btnCalculateBFC.setOnClickListener {

            hideKeyboard(it)


                 if(validateUnits()) {

                              val ageValue = etMetricBFCAge.text.toString().toFloat()
                              val bmiValue = etMetricBFCBMI.text.toString().toFloat()
                              val weightValue = etMetricBFCWeight.text.toString().toFloat()

                            calculateBF(ageValue,bmiValue,weightValue)
                  }else{
                     Toast.makeText(this, "❌ Please enter valid values !!", Toast.LENGTH_SHORT).show()
                  }

              }
          }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun calculateBF(ageValue: Float, bmiValue: Float, weightValue: Float) {

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

                val currentDateTime = LocalDateTime.now()
                val x = currentDateTime.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")).toString()

                viewModel.insertFatHistory(FatCalcHistoryEntity(resultBFValue,massFatValue,ageValue.toString(),bmiValue.toString(),x))
                Toast.makeText(this, "⛳ $resultBFValue saved to history !!", Toast.LENGTH_SHORT).show()

            }
            1 -> {
                print(mGender)
                val resultBF = (1.20 * bmiValue) + (0.23 * ageValue) - 5.4
                val resultBFValue = BigDecimal(resultBF).setScale(2, RoundingMode.HALF_EVEN).toString()
                val massFat = (resultBF * weightValue)/100
                val massFatValue = BigDecimal(massFat).setScale(2, RoundingMode.HALF_EVEN).toString()
                tvFatPercBFCValue.text = resultBFValue
                tvFatMassBFCValue.text = massFatValue


                val currentDateTime = LocalDateTime.now()
                val x = currentDateTime.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")).toString()

                viewModel.insertFatHistory(FatCalcHistoryEntity(resultBFValue,massFatValue,ageValue.toString(),bmiValue.toString(),x))
                Toast.makeText(this, "⛳ $resultBFValue saved to history !!", Toast.LENGTH_SHORT).show()
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
                        " ",
                        Toast.LENGTH_SHORT).show()
            }
        }
    }



    private fun validateUnits(): Boolean {

        var isValid: Boolean = true

        if (etMetricBFCAge.text.toString().isEmpty()) {
            isValid = false
        }
        if(etMetricBFCBMI.text.toString().isEmpty()){
            isValid = false
        }
        if(etMetricBFCWeight.text.toString().isEmpty()){
            isValid = false
        }
        return isValid
    }

    fun bmiCalled(view: View) {

        val intent = Intent(this@BodyFatCalculatorActivity, BMICalculatorActivity::class.java)
        startActivity(intent)

    }

    private fun hideKeyboard(view: View) {
        view.apply {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }
}




