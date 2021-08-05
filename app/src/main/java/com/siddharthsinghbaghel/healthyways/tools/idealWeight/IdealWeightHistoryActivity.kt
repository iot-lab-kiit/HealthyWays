package com.siddharthsinghbaghel.healthyways.tools.idealWeight


import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.siddharthsinghbaghel.healthyways.adapters.IWHistoryAdapter
import com.siddharthsinghbaghel.healthyways.R
import com.siddharthsinghbaghel.healthyways.room.iWHistory.IWHistoryEntity
import com.siddharthsinghbaghel.healthyways.room.iWHistory.IWHistoryViewModel
import kotlinx.android.synthetic.main.activity_iw_history.*


class IdealWeightHistoryActivity : AppCompatActivity(), IWHistoryAdapter.IIWHistoryRVAdapter {


    lateinit var viewModel: IWHistoryViewModel

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_iw_history)

        setSupportActionBar(toolbar_iw_history_activity)

        val actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
        actionBar!!.title = "Ideal weight History"
        toolbar_iw_history_activity.setNavigationOnClickListener {
            onBackPressed()
        }


        val iwHistoryRecyclerView = rvAllIwHistory
        val adapter = IWHistoryAdapter(this,this)
        iwHistoryRecyclerView.adapter = adapter
        iwHistoryRecyclerView.layoutManager = LinearLayoutManager(this)

        viewModel = ViewModelProvider(this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(IWHistoryViewModel::class.java)

        viewModel.allIwHistory.observe(this,{
            it?.let{
                adapter.updateList(it)
            }
            when {
                it.isEmpty() -> {
                    iwLaYoga.visibility = View.VISIBLE
                    iwTvEmpty.visibility = View.VISIBLE
                }
            }
        })
    }

    override fun onItemClicked(iwHistory: IWHistoryEntity) {
        Toast.makeText(this, "onItemClicked", Toast.LENGTH_SHORT).show()
        viewModel.deleteIWHistory(iwHistory)
    }
}