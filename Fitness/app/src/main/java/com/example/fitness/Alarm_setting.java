package com.example.fitness;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TimePicker;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Calendar;

public class Alarm_setting extends AppCompatActivity {

    private TimePicker timePicker;
    private Button btnSetAlarm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_setting);

        timePicker = findViewById(R.id.timePicker);
        btnSetAlarm = findViewById(R.id.btnSetAlarm);

        // Set a click listener for the "Set Alarm" button
        btnSetAlarm.setOnClickListener(v -> {
            int hour = timePicker.getHour();
            int minute = timePicker.getMinute();

            // Schedule the alarm
            setAlarm(hour, minute);

            // Transition to the Alarm Display Activity after setting the alarm
            Intent intent = new Intent(Alarm_setting.this, Alarm_display.class);

            // Passing the alarm time (hour, minute) to AlarmDisplayActivity
            intent.putExtra("hour", hour);
            intent.putExtra("minute", minute);
            startActivity(intent);  // Start Alarm Display Activity
        });
    }

    // Inside AlarmSettingActivity.java
    private void setAlarm(int hour, int minute) {
        // Use Calendar to set the alarm time
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, 0);

        // If the set time is in the past, schedule it for the next day
        if (calendar.getTimeInMillis() < System.currentTimeMillis()) {
            calendar.add(Calendar.DAY_OF_YEAR, 1);
        }

        // Create an intent for the alarm receiver
        Intent intent = new Intent(this, AlarmReceiver.class);

        // Create a PendingIntent to send when the alarm goes off
        PendingIntent pendingIntent = PendingIntent.getBroadcast(
                this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        // Get the AlarmManager system service
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

        // Set the alarm using the AlarmManager to trigger it even if the device is asleep
        if (alarmManager != null) {
            alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
        }
    }

}
