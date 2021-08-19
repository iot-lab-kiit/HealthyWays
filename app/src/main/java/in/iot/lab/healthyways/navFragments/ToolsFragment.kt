package `in`.iot.lab.healthyways.navFragments

import `in`.iot.lab.healthyways.tools.BMI.BMICalculatorActivity
import `in`.iot.lab.healthyways.tools.BMR.BMRCalculatorActivity
import `in`.iot.lab.healthyways.tools.bodyFat.BodyFatCalculatorActivity
import `in`.iot.lab.healthyways.tools.goalCalc.GoalCalculator
import `in`.iot.lab.healthyways.tools.idealWeight.IdealWeightActivity
import `in`.iot.lab.healthyways.tools.oneRM.OneRMCalculatorActivity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.iot.lab.healthyways.R
import kotlinx.android.synthetic.main.fragment_tools.view.*

class ToolsFragment : Fragment() {

    /* onCrateView inflates the view*/
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val myToolsFragmentView =  inflater.inflate(R.layout.fragment_tools, container, false)


        myToolsFragmentView.cvBmi.setOnClickListener{
            val intent = Intent(context, BMICalculatorActivity::class.java)
            startActivity(intent)
        }
        myToolsFragmentView.cvOneRm.setOnClickListener{
            val intent = Intent(context, OneRMCalculatorActivity::class.java)
            startActivity(intent)
        }
        myToolsFragmentView.cvFatCalc.setOnClickListener{
            val intent = Intent(context, BodyFatCalculatorActivity::class.java)
            startActivity(intent)
        }
        myToolsFragmentView.cvCalorieCalc.setOnClickListener{
            val intent = Intent(context, BMRCalculatorActivity::class.java)
            startActivity(intent)
        }
        myToolsFragmentView.cvIdealWeight.setOnClickListener{
            val intent = Intent(context, IdealWeightActivity::class.java)
            startActivity(intent)
        }
        myToolsFragmentView.cvGoalCalc.setOnClickListener{
            val intent = Intent(context, GoalCalculator::class.java)
            startActivity(intent)
        }

        return myToolsFragmentView

    }



}