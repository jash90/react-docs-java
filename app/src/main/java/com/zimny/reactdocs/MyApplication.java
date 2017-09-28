package com.zimny.reactdocs;

import android.app.Application;

import com.elvishew.xlog.LogLevel;
import com.elvishew.xlog.XLog;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        XLog.init(LogLevel.ALL);
    }
}
