package nyc.c4q.huilin.celebritytrap;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;

/**
 * Created by huilin on 12/16/16.
 */

public class MyNotificationService extends IntentService {

    private static final String SERVICE_NAME = "notification-service";
    public static final String CELEB_NAME = "Default as Celebrity";
    public static final String CELEB_IMG = "Headshot";

    private int count = 0;

    public MyNotificationService() {
        super(SERVICE_NAME);
    }


    @Override
    protected void onHandleIntent(Intent intent) {
        startPendingIntent();
    }

    private void startPendingIntent() {
        int NOTIFICATION_ID = 1217;
        Resources resources = getResources();
        String celebImgTxt = resources.getResourceEntryName(R.drawable.ic_face_black_24dp);

        // FIXME converted drawable as string and put into intent
        Intent intent = new Intent(this, MainActivity.class);
        Bundle extras = new Bundle();
        extras.putString(CELEB_NAME, nameCeleb());
        extras.putString(CELEB_IMG, celebImgTxt);
        intent.putExtras(extras);

        int requestID = (int) System.currentTimeMillis();
        int flags = PendingIntent.FLAG_CANCEL_CURRENT;
        PendingIntent pendingIntent = PendingIntent.getActivity(this, requestID, intent, flags);

        Notification notification = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.ic_face_black_24dp)
                .setContentTitle("Contract a Celebrity!")
                .setContentText("Tap if they agreed to your terms")
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .build();

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(NOTIFICATION_ID, notification);
    }

    private String nameCeleb() {
        if (count == 0) {
            count++;
            return "Celebrity";
        }

        String celebName = "Celebrity" + count;
        count++;
        return celebName;

    }
}
