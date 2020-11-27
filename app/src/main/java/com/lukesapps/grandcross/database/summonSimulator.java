package com.lukesapps.grandcross.database;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.github.aakira.expandablelayout.ExpandableLinearLayout;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.rewarded.RewardItem;
import com.google.android.gms.ads.rewarded.RewardedAd;
import com.google.android.gms.ads.rewarded.RewardedAdCallback;
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class summonSimulator extends AppCompatActivity {

    TextView summonsLeftText;
    int pity = 0;
    int characters = 2;
    String summon11;
    Random rand;
    ArrayList<String> rCharacterName;
    ArrayList<String> rCharactersType;
    ArrayList<String> rCharactersImage;
    ArrayList<String> srCharacterName;
    ArrayList<String> srCharactersType;
    ArrayList<String> srCharactersImage;
    ArrayList<String> ssrCharacterName;
    ArrayList<String> ssrCharactersType;
    ArrayList<String> ssrCharactersImage;
    ArrayList<String> rssrCharacterName;
    ArrayList<String> rssrCharactersType;
    ArrayList<String> rssrCharactersImage;
    ArrayList<String> checkForSummons;
    ArrayList<String> summonedCharacterDoNotRemove;
    ArrayList<summonedCharactersList> summonedCharactersList;
    ArrayList<summonedCharactersList> summonedCharactersListSort;
    summonedCharactersListAdapter adapter;
    ListView listView;
    int gemsSpent = 0;
    TextView spent;
    ImageView summon1View;
    ImageView summon2View;
    ImageView summon3View;
    ImageView summon4View;
    ImageView summon5View;
    ImageView summon6View;
    ImageView summon7View;
    ImageView summon8View;
    ImageView summon9View;
    ImageView summon10View;
    ImageView summon11View;
    ImageView backArrow;
    ImageView nextArrow;
    TextView gssr;
    String summonedRarity;
    String summonedCharacter;
    String summonedImage;
    String summonedCharacterNoType;
    String banner = "Draw Heroes: Part 1 (GLB/AS)";
    ExpandableLinearLayout selectBannerView;
    RadioButton checkedRadioButton;
    boolean isChecked;
    int isRateUp;
    int bannerCharacters;
    RadioGroup rGroup;
    RadioButton banner1Name;
    RadioButton banner2Name;
    RadioButton banner3Name;
    RadioButton banner4Name;
    RadioButton banner5Name;
    int bannerDisplayCount = 0;
    int created = 0;
    FirebaseDatabase database;
    DatabaseReference ref;
    int summonsLeft;
    TextView getAd;
    public static final String PREFS_NAME = "MyPrefsFile";
    SharedPreferences settings;
    String darkMode;
    ScrollView backgroundColor;
    private RewardedAd rewardedAd;
    int bannerCount = 5;
    String bannerName;
    RadioGroup.OnCheckedChangeListener checkedChangeListener;
    String bannerNumber;
    String rateupNumber;
    ArrayList<String> bannerList;
    ArrayList<String> rateUpList;
    int numberOfBanners = 1;
    int start = 0;
    String getSummonsText;
    LinearLayout adsLayout;
    boolean unlimitedSummons;


    @SuppressLint({"ClickableViewAccessibility", "SetTextI18n"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.summon_simulator);
        ref = FirebaseDatabase.getInstance().getReference();
        ref.keepSynced(true);
        Picasso.with(summonSimulator.this).setIndicatorsEnabled(false);
        ImageView ssBackground = findViewById(R.id.summon_simulator_background);
        selectBannerView = findViewById(R.id.select_banner);
        Picasso.with(this).load(R.drawable.boat_hat_summon).fit().centerCrop().into(ssBackground);
        summonsLeftText = findViewById(R.id.summonsLeft);
        settings = getSharedPreferences(PREFS_NAME, 0);
        backArrow = findViewById(R.id.backArrow);
        nextArrow = findViewById(R.id.nextArrow);
        backArrow.setVisibility(View.INVISIBLE);
        getAd = findViewById(R.id.loadAd);
        adsLayout = findViewById(R.id.getAd);

        unlimitedSummons = settings.getBoolean("removeAds", false);

        if (unlimitedSummons) {
            adsLayout.setVisibility(View.GONE);
        }

        //Ads
        //Test Ad - ca-app-pub-3940256099942544/5224354917
        //My Ad - ca-app-pub-8009364805724327/7225785804
        rewardedAd = new RewardedAd(this, "ca-app-pub-8009364805724327/7225785804");
        RewardedAdLoadCallback adLoadCallback = new RewardedAdLoadCallback() {
            @Override
            public void onRewardedAdLoaded() {
                // Ad successfully loaded.
                getAd.setText("AD (+20)");
                getAd.setTextSize(22);
            }

            @Override
            public void onRewardedAdFailedToLoad(int errorCode) {
                // Ad failed to load.
            }
        };
        rewardedAd.loadAd(new AdRequest.Builder().build(), adLoadCallback);

        getAd.setText("...");
        getAd.setTextSize(15);
        getAd.setOnClickListener(v -> {
            getSummonsText = getAd.getText().toString();
            if (getSummonsText.equals("AD (+20)")) {
                if (summonsLeft < 100) {
                    if (rewardedAd.isLoaded()) {
                        Toast.makeText(summonSimulator.this, "Loading Ad.", Toast.LENGTH_SHORT).show();
                        Activity activityContext = summonSimulator.this;
                        RewardedAdCallback adCallback = new RewardedAdCallback() {
                            @Override
                            public void onRewardedAdOpened() {
                                // Ad opened.
                                getAd.setText("...");
                                getAd.setTextSize(15);
                            }

                            @Override
                            public void onRewardedAdClosed() {
                                // Ad closed.
                                createAndLoadRewardedAd();
                            }

                            @Override
                            public void onUserEarnedReward(@NonNull RewardItem reward) {
                                // User earned reward.
                                Toast.makeText(summonSimulator.this, "You earned 20 Multi Summons!", Toast.LENGTH_SHORT).show();
                                summonsLeft = summonsLeft + 20;
                                SharedPreferences.Editor editor = settings.edit();
                                editor.putInt("summonsLeft", summonsLeft);
                                editor.apply();
                                summonsLeftText.setText("Summons Left: " + summonsLeft);
                            }

                            @Override
                            public void onRewardedAdFailedToShow(int errorCode) {
                                // Ad failed to display.
                            }
                        };
                        rewardedAd.show(activityContext, adCallback);
                    } else {
                        Toast.makeText(summonSimulator.this, "Ad failed to load. Have 10 summons on me.", Toast.LENGTH_SHORT).show();
                        summonsLeft = summonsLeft + 10;
                        SharedPreferences.Editor editor = settings.edit();
                        editor.putInt("summonsLeft", summonsLeft);
                        editor.apply();
                        summonsLeftText.setText("Summons Left: " + summonsLeft);
                        getAd.setText("...");
                        getAd.setTextSize(15);
                        createAndLoadRewardedAd();
                    }
                } else {
                    Toast.makeText(summonSimulator.this, "Cannot add summons after reaching 100 stored. Please use some before attempting to get more.", Toast.LENGTH_SHORT).show();
                }
            }
        });


        rand = new Random();
        rCharacterName = new ArrayList<>();
        rCharactersType = new ArrayList<>();
        rCharactersImage = new ArrayList<>();
        srCharacterName = new ArrayList<>();
        srCharactersType = new ArrayList<>();
        srCharactersImage = new ArrayList<>();
        ssrCharacterName = new ArrayList<>();
        ssrCharactersType = new ArrayList<>();
        ssrCharactersImage = new ArrayList<>();
        rssrCharacterName = new ArrayList<>();
        rssrCharactersType = new ArrayList<>();
        rssrCharactersImage = new ArrayList<>();
        checkForSummons = new ArrayList<>();
        summonedCharactersList = new ArrayList<>();
        summonedCharactersListSort = new ArrayList<>();
        summonedCharacterDoNotRemove = new ArrayList<>();
        spent = findViewById(R.id.gems_spent);
        summon1View = findViewById(R.id.summon1);
        summon2View = findViewById(R.id.summon2);
        summon3View = findViewById(R.id.summon3);
        summon4View = findViewById(R.id.summon4);
        summon5View = findViewById(R.id.summon5);
        summon6View = findViewById(R.id.summon6);
        summon7View = findViewById(R.id.summon7);
        summon8View = findViewById(R.id.summon8);
        summon9View = findViewById(R.id.summon9);
        summon10View = findViewById(R.id.summon10);
        summon11View = findViewById(R.id.summon11);
        banner1Name = findViewById(R.id.banner1);
        banner2Name = findViewById(R.id.banner2);
        banner3Name = findViewById(R.id.banner3);
        banner4Name = findViewById(R.id.banner4);
        banner5Name = findViewById(R.id.banner5);
        gssr = findViewById(R.id.gssr);
        adapter = new summonedCharactersListAdapter(summonSimulator.this, summonedCharactersListSort);
        listView = findViewById(R.id.summoned_characters);
        callData();
        // Setting on Touch Listener for handling the touch inside ScrollView
        listView.setOnTouchListener((v, event) -> {
            // Disallow the touch request for parent scroll on touch of child view
            v.getParent().requestDisallowInterceptTouchEvent(true);
            return false;
        });
        rGroup = findViewById(R.id.banner_filter);
        checkedChangeListener = (group, checkedId) -> {
            checkedRadioButton = group.findViewById(checkedId);
            // This puts the value (true/false) into the variable
            isChecked = checkedRadioButton.isChecked();
            // If the radiobutton that has changed in check state is now checked...
            if (isChecked) {
                banner = checkedRadioButton.getText().toString();
                gemsSpent = 0;
                spent.setText("Gems spent: " + gemsSpent);
                pity = 0;
                gssr.setText("Pity: " + pity + "/5");
                summonedCharactersList.clear();
                checkForSummons.clear();
                summonedCharacterDoNotRemove.clear();
                summonedCharactersListSort.clear();
                listView.setAdapter(adapter);
                Picasso.with(summonSimulator.this).load(R.drawable.salt).fit().into(summon1View);
                Picasso.with(summonSimulator.this).load(R.drawable.salt).fit().into(summon2View);
                Picasso.with(summonSimulator.this).load(R.drawable.salt).fit().into(summon3View);
                Picasso.with(summonSimulator.this).load(R.drawable.salt).fit().into(summon4View);
                Picasso.with(summonSimulator.this).load(R.drawable.salt).fit().into(summon5View);
                Picasso.with(summonSimulator.this).load(R.drawable.salt).fit().into(summon6View);
                Picasso.with(summonSimulator.this).load(R.drawable.salt).fit().into(summon7View);
                Picasso.with(summonSimulator.this).load(R.drawable.salt).fit().into(summon8View);
                Picasso.with(summonSimulator.this).load(R.drawable.salt).fit().into(summon9View);
                Picasso.with(summonSimulator.this).load(R.drawable.salt).fit().into(summon10View);
                Picasso.with(summonSimulator.this).load(R.drawable.salt).fit().into(summon11View);
                isRateUp = 0;
                bannerCharacters = 0;
                callData();
            }
        };
        rGroup.setOnCheckedChangeListener(checkedChangeListener);
        created = 1;
        backgroundColor = findViewById(R.id.summoning_background_colour);
        //Recall the storage
        darkMode = settings.getString("darkMode", darkMode);
        if (darkMode.equals("yes")) {
            backgroundColor.setBackgroundColor(Color.parseColor("#616161"));
        } else {
            backgroundColor.setBackgroundColor(Color.parseColor("#4DFF4E50"));
        }
        if (!settings.contains("summonsLeft")) {
            SharedPreferences.Editor editor = settings.edit();
            editor.putInt("summonsLeft", 50);
            editor.apply();
        }
        //Recall the storage
        if (unlimitedSummons) {
            summonsLeft = 100;
        } else {
            summonsLeft = settings.getInt("summonsLeft", summonsLeft);
            summonsLeftText.setText("Summons Left: " + summonsLeft);
        }
    }

    protected void onRestart() {
        super.onRestart();
        darkMode = settings.getString("darkMode", darkMode);
        if (darkMode.equals("yes")) {
            backgroundColor.setBackgroundColor(Color.parseColor("#616161"));
        } else {
            backgroundColor.setBackgroundColor(Color.parseColor("#4DFF4E50"));
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
            Intent help = new Intent(summonSimulator.this, help_menu.class);
            startActivity(help);
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressLint("SetTextI18n")
    public String summon() {
        if (summonsLeft > 199) {
            summonsLeft = 100;
            summonsLeftText.setText("Summons Left: " + summonsLeft);
            SharedPreferences.Editor editor = settings.edit();
            editor.putInt("summonsLeft", summonsLeft);
            editor.apply();
        }
        if (bannerCharacters == isRateUp) {
            String[] rarity = {"R", "R", "R", "R", "R", "R", "R", "R", "R", "R",
                    "R", "R", "R", "R", "R", "R", "R", "R", "R", "R",
                    "R", "R", "R", "R", "R", "R", "R", "R", "R", "R",
                    "R", "R", "R", "R", "R", "R", "R", "R", "R", "R",
                    "R", "R", "R", "R", "R", "R", "R", "R", "R", "R",
                    "R", "R", "R", "R", "R", "R", "R", "R", "R", "R",
                    "R", "R", "R", "R", "R", "R", "R", "R", "R", "R",
                    "R", "R", "R", "R", "R", "R", "R", "R", "R", "R",
                    "R", "R", "R", "R", "R", "R", "R", "R", "R", "R",
                    "R", "R", "R", "R", "R", "R", "R", "R", "R", "R",
                    "R", "R", "R", "R", "R", "R", "R", "R", "R", "R",
                    "R", "R", "R", "R", "R", "R", "R", "R", "R", "R",
                    "SR", "SR", "SR", "SR", "SR", "SR", "SR", "SR", "SR", "SR",
                    "SR", "SR", "SR", "SR", "SR", "SR", "SR", "SR", "SR", "SR",
                    "SR", "SR", "SR", "SR", "SR", "SR", "SR", "SR", "SR", "SR",
                    "SR", "SR", "SR", "SR", "SR", "SR", "SR", "SR", "SR", "SR",
                    "SR", "SR", "SR", "SR", "SR", "SR", "SR", "SR", "SR", "SR",
                    "SR", "SR", "SR", "SR", "SR", "SR", "SR", "SR", "SR", "SR",
                    "SR", "SR", "SR", "SR", "SR", "SR", "SSR (Rate Up)", "SSR (Rate Up)", "SSR (Rate Up)", "SSR (Rate Up)",
                    "SSR (Rate Up)", "SSR (Rate Up)",
                    "SSR (Rate Up)", "SSR (Rate Up)", "SSR (Rate Up)", "SSR (Rate Up)", "SSR (Rate Up)", "SSR (Rate Up)", "SSR (Rate Up)", "SSR (Rate Up)"};
            summonedRarity = rarity[rand.nextInt(rarity.length)];
        } else if (isRateUp == 1) {
            String[] rarity = {"R", "R", "R", "R", "R", "R", "R", "R", "R", "R",
                    "R", "R", "R", "R", "R", "R", "R", "R", "R", "R",
                    "R", "R", "R", "R", "R", "R", "R", "R", "R", "R",
                    "R", "R", "R", "R", "R", "R", "R", "R", "R", "R",
                    "R", "R", "R", "R", "R", "R", "R", "R", "R", "R",
                    "R", "R", "R", "R", "R", "R", "R", "R", "R", "R",
                    "R", "R", "R", "R", "R", "R", "R", "R", "R", "R",
                    "R", "R", "R", "R", "R", "R", "R", "R", "R", "R",
                    "R", "R", "R", "R", "R", "R", "R", "R", "R", "R",
                    "R", "R", "R", "R", "R", "R", "R", "R", "R", "R",
                    "R", "R", "R", "R", "R", "R", "R", "R", "R", "R",
                    "R", "R", "R", "R", "R", "R", "R", "R", "R", "R",
                    "SR", "SR", "SR", "SR", "SR", "SR", "SR", "SR", "SR", "SR",
                    "SR", "SR", "SR", "SR", "SR", "SR", "SR", "SR", "SR", "SR",
                    "SR", "SR", "SR", "SR", "SR", "SR", "SR", "SR", "SR", "SR",
                    "SR", "SR", "SR", "SR", "SR", "SR", "SR", "SR", "SR", "SR",
                    "SR", "SR", "SR", "SR", "SR", "SR", "SR", "SR", "SR", "SR",
                    "SR", "SR", "SR", "SR", "SR", "SR", "SR", "SR", "SR", "SR",
                    "SR", "SR", "SR", "SR", "SR", "SR", "SR", "SR", "SR", "SR",
                    "SR", "SR", "SR", "SR",
                    "SSR", "SSR", "SSR", "SSR", "SSR", "SSR (Rate Up)"};
            summonedRarity = rarity[rand.nextInt(rarity.length)];
            Log.v("summoned", "1 Rate up");
        } else if (isRateUp == 2) {
            String[] rarity = {"R", "R", "R", "R", "R", "R", "R", "R", "R", "R",
                    "R", "R", "R", "R", "R", "R", "R", "R", "R", "R",
                    "R", "R", "R", "R", "R", "R", "R", "R", "R", "R",
                    "R", "R", "R", "R", "R", "R", "R", "R", "R", "R",
                    "R", "R", "R", "R", "R", "R", "R", "R", "R", "R",
                    "R", "R", "R", "R", "R", "R", "R", "R", "R", "R",
                    "R", "R", "R", "R", "R", "R", "R", "R", "R", "R",
                    "R", "R", "R", "R", "R", "R", "R", "R", "R", "R",
                    "R", "R", "R", "R", "R", "R", "R", "R", "R", "R",
                    "R", "R", "R", "R", "R", "R", "R", "R", "R", "R",
                    "R", "R", "R", "R", "R", "R", "R", "R", "R", "R",
                    "R", "R", "R", "R", "R", "R", "R", "R", "R", "R",
                    "SR", "SR", "SR", "SR", "SR", "SR", "SR", "SR", "SR", "SR",
                    "SR", "SR", "SR", "SR", "SR", "SR", "SR", "SR", "SR", "SR",
                    "SR", "SR", "SR", "SR", "SR", "SR", "SR", "SR", "SR", "SR",
                    "SR", "SR", "SR", "SR", "SR", "SR", "SR", "SR", "SR", "SR",
                    "SR", "SR", "SR", "SR", "SR", "SR", "SR", "SR", "SR", "SR",
                    "SR", "SR", "SR", "SR", "SR", "SR", "SR", "SR", "SR", "SR",
                    "SR", "SR", "SR", "SR", "SR", "SR", "SR", "SR", "SR", "SR",
                    "SR", "SR", "SR", "SR",
                    "SSR", "SSR", "SSR", "SSR", "SSR (Rate Up)", "SSR (Rate Up)"};
            summonedRarity = rarity[rand.nextInt(rarity.length)];
            Log.v("summoned", "2 Rate up");
        } else if (isRateUp > 2 && isRateUp < 7 ) {
            String[] rarity = {"R", "R", "R", "R", "R", "R", "R", "R", "R", "R",
                    "R", "R", "R", "R", "R", "R", "R", "R", "R", "R",
                    "R", "R", "R", "R", "R", "R", "R", "R", "R", "R",
                    "R", "R", "R", "R", "R", "R", "R", "R", "R", "R",
                    "R", "R", "R", "R", "R", "R", "R", "R", "R", "R",
                    "R", "R", "R", "R", "R", "R", "R", "R", "R", "R",
                    "R", "R", "R", "R", "R", "R", "R", "R", "R", "R",
                    "R", "R", "R", "R", "R", "R", "R", "R", "R", "R",
                    "R", "R", "R", "R", "R", "R", "R", "R", "R", "R",
                    "R", "R", "R", "R", "R", "R", "R", "R", "R", "R",
                    "R", "R", "R", "R", "R", "R", "R", "R", "R", "R",
                    "R", "R", "R", "R", "R", "R", "R", "R", "R", "R",
                    "SR", "SR", "SR", "SR", "SR", "SR", "SR", "SR", "SR", "SR",
                    "SR", "SR", "SR", "SR", "SR", "SR", "SR", "SR", "SR", "SR",
                    "SR", "SR", "SR", "SR", "SR", "SR", "SR", "SR", "SR", "SR",
                    "SR", "SR", "SR", "SR", "SR", "SR", "SR", "SR", "SR", "SR",
                    "SR", "SR", "SR", "SR", "SR", "SR", "SR", "SR", "SR", "SR",
                    "SR", "SR", "SR", "SR", "SR", "SR", "SR", "SR", "SR", "SR",
                    "SR", "SR", "SR", "SR", "SR", "SR", "SR", "SR", "SR", "SR",
                    "SR", "SR", "SR", "SR",
                    "SSR", "SSR", "SSR", "SSR (Rate Up)", "SSR (Rate Up)", "SSR (Rate Up)"};
            summonedRarity = rarity[rand.nextInt(rarity.length)];
            Log.v("summoned", "3 Rate up");
        } else {
            String[] rarity = {"R", "R", "R", "R", "R", "R", "R", "R", "R", "R",
                    "R", "R", "R", "R", "R", "R", "R", "R", "R", "R",
                    "R", "R", "R", "R", "R", "R", "R", "R", "R", "R",
                    "R", "R", "R", "R", "R", "R", "R", "R", "R", "R",
                    "R", "R", "R", "R", "R", "R", "R", "R", "R", "R",
                    "R", "R", "R", "R", "R", "R", "R", "R", "R", "R",
                    "R", "R", "R", "R", "R", "R", "R", "R", "R", "R",
                    "R", "R", "R", "R", "R", "R", "R", "R", "R", "R",
                    "R", "R", "R", "R", "R", "R", "R", "R", "R", "R",
                    "R", "R", "R", "R", "R", "R", "R", "R", "R", "R",
                    "R", "R", "R", "R", "R", "R", "R", "R", "R", "R",
                    "R", "R", "R", "R", "R", "R", "R", "R", "R", "R",
                    "SR", "SR", "SR", "SR", "SR", "SR", "SR", "SR", "SR", "SR",
                    "SR", "SR", "SR", "SR", "SR", "SR", "SR", "SR", "SR", "SR",
                    "SR", "SR", "SR", "SR", "SR", "SR", "SR", "SR", "SR", "SR",
                    "SR", "SR", "SR", "SR", "SR", "SR", "SR", "SR", "SR", "SR",
                    "SR", "SR", "SR", "SR", "SR", "SR", "SR", "SR", "SR", "SR",
                    "SR", "SR", "SR", "SR", "SR", "SR", "SR", "SR", "SR", "SR",
                    "SR", "SR", "SR", "SR", "SR", "SR", "SR", "SR", "SR", "SR",
                    "SR", "SR", "SR", "SR",
                    "SSR", "SSR", "SSR", "SSR", "SSR", "SSR"};
            summonedRarity = rarity[rand.nextInt(rarity.length)];
            Log.v("summoned", "No Rate up");
        }
        return summonedRarity;
    }

    public void summonedCharacter(String rarity, ImageView summonView) {
        String characterSummoned;
        if (rarity.equals("R")) {
            if (!rCharactersType.isEmpty()) {
                characterSummoned = rCharactersType.get(rand.nextInt(rCharactersType.size()));
                int index = rCharactersType.indexOf(characterSummoned);
                String image_url = rCharactersImage.get(index);
                Picasso.with(summonSimulator.this).load(image_url).fit().into(summonView);
                summonedRarity = rarity;
                summonedCharacter = characterSummoned + summonedRarity;
                summonedCharacterNoType = rCharacterName.get(index);
                summonedImage = image_url;
            } else {
                summonedRarity = "R";
                summonedCharacter = "Error";
                summonedCharacterNoType = "ErrorError";
                summonedImage = "https://firebasestorage.googleapis.com/v0/b/ds-grand-cross-database.appspot.com/o/undergoing_maintenance.png?alt=media&token=7938ecd6-dcf1-48ae-9d2f-cd428a589e05";
            }
        }
        if (rarity.equals("SR")) {
            if (!srCharactersType.isEmpty()) {
                characterSummoned = srCharactersType.get(rand.nextInt(srCharactersType.size()));
                int index = srCharactersType.indexOf(characterSummoned);
                String image_url = srCharactersImage.get(index);
                Picasso.with(summonSimulator.this).load(image_url).fit().into(summonView);
                summonedRarity = rarity;
                summonedCharacter = characterSummoned + summonedRarity;
                summonedCharacterNoType = srCharacterName.get(index);
                summonedImage = image_url;
            } else {
                summonedRarity = "R";
                summonedCharacter = "Error";
                summonedCharacterNoType = "ErrorError";
                summonedImage = "https://firebasestorage.googleapis.com/v0/b/ds-grand-cross-database.appspot.com/o/undergoing_maintenance.png?alt=media&token=7938ecd6-dcf1-48ae-9d2f-cd428a589e05";
            }
        }
        if (rarity.equals("SSR")) {
            if (!ssrCharactersType.isEmpty()) {
                characterSummoned = ssrCharactersType.get(rand.nextInt(ssrCharactersType.size()));
                int index = ssrCharactersType.indexOf(characterSummoned);
                String image_url = ssrCharactersImage.get(index);
                Picasso.with(summonSimulator.this).load(image_url).fit().into(summonView);
                summonedRarity = rarity;
                summonedCharacter = characterSummoned + summonedRarity;
                summonedCharacterNoType = ssrCharacterName.get(index);
                summonedImage = image_url;
            } else {
                summonedRarity = "R";
                summonedCharacter = "Error";
                summonedCharacterNoType = "ErrorError";
                summonedImage = "https://firebasestorage.googleapis.com/v0/b/ds-grand-cross-database.appspot.com/o/undergoing_maintenance.png?alt=media&token=7938ecd6-dcf1-48ae-9d2f-cd428a589e05";
            }
        }
        if (rarity.equals("SSR (Rate Up)")) {
            if (!rssrCharactersType.isEmpty()) {
                characterSummoned = rssrCharactersType.get(rand.nextInt(rssrCharactersType.size()));
                int index = rssrCharactersType.indexOf(characterSummoned);
                String image_url = rssrCharactersImage.get(index);
                Picasso.with(summonSimulator.this).load(image_url).fit().into(summonView);
                summonedRarity = rarity;
                summonedCharacter = characterSummoned + summonedRarity;
                summonedCharacterNoType = rssrCharacterName.get(index);
                summonedImage = image_url;
            } else {
                summonedRarity = "R";
                summonedCharacter = "Error";
                summonedCharacterNoType = "ErrorError";
                summonedImage = "https://firebasestorage.googleapis.com/v0/b/ds-grand-cross-database.appspot.com/o/undergoing_maintenance.png?alt=media&token=7938ecd6-dcf1-48ae-9d2f-cd428a589e05";
            }
        }
        int checkSummons = countStringOccurance(checkForSummons, summonedCharacter);
        int checkSummonsDoNotRemove = countStringOccurance(summonedCharacterDoNotRemove, summonedCharacter);
        checkForSummons.add(summonedCharacter);
        summonedCharacterDoNotRemove.add(summonedCharacter);
        if (checkSummons == 1) {
            summonedCharactersList.add(new summonedCharactersList(summonedRarity, summonedCharacterNoType, summonedImage));
        } else {
            int indexOfCharacter = 0;
            for (int i = 1; i < checkSummons; i++) {
                indexOfCharacter = checkForSummons.indexOf(summonedCharacter);
                checkForSummons.remove(indexOfCharacter);
            }
            summonedCharactersList.remove(indexOfCharacter);
            summonedCharactersList.add(new summonedCharactersList(summonedRarity, summonedCharacterNoType + " x" + checkSummonsDoNotRemove, summonedImage));
        }
        summonedCharactersListSort.clear();
        summonedCharactersListSort.addAll(summonedCharactersList);
        Collections.sort(summonedCharactersListSort);
        listView.setAdapter(adapter);
    }

    @SuppressLint("SetTextI18n")
    public void multiSummon(View view) {
        if (summonsLeft != 0) {
            if (!unlimitedSummons) {
                summonsLeft = summonsLeft - 1;
            }
            summonsLeftText.setText("summons left: " + summonsLeft);
            SharedPreferences.Editor editor = settings.edit();
            editor.putInt("summonsLeft", summonsLeft);
            editor.apply();
            String summon1 = summon();
            summonedCharacter(summon1, summon1View);

            String summon2 = summon();
            summonedCharacter(summon2, summon2View);

            String summon3 = summon();
            summonedCharacter(summon3, summon3View);

            String summon4 = summon();
            summonedCharacter(summon4, summon4View);

            String summon5 = summon();
            summonedCharacter(summon5, summon5View);

            String summon6 = summon();
            summonedCharacter(summon6, summon6View);

            String summon7 = summon();
            summonedCharacter(summon7, summon7View);

            String summon8 = summon();
            summonedCharacter(summon8, summon8View);

            String summon9 = summon();
            summonedCharacter(summon9, summon9View);

            String summon10 = summon();
            summonedCharacter(summon10, summon10View);
            gssr.setText("Pity: " + pity + "%");
            if (pity == 100) {
                if (bannerCharacters == isRateUp) {
                    String[] gssrRarity = {"SSR (Rate Up)", "SSR (Rate Up)", "SSR (Rate Up)", "SSR (Rate Up)", "SSR (Rate Up)"};
                    summon11 = gssrRarity[rand.nextInt(gssrRarity.length)];
                } else if (isRateUp == 1) {
                    String[] gssrRarity = {"SSR", "SSR", "SSR", "SSR", "SSR (Rate Up)"};
                    summon11 = gssrRarity[rand.nextInt(gssrRarity.length)];
                } else if (isRateUp == 2 || isRateUp == 3) {
                    String[] gssrRarity = {"SSR", "SSR", "SSR", "SSR (Rate Up)", "SSR (Rate Up)"};
                    summon11 = gssrRarity[rand.nextInt(gssrRarity.length)];
                } else {
                    summon11 = "SSR";
                }
                gssr.setText("GSSR");
                pity = 0;
            } else {
                summon11 = summon();
            }
            summonedCharacter(summon11, summon11View);
            if (!summon1.equals("SSR (Rate Up)")) {
                if (!summon1.equals("SSR")) {
                    if (!summon2.equals("SSR")) {
                        if (!summon2.equals("SSR (Rate Up)")) {
                            if (!summon3.equals("SSR")) {
                                if (!summon3.equals("SSR (Rate Up)")) {
                                    if (!summon4.equals("SSR")) {
                                        if (!summon4.equals("SSR (Rate Up)")) {
                                            if (!summon5.equals("SSR")) {
                                                if (!summon5.equals("SSR (Rate Up)")) {
                                                    if (!summon6.equals("SSR")) {
                                                        if (!summon6.equals("SSR (Rate Up)")) {
                                                            if (!summon7.equals("SSR")) {
                                                                if (!summon7.equals("SSR (Rate Up)")) {
                                                                    if (!summon8.equals("SSR")) {
                                                                        if (!summon8.equals("SSR (Rate Up)")) {
                                                                            if (!summon9.equals("SSR")) {
                                                                                if (!summon9.equals("SSR (Rate Up)")) {
                                                                                    if (!summon10.equals("SSR")) {
                                                                                        if (!summon10.equals("SSR (Rate Up)")) {
                                                                                            if (!summon11.equals("SSR")) {
                                                                                                if (!summon11.equals("SSR (Rate Up)")) {
                                                                                                    pity = pity + 20;
                                                                                                    gssr.setText("Pity: " + pity + "%");
                                                                                                }
                                                                                            }
                                                                                        }
                                                                                    }
                                                                                }
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            gemsSpent = gemsSpent + 30;
            spent.setText("Gems spent: " + gemsSpent);
        } else {
            Toast.makeText(summonSimulator.this, "Watch an ad to get 20 multi summons", Toast.LENGTH_SHORT).show();
        }
    }

    @SuppressLint("SetTextI18n")
    public void resetSummons(View view) {
        gemsSpent = 0;
        spent.setText("Gems spent: " + gemsSpent);
        pity = 0;
        gssr.setText("Pity: " + pity + "/5");
        if (!summonedCharactersList.isEmpty()) {
            summonedCharactersList.clear();
        }
        if (!checkForSummons.isEmpty()) {
            checkForSummons.clear();
        }
        if (!summonedCharacterDoNotRemove.isEmpty()) {
            summonedCharacterDoNotRemove.clear();
        }
        if (!summonedCharactersListSort.isEmpty()) {
            summonedCharactersListSort.clear();
        }
        listView.setAdapter(adapter);
        Picasso.with(summonSimulator.this).load(R.drawable.salt).fit().into(summon1View);
        Picasso.with(summonSimulator.this).load(R.drawable.salt).fit().into(summon2View);
        Picasso.with(summonSimulator.this).load(R.drawable.salt).fit().into(summon3View);
        Picasso.with(summonSimulator.this).load(R.drawable.salt).fit().into(summon4View);
        Picasso.with(summonSimulator.this).load(R.drawable.salt).fit().into(summon5View);
        Picasso.with(summonSimulator.this).load(R.drawable.salt).fit().into(summon6View);
        Picasso.with(summonSimulator.this).load(R.drawable.salt).fit().into(summon7View);
        Picasso.with(summonSimulator.this).load(R.drawable.salt).fit().into(summon8View);
        Picasso.with(summonSimulator.this).load(R.drawable.salt).fit().into(summon9View);
        Picasso.with(summonSimulator.this).load(R.drawable.salt).fit().into(summon10View);
        Picasso.with(summonSimulator.this).load(R.drawable.salt).fit().into(summon11View);
    }

    public int countStringOccurance(ArrayList<String> arr, String str) {
        int count = 1;
        for (int i = 0; i < arr.size(); i++) {
            if (str.equals(arr.get(i))) {
                count += 1;
            }
        }
        return count;
    }

    public void selectBanner(View view) {
        selectBannerView.toggle();
        if (bannerDisplayCount == 0) {
            selectBannerView.initLayout();
        }
        bannerDisplayCount = 1;
    }

    public void backArrow(View view) {
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    rGroup.setOnCheckedChangeListener(null);
                    rGroup.clearCheck();
                    rGroup.setOnCheckedChangeListener(checkedChangeListener);
                    banner1Name.setVisibility(View.VISIBLE);
                    banner2Name.setVisibility(View.VISIBLE);
                    banner3Name.setVisibility(View.VISIBLE);
                    banner4Name.setVisibility(View.VISIBLE);
                    banner5Name.setVisibility(View.VISIBLE);
                    bannerCount = bannerCount - 10;
                    if (bannerCount == 0) {
                        backArrow.setVisibility(View.INVISIBLE);
                    }
                    nextArrow.setVisibility(View.VISIBLE);
                    bannerCount++;
                    bannerName = dataSnapshot.child(Integer.toString(bannerCount)).child("banners").getValue().toString();
                    banner1Name.setText(bannerName);
                    bannerCount++;
                    bannerName = dataSnapshot.child(Integer.toString(bannerCount)).child("banners").getValue().toString();
                    banner2Name.setText(bannerName);
                    bannerCount++;
                    bannerName = dataSnapshot.child(Integer.toString(bannerCount)).child("banners").getValue().toString();
                    banner3Name.setText(bannerName);
                    bannerCount++;
                    bannerName = dataSnapshot.child(Integer.toString(bannerCount)).child("banners").getValue().toString();
                    banner4Name.setText(bannerName);
                    bannerCount++;
                    bannerName = dataSnapshot.child(Integer.toString(bannerCount)).child("banners").getValue().toString();
                    banner5Name.setText(bannerName);
                    if (banner1Name.getText().toString().equals(banner)) {
                        banner1Name.setChecked(true);
                    } else if (banner2Name.getText().toString().equals(banner)) {
                        banner2Name.setChecked(true);
                    } else if (banner3Name.getText().toString().equals(banner)) {
                        banner3Name.setChecked(true);
                    } else if (banner4Name.getText().toString().equals(banner)) {
                        banner4Name.setChecked(true);
                    } else if (banner5Name.getText().toString().equals(banner)) {
                        banner5Name.setChecked(true);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        Log.v("BannerCount", "Banner Count = " + bannerCount);
    }

    public void nextArrow(View view) {
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    rGroup.setOnCheckedChangeListener(null);
                    rGroup.clearCheck();
                    rGroup.setOnCheckedChangeListener(checkedChangeListener);
                    backArrow.setVisibility(View.VISIBLE);
                    bannerCount++;
                    bannerName = dataSnapshot.child(Integer.toString(bannerCount)).child("banners").getValue().toString();
                    if (!bannerName.equals("-")) {
                        banner1Name.setVisibility(View.VISIBLE);
                        banner1Name.setText(bannerName);
                        bannerCount++;
                        if (banner1Name.getText().toString().equals(banner)) {
                            banner1Name.setChecked(true);
                        }
                        bannerName = dataSnapshot.child(Integer.toString(bannerCount)).child("banners").getValue().toString();
                        if (!bannerName.equals("-")) {
                            banner2Name.setText(bannerName);
                            bannerCount++;
                            if (banner2Name.getText().toString().equals(banner)) {
                                banner2Name.setChecked(true);
                            }
                            bannerName = dataSnapshot.child(Integer.toString(bannerCount)).child("banners").getValue().toString();
                            if (!bannerName.equals("-")) {
                                banner3Name.setText(bannerName);
                                bannerCount++;
                                if (banner3Name.getText().toString().equals(banner)) {
                                    banner3Name.setChecked(true);
                                }
                                bannerName = dataSnapshot.child(Integer.toString(bannerCount)).child("banners").getValue().toString();
                                if (!bannerName.equals("-")) {
                                    banner4Name.setText(bannerName);
                                    bannerCount++;
                                    if (banner4Name.getText().toString().equals(banner)) {
                                        banner4Name.setChecked(true);
                                    }
                                    bannerName = dataSnapshot.child(Integer.toString(bannerCount)).child("banners").getValue().toString();
                                    if (!bannerName.equals("-")) {
                                        banner5Name.setText(bannerName);
                                        bannerCount++;
                                        if (banner5Name.getText().toString().equals(banner)) {
                                            banner5Name.setChecked(true);
                                        }
                                        bannerName = dataSnapshot.child(Integer.toString(bannerCount)).child("banners").getValue().toString();
                                        if (bannerName.equals("-")) {
                                            nextArrow.setVisibility(View.GONE);
                                        }
                                        bannerCount--;
                                    } else {
                                        banner5Name.setVisibility(View.GONE);
                                        nextArrow.setVisibility(View.INVISIBLE);
                                        bannerCount = bannerCount + 2;
                                        bannerName = dataSnapshot.child(Integer.toString(bannerCount)).child("banners").getValue().toString();
                                        if (bannerName.equals("-")) {
                                            nextArrow.setVisibility(View.GONE);
                                        }
                                        bannerCount--;
                                    }
                                } else {
                                    banner4Name.setVisibility(View.GONE);
                                    banner5Name.setVisibility(View.GONE);
                                    nextArrow.setVisibility(View.INVISIBLE);
                                    bannerCount = bannerCount + 3;
                                    bannerName = dataSnapshot.child(Integer.toString(bannerCount)).child("banners").getValue().toString();
                                    if (bannerName.equals("-")) {
                                        nextArrow.setVisibility(View.GONE);
                                    }
                                    bannerCount--;
                                }
                            } else {
                                banner3Name.setVisibility(View.GONE);
                                banner4Name.setVisibility(View.GONE);
                                banner5Name.setVisibility(View.GONE);
                                nextArrow.setVisibility(View.INVISIBLE);
                                bannerCount = bannerCount + 4;
                                bannerName = dataSnapshot.child(Integer.toString(bannerCount)).child("banners").getValue().toString();
                                if (bannerName.equals("-")) {
                                    nextArrow.setVisibility(View.GONE);
                                }
                                bannerCount--;
                            }
                        } else {
                            banner2Name.setVisibility(View.GONE);
                            banner3Name.setVisibility(View.GONE);
                            banner4Name.setVisibility(View.GONE);
                            banner5Name.setVisibility(View.GONE);
                            nextArrow.setVisibility(View.INVISIBLE);
                            bannerCount = bannerCount + 5;
                            bannerName = dataSnapshot.child(Integer.toString(bannerCount)).child("banners").getValue().toString();
                            if (bannerName.equals("-")) {
                                nextArrow.setVisibility(View.GONE);
                            }
                            bannerCount--;
                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        Log.v("BannerCount", "Banner Count = " + bannerCount);
    }

    public void callData() {
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    long count = dataSnapshot.getChildrenCount();
                    characters = (int) count;
                    rCharactersType.clear();
                    rCharacterName.clear();
                    rCharactersImage.clear();
                    srCharactersType.clear();
                    srCharacterName.clear();
                    srCharactersImage.clear();
                    ssrCharactersType.clear();
                    ssrCharacterName.clear();
                    ssrCharactersImage.clear();
                    rssrCharactersType.clear();
                    rssrCharacterName.clear();
                    rssrCharactersImage.clear();

                    if (start == 0) {
                        getData1(dataSnapshot);
                    }
                    getData2(dataSnapshot);
                    start = 1;
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }

        });

    }

    public void getData1(DataSnapshot dataSnapshot) {
        for (int characterId = 1; characterId <= characters; characterId++) {
            String selectId = String.valueOf(characterId);
            DataSnapshot characterIdChild = dataSnapshot.child(selectId);
            String banners = characterIdChild.child("banners").getValue().toString();
            if (!banners.equals("-")) {
                numberOfBanners++;
            }
        }
    }

    public void getData2(DataSnapshot dataSnapshot) {
        for (int characterId = 1; characterId <= characters; characterId++) {
            bannerList = new ArrayList<>();
            rateUpList = new ArrayList<>();
            String selectId = String.valueOf(characterId);
            DataSnapshot characterIdChild = dataSnapshot.child(selectId);
            String character = characterIdChild.child("character").getValue().toString();
            String characterName = characterIdChild.child("nameAndType").getValue().toString();
            String characterImage = characterIdChild.child("characterImage").getValue().toString();
            String characterRarity = characterIdChild.child("rarity").getValue().toString();
            String banners = characterIdChild.child("banners").getValue().toString();
            for (int i = 1; i < numberOfBanners; i++) {
                bannerNumber = "banner" + i;
                rateupNumber = "rateUp" + i;
                bannerList.add(characterIdChild.child(bannerNumber).getValue().toString());
                rateUpList.add(characterIdChild.child(rateupNumber).getValue().toString());
            }
            if (start == 0) {
                if (banner.equals("Draw Heroes: Part 1 (GLB/AS)")) {
                    banner = banners;
                }
            }
            if (bannerDisplayCount == 0) {
                if (!banners.equals("-")) {
                    switch (selectId) {
                        case "1":
                            banner1Name.setText(banners);
                            break;
                        case "2":
                            banner2Name.setText(banners);
                            break;
                        case "3":
                            banner3Name.setText(banners);
                            break;
                        case "4":
                            banner4Name.setText(banners);
                            break;
                        case "5":
                            banner5Name.setText(banners);
                            break;
                    }
                }
            }
            if (!banner.equals("-")) {
                if (bannerList.contains(banner)) {
                    if (rateUpList.contains(banner)) {
                        isRateUp = isRateUp + 1;
                        Log.e("summonSimulator", "Rate up = " + character);
                        bannerCharacters = bannerCharacters + 1;
                        rssrCharacterName.add(character);
                        rssrCharactersType.add(characterName);
                        rssrCharactersImage.add(characterImage);
                    } else {
                        switch (characterRarity) {
                            case "R":
                                rCharacterName.add(character);
                                rCharactersType.add(characterName);
                                rCharactersImage.add(characterImage);
                                break;
                            case "SR":
                                srCharacterName.add(character);
                                srCharactersType.add(characterName);
                                srCharactersImage.add(characterImage);
                                break;
                            case "SSR":
                                ssrCharacterName.add(character);
                                ssrCharactersType.add(characterName);
                                ssrCharactersImage.add(characterImage);
                                bannerCharacters = bannerCharacters + 1;
                                break;
                        }
                    }
                }
            }
        }
    }

    public void createAndLoadRewardedAd() {
        rewardedAd = new RewardedAd(this, "ca-app-pub-8009364805724327/7225785804");
        RewardedAdLoadCallback adLoadCallback = new RewardedAdLoadCallback() {
            @Override
            public void onRewardedAdLoaded() {
                // Ad successfully loaded.
                getAd.setText("AD (+20)");
                getAd.setTextSize(22);
            }

            @Override
            public void onRewardedAdFailedToLoad(int errorCode) {
                // Ad failed to load.
                getAd.setText("AD (+20)");
                getAd.setTextSize(22);
            }
        };
        rewardedAd.loadAd(new AdRequest.Builder().build(), adLoadCallback);
    }
}