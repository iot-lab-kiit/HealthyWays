package com.siddharthsinghbaghel.healthyways.navFragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.siddharthsinghbaghel.healthyways.R


class AboutUsFragment : Fragment() {





    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val myAboutFragment = inflater.inflate(R.layout.fragment_about_us, container, false)
        return myAboutFragment
    }

}