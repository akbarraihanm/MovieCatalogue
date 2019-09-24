package com.example.moviecatalogue.reminders

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import com.example.moviecatalogue.R
import kotlinx.android.synthetic.main.activity_reminders.*
import java.text.SimpleDateFormat
import java.util.*

class RemindersActivity : AppCompatActivity() {

    private lateinit var dailyAlarmReceiver: DailyAlarmReceiver
    private lateinit var releasedAlarmReceiver: ReleasedAlarmReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reminders)

        dailyAlarmReceiver = DailyAlarmReceiver()
        releasedAlarmReceiver = ReleasedAlarmReceiver()

        supportActionBar?.title = this.resources.getString(R.string.reminder)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val checkDailySwitch = getSharedPreferences("CheckDailySwitch",0)
        val checkReleasedSwitch = getSharedPreferences("CheckReleasedSwitch",0)

        val checkTheDaily = checkDailySwitch.getBoolean("switchDaily", false)
        val checTheReleased = checkReleasedSwitch.getBoolean("switchReleased", false)

        switch_daily.isChecked = checkTheDaily
        switch_released.isChecked = checTheReleased

        switch_daily.setOnCheckedChangeListener { button, checked ->
            if(checked) {
                dailyAlarmReceiver.setRepeatingAlarm(this, DailyAlarmReceiver.TYPE_REPEATING, "07:00")
            }
            else{
                dailyAlarmReceiver.cancelAlarm(this, DailyAlarmReceiver.TYPE_REPEATING)
            }

            val setting = getSharedPreferences("CheckDailySwitch", 0)
            val editor = setting.edit()
            editor.putBoolean("switchDaily", checked)
            editor.apply()
        }

        switch_released.setOnCheckedChangeListener { button, checked ->
            if(checked){
                releasedAlarmReceiver.setRepeatingAlarm(this, ReleasedAlarmReceiver.TYPE_REPEATING, "08:00")
            }
            else{
                releasedAlarmReceiver.cancelAlarm(this, ReleasedAlarmReceiver.TYPE_REPEATING)
            }

            val setting = getSharedPreferences("CheckReleasedSwitch", 0)
            val editor = setting.edit()
            editor.putBoolean("switchReleased", checked)
            editor.apply()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when(item?.itemId){
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
