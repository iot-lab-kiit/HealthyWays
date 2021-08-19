package `in`.iot.lab.healthyways.tools.goalCalc

import `in`.iot.lab.healthyways.room.history.HistoryViewModel
import `in`.iot.lab.healthyways.room.history.entities.GCHistoryEntity
import `in`.iot.lab.healthyways.tools.BMR.BMRCalculatorActivity
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ScrollView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import com.iot.lab.healthyways.R
import kotlinx.android.synthetic.main.activity_goal_calculator.*
import java.math.BigDecimal
import java.math.RoundingMode
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class GoalCalculator : AppCompatActivity() {

    lateinit var viewModel : HistoryViewModel

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_goal_calculator)

        setSupportActionBar(toolbar_GC_activity)



        val actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
        actionBar!!.title = "Goal Calculator"
        toolbar_GC_activity.setNavigationOnClickListener {

            onBackPressed()
        }

        viewModel = ViewModelProvider(this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(
            HistoryViewModel::class.java)

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
                Toast.makeText(this, "❌ Please enter valid values !!", Toast.LENGTH_SHORT).show()
            }

        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun calculateGC(currentWeight: Float, targetWeight: Float, tee: Float, weeks: Int, view: View) {

        when {

            currentWeight > targetWeight -> {

                val totalWeightLoss = currentWeight - targetWeight
                val totalCaloriesToLoose = totalWeightLoss * 7700
                val totalDays = weeks * 7
                val caloriesLoosePerDay = totalCaloriesToLoose / totalDays
                val caloriesLoosePerDayFormatted = BigDecimal(caloriesLoosePerDay.toDouble()).setScale(2, RoundingMode.HALF_EVEN).toString()
                if (caloriesLoosePerDayFormatted.toFloat() > 1000 || caloriesLoosePerDayFormatted.toFloat() < 0) {
                    val snackBar = Snackbar.make(
                        view, "❌ This goal seems to be too aggressive you should try to set an achievable and sustainable goal",
                        Snackbar.LENGTH_LONG
                    ).setAction("Action", null)
                    snackBar.setActionTextColor(Color.BLUE)
                    snackBar.show()

                    llResultGC.visibility = View.GONE
                }else{

                    llResultGC.visibility = View.VISIBLE
                    val goalCalorieResult = tee - caloriesLoosePerDayFormatted.toFloat()
                    val finalGoalValue = BigDecimal(goalCalorieResult.toDouble()).setScale(2, RoundingMode.HALF_EVEN).toString()
                    val deficitCaloriesValue = caloriesLoosePerDayFormatted.toFloat()

                    tvKcalPerDayValue.text = finalGoalValue.toString()

                    tvCalDefSur.text = "Calories Deficit"
                    tvSurplus.text = deficitCaloriesValue.toString()


                    val currentDateTime = LocalDateTime.now()
                    val x = currentDateTime.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")).toString()

                    viewModel.insertGCHistory(GCHistoryEntity(currentWeight.toString(),targetWeight.toString(),weeks.toString(),x,goalCalorieResult.toString()))
                    Toast.makeText(this, "⛳ $goalCalorieResult saved in history !!", Toast.LENGTH_SHORT).show()
                }



            }
            currentWeight < targetWeight -> {

                val totalWeightGain = targetWeight - currentWeight
                val totalCaloriesToGain = totalWeightGain * 7700
                val totalDays = weeks * 7
                val caloriesGainPerDay = totalCaloriesToGain / totalDays
                val caloriesGainPerDayFormatted = BigDecimal(caloriesGainPerDay.toDouble()).setScale(2, RoundingMode.HALF_EVEN).toString()

                if (caloriesGainPerDayFormatted.toFloat() > 1000 || caloriesGainPerDayFormatted.toFloat() < 0) {

                    val snackBar = Snackbar.make(
                        view, "This goal seems to be too aggressive you should try to set an achievable and sustainable goal",
                        Snackbar.LENGTH_LONG
                    ).setAction("Action", null)
                    snackBar.setActionTextColor(Color.BLUE)
                    snackBar.show()

                    llResultGC.visibility = View.GONE

                }else{

                    llResultGC.visibility = View.VISIBLE
                     val goalCalorieResult = tee + caloriesGainPerDayFormatted.toFloat()
                     val surplusCaloriesValue = caloriesGainPerDayFormatted.toFloat()

                     tvKcalPerDayValue.text = goalCalorieResult.toString()
                     tvSurplus.text = surplusCaloriesValue.toString()


                    val currentDateTime = LocalDateTime.now()
                    val x = currentDateTime.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")).toString()

                    viewModel.insertGCHistory(GCHistoryEntity(currentWeight.toString(),targetWeight.toString(),weeks.toString(),x,goalCalorieResult.toString()))
                    Toast.makeText(this, "⛳ $goalCalorieResult saved in history !!", Toast.LENGTH_SHORT).show()

                }

            }
            else -> {
                llResultGC.visibility = View.GONE
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

    fun openBMRCalculator(view: View) {
        val intent = Intent(this, BMRCalculatorActivity::class.java)
        startActivity(intent)
    }

    fun openConverter(view: View) {
        val intent = Intent("android.intent.action.VIEW", Uri.parse("https://www.unitconverters.net/"));
        startActivity(intent)
    }
}


