package `in`.iot.lab.healthyways.tools.oneRM


import `in`.iot.lab.healthyways.adapters.ORMHistoryAdapter
import `in`.iot.lab.healthyways.room.history.HistoryViewModel
import `in`.iot.lab.healthyways.room.history.entities.OneRMCalcHistoryEntity
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
import kotlinx.android.synthetic.main.activity_orm_history.*


class ORMHistoryViewActivity : AppCompatActivity(), ORMHistoryAdapter.IORMHistoryRVAdapter {


    lateinit var viewModel: HistoryViewModel

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_orm_history)

        setSupportActionBar(toolbar_orm_history_activity)

        val actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
        actionBar!!.title = "1RM History"
        toolbar_orm_history_activity.setNavigationOnClickListener {
            onBackPressed()
        }


        val ormHistoryRecyclerView = rvAllORMHistory
        val adapter =
            ORMHistoryAdapter(this, this)
        ormHistoryRecyclerView.adapter = adapter
        ormHistoryRecyclerView.layoutManager = LinearLayoutManager(this)

        viewModel = ViewModelProvider(this,
                ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(HistoryViewModel::class.java)

        viewModel.allORMHistory.observe(this) {
            it.let{
                adapter.updateList(it)
            }
            when {
                it.isEmpty() -> {
                    omrLaYoga.visibility = View.VISIBLE
                    tvEmptyOrm.visibility = View.VISIBLE
                }
            }
        }
    }

    override fun onItemClicked(ormHistory: OneRMCalcHistoryEntity) {
        Toast.makeText(this, "✅ Deleted Successfully !!", Toast.LENGTH_SHORT).show()
        viewModel.deleteORMHistory(ormHistory)
    }
}