package com.siddharthsinghbaghel.healthyways.tools.BMR


import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.siddharthsinghbaghel.healthyways.R
import com.siddharthsinghbaghel.healthyways.adapters.BMRHistoryAdapter
import com.siddharthsinghbaghel.healthyways.adapters.ORMHistoryAdapter
import com.siddharthsinghbaghel.healthyways.room.history.HistoryViewModel
import com.siddharthsinghbaghel.healthyways.room.history.entities.BMRCalcHistoryEntity
import com.siddharthsinghbaghel.healthyways.room.history.entities.OneRMCalcHistoryEntity
import kotlinx.android.synthetic.main.activity_bmr_history.*
import kotlinx.android.synthetic.main.activity_orm_history.*
import kotlinx.android.synthetic.main.activity_orm_history.toolbar_orm_history_activity


class BMRHistoryViewActivity : AppCompatActivity(), BMRHistoryAdapter.IBMRHistoryRVAdapter {


    lateinit var viewModel: HistoryViewModel

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bmr_history)

        setSupportActionBar(toolbar_bmr_history_activity)

        val actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
        actionBar!!.title = "BMR History"
        toolbar_bmr_history_activity.setNavigationOnClickListener {
            onBackPressed()
        }


        val bmrHistoryRecyclerView = rvAllBMRHistory
        val adapter = BMRHistoryAdapter(this,this)
        bmrHistoryRecyclerView.adapter = adapter
        bmrHistoryRecyclerView.layoutManager = LinearLayoutManager(this)

        viewModel = ViewModelProvider(this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(HistoryViewModel::class.java)

        viewModel.allBMRHistory.observe(this,{
            it?.let{
                adapter.updateList(it)
            }
            when {
                it.isEmpty() -> {
                    bmrLaYoga.visibility = View.VISIBLE
                    tvEmptyBmr.visibility = View.VISIBLE
                }
            }
        })
    }


    override fun onItemClicked(bmrHistory: BMRCalcHistoryEntity) {
        Toast.makeText(this, "onItemClicked", Toast.LENGTH_SHORT).show()
        viewModel.deleteBMRHistory(bmrHistory)
    }
}