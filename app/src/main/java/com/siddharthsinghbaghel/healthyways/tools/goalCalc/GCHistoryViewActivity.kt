package com.siddharthsinghbaghel.healthyways.tools.goalCalc


import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.siddharthsinghbaghel.healthyways.R
import com.siddharthsinghbaghel.healthyways.adapters.GCHistoryAdapter
import com.siddharthsinghbaghel.healthyways.room.history.HistoryViewModel
import com.siddharthsinghbaghel.healthyways.room.history.entities.GCHistoryEntity
import kotlinx.android.synthetic.main.activity_gc_history.*


class GCHistoryViewActivity : AppCompatActivity(), GCHistoryAdapter.IGCHistoryRVAdapter {


    lateinit var viewModel: HistoryViewModel

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gc_history)

        setSupportActionBar(toolbar_gc_history_activity)

        val actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
        actionBar!!.title = "Goal Calculator History"
        toolbar_gc_history_activity.setNavigationOnClickListener {
            onBackPressed()
        }


        val gcHistoryRecyclerView = rvAllGCHistory
        val adapter = GCHistoryAdapter(this,this)
        gcHistoryRecyclerView.adapter = adapter
        gcHistoryRecyclerView.layoutManager = LinearLayoutManager(this)

        viewModel = ViewModelProvider(this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(HistoryViewModel::class.java)

        viewModel.allGCHistory.observe(this) {
            it.let{
                adapter.updateList(it)
            }
            when {
                it.isEmpty() -> {
                    GCLaYoga.visibility = View.VISIBLE
                    tvEmptyGC.visibility = View.VISIBLE
                }
            }
        }
    }



    override fun onItemClicked(gcHistory: GCHistoryEntity) {
        Toast.makeText(this, "✅ Deleted Successfully", Toast.LENGTH_SHORT).show()
        viewModel.deleteGCHistory(gcHistory)
    }
}