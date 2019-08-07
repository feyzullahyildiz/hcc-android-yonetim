package com.serveriletisim.hccynetim

import android.app.Activity
import okhttp3.OkHttpClient
import java.lang.Exception

object RequestManager {

//    var client = OkHttpClient()
    lateinit var token: String

    fun mosques(activity: Activity, mosqueResponseCallback: MosqueResponse ) {
        Thread {
            Thread.sleep(1000)

            val m1 = Mosque("_cami.1").apply {
                name = "Cami 1"
                city = "İstanbul"
                district = "Fatih"
            }
            val m2 = Mosque("_cami.2").apply {
                name = "Cami 2"
                city = "İstanbul"
                district = "Fatih"
            }
            val m3 = Mosque("_cami.3").apply {
                name = "Cami 3"
                city = "İstanbul"
                district = "Fatih"
            }
            activity.runOnUiThread {
                val res = arrayListOf<Mosque>(m1, m2, m3)
                mosqueResponseCallback.success(res)
            }
        }.run()
    }

}


interface MosqueResponse {
    fun success(response: ArrayList<Mosque>)
    fun error(error: Exception)
}

