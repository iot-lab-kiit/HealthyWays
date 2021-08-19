package `in`.iot.lab.healthyways.navFragments

import `in`.iot.lab.healthyways.tools.BMI.BMIHistoryViewActivity
import `in`.iot.lab.healthyways.tools.BMR.BMRHistoryViewActivity
import `in`.iot.lab.healthyways.tools.bodyFat.FatHistoryViewActivity
import `in`.iot.lab.healthyways.tools.goalCalc.GCHistoryViewActivity
import `in`.iot.lab.healthyways.tools.idealWeight.IdealWeightHistoryActivity
import `in`.iot.lab.healthyways.tools.oneRM.ORMHistoryViewActivity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.iot.lab.healthyways.R
import kotlinx.android.synthetic.main.fragment_history.view.*


class HistoryFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val myHistoryFragmentView =  inflater.inflate(R.layout.fragment_history, container, false)

        myHistoryFragmentView.cvBMI.setOnClickListener {

            val intent = Intent(context, BMIHistoryViewActivity::class.java)
            startActivity(intent)
        }
        myHistoryFragmentView.cvIW.setOnClickListener {

            val intent = Intent(context, IdealWeightHistoryActivity::class.java)
            startActivity(intent)
        }
        myHistoryFragmentView.cvORMHistory.setOnClickListener {

            val intent = Intent(context, ORMHistoryViewActivity::class.java)
            startActivity(intent)
        }
        myHistoryFragmentView.cvBMRHistory.setOnClickListener {

            val intent = Intent(context, BMRHistoryViewActivity::class.java)
            startActivity(intent)
        }
        myHistoryFragmentView.cvFatHistory.setOnClickListener {

            val intent = Intent(context, FatHistoryViewActivity::class.java)
            startActivity(intent)
        }
        myHistoryFragmentView.cvGCHistory.setOnClickListener {

            val intent = Intent(context, GCHistoryViewActivity::class.java)
            startActivity(intent)
        }

        return myHistoryFragmentView


    }


}