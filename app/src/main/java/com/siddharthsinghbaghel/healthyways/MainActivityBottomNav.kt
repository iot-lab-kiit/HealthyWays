package com.siddharthsinghbaghel.healthyways

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.siddharthsinghbaghel.healthyways.navFragments.API_Frag_NameTOBeChanged
import com.siddharthsinghbaghel.healthyways.navFragments.ExerciseFragment
import com.siddharthsinghbaghel.healthyways.navFragments.HistoryFragment
import com.siddharthsinghbaghel.healthyways.navFragments.ToolsFragment
import kotlinx.android.synthetic.main.activity_main_bottom_nav.*



class MainActivityBottomNav : AppCompatActivity() {

    private val toolsFragment = ToolsFragment()
    private val historyFragment = HistoryFragment()
    private val exerciseFragment = ExerciseFragment()
    private val apiFragment = API_Frag_NameTOBeChanged()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_bottom_nav)

        setSupportActionBar(toolbar_bottom_nav)

        val actionBar = supportActionBar
        actionBar!!.title = "🌟  Healthy Ways"



        replaceFragments(toolsFragment)

        bottomNav.setOnNavigationItemSelectedListener {

            when(it.itemId){
                R.id.tools -> replaceFragments(toolsFragment)
                R.id.sevenMinExercises -> replaceFragments(exerciseFragment)
                R.id.history -> replaceFragments(historyFragment)
                R.id.api -> replaceFragments(apiFragment)
            }
            true
        }
    }

    private fun replaceFragments(fragment: Fragment){

        if(fragment != null){
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_container, fragment)
            transaction.commit()
        }
    }
}