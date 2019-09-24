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
import android.util.Log
import android.widget.Toast
import com.example.moviecatalogue.R
import com.example.moviecatalogue.model.ResponseMovie
import com.example.moviecatalogue.service.ApiClient
import com.example.moviecatalogue.service.ApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class ReleasedAlarmReceiver : BroadcastReceiver() {

    companion object{
        var TYPE_ONE_TIME = "OneTimeAlarm"
        var TYPE_REPEATING = "RepeatingAlarm"
        var EXTRA_MESSAGE = "message"
        var EXTRA_TYPE ="type"

        var ID_ONETIME = 100
        var ID_REPEATING = 101

        const val CHANNEL_NAME = "movie channel"
        const val GROUP_KEY_NOTIF = "group_key_notify"
    }

    lateinit var builder : NotificationCompat.Builder
    var idNotif = 0
    var notification = arrayListOf<Notification>()

    override fun onReceive(context: Context?, intent: Intent?) {
//        val time = SimpleDateFormat("HH:mm", Locale.getDefault()).format(Date())
//        if(time >= "08:00" && time <= "08:30"){
//            showAlarmNotification(context)
//        }
//        else{
//            Log.d("asdasd890","Ihhhhhhhhh")
//        }
        showAlarmNotification(context)

    }

    fun setRepeatingAlarm(context: Context?, type : String?, time : String?){
        val TIME_FORMAT = "HH:mm"

        if(isDateInvalid(time, TIME_FORMAT)) return

        val alarmManager = context?.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(context, ReleasedAlarmReceiver::class.java)
        intent.putExtra(EXTRA_MESSAGE, context.getString(R.string.miss_you))
        intent.putExtra(EXTRA_TYPE, type)

        Log.d("asdasd", time)

        val timeArray : List<String> = time?.split(":") ?: listOf()

        val calendar = Calendar.getInstance()
        calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(timeArray[0]))
        calendar.set(Calendar.MINUTE, Integer.parseInt(timeArray[1]))
        calendar.set(Calendar.SECOND, 0)

        val pendingIntent = PendingIntent.getBroadcast(context,
            ID_REPEATING, intent, 0)
        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.timeInMillis, AlarmManager.INTERVAL_DAY, pendingIntent)

        Toast.makeText(context, context.getString(R.string.rem_set_up), Toast.LENGTH_SHORT).show()
    }

    fun cancelAlarm(context: Context?, type: String?){
        val alarmManager = context?.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(context, ReleasedAlarmReceiver::class.java)
        val requestCode = if(type.equals(TYPE_ONE_TIME, true)) ID_ONETIME else ID_REPEATING
        val pendingIntent = PendingIntent.getBroadcast(context, requestCode, intent, 0)
        pendingIntent.cancel()

        alarmManager.cancel(pendingIntent)

        Toast.makeText(context, context.getString(R.string.rem_cancelled), Toast.LENGTH_SHORT).show()
    }

    private fun isDateInvalid(time: String?, format: String): Boolean {

        return try {
            val df = SimpleDateFormat(format, Locale.getDefault())
            df.isLenient = false
            df.parse(time)
            false
        }catch (e : ParseException){
            true
        }

    }

    private fun showAlarmNotification(context: Context?){

        val date = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())
        Log.d("asdasd6", date)

        val apiInterface = ApiClient.getApi().create(ApiInterface::class.java)
        val call = apiInterface.getReleasedToday(context!!.getString(R.string.lang), date, date)
        call.enqueue(object : Callback<ResponseMovie>{
            override fun onFailure(call: Call<ResponseMovie>, t: Throwable) {
                Toast.makeText(context, "No connection", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<ResponseMovie>, response: Response<ResponseMovie>) {
                val result = response.body()?.resultListMovie
                try {
                    Log.d("asdasd123", result.toString())
                    if (result != null) {
                        for(i in 0..5){
                            notification.add(Notification(i, result[i].title,result[i].title+" "+context.getString(
                                                            R.string.has_released_today)))

                            val time = SimpleDateFormat("HH:mm", Locale.getDefault()).format(Date())

                            if(time >= "08:00" && time <= "08:30"){
                                val CHANNEL_ID = "channel_01"

                                val notifManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

                                val inboxStyle = NotificationCompat.InboxStyle()
                                    .addLine(notification[idNotif].title+" "+context.getString(R.string.has_released_today))
                                    .setSummaryText("Released today!")
                                builder = NotificationCompat.Builder(context, CHANNEL_ID)
                                    .setContentTitle(notification[idNotif].title)
                                    .setContentText("Movie released today")
                                    .setSmallIcon(R.drawable.ic_star_black)
                                    .setGroup(GROUP_KEY_NOTIF)
                                    .setGroupSummary(true)
                                    .setStyle(inboxStyle)
                                    .setAutoCancel(true)

                                Log.d("asdasd454", "$builder")

                                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
                                    val channel = NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT)
                                    builder.setChannelId(CHANNEL_ID)

                                    notifManager.createNotificationChannel(channel)
                                }

                                val notif = builder.build()

                                notifManager.notify(idNotif, notif)

                                Log.d("asdasd424", "$builder")
                                idNotif++
                                Log.d("asdasd5", "$idNotif")
                            }
                            else{
                                Log.d("asdasd890","Ihhhhhhhhh")
                            }

                        }

                        Log.d("asdasd545", "$notification")
                    }
                    else{
                        Toast.makeText(context, "Data is empty", Toast.LENGTH_SHORT).show()
                    }
                }catch (e:Exception){

                }
            }

        })
    }
}