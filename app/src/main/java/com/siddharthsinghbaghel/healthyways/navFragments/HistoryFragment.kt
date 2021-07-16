package com.siddharthsinghbaghel.healthyways.navFragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.siddharthsinghbaghel.healthyways.R
import com.siddharthsinghbaghel.healthyways.tools.BMI.BMIHistoryViewActivity
import kotlinx.android.synthetic.main.fragment_history.*
import kotlinx.android.synthetic.main.fragment_history.view.*


class HistoryFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val myHistoryFragmentView =  inflater.inflate(R.layout.fragment_history, container, false)

        myHistoryFragmentView.llBMIHistory.setOnClickListener {

            val intent = Intent(context, BMIHistoryViewActivity::class.java)
            startActivity(intent)
        }

        return myHistoryFragmentView


    }


}