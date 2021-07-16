package com.siddharthsinghbaghel.healthyways.navFragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.siddharthsinghbaghel.healthyways.R
import com.siddharthsinghbaghel.healthyways.tools.BMI.BMICalculatorActivity
import com.siddharthsinghbaghel.healthyways.tools.BMR.BMRCalculatorActivity
import com.siddharthsinghbaghel.healthyways.tools.bodyFat.BodyFatCalculatorActivity
import com.siddharthsinghbaghel.healthyways.tools.idealWeight.IdealWeightActivity
import com.siddharthsinghbaghel.healthyways.tools.oneRM.OneRMCalculatorActivity
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

        return myToolsFragmentView

    }



}