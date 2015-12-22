package com.humoule.notificationbtn;

import android.app.Activity;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by Farouk Touzi.
 */
public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button mNotifyBtn = (Button) findViewById(R.id.notification_btn);
        mNotifyBtn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0) {
                Notification();
            }
        });

    }

    public void Notification() {
        // Set Notification Title
        String title = getString(R.string.notification_title);
        // Set Notification Text
        String text = getString(R.string.notification_text);

        // Open NotificationView Class on Notification Click
        Intent intent = new Intent(this, NotificationView.class);
        // Send data to NotificationView Class
        intent.putExtra("title", title);
        intent.putExtra("text", text);
        // Open NotificationView.java Activity
        PendingIntent pIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        PendingIntent dismissIntent = NotificationActivity.getDismissIntent(0, getApplicationContext());

        // Create Notification using NotificationCompat.Builder
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                // Set Icon
                .setSmallIcon(R.drawable.icon_app)

                        // Set Ticker Message
                .setTicker(getString(R.string.notification_ticker))// Set Title
                .setContentTitle(getString(R.string.custom_notification_title))

                        // Set Text
                .setContentText(getString(R.string.custom_notification_text))

                        // Add an Actions Buttons below Notification
                .addAction(R.drawable.ic_close_notif, getString(R.string.close_text), dismissIntent)
                .addAction(R.drawable.ic_rate_notif, getString(R.string.rate_text), pIntent)

                        // Set PendingIntent into Notification
                .setContentIntent(pIntent)

                        // Dismiss Notification
                .setAutoCancel(true);

        // Create Notification Manager
        NotificationManager notificationmanager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        // Build Notification with Notification Manager
        notificationmanager.notify(0, builder.build());

    }

}

