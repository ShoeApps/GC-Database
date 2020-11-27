package com.lukesapps.grandcross.database;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;


public class otherResources extends AppCompatActivity {
    LinearLayout adViewHolder;
    public static final String PREFS_NAME = "MyPrefsFile";
    SharedPreferences settings;
    String darkMode;
    ScrollView backgroundColor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.other_resources);
        LinearLayout jpdatabase = findViewById(R.id.jpdatabase);
        jpdatabase.setOnClickListener(view -> {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("https://gcdatabase.com/index.html"));
            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);
            }
        });
        LinearLayout globaldatabase = findViewById(R.id.globaldatabase);
        globaldatabase.setOnClickListener(view -> {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("https://www.sdsgc.gg/"));
            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);
            }
        });
        LinearLayout toolsWebsite = findViewById(R.id.sdstools);
        toolsWebsite.setOnClickListener(view -> {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("https://7dstools.com/teams"));
            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);
            }
        });
        LinearLayout reddit = findViewById(R.id.reddit);
        reddit.setOnClickListener(view -> {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("https://www.reddit.com/r/SDSGrandCross/"));
            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);
            }
        });
        LinearLayout redditdiscord = findViewById(R.id.redditdiscord);
        redditdiscord.setOnClickListener(view -> {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("https://discord.gg/XVySfxC"));
            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);
            }
        });
        LinearLayout heavendiscord = findViewById(R.id.heavendiscord);
        heavendiscord.setOnClickListener(view -> {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("https://discord.gg/EAKNqfk"));
            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);
            }
        });
        LinearLayout netmarble = findViewById(R.id.netmarble);
        netmarble.setOnClickListener(view -> {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("https://forum.netmarble.com/7ds_en"));
            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);
            }
        });
        ImageView otherResourcesBackground = findViewById(R.id.other_resources_background);
        Picasso.with(this).load(R.drawable.character_background).fit().centerCrop().into(otherResourcesBackground);
        backgroundColor = findViewById(R.id.other_background_colour);
        settings = getSharedPreferences(PREFS_NAME, 0);
        AdMobSdk adMobSdk;

        adViewHolder = findViewById(R.id.adViewHolder);
        adMobSdk = AdMobSdk.getInstance(this);
        adMobSdk.isAdMobSdkInitialized().observe(this, aBoolean -> {
            if (aBoolean) {
                adMobSdk.callAdView(adViewHolder);
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
