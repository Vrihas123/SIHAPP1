package com.sih.app1.kisaanmitra.utils;

import android.app.Application;

//import com.amitshekhar.DebugDB;


public class AppController extends Application {
    private static final String TAG = AppController.class.getSimpleName();
    private static AppController mInstance;
//    private RefWatcher refWatcher;

    public AppController() {
        super();
    }

    public static synchronized AppController getInstance() {
        return mInstance;
    }

//    public static RefWatcher getRefWatcher(Context context) {
//        AppController application = (AppController) context.getApplicationContext();
//        return application.refWatcher;
//    }


    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;

        //DebugDB.getAddressLog();

//        if (LeakCanary.isInAnalyzerProcess(this)) {
//            // This process is dedicated to LeakCanary for heap analysis.
//            // You should not init your app in this process.
//            return;
//        }
//        refWatcher = LeakCanary.install(this);
    }

}
