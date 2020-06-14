/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.lukesapps.grandcross.database;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import com.mopub.mobileads.MoPubView;
import com.squareup.picasso.Picasso;


public class a_main_screen extends AppCompatActivity {

    public static final String PREFS_NAME = "MyPrefsFile";
    SharedPreferences settings;
    String darkMode;
    MoPubView moPubView;
    LinearLayout adViewHolder;
    private MoPubSdk moPubSdk;
    int loadedAd = 0;
    TextView characters;
    TextView awakening;
    TextView cooking;
    TextView summonSimulatorView;
    TextView otherResources;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Thread.setDefaultUncaughtExceptionHandler(new DefaultExceptionHandler(this));
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_main);
        adViewHolder = findViewById(R.id.adViewHolder);


        //Ads
        Log.v("Loading", "Loading");
        moPubSdk = MoPubSdk.getInstance(a_main_screen.this);
        moPubSdk.isMoPubSdkInitialized().observe(a_main_screen.this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean) {
                    moPubSdk.callAdView(adViewHolder);
                }
            }
        });
        onClickListeners();

        if (getIntent().getBooleanExtra("crash", false)) {
            Toast.makeText(this, "An error occurred.\nPlease contact Shoe#0388 on Discord.", Toast.LENGTH_SHORT).show();
        }
        ImageView mainBackground = findViewById(R.id.main_background);
        Picasso.with(this).load(R.drawable.main_background_2).fit().centerCrop().into(mainBackground);
        ImageView charactersMain = findViewById(R.id.characters_main);
        Picasso.with(this).load(R.drawable.meliodas_background).fit().centerCrop().into(charactersMain);
        ImageView awakeningMain = findViewById(R.id.awakening_main);
        Picasso.with(this).load(R.drawable.merlin_background).fit().centerCrop().into(awakeningMain);
        ImageView cookingMain = findViewById(R.id.cooking_main);
        Picasso.with(this).load(R.drawable.ban_background).fit().centerCrop().into(cookingMain);
        ImageView summoningMain = findViewById(R.id.summoning_main);
        Picasso.with(this).load(R.drawable.diane_background).fit().centerCrop().into(summoningMain);
        ImageView otherResourcesMain = findViewById(R.id.otherResources_main);
        Picasso.with(this).load(R.drawable.gowther_background).fit().centerCrop().into(otherResourcesMain);
        ImageView supportMain = findViewById(R.id.support_main);
        Picasso.with(this).load(R.drawable.escanor_background).fit().centerCrop().into(supportMain);
        ScrollView backgroundColor = findViewById(R.id.main_background_colour);
        //Create the storage
        settings = getSharedPreferences(PREFS_NAME, 0);
        Log.e("tag", "settings = " + settings.contains("darkMode"));
        if (!settings.contains("darkMode")) {
            SharedPreferences.Editor editor = settings.edit();
            editor.putString("darkMode", "no");
            editor.apply();
        }
        //Recall the storage
        darkMode = settings.getString("darkMode", darkMode);
        if (darkMode.equals("yes")) {
            backgroundColor.setBackgroundColor(Color.parseColor("#616161"));
            adViewHolder.setBackgroundColor(Color.parseColor("#616161"));
        } else {
            backgroundColor.setBackgroundColor(Color.parseColor("#EDDADADA"));
            adViewHolder.setBackgroundColor(Color.parseColor("#EDDADADA"));
        }
    }

    public void onClickListeners() {
        //Go to Character List Menu
        characters = findViewById(R.id.characters);
        characters.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent characterIntent = new Intent(a_main_screen.this, character_list.class);
                startActivity(characterIntent);
                removeOnClicks();
            }
        });

        //Go to Awakening Menu
        awakening = findViewById(R.id.awakening);
        awakening.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent awakeningIntent = new Intent(a_main_screen.this, awakening.class);
                startActivity(awakeningIntent);
                removeOnClicks();
            }
        });

        //Go to Cooking Menu
        cooking = findViewById(R.id.cooking);
        cooking.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cookingIntent = new Intent(a_main_screen.this, cooking.class);
                startActivity(cookingIntent);
                removeOnClicks();
            }
        });

        //Go to Other Resources Menu
        otherResources = findViewById(R.id.other_resources);
        otherResources.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent otherResourcesIntent = new Intent(a_main_screen.this, otherResources.class);
                startActivity(otherResourcesIntent);
                removeOnClicks();
            }
        });

        //Go to Summon Simulator
        summonSimulatorView = findViewById(R.id.summon_simulator);
        summonSimulatorView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent summonSimulatorIntent = new Intent(a_main_screen.this, summonSimulator.class);
                startActivity(summonSimulatorIntent);
                removeOnClicks();
            }
        });
    }

    protected void onRestart() {
        super.onRestart();
        darkMode = settings.getString("darkMode", darkMode);
        ScrollView backgroundColor = findViewById(R.id.main_background_colour);
        if (darkMode.equals("yes")) {
            backgroundColor.setBackgroundColor(Color.parseColor("#616161"));
        } else {
            backgroundColor.setBackgroundColor(Color.parseColor("#EDDADADA"));
        }

        onClickListeners();
        moPubSdk.callAdView(adViewHolder);
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
            Intent help = new Intent(a_main_screen.this, help_menu.class);
            startActivity(help);
        }
        return super.onOptionsItemSelected(item);
    }

    public void donate(View view) {
        String url = "https://www.patreon.com/GCDatabase";
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }


    public void removeOnClicks() {
        characters.setOnClickListener(null);
        awakening.setOnClickListener(null);
        cooking.setOnClickListener(null);
        summonSimulatorView.setOnClickListener(null);
        otherResources.setOnClickListener(null);
    }
}
