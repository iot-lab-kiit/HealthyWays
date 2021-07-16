package com.siddharthsinghbaghel.healthyways.exercise

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.siddharthsinghbaghel.healthyways.R
import com.siddharthsinghbaghel.healthyways.navFragments.ExerciseFragment
import kotlinx.android.synthetic.main.activity_exercise_finish.*

class ExerciseFinishActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exercise_finish)

        setSupportActionBar(toolbar_finish_activity)
        val actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)

        toolbar_finish_activity.setNavigationOnClickListener {
            onBackPressed()
        }

    }
}