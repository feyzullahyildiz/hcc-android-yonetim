package com.serveriletisim.hccynetim

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.w3c.dom.Text

class DaySelectorDialog(val daylist: ArrayList<HccDate>, val listener: ResultListener) : DialogFragment() {
    interface ResultListener {
        fun success(day: HccDate)
        fun fail()
    }

    var adapter: HccDateAdapter? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.dialog_fragment_day_selector, container, false)

        val recyclerView = view.findViewById<RecyclerView>(R.id.dialog_fragment_day_selector_recyclerview)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        this.adapter = HccDateAdapter()
        recyclerView.adapter = this.adapter
        val accept = view.findViewById<Button>(R.id.dialog_fragment_day_selector_accept_button)
        accept.setOnClickListener {
            val active = daylist.find { it.isActive }
            this.listener.success(active!!)
            this.dismiss()
        }
        val cancel = view.findViewById<Button>(R.id.dialog_fragment_day_selector_cancel_button)
        cancel.setOnClickListener {
            this.listener.fail()
            this.dismiss()
        }

        return view
    }

    fun activateHccItem(hccdate: HccDate) {
        if (hccdate.isActive) {
            return
        }
        val activeItem = daylist.find { it.isActive == true}
        activeItem?.isActive = false
        hccdate.isActive = true
        this.adapter?.notifyDataSetChanged()

    }


    inner class HccDateViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        var model: HccDate? = null
        var leftTextView: TextView
        var rightTextView: TextView

        init {
            itemView.setOnClickListener(this)
            leftTextView = itemView.findViewById<TextView>(R.id.item_dialog_fragment_day_selector_left_textview)
            rightTextView = itemView.findViewById<TextView>(R.id.item_dialog_fragment_day_selector_right_textview)
        }

        fun bind(hccdate: HccDate) {
            leftTextView.text = hccdate.dateString
            rightTextView.text = activity!!.getString(R.string.xDay, hccdate.dayIndex)
            if (hccdate.isActive) {
                val color = ContextCompat.getColor(activity!!, R.color.activeBackgroundColor)
                itemView.setBackgroundColor(color)
            } else {
                val color = ContextCompat.getColor(activity!!, R.color.cardview_light_background)
                itemView.setBackgroundColor(color)
            }

            this.model = hccdate
        }

        override fun onClick(p0: View?) {
            this@DaySelectorDialog.activateHccItem(model!!)
        }
    }

    inner class HccDateAdapter() : RecyclerView.Adapter<HccDateViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HccDateViewHolder {
            val view = LayoutInflater.from(activity).inflate(R.layout.item_dialog_fragment_day_selector, parent, false)
            return HccDateViewHolder(view)
        }

        override fun getItemCount(): Int {
            return daylist.size
        }

        override fun onBindViewHolder(holder: HccDateViewHolder, position: Int) {
            holder.bind(daylist.get(position))
        }

    }

}