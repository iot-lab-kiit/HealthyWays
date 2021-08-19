package `in`.iot.lab.healthyways.tools.BMR


import `in`.iot.lab.healthyways.adapters.BMRHistoryAdapter
import `in`.iot.lab.healthyways.room.history.HistoryViewModel
import `in`.iot.lab.healthyways.room.history.entities.BMRCalcHistoryEntity
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.iot.lab.healthyways.R
import kotlinx.android.synthetic.main.activity_bmr_history.*


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
        val adapter =
            BMRHistoryAdapter(this, this)
        bmrHistoryRecyclerView.adapter = adapter
        bmrHistoryRecyclerView.layoutManager = LinearLayoutManager(this)

        viewModel = ViewModelProvider(this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(HistoryViewModel::class.java)

        viewModel.allBMRHistory.observe(this) {
            it.let{
                adapter.updateList(it)
            }
            when {
                it.isEmpty() -> {
                    bmrLaYoga.visibility = View.VISIBLE
                    tvEmptyBmr.visibility = View.VISIBLE
                }
            }
        }
    }


    override fun onItemClicked(bmrHistory: BMRCalcHistoryEntity) {
        Toast.makeText(this, "✅ Deleted Successfully !!", Toast.LENGTH_SHORT).show()
        viewModel.deleteBMRHistory(bmrHistory)
    }
}