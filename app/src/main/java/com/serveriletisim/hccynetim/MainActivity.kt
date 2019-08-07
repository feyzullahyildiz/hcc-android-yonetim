package com.serveriletisim.hccynetim

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    var recyclerView: RecyclerView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar = supportActionBar
        toolbar?.title = resources.getString(R.string.mosques)
        recyclerView = findViewById(R.id.activity_main_recyclerview)
        recyclerView?.layoutManager = LinearLayoutManager(this)
        reload()
    }

    fun reload() {

        RequestManager.mosques(this, object : MosqueResponse {
            override fun success(response: ArrayList<Mosque>) {
                recyclerView?.adapter = MosqueAdapter(response)
            }

            override fun error(error: Exception) {
            }
        })
    }

    inner class MosqueAdapter(val mosques: ArrayList<Mosque>): RecyclerView.Adapter<MosqueViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MosqueViewHolder {
            val view = LayoutInflater.from(this@MainActivity)
                .inflate(R.layout.item_mosque, parent, false)

            return  MosqueViewHolder(view)
        }

        override fun getItemCount(): Int {
            return mosques.size
        }

        override fun onBindViewHolder(holder: MosqueViewHolder, position: Int) {
            holder.bind(mosques.get(position))
        }

    }

    inner class MosqueViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        val nameText: TextView
        val locationText: TextView
        var mosque: Mosque? = null

        init {
            nameText = itemView.findViewById<TextView>(R.id.item_mosque_name)
            locationText = itemView.findViewById<TextView>(R.id.item_mosque_location)
            itemView.setOnClickListener(this)
        }

        fun bind(m: Mosque) {
            nameText.text = m.name
            locationText.text = m.location()

            this.mosque = m
        }

        override fun onClick(p0: View?) {
            Utils.activeMosqueSubject.onNext(this.mosque!!)
            val detailIntent = Intent(this@MainActivity, MosqueDetailActivity::class.java)
            startActivity(detailIntent)
        }

    }
}
