package com.example.moviecatalogue.reminders

import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.support.v4.app.NotificationCompat
import android.support.v4.content.ContextCompat
import android.util.Log
import android.widget.Toast
import com.example.moviecatalogue.MenuActivity
import com.example.moviecatalogue.R
import com.example.moviecatalogue.R.string.rem_set_up
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*


class DailyAlarmReceiver : BroadcastReceiver() {

    companion object{
        var TYPE_ONE_TIME = "OneTimeAlarm"
        var TYPE_REPEATING = "RepeatingAlarm"
        var EXTRA_MESSAGE = "message"
        var EXTRA_TYPE ="type"

        var ID_ONETIME = 100
        var ID_REPEATING = 101
    }

    override fun onReceive(context: Context?, intent: Intent?) {
        val type = intent?.getStringExtra(EXTRA_TYPE)
        val message = intent?.getStringExtra(EXTRA_MESSAGE)

        val title = context?.resources?.getString(R.string.app_name)
        val notifId = if(type.equals(TYPE_ONE_TIME, true)) ID_ONETIME else ID_REPEATING

        Log.d("asdasd2", message)

//        val time = SimpleDateFormat("HH:mm", Locale.getDefault()).format(Date())
//        Log.d("asdasd251", time)

        showAlarmNotification(context, title, message, notifId)


//        if(time >= "07:00" && time <= "07:30"){
//            Log.d("asdasd24134", message)
//        }
//        else{
//            Log.d("asdasd890","Ihhhhhhhhh")
//        }
    }

    fun setRepeatingAlarm(context: Context?, type : String?, time : String?){

        val TIME_FORMAT = "HH:mm"

        if(isDateInvalid(time, TIME_FORMAT)) return

        val alarmManager = context?.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(context, DailyAlarmReceiver::class.java)
        intent.putExtra(EXTRA_MESSAGE, context.getString(R.string.miss_you))
        intent.putExtra(EXTRA_TYPE, type)

        Log.d("asdasd", time)

        val timeArray : List<String> = time?.split(":") ?: listOf()

        val calendar = Calendar.getInstance()
        calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(timeArray[0]))
        calendar.set(Calendar.MINUTE, Integer.parseInt(timeArray[1]))
        calendar.set(Calendar.SECOND, 0)

        val pendingIntent = PendingIntent.getBroadcast(context, ID_REPEATING, intent, 0)
        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.timeInMillis, AlarmManager.INTERVAL_DAY, pendingIntent)

        Toast.makeText(context, context.getString(rem_set_up), Toast.LENGTH_SHORT).show()

    }

    fun cancelAlarm(context: Context?, type: String?){
        val alarmManager = context?.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(context, DailyAlarmReceiver::class.java)
        val requestCode = if(type.equals(TYPE_ONE_TIME, true)) ID_ONETIME else ID_REPEATING
        val pendingIntent = PendingIntent.getBroadcast(context, requestCode, intent, 0)
        pendingIntent.cancel()

        alarmManager.cancel(pendingIntent)

        Toast.makeText(context, context.getString(R.string.rem_cancelled), Toast.LENGTH_SHORT).show()
    }

    private fun isDateInvalid(time: String?, format: String?): Boolean {
        return try {
            val df = SimpleDateFormat(format, Locale.getDefault())
            df.isLenient = false
            df.parse(time)
            false
        }catch (e : ParseException){
            true
        }
    }

    private fun showAlarmNotification(context: Context?, title: String?, message: String?, notifId : Int?){

        val time = SimpleDateFormat("HH:mm", Locale.getDefault()).format(Date())

        if(time >= "07:00" && time <= "07:30"){
            val CHANNEL_ID = "Channel_1"
            val CHANNEL_NAME = "AlarmManager Channel"

            val notifManCompat = context!!.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            val intent = Intent(context, MenuActivity::class.java)
            val penIntent = PendingIntent.getActivity(context, 100, intent, PendingIntent.FLAG_CANCEL_CURRENT)

            val builder = NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_star_black)
                .setContentTitle(title)
                .setContentText(message)
                .setContentIntent(penIntent)
                .setColor(ContextCompat.getColor(context, android.R.color.transparent))
                .setAutoCancel(true)

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
                val channel = NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT)

                builder.setChannelId(CHANNEL_ID)

                notifManCompat.createNotificationChannel(channel)
            }

            val notification = builder.build()

            notifManCompat.notify(notifId!!, notification)
            Log.d("asdasd24134", message)
        }
        else{
            Log.d("asdasd890","Ihhhhhhhhh")
        }


    }

}