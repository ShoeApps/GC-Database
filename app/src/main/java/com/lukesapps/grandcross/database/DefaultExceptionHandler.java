package com.lukesapps.grandcross.database;

import android.app.Activity;
import android.content.Intent;

import androidx.annotation.NonNull;

public class DefaultExceptionHandler implements Thread.UncaughtExceptionHandler {

    Activity activity;

    public DefaultExceptionHandler(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void uncaughtException(@NonNull Thread thread, @NonNull final Throwable ex) {
        Intent intent = new Intent(activity, a_main_screen.class);
        intent.putExtra("crash", true);
        activity.startActivity(intent);
        activity.finish();

        System.exit(0);
    }

}