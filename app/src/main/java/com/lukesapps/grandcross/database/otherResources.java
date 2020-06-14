package com.lukesapps.grandcross.database;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import com.anjlab.android.iab.v3.BillingProcessor;
import com.mopub.mobileads.MoPubView;
import com.squareup.picasso.Picasso;


public class otherResources extends AppCompatActivity {
    LinearLayout adViewHolder;
    BillingProcessor bp;
    public static final String PREFS_NAME = "MyPrefsFile";
    SharedPreferences settings;
    String darkMode;
    ScrollView backgroundColor;
    MoPubView moPubView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Thread.setDefaultUncaughtExceptionHandler(new DefaultExceptionHandler(this));
        super.onCreate(savedInstanceState);
        setContentView(R.layout.other_resources);
        LinearLayout jpdatabase = findViewById(R.id.jpdatabase);
        jpdatabase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://gcdatabase.com/index.html"));
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }
            }
        });
        LinearLayout globaldatabase = findViewById(R.id.globaldatabase);
        globaldatabase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://www.sdsgc.gg/"));
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }
            }
        });
        LinearLayout reddit = findViewById(R.id.reddit);
        reddit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://www.reddit.com/r/SDSGrandCross/"));
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }
            }
        });
        LinearLayout redditdiscord = findViewById(R.id.redditdiscord);
        redditdiscord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://discord.gg/XVySfxC"));
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }
            }
        });
        LinearLayout heavendiscord = findViewById(R.id.heavendiscord);
        heavendiscord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://discord.gg/EAKNqfk"));
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }
            }
        });
        LinearLayout netmarble = findViewById(R.id.netmarble);
        netmarble.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://forum.netmarble.com/7ds_en"));
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }
            }
        });
        ImageView otherResourcesBackground = findViewById(R.id.other_resources_background);
        Picasso.with(this).load(R.drawable.character_background).fit().centerCrop().into(otherResourcesBackground);
        backgroundColor = findViewById(R.id.other_background_colour);
        settings = getSharedPreferences(PREFS_NAME, 0);
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
        //Recall the storage
        darkMode = settings.getString("darkMode", darkMode);
        if (darkMode.equals("yes")) {
            backgroundColor.setBackgroundColor(Color.parseColor("#616161"));
            LinearLayout adViewHolder = findViewById(R.id.adViewHolder);
            adViewHolder.setBackgroundColor(Color.parseColor("#616161"));
        } else {
            backgroundColor.setBackgroundColor(Color.parseColor("#66FFEFDC"));
            LinearLayout adViewHolder = findViewById(R.id.adViewHolder);
            adViewHolder.setBackgroundColor(Color.parseColor("#66FFEFDC"));
        }
    }

    protected void onRestart() {
        super.onRestart();
        darkMode = settings.getString("darkMode", darkMode);
        if (darkMode.equals("yes")) {
            backgroundColor.setBackgroundColor(Color.parseColor("#616161"));
        } else {
            backgroundColor.setBackgroundColor(Color.parseColor("#66FFEFDC"));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // R.menu.mymenu is a reference to an xml file named mymenu.xml which should be inside your res/menu directory.
        // If you don't have res/menu, just create a directory named "menu" inside res
        getMenuInflater().inflate(R.menu.mymenu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.help_page) {
            Intent help = new Intent(otherResources.this, help_menu.class);
            startActivity(help);
        }
        return super.onOptionsItemSelected(item);
    }

}
