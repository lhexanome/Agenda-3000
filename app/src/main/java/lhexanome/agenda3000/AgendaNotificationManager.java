package lhexanome.agenda3000;


import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.app.NotificationCompat;

import com.alamkanak.weekview.WeekViewEvent;

public class AgendaNotificationManager {

    private static final int SNOOZE_MIN = 10;
    private Context context;
    private NotificationManager mNotificationManager;

    public static final String CHANNEL_ID = "event_channel";

    public AgendaNotificationManager(Context context) {
        this.context = context;
        this.mNotificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            CharSequence name = context.getString(R.string.event_channel_name);
            String description = context.getString(R.string.event_channel_description);

            NotificationChannel mChannel =
                    new NotificationChannel(CHANNEL_ID, name, NotificationManager.IMPORTANCE_HIGH);

            mChannel.setDescription(description);
            mChannel.enableLights(true);
            mChannel.setLightColor(Color.BLUE);
            mChannel.enableVibration(true);
            mChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
            mNotificationManager.createNotificationChannel(mChannel);
        }
    }

    public void displayEventNotification(WeekViewEvent event) {
        Intent snoozeIntent = new Intent(context, SnoozedEventActivity.class);
        PendingIntent snoozePendingIntent = PendingIntent.getBroadcast(
                context.getApplicationContext(),
                (int) event.getId(),
                snoozeIntent,
                0
        );

        NotificationCompat.Action snoozeButton = new NotificationCompat.Action.Builder(
                R.drawable.ic_snooze_black_24dp,
                String.format(context.getString(R.string.snooze), SNOOZE_MIN),
                snoozePendingIntent
        ).build();


        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context, CHANNEL_ID)
                .setContentTitle(event.getName())
                .setSmallIcon(R.drawable.ic_event_black_24dp)
                .setContentText(event.getLocation())
                .setAutoCancel(true)
                .addAction(snoozeButton);

        int id = (int) event.getId();
        mNotificationManager.notify(id, mBuilder.build());
    }

    public void cancel(long eventId) {
        mNotificationManager.cancel((int) eventId);
    }

    public void clearAll() {
        mNotificationManager.cancelAll();
    }
}
