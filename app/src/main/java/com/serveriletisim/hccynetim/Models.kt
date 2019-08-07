package com.serveriletisim.hccynetim

import android.annotation.SuppressLint
import android.content.Context
import androidx.core.content.ContextCompat
import java.text.SimpleDateFormat
import java.util.*

class Mosque(var id: String) {

    var name: String? = null
    var city: String? = null
    var district: String? = null

    fun location(): String {
        return "$city / $district"
    }
}

class User(var id: String) {
    var name: String? = null
}

class InspectionTime(var prayerTime: PrayerTime) {
    enum class PrayerTime {
        FAJR,
        DHUHR,
        ASR,
        MAGRIB,
        ISHA
    }

    enum class STATE {
        EMPTY,
        INSPECTION_COMPLATED,
        SYNCED,
    }

    var date: Long? = null
    var state: STATE = STATE.EMPTY

    fun prayerTimeValue(context: Context): String {
        return when (prayerTime) {
            PrayerTime.FAJR -> context.resources.getString(R.string.fajr)
            PrayerTime.DHUHR -> context.resources.getString(R.string.dhuhr)
            PrayerTime.ASR -> context.resources.getString(R.string.asr)
            PrayerTime.MAGRIB -> context.resources.getString(R.string.magrib)
            PrayerTime.ISHA -> context.resources.getString(R.string.isha)
        }
    }

    fun stateValue(context: Context): Pair<Int, String> {
        return when (state) {
            STATE.EMPTY -> Pair(
                ContextCompat.getColor(context, R.color.inspectionEmpty),
                context.getString(R.string.inspecitonEmpty)
            )
            STATE.INSPECTION_COMPLATED -> Pair(
                ContextCompat.getColor(context, R.color.inspectionCompleted),
                context.getString(R.string.inspectionCompleted)
            )
            STATE.SYNCED -> Pair(
                ContextCompat.getColor(context, R.color.inspectionSynced),
                context.getString(R.string.inspectionSynced)
            )
        }
    }
}


class HccDate(val dateString: String, val dayIndex: Int, var isActive: Boolean) {
    val simpleDateFormat = SimpleDateFormat("dd MM yyyy", Locale.ROOT)
    val date: Date
    init {
        date = simpleDateFormat.parse(dateString)!!
    }
}
