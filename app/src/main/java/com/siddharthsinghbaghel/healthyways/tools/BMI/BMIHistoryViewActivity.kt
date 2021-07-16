package com.siddharthsinghbaghel.healthyways.tools.BMI

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.siddharthsinghbaghel.healthyways.BMIHistoryAdapter
import com.siddharthsinghbaghel.healthyways.R
import com.siddharthsinghbaghel.healthyways.room.BMIHistoryEntity
import com.siddharthsinghbaghel.healthyways.room.BMIHistoryViewModel
import kotlinx.android.synthetic.main.activity_bmi.*
import kotlinx.android.synthetic.main.activity_bmi_history.*

class BMIHistoryViewActivity : AppCompatActivity(), BMIHistoryAdapter.IBMIHistoryRVAdapter {

    lateinit var viewModel: BMIHistoryViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bmi_history)

        setSupportActionBar(toolbar_bmi_history_activity)

        val actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
        actionBar!!.title = "BMI History"
        toolbar_bmi_history_activity.setNavigationOnClickListener {

            onBackPressed()
        }

        val bmiHistoryRecyclerView = rvAllBmiHistory
        val adapter = BMIHistoryAdapter(this,this)
        bmiHistoryRecyclerView.adapter = adapter
        bmiHistoryRecyclerView.layoutManager = LinearLayoutManager(this)

        viewModel = ViewModelProvider(this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(BMIHistoryViewModel::class.java)

        viewModel.allBmiHistory.observe(this,{
            it?.let{
                adapter.updateList(it)
            }
            when {
                it.isEmpty() -> {
                    laYoga.visibility = View.VISIBLE
                    tvEmpty.visibility = View.VISIBLE
                }
            }
        })
    }

    override fun onItemClicked(bmiHistory: BMIHistoryEntity) {
        Toast.makeText(this, "onItemClicked", Toast.LENGTH_SHORT).show()
        viewModel.deleteBMIHistory(bmiHistory)
    }
}