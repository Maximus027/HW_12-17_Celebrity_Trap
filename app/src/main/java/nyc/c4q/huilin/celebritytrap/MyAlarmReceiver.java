package nyc.c4q.huilin.celebritytrap;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by huilin on 12/16/16.
 */
public class MyAlarmReceiver extends BroadcastReceiver {
    public static final int REQUEST_CODE = 1216;

    @Override
    public void onReceive(Context context, Intent intent) {
        Intent i = new Intent(context, MyNotificationService.class);
        context.startService(i);
    }
}
