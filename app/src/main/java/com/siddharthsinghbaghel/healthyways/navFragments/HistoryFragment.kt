package com.siddharthsinghbaghel.healthyways.navFragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.siddharthsinghbaghel.healthyways.R
import com.siddharthsinghbaghel.healthyways.tools.BMI.BMIHistoryViewActivity
import com.siddharthsinghbaghel.healthyways.tools.BMR.BMRHistoryViewActivity
import com.siddharthsinghbaghel.healthyways.tools.bodyFat.FatHistoryViewActivity
import com.siddharthsinghbaghel.healthyways.tools.idealWeight.IdealWeightHistoryActivity
import com.siddharthsinghbaghel.healthyways.tools.oneRM.ORMHistoryViewActivity
import kotlinx.android.synthetic.main.fragment_history.view.*
import kotlinx.android.synthetic.main.fragment_tools.view.*


class HistoryFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val myHistoryFragmentView =  inflater.inflate(R.layout.fragment_history, container, false)

        myHistoryFragmentView.cvBMI.setOnClickListener {

            val intent = Intent(context, BMIHistoryViewActivity::class.java)
            startActivity(intent)
        }
        myHistoryFragmentView.cvIW.setOnClickListener {

            val intent = Intent(context, IdealWeightHistoryActivity::class.java)
            startActivity(intent)
        }
        myHistoryFragmentView.cvORMHistory.setOnClickListener {

            val intent = Intent(context, ORMHistoryViewActivity::class.java)
            startActivity(intent)
        }
        myHistoryFragmentView.cvBMRHistory.setOnClickListener {

            val intent = Intent(context, BMRHistoryViewActivity::class.java)
            startActivity(intent)
        }
        myHistoryFragmentView.cvFatHistory.setOnClickListener {

            val intent = Intent(context, FatHistoryViewActivity::class.java)
            startActivity(intent)
        }

        return myHistoryFragmentView


    }


}