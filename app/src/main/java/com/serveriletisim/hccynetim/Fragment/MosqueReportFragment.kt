package com.serveriletisim.hccynetim.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.serveriletisim.hccynetim.R
import kotlinx.android.synthetic.main.fragment_mosque_info.view.*

class MosqueReportFragment: Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_mosque_report, container, false)


        return view
    }
}