package nyc.c4q.huilin.celebritytrap;

import android.app.Application;

import com.facebook.stetho.Stetho;

/**
 * Created by huilin on 12/12/16.
 */

public class CelebrityTrapApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);
    }
}
