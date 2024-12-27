package com.example.fitness;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class StopAlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // Stop the alarm sound
        AlarmReceiver.stopAlarmSound(); // Call the static method to stop the ringtone

        // Cancel the alarm
        Intent alarmIntent = new Intent(context, AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, alarmIntent, PendingIntent.FLAG_CANCEL_CURRENT);

        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        if (alarmManager != null) {
            alarmManager.cancel(pendingIntent);
        }

        // Cancel the notification
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        if (notificationManager != null) {
            notificationManager.cancel(1); // Cancel the notification with ID 1
        }

        // Display a toast to confirm stopping the alarm
        Toast.makeText(context, "Alarm Stopped", Toast.LENGTH_SHORT).show();

        // Navigate back to the AlarmSettingActivity
        Intent settingIntent = new Intent(context, Alarm_setting.class);
        settingIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(settingIntent);
    }
}
