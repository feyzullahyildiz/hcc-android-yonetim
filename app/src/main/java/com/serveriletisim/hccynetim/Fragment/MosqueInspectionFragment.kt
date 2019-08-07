package com.serveriletisim.hccynetim.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.serveriletisim.hccynetim.DaySelectorDialog
import com.serveriletisim.hccynetim.HccDate
import com.serveriletisim.hccynetim.InspectionTime
import com.serveriletisim.hccynetim.R
import kotlin.collections.ArrayList

class MosqueInspectionFragment : Fragment(), DaySelectorDialog.ResultListener {
    override fun success(day: HccDate) {

    }

    override fun fail() {
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_mosque_inspection, container, false)

        val dateCardView = view.findViewById<CardView>(R.id.fragment_mosque_inspection_cardview)
        dateCardView.setOnClickListener {
            val list = arrayListOf<HccDate>(
                HccDate("10 01 2020", 1, true),
                HccDate("11 01 2020", 2, false),
                HccDate("12 01 2020", 3, false),
                HccDate("13 01 2020", 4, false),
                HccDate("14 01 2020", 5, false),
                HccDate("15 01 2020", 6, false),
                HccDate("16 01 2020", 7, false),
                HccDate("17 01 2020", 8, false),
                HccDate("18 01 2020", 9, false)

//                ,
//                HccDate("19 01 2020", 10, false),
//                HccDate("21 01 2020", 11, false),
//                HccDate("22 01 2020", 12, false),
//                HccDate("23 01 2020", 13, false),
//                HccDate("24 01 2020", 14, false),
//                HccDate("25 01 2020", 15, false),
//                HccDate("26 01 2020", 16, false),
//                HccDate("27 01 2020", 17, false),
//                HccDate("28 01 2020", 18, false),
//                HccDate("29 01 2020", 19, false)
            )
            val dialog = DaySelectorDialog(list, this)
            dialog.show(activity!!.supportFragmentManager, "day-selector")
        }
        val dateTextView = view.findViewById<TextView>(R.id.fragment_mosque_inspection_date)
        dateTextView.text = "11 OCAK 2020"

        val dayTextView = view.findViewById<TextView>(R.id.fragment_mosque_inspection_day)
        dayTextView.text = "2"
        val recyclerView = view.findViewById<RecyclerView>(R.id.fragment_mosque_inspection_recyclerview)
        recyclerView.layoutManager = LinearLayoutManager(activity)

        val inspectionList = arrayListOf<InspectionTime>(
            InspectionTime(InspectionTime.PrayerTime.FAJR).apply { state = InspectionTime.STATE.SYNCED},
            InspectionTime(InspectionTime.PrayerTime.DHUHR).apply { state = InspectionTime.STATE.INSPECTION_COMPLATED },
            InspectionTime(InspectionTime.PrayerTime.ASR),
            InspectionTime(InspectionTime.PrayerTime.MAGRIB),
            InspectionTime(InspectionTime.PrayerTime.ISHA)
        )
        val adapter = InspecionTimeAdapter(inspectionList)
        recyclerView.adapter = adapter

        return view
    }

    inner class InspecionTimeViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var stateTextView: TextView
        var timeTextView : TextView
        var model: InspectionTime? = null
        init {
            timeTextView = itemView.findViewById<TextView>(R.id.item_inspection_time_time_textview)
            stateTextView = itemView.findViewById<TextView>(R.id.item_inspection_time_state_textview)
        }
        fun bind(_inspection: InspectionTime) {
            model = _inspection
            val timeValue = model?.prayerTimeValue(this@MosqueInspectionFragment.activity!!)
            timeTextView.text = timeValue
            val stateValue = model?.stateValue(this@MosqueInspectionFragment.activity!!)
            stateTextView.text = stateValue?.second
            stateTextView.setTextColor(stateValue?.first!!)
        }
    }

    inner class InspecionTimeAdapter(val inspections: ArrayList<InspectionTime>): RecyclerView.Adapter<InspecionTimeViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InspecionTimeViewHolder {
            val view = layoutInflater.inflate(R.layout.item_inspection_time, parent, false)
            return InspecionTimeViewHolder(view);
        }

        override fun getItemCount(): Int {
            return inspections.size
        }

        override fun onBindViewHolder(holder: InspecionTimeViewHolder, position: Int) {
            holder.bind(inspections.get(position))
        }

    }
}