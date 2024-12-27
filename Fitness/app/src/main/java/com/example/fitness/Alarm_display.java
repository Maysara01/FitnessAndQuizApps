package com.example.fitness;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import java.util.Calendar;

import androidx.appcompat.app.AppCompatActivity;

public class Alarm_display extends AppCompatActivity {

    private TextView tvAlarmTime, tvTimeLeft;
    private Button btnStopAlarm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_display);

        tvAlarmTime = findViewById(R.id.tvAlarmTime);
        tvTimeLeft = findViewById(R.id.tvTimeLeft); // Get reference to the new TextView
        btnStopAlarm = findViewById(R.id.btnStopAlarm);

        // Get the time passed from the AlarmSettingActivity
        int hour = getIntent().getIntExtra("hour", 0);
        int minute = getIntent().getIntExtra("minute", 0);

        // Format the alarm time
        String alarmTime = String.format("%02d:%02d", hour, minute);
        tvAlarmTime.setText("Alarm set for: " + alarmTime);

        // Calculate the time left until the alarm
        calculateTimeLeft(hour, minute);

        btnStopAlarm.setOnClickListener(v -> {
            // Cancel the alarm
            cancelAlarm();
            finish(); // Close this activity and go back
        });
    }

    private void cancelAlarm() {
        Intent intent = new Intent(this, AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(
                this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        if (alarmManager != null) {
            alarmManager.cancel(pendingIntent); // Cancel the alarm
        }
    }

    private void calculateTimeLeft(int hour, int minute) {
        // Get the current time
        Calendar currentTime = Calendar.getInstance();

        // Set the alarm time
        Calendar alarmTime = Calendar.getInstance();
        alarmTime.set(Calendar.HOUR_OF_DAY, hour);
        alarmTime.set(Calendar.MINUTE, minute);
        alarmTime.set(Calendar.SECOND, 0);

        // If the alarm time is in the past, set it for the next day
        if (alarmTime.getTimeInMillis() < currentTime.getTimeInMillis()) {
            alarmTime.add(Calendar.DAY_OF_YEAR, 1);
        }

        // Calculate the difference in milliseconds
        long timeDifference = alarmTime.getTimeInMillis() - currentTime.getTimeInMillis();

        // Convert milliseconds into hours, minutes, and seconds
        long hours = timeDifference / (1000 * 60 * 60);
        long minutes = (timeDifference % (1000 * 60 * 60)) / (1000 * 60);
        long seconds = (timeDifference % (1000 * 60)) / 1000;

        // Update the UI with the remaining time
        String timeLeft = String.format("Time left: %02d:%02d:%02d", hours, minutes, seconds);
        tvTimeLeft.setText(timeLeft);
    }
}
