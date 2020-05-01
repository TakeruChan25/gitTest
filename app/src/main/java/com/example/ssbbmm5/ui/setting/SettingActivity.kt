package com.example.ssbbmm5.ui.setting

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ssbbmm5.R
import com.example.ssbbmm5.broadcast.DailyReminder
import kotlinx.android.synthetic.main.activity_setting.*
import kotlin.properties.Delegates

class SettingActivity : AppCompatActivity() {

    private var dailyReminder = DailyReminder()
    private var Daily by Delegates.notNull<Boolean>()

    companion object{
        var DAILY = "daily"
        var SAVE = "save"
    }


    @SuppressLint("CommitPrefEdits")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)
        title = getString(R.string.notification_setting)
        val sharedPreferences = getSharedPreferences(SAVE, MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        if (savedInstanceState != null){
            Daily = savedInstanceState.getBoolean(DAILY)
        }
        else {
            Daily = sharedPreferences.getBoolean(DAILY, false)
        }
    switch_daily_reminder.isChecked = Daily

        switch_daily_reminder.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                dailyReminder.setAlarm(applicationContext)
            } else {
                dailyReminder.cancelAlarm(applicationContext)
            }
            Daily = isChecked
            editor.putBoolean(DAILY, Daily)
            editor.apply()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean(DAILY, Daily)
    }

}
