package com.lukesapps.grandcross.database;

import android.app.Activity;
import android.util.Log;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

public class AdMobSdk {

    private final MutableLiveData<Boolean> isAdMobSdkInitialized = new MutableLiveData<>();
    private Activity mActivity;
    int ViewCreated = 0;
    AdView adView;
    //Test ca-app-pub-3940256099942544/6300978111
    //Real ca-app-pub-8009364805724327/7711963430
    String adUnitId = "ca-app-pub-8009364805724327/7711963430";
    boolean removeAds;

    private AdMobSdk(Activity activity) {
        this.mActivity = activity;
        MobileAds.initialize(activity, initSdkListener());
    }

    private OnInitializationCompleteListener initSdkListener() {
        return initializationStatus -> {
            Log.d("MoPub", "SDK initialized");
            isAdMobSdkInitialized.setValue(true);
        };
    }

    public LiveData<Boolean> isAdMobSdkInitialized() {
        return isAdMobSdkInitialized;
    }

    private static AdMobSdk INSTANCE;

    public static AdMobSdk getInstance(Activity activity) {
        if (AdMobSdk.INSTANCE == null) {
            AdMobSdk.INSTANCE = new AdMobSdk(activity);
        }
        return AdMobSdk.INSTANCE;
    }

    public void setRemoveAds(Boolean removeAdsBoolean) {
        removeAds = removeAdsBoolean;
    }

    public AdView callAdView(LinearLayout adViewHolder) {
        if (ViewCreated == 0) {
            adView = new AdView(mActivity);
            adView.setAdUnitId(adUnitId);
            AdRequest adRequest = new AdRequest.Builder().build();
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
            params.weight = 1.0f;
            params.gravity = Gravity.TOP;
            adView.setLayoutParams(params);
            adView.setAdSize(AdSize.BANNER);
            if (!removeAds) {
                adView.loadAd(adRequest);
                adViewHolder.addView(adView, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
            } else {
                if (adView.getParent() != null) {
                    ((ViewGroup) adView.getParent()).removeView(adView);
                }
            }
        }
        while (adView.getParent() != null) {
            ((ViewGroup) adView.getParent()).removeView(adView);
            if (adView.getParent() == null) {
                adViewHolder.addView(adView, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
                break;
            }
        }
        ViewCreated = 1;
        return adView;
    }
}
