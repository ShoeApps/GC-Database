package com.lukesapps.grandcross.database;

import android.app.Activity;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.mopub.common.MoPub;
import com.mopub.common.SdkConfiguration;
import com.mopub.common.SdkInitializationListener;
import com.mopub.common.logging.MoPubLog;
import com.mopub.common.privacy.ConsentDialogListener;
import com.mopub.common.privacy.PersonalInfoManager;
import com.mopub.mobileads.MoPubErrorCode;
import com.mopub.mobileads.MoPubView;

public class MoPubSdk{

    private final MutableLiveData<Boolean> isMoPubSdkInitialized = new MutableLiveData<>();
    private Activity mActivity;
    int ViewCreated = 0;
    MoPubView moPubView;
    //Test b195f8dd8ded45fe847ad89ed1d016da
    //Real 4d6512e920b24f75987dfc6e1025a354
    String adUnitId = "4d6512e920b24f75987dfc6e1025a354";

    private MoPubSdk(Activity activity) {
        this.mActivity = activity;
        // Initialize the AdColony SDK with the above IDs
        SdkConfiguration sdkConfiguration = new SdkConfiguration.Builder(adUnitId)
                .withLogLevel(MoPubLog.LogLevel.DEBUG)
                .withLegitimateInterestAllowed(false)
                .build();
        MoPub.initializeSdk(activity, sdkConfiguration, initSdkListener());
    }

    private SdkInitializationListener initSdkListener() {
        return new SdkInitializationListener() {
            @Override
            public void onInitializationFinished() {
           /* MoPub SDK initialized.
           Check if you should show the consent dialog here, and make your ad requests. */
                Log.d("MoPub", "SDK initialized");
                isMoPubSdkInitialized.setValue(true);
                showConsentIfNeeded();
            }
        };
    }

    public LiveData<Boolean> isMoPubSdkInitialized() {
        return isMoPubSdkInitialized;
    }

    private static MoPubSdk INSTANCE;

    public static MoPubSdk getInstance(Activity activity) {
        if(MoPubSdk.INSTANCE == null) {
            MoPubSdk.INSTANCE = new MoPubSdk(activity);
        }
        return MoPubSdk.INSTANCE;
    }

    public MoPubView callAdView(LinearLayout adViewHolder) {
        if (ViewCreated == 0) {
            moPubView = new MoPubView(mActivity);
            moPubView.setAdUnitId(adUnitId);
            moPubView.loadAd();
            adViewHolder.addView(moPubView, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        }
        while (moPubView.getParent() != null) {
            ((ViewGroup) moPubView.getParent()).removeView(moPubView);
            if (moPubView.getParent() == null) {
                adViewHolder.addView(moPubView, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
                break;
            }
        }
        ViewCreated = 1;
        return moPubView;
    }
    private void showConsentIfNeeded() {
        PersonalInfoManager mPersonalInfoManager = MoPub.getPersonalInformationManager();

        Log.d("customeee", "Can collect pers information? "+MoPub.canCollectPersonalInformation()
                + ".\nShould show consent dialog? "+mPersonalInfoManager.shouldShowConsentDialog());

        if(!MoPub.canCollectPersonalInformation()) {
            if(mPersonalInfoManager.shouldShowConsentDialog()) {
                mPersonalInfoManager.loadConsentDialog(new ConsentDialogListener() {
                    @Override
                    public void onConsentDialogLoaded() {
                        mPersonalInfoManager.showConsentDialog();
                    }

                    @Override
                    public void onConsentDialogLoadFailed(@NonNull MoPubErrorCode moPubErrorCode) {
                        MoPubLog.i("Consent dialog failed to load.");
                    }
                });
            }
        }
    }
}