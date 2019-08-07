package com.serveriletisim.hccynetim

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.serveriletisim.hccynetim.Fragment.MosqueInspectionFragment
import com.serveriletisim.hccynetim.Fragment.MosqueReportFragment

class MosqueDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mosque_detail)
        val viewPager = findViewById<ViewPager>(R.id.activity_mosque_detail_viewpager)
        val tabLayout = findViewById<TabLayout>(R.id.activity_mosque_detail_tablayout)

        val mosque = Utils.activeMosqueSubject.value

        val location = findViewById<TextView>(R.id.activity_mosque_detail_textview1)
        val info = findViewById<TextView>(R.id.activity_mosque_detail_textview2)

        location.text = mosque?.location()
        info.text = mosque?.name

        tabLayout.tabGravity = TabLayout.GRAVITY_FILL
        tabLayout.addTab(tabLayout.newTab().apply { text = resources.getText(R.string.info) })
        tabLayout.addTab(tabLayout.newTab().apply { text = resources.getText(R.string.inspection) })
        tabLayout.addTab(tabLayout.newTab().apply { text = resources.getText(R.string.report) })
        viewPager.adapter = TabAdapter(supportFragmentManager, 3)
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(p0: TabLayout.Tab?) {}
            override fun onTabUnselected(p0: TabLayout.Tab?) {}
            override fun onTabSelected(tab: TabLayout.Tab?) {
                viewPager.currentItem = tab!!.position
            }

        })
        viewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))
    }

    inner class TabAdapter(val fm: FragmentManager, private val pageCount: Int) : FragmentStatePagerAdapter(fm) {
        override fun getCount(): Int {
            return pageCount
        }

        override fun getItem(position: Int): Fragment {
            return when (position) {
                0 -> MosqueInfoFragment()
                1 -> MosqueInspectionFragment()
                else -> MosqueReportFragment()
            }
        }
    }
}
