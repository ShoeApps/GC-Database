package com.lukesapps.grandcross.database;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.lifecycle.Observer;
import androidx.viewpager.widget.ViewPager;

import com.anjlab.android.iab.v3.BillingProcessor;
import com.google.android.material.tabs.TabLayout;
import com.mopub.mobileads.MoPubView;
import com.squareup.picasso.Picasso;

public class awakening extends AppCompatActivity {

    BillingProcessor bp;
    public static final String PREFS_NAME = "MyPrefsFile";
    SharedPreferences settings;
    String darkMode;
    ViewPager viewPager;
    MoPubView moPubView;
    LinearLayout adViewHolder;
    MoPubSdk moPubSdk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Thread.setDefaultUncaughtExceptionHandler(new DefaultExceptionHandler(this));
        super.onCreate(savedInstanceState);
        setContentView(R.layout.awakening);
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        viewPager = findViewById(R.id.awakening_view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ImageView mainBackground = findViewById(R.id.awakening_background);
        Picasso.with(this).load(R.drawable.awakening_background2).fit().centerCrop().into(mainBackground);
        settings = getSharedPreferences(PREFS_NAME, 0);
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
            viewPager.setBackgroundColor(Color.parseColor("#616161"));
            adViewHolder.setBackgroundColor(Color.parseColor("#616161"));
        } else {
            viewPager.setBackgroundColor(Color.parseColor("#4DFC913A"));
            adViewHolder.setBackgroundColor(Color.parseColor("#4DFC913A"));
        }

    }

    protected void onRestart() {
        super.onRestart();
        darkMode = settings.getString("darkMode", darkMode);
        if (darkMode.equals("yes")) {
            viewPager.setBackgroundColor(Color.parseColor("#616161"));
        } else {
            viewPager.setBackgroundColor(Color.parseColor("#4DFC913A"));
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
            Intent help = new Intent(awakening.this, help_menu.class);
            startActivity(help);
        }
        if (id == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    public class SectionsPagerAdapter extends FragmentPagerAdapter {


        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment = null;
            switch (position) {
                case 0:
                    fragment = new awakening_red();
                    break;
                case 1:
                    fragment = new awakening_blue();
                    break;
                case 2:
                    fragment = new awakening_green();
                    break;
            }
            return fragment;
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Strength (Red)";
                case 1:
                    return "Speed (Blue)";
                case 2:
                    return "HP (Green)";
            }
            return null;
        }

        @Override
        public int getCount() {
            return 3;
        }
    }


}
