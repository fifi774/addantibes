package com.addantibes.addantibes;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.support.annotation.Nullable;

class MyAlarmService extends Service {

    private NotificationManager notificationmanager;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public void onCreate() {
        notificationmanager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        showNotification();
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void showNotification() {
        // In this sample, we'll use the same text for the ticker and the expanded notification
        //CharSequence text = getText(R.string.local_service_started);

        // The PendingIntent to launch our activity if the user selects this notification
        Intent intent1 = new Intent(this.getApplicationContext(),MainActivity.class);
        PendingIntent pendingNotificationIntent = PendingIntent.getActivity( this.getApplicationContext(),0, intent1,PendingIntent.FLAG_UPDATE_CURRENT);

        // Set the info for the views that show in the notification panel.
        Notification notification = new Notification.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)  // the status icon
                .setTicker("Message addantibes")  // the status text
                .setWhen(System.currentTimeMillis())  // the time stamp
                .setContentTitle("Message Addantibes")  // the label of the entry
                .setContentText("L'heure de la réuion approche")  // the contents of the entry
                .setContentIntent(pendingNotificationIntent)  // The intent to send when the entry is clicked
                .build();
        notification.defaults = Notification.DEFAULT_ALL;
        // Send the notification.
        notificationmanager.notify(0, notification);
    }

}
