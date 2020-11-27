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
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.anjlab.android.iab.v3.BillingProcessor;
import com.anjlab.android.iab.v3.TransactionDetails;
import com.squareup.picasso.Picasso;


public class a_main_screen extends AppCompatActivity implements BillingProcessor.IBillingHandler {

    BillingProcessor bp;

    public static final String PREFS_NAME = "MyPrefsFile";
    SharedPreferences settings;
    SharedPreferences.Editor editor;
    String darkMode;
    LinearLayout adViewHolder;
    private AdMobSdk adMobSdk;
    TextView characters;
    TextView cosmetics;
    TextView summonSimulatorView;
    TextView awakening;
    TextView cooking;
    TextView otherResources;
    RelativeLayout removeAds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_main);
        adViewHolder = findViewById(R.id.adViewHolder);
        settings = getSharedPreferences(PREFS_NAME, 0);
        editor = settings.edit();
        removeAds = findViewById(R.id.removeAds);

        //IAP
        bp = new BillingProcessor(this,
                "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAynZx/2bwpVm9e5l/y4gj+73YHiaMhyUkqadLHX7LFGwL7fuRwxeDiDca9ru7R4UdeQSUXG7I9QmyvYlfueJBDMcuz6PLZPhw3qviODxXdF0tTp0l1PNN392DgtZ1uyywLptcjBFZCJtSnb6OndDMOmhY2Rl2pC34SxyuGIZ3KfJ0DFjP8dn6qAEZrk8Uhdr6XX6c8ExSozTYSL/8BiGdR/+K1Um+Ni0dUnjyJwYHwHY00mL0a7m8//L4HT/girnV38IJe0D7hJD/wet00X3YYlZCzb151wR4/3Z8OB+EmJJZa3aU5sqMPk9nhepoYIdHoZfdDwvr8eA19IVY4wjWDwIDAQAB",
                this);
        bp.initialize();
        

        if(bp.isPurchased("remove_ads")) {
            removeAds.setVisibility(View.GONE);
        }

        //Ads
        adMobSdk = AdMobSdk.getInstance(a_main_screen.this);
        adMobSdk.setRemoveAds(settings.getBoolean("removeAds", false));
        adMobSdk.isAdMobSdkInitialized().observe(a_main_screen.this, aBoolean -> {
            if (aBoolean) {
                adMobSdk.callAdView(adViewHolder);
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
        ImageView cosmeticsMain = findViewById(R.id.cosmetics_main);
        Picasso.with(this).load(R.drawable.king_background).fit().centerCrop().into(cosmeticsMain);
        ImageView summoningMain = findViewById(R.id.summoning_main);
        Picasso.with(this).load(R.drawable.diane_background).fit().centerCrop().into(summoningMain);
        ImageView awakeningMain = findViewById(R.id.awakening_main);
        Picasso.with(this).load(R.drawable.merlin_background).fit().centerCrop().into(awakeningMain);
        ImageView cookingMain = findViewById(R.id.cooking_main);
        Picasso.with(this).load(R.drawable.ban_background).fit().centerCrop().into(cookingMain);
        ImageView otherResourcesMain = findViewById(R.id.otherResources_main);
        Picasso.with(this).load(R.drawable.gowther_background).fit().centerCrop().into(otherResourcesMain);
        ImageView supportMain = findViewById(R.id.support_main);
        Picasso.with(this).load(R.drawable.escanor_background).fit().centerCrop().into(supportMain);
        ScrollView backgroundColor = findViewById(R.id.main_background_colour);
        //Create the storage
        if (!settings.contains("darkMode")) {
            editor.putString("darkMode", "no");
            editor.apply();
        }
        editor = settings.edit();
        editor.putString("typeFilter", "none");
        editor.putString("regionFilter", "none");
        editor.putString("rarityFilter", "none");
        editor.putString("raceFilter", "none");
        editor.putString("obtainedFilter", "none");
        editor.putInt("listClicked", 0);
        editor.putString("charSearch", "");
        editor.putInt("sortedCC", 0);
        editor.putInt("sortedHP", 0);
        editor.putInt("sortedATK", 0);
        editor.putInt("sortedDEF", 0);
        editor.putString("sortBy", "");
        editor.putString("selectedCharName", "");
        editor.apply();
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
        characters.setOnClickListener(view -> {
            Intent characterIntent = new Intent(a_main_screen.this, character_list.class);
            startActivity(characterIntent);
            removeOnClicks();
        });

        //Go to Character List Menu
        cosmetics = findViewById(R.id.cosmetics);
        cosmetics.setOnClickListener(view -> {
            Intent cosmeticsIntent = new Intent(a_main_screen.this, cosmetics_list.class);
            startActivity(cosmeticsIntent);
            removeOnClicks();
        });

        //Go to Summon Simulator
        summonSimulatorView = findViewById(R.id.summon_simulator);
        summonSimulatorView.setOnClickListener(view -> {
            Intent summonSimulatorIntent = new Intent(a_main_screen.this, summonSimulator.class);
            startActivity(summonSimulatorIntent);
            removeOnClicks();
        });

        //Go to Awakening Menu
        awakening = findViewById(R.id.awakening);
        awakening.setOnClickListener(view -> {
            Intent awakeningIntent = new Intent(a_main_screen.this, awakening.class);
            startActivity(awakeningIntent);
            removeOnClicks();
        });

        //Go to Cooking Menu
        cooking = findViewById(R.id.cooking);
        cooking.setOnClickListener(view -> {
            Intent cookingIntent = new Intent(a_main_screen.this, cooking.class);
            startActivity(cookingIntent);
            removeOnClicks();
        });

        //Go to Other Resources Menu
        otherResources = findViewById(R.id.other_resources);
        otherResources.setOnClickListener(view -> {
            Intent otherResourcesIntent = new Intent(a_main_screen.this, otherResources.class);
            startActivity(otherResourcesIntent);
            removeOnClicks();
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
        adMobSdk.callAdView(adViewHolder);
    }

    public void removeAds(View view) {
        bp.purchase(this, "remove_ads");
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

    public void removeOnClicks() {
        characters.setOnClickListener(null);
        awakening.setOnClickListener(null);
        cooking.setOnClickListener(null);
        summonSimulatorView.setOnClickListener(null);
        otherResources.setOnClickListener(null);
    }

    @Override
    public void onProductPurchased(@NonNull String productId, TransactionDetails details) {
        if (!settings.getBoolean("removeAds", false)) {
            Toast.makeText(a_main_screen.this, "Purchase Success. Please restart the application.", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(a_main_screen.this, "Already Purchased. Please restart the application.", Toast.LENGTH_SHORT).show();
        }
        editor.putBoolean("removeAds", true);
        editor.apply();
        adMobSdk.setRemoveAds(settings.getBoolean("removeAds", false));
    }

    @Override
    public void onPurchaseHistoryRestored() {

    }

    @Override
    public void onBillingError(int errorCode, Throwable error) {
        Toast.makeText(a_main_screen.this, "Something went wrong.", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBillingInitialized() {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (!bp.handleActivityResult(requestCode, resultCode, data))
            super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onDestroy() {
        if (bp != null) {
            bp.release();
        }
        super.onDestroy();
    }
}
