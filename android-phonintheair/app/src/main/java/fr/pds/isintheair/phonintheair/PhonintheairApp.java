package fr.pds.isintheair.phonintheair;

import android.app.Application;
import android.content.Context;

public class PhonintheairApp extends Application {
    public static Context context;

    @Override
    public void onCreate() {
        super.onCreate();

        context = getApplicationContext();
    }
}
