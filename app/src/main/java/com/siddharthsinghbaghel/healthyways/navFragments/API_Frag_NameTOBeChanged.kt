package com.siddharthsinghbaghel.healthyways.navFragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.siddharthsinghbaghel.healthyways.R


class API_Frag_NameTOBeChanged : Fragment(){

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val myApiFragment = inflater.inflate(R.layout.api_name_to_be_changed, container, false)
        return myApiFragment
    }


}