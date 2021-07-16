package com.siddharthsinghbaghel.healthyways.navFragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.siddharthsinghbaghel.healthyways.R
import com.siddharthsinghbaghel.healthyways.exercise.ExerciseActivity
import kotlinx.android.synthetic.main.fragment_exercise.view.*


class ExerciseFragment : Fragment() {


    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        val myExerciseFragment = inflater.inflate(R.layout.fragment_exercise, container, false)


        myExerciseFragment.llStart.setOnClickListener{
            val intent = Intent(context, ExerciseActivity::class.java)
            startActivity(intent)
        }

        return myExerciseFragment
    }



}