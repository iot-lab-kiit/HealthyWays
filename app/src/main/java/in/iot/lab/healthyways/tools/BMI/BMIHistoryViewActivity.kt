package `in`.iot.lab.healthyways.tools.BMI

import `in`.iot.lab.healthyways.R
import `in`.iot.lab.healthyways.adapters.BMIHistoryAdapter
import `in`.iot.lab.healthyways.room.history.HistoryViewModel
import `in`.iot.lab.healthyways.room.history.entities.BMIHistoryEntity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager


import kotlinx.android.synthetic.main.activity_bmi_history.*

class BMIHistoryViewActivity : AppCompatActivity(), BMIHistoryAdapter.IBMIHistoryRVAdapter {

    lateinit var viewModel: HistoryViewModel

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
        val adapter =
            BMIHistoryAdapter(this, this)
        bmiHistoryRecyclerView.adapter = adapter
        bmiHistoryRecyclerView.layoutManager = LinearLayoutManager(this)

        viewModel = ViewModelProvider(this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(HistoryViewModel::class.java)

        viewModel.allBmiHistory.observe(this) {
            it.let{
                adapter.updateList(it)
            }
            when {
                it.isEmpty() -> {
                    laYoga.visibility = View.VISIBLE
                    tvEmpty.visibility = View.VISIBLE
                }
            }
        }
    }

    override fun onItemClicked(bmiHistory: BMIHistoryEntity) {
        Toast.makeText(this, "✅ Deleted Successfully", Toast.LENGTH_SHORT).show()
        viewModel.deleteBMIHistory(bmiHistory)
    }
}