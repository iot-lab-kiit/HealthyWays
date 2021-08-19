package `in`.iot.lab.healthyways.tools.idealWeight


import `in`.iot.lab.healthyways.R
import `in`.iot.lab.healthyways.adapters.IWHistoryAdapter
import `in`.iot.lab.healthyways.room.history.HistoryViewModel
import `in`.iot.lab.healthyways.room.history.entities.IWHistoryEntity
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_iw_history.*


class IdealWeightHistoryActivity : AppCompatActivity(), IWHistoryAdapter.IIWHistoryRVAdapter {


    lateinit var viewModel: HistoryViewModel

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
        val adapter =
            IWHistoryAdapter(this, this)
        iwHistoryRecyclerView.adapter = adapter
        iwHistoryRecyclerView.layoutManager = LinearLayoutManager(this)

        viewModel = ViewModelProvider(this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(HistoryViewModel::class.java)

        viewModel.allIwHistory.observe(this) {
            it.let{
                adapter.updateList(it)
            }
            when {
                it.isEmpty() -> {
                    iwLaYoga.visibility = View.VISIBLE
                    iwTvEmpty.visibility = View.VISIBLE
                }
            }
        }
    }

    override fun onItemClicked(iwHistory: IWHistoryEntity) {
        Toast.makeText(this, "✅ Deleted Successfully", Toast.LENGTH_SHORT).show()
        viewModel.deleteIWHistory(iwHistory)
    }
}