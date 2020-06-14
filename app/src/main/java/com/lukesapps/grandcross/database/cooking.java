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

public class cooking extends AppCompatActivity {

    BillingProcessor bp;
    public static final String PREFS_NAME = "MyPrefsFile";
    SharedPreferences settings;
    String darkMode;
    ViewPager viewPager;
    MoPubView moPubView;
    LinearLayout adViewHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Thread.setDefaultUncaughtExceptionHandler(new DefaultExceptionHandler(this));
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cooking);
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        viewPager = findViewById(R.id.cooking_view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
        ImageView mainBackground = findViewById(R.id.cooking_background);
        Picasso.with(this).load(R.drawable.cooking_background_bw).fit().centerCrop().into(mainBackground);
        settings = getSharedPreferences(PREFS_NAME, 0);
        adViewHolder = findViewById(R.id.adViewHolder);
        MoPubSdk moPubSdk;
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
            LinearLayout adViewHolder = findViewById(R.id.adViewHolder);
            adViewHolder.setBackgroundColor(Color.parseColor("#616161"));
        } else {
            viewPager.setBackgroundColor(Color.parseColor("#66FFEFDC"));
            LinearLayout adViewHolder = findViewById(R.id.adViewHolder);
            adViewHolder.setBackgroundColor(Color.parseColor("#66FFEFDC"));
        }
    }

    protected void onRestart() {
        super.onRestart();
        darkMode = settings.getString("darkMode", darkMode);
        if (darkMode.equals("yes")) {
            viewPager.setBackgroundColor(Color.parseColor("#616161"));
        } else {
            viewPager.setBackgroundColor(Color.parseColor("#66FFEFDC"));
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
            Intent help = new Intent(cooking.this, help_menu.class);
            startActivity(help);
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
                    fragment = new cooking_chapter_1();
                    break;
                case 1:
                    fragment = new cooking_chapter_2();
                    break;
                case 2:
                    fragment = new cooking_chapter_3();
                    break;
                case 3:
                    fragment = new cooking_chapter_4();
                    break;
                case 4:
                    fragment = new cooking_chapter_5();
                    break;
                case 5:
                    fragment = new cooking_chapter_6();
                    break;
            }
            return fragment;
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Chap 1";
                case 1:
                    return "Chap 2";
                case 2:
                    return "Chap 3";
                case 3:
                    return "Chap 4";
                case 4:
                    return "Chap 5";
                case 5:
                    return "Chap 6";
            }
            return null;
        }

        @Override
        public int getCount() {
            return 6;
        }
    }
}
