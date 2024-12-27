package com.example.fitness;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import androidx.core.app.NotificationCompat;

public class AlarmReceiver extends BroadcastReceiver {

    private static Ringtone ringtone; // Make it static to access from StopReceiver

    @Override
    public void onReceive(Context context, Intent intent) {
        // Play the alarm sound
        Uri alarmUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
        ringtone = RingtoneManager.getRingtone(context, alarmUri);
        ringtone.play();

        // Create intent to go to AlarmSettingActivity
        Intent settingIntent = new Intent(context, Alarm_setting.class);
        settingIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        // PendingIntent to open AlarmSettingActivity
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, settingIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        // Create notification
        @SuppressLint("NotificationTrampoline") NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "alarm_channel")
                .setSmallIcon(android.R.drawable.ic_dialog_alert)
                .setContentTitle("Alarm Triggered")
                .setContentText("It's time to wake up!")
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setContentIntent(pendingIntent)
                .setAutoCancel(false)
                .setOngoing(true)
                .addAction(android.R.drawable.ic_delete, "Stop", getStopPendingIntent(context));

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("alarm_channel", "Alarm Channel", NotificationManager.IMPORTANCE_HIGH);
            notificationManager.createNotificationChannel(channel);
        }

        notificationManager.notify(1, builder.build());
    }

    private PendingIntent getStopPendingIntent(Context context) {
        Intent stopIntent = new Intent(context, StopAlarmReceiver.class);
        return PendingIntent.getBroadcast(context, 0, stopIntent, PendingIntent.FLAG_CANCEL_CURRENT); // FLAG_CANCEL_CURRENT
    }

    // Method to stop the ringtone
    public static void stopAlarmSound() {
        if (ringtone != null && ringtone.isPlaying()) {
            ringtone.stop();
        }
    }
}
