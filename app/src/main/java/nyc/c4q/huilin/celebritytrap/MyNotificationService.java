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

import java.util.Random;

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
        final String celebImgTxt = "http://i2.cdn.turner.com/cnn/dam/assets/140421092213-lindsay-lohan-january-2014-story-top.jpg";
        int randomNumberChosen = randomNumber();
        // FIXME converted drawable as string and put into intent
        Intent intent = new Intent(this, MainActivity.class);
        Bundle extras = new Bundle();
        extras.putString(CELEB_NAME, nameCeleb(randomNumberChosen));
        extras.putString(CELEB_IMG, celebImageChosen(randomNumberChosen));
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

    private String nameCeleb(int numberChosen) {
        String celebName = null;
        switch (numberChosen) {
            case 0: celebName = "Lindsay Lohan";
                break;
            case 1: celebName = "Michael Jordan";
                break;
            case 2: celebName = "Jackie Chan";
                break;
            case 3: celebName = "Bruce Willis";
                break;
        }
//        if (count == 0) {
//            count++;
//            return "Celebrity";
//        }
//        String celebName = "Celebrity" + count;
//        count++;
        return celebName;

    }

    public String celebImageChosen (int numberChosen) {
        String celebImageToDisplay = null;
        switch (numberChosen) {
            case 0: celebImageToDisplay = "http://i2.cdn.turner.com/cnn/dam/assets/140421092213-lindsay-lohan-january-2014-story-top.jpg";
                break;
            case 1: celebImageToDisplay = "http://make-me-successful.com/wp-content/uploads/2012/11/michael-jordan.jpg";
                break;
            case 2: celebImageToDisplay = "http://coolspotters.com/files/photos/195266/jackie-chan-profile.jpg";
                break;
            case 3: celebImageToDisplay = "http://imagecache2.allposters.com/images/MMPH-E/253404.jpg";
                break;
        }
        return celebImageToDisplay;
    }

    public int randomNumber() {
        Random rand = new Random();
        int  n = rand.nextInt(4);
        return n;
    }


}
