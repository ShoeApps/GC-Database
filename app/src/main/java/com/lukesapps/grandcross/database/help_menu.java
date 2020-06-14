package com.lukesapps.grandcross.database;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import com.anjlab.android.iab.v3.BillingProcessor;
import com.github.aakira.expandablelayout.ExpandableLinearLayout;
import com.mopub.mobileads.MoPubView;
import com.squareup.picasso.Picasso;

public class help_menu extends AppCompatActivity {
    LinearLayout adViewHolder;
    BillingProcessor bp;
    ExpandableLinearLayout basestatshelp;
    ExpandableLinearLayout damagetypeshelp;
    ExpandableLinearLayout buffshelp;
    ExpandableLinearLayout statuseffectshelp;
    ExpandableLinearLayout characterIcons;
    Switch mySwitch;
    String darkMode;

    //Get shared prefs
    public static final String PREFS_NAME = "MyPrefsFile";
    SharedPreferences settings;

    MoPubView moPubView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Thread.setDefaultUncaughtExceptionHandler(new DefaultExceptionHandler(this));
        super.onCreate(savedInstanceState);
        setContentView(R.layout.help_menu);
        basestatshelp = findViewById(R.id.basestatshelp);
        damagetypeshelp = findViewById(R.id.damagetypeshelp);
        buffshelp = findViewById(R.id.buffshelp);
        statuseffectshelp = findViewById(R.id.statuseffectshelp);
        characterIcons = findViewById(R.id.charactericonshelp);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ImageView helpBackground = findViewById(R.id.help_background);
        Picasso.with(this).load(R.drawable.character_background).fit().centerCrop().into(helpBackground);
        mySwitch = findViewById(R.id.darkModeSwitch);
        final ScrollView helpPage = findViewById(R.id.help_background_colour);

        settings = getSharedPreferences(PREFS_NAME, 0);
        //Recall the storage
        darkMode = settings.getString("darkMode", darkMode);
        if (darkMode.equals("yes")) {
            mySwitch.setChecked(true);
            helpPage.setBackgroundColor(Color.parseColor("#616161"));
        } else {
            mySwitch.setChecked(false);
            helpPage.setBackgroundColor(Color.parseColor("#9A5E5E5E"));
        }
        MoPubSdk moPubSdk;
        adViewHolder = findViewById(R.id.adViewHolder);
        moPubSdk = MoPubSdk.getInstance(this);
        moPubSdk.isMoPubSdkInitialized().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean) {
                    moPubSdk.callAdView(adViewHolder);
                }
            }
        });
        mySwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    SharedPreferences.Editor editor = settings.edit();
                    editor.putString("darkMode", "yes");
                    editor.apply();
                    helpPage.setBackgroundColor(Color.parseColor("#616161"));
                    LinearLayout adViewHolder = findViewById(R.id.adViewHolder);
                    adViewHolder.setBackgroundColor(Color.parseColor("#616161"));
                } else {
                    SharedPreferences.Editor editor = settings.edit();
                    editor.putString("darkMode", "no");
                    editor.apply();
                    helpPage.setBackgroundColor(Color.parseColor("#9A5E5E5E"));
                    LinearLayout adViewHolder = findViewById(R.id.adViewHolder);
                    adViewHolder.setBackgroundColor(Color.parseColor("#9A5E5E5E"));
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void showBaseStatsHelp(View view) {
        basestatshelp.toggle();
    }

    public void showDamageTypesHelp(View view) {
        damagetypeshelp.toggle();
    }

    public void showBuffsHelp(View view) {
        buffshelp.toggle();
    }

    public void showStatusEffectsHelp(View view) {
        statuseffectshelp.toggle();
    }

    public void showCharacterIcons(View view) {
        characterIcons.toggle();
    }

    public void donate(View view) {
        String url = "https://www.patreon.com/GCDatabase";
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (!bp.handleActivityResult(requestCode, resultCode, data)) {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
