package com.lukesapps.grandcross.database;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.adcolony.sdk.AdColony;
import com.applovin.sdk.AppLovinSdk;
import com.github.aakira.expandablelayout.ExpandableLinearLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.mopub.common.MoPub;
import com.mopub.common.MoPubReward;
import com.mopub.common.SdkConfiguration;
import com.mopub.common.SdkInitializationListener;
import com.mopub.mobileads.MoPubErrorCode;
import com.mopub.mobileads.MoPubRewardedVideoListener;
import com.mopub.mobileads.MoPubRewardedVideos;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Objects;
import java.util.Random;
import java.util.Set;

public class summonSimulator extends AppCompatActivity {

    TextView summonsLeftText;
    int pity = 0;
    int characters = 2;
    String summon11;
    Random rand;
    ArrayList<String> rCharacterName;
    ArrayList<String> rCharacters;
    ArrayList<String> rCharactersImage;
    ArrayList<String> srCharacterName;
    ArrayList<String> srCharacters;
    ArrayList<String> srCharactersImage;
    ArrayList<String> ssrCharacterName;
    ArrayList<String> ssrCharacters;
    ArrayList<String> ssrCharactersImage;
    ArrayList<String> rssrCharacterName;
    ArrayList<String> rssrCharacters;
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
    TextView gssr;
    String summonedRarity;
    String summonedCharacter;
    String summonedImage;
    String summonedCharacterNoType;
    String banner = "Goddess Elizabeth (Festival) (JP/KR)";
    ExpandableLinearLayout selectBannerView;
    RadioButton checkedRadioButton;
    boolean isChecked;
    int isRateUp;
    int bannerCharacters;
    RadioButton banner1Name;
    RadioButton banner2Name;
    RadioButton banner3Name;
    RadioButton banner4Name;
    RadioButton banner5Name;
    RadioButton banner6Name;
    RadioButton banner7Name;
    RadioButton banner8Name;
    RadioButton banner9Name;
    RadioButton banner10Name;
    int bannerDisplayCount = 0;
    int created = 0;
    FirebaseDatabase database;
    DatabaseReference ref;
    int summonsLeft;
    TextView getSummons;
    public static final String PREFS_NAME = "MyPrefsFile";
    SharedPreferences settings;
    String darkMode;
    ScrollView backgroundColor;
    private MoPubRewardedVideoListener rewardedVideoListener;

    @SuppressLint({"ClickableViewAccessibility", "SetTextI18n"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Thread.setDefaultUncaughtExceptionHandler(new DefaultExceptionHandler(this));
        super.onCreate(savedInstanceState);
        MoPub.onCreate(this);
        setContentView(R.layout.summon_simulator);
        database = FirebaseDatabase.getInstance();
        ref = database.getReference();
        ref.keepSynced(true);
        Picasso.with(summonSimulator.this).setIndicatorsEnabled(false);
        ImageView ssBackground = findViewById(R.id.summon_simulator_background);
        Picasso.with(this).load(R.drawable.boat_hat_summon).fit().centerCrop().into(ssBackground);
        summonsLeftText = findViewById(R.id.summonsLeft);
        settings = getSharedPreferences(PREFS_NAME, 0);
        settings = getSharedPreferences(PREFS_NAME, 0);

        //Ads
        String appId = "app3d46291c0233493693";
        String[] zoneIds = {"vz8ea83557b4064da4a4"};
        // Initialize the AdColony SDK with the above IDs
        AdColony.configure(summonSimulator.this, appId, zoneIds);
        AppLovinSdk.initializeSdk(summonSimulator.this);
        //Test = 920b6145fb1546cf8b5cf2ac34638bb7
        //Mine = 172f9124da5d4cc6a8ea62bf7085a753
        SdkConfiguration sdkConfiguration = new SdkConfiguration.Builder("172f9124da5d4cc6a8ea62bf7085a753").build();
        MoPub.initializeSdk(this, sdkConfiguration, initSdkListener());
        getSummons = findViewById(R.id.loadAd);
        getSummons.setOnClickListener(v -> {
            if (summonsLeft < 100) {
                if (MoPubRewardedVideos.hasRewardedVideo("172f9124da5d4cc6a8ea62bf7085a753")) {
                    MoPubRewardedVideos.showRewardedVideo("172f9124da5d4cc6a8ea62bf7085a753");
                } else {
                    MoPubRewardedVideos.loadRewardedVideo("172f9124da5d4cc6a8ea62bf7085a753");
                    getSummons.setText("...");
                }
            } else {
                Toast.makeText(summonSimulator.this, "Cannot add summons after reaching 100 stored. Please use some before attempting to get more.", Toast.LENGTH_SHORT).show();
            }
        });
        rewardedVideoListener = new MoPubRewardedVideoListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onRewardedVideoLoadSuccess(@NonNull String adUnitId) {
                // Called when the video for the given adUnitId has loaded. At this point you should be able to call MoPubRewardedVideos.showRewardedVideo(String) to show the video.
                getSummons.setText("+20");
                MoPubRewardedVideos.showRewardedVideo("172f9124da5d4cc6a8ea62bf7085a753");
            }
            @SuppressLint("SetTextI18n")
            @Override
            public void onRewardedVideoLoadFailure(@NonNull String adUnitId, @NonNull MoPubErrorCode errorCode) {
                // Called when a video fails to load for the given adUnitId. The provided error code will provide more insight into the reason for the failure to load.
                Toast.makeText(summonSimulator.this, "Ad failed to load. Have 10 summons on me.", Toast.LENGTH_SHORT).show();
                summonsLeft = summonsLeft + 10;
                SharedPreferences.Editor editor = settings.edit();
                editor.putInt("summonsLeft", summonsLeft);
                editor.apply();
                summonsLeftText.setText("Summons Left: " + summonsLeft);
                getSummons.setText("+20");
            }

            @Override
            public void onRewardedVideoStarted(@NonNull String adUnitId) {
                // Called when a rewarded video starts playing.
            }

            @Override
            public void onRewardedVideoPlaybackError(@NonNull String adUnitId, @NonNull MoPubErrorCode errorCode) {
                //  Called when there is an error during video playback.
            }

            @Override
            public void onRewardedVideoClicked(@NonNull String adUnitId) {
                //  Called when a rewarded video is clicked.
                getSummons.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onRewardedVideoClosed(@NonNull String adUnitId) {
                // Called when a rewarded video is closed. At this point your application should resume.
            }

            @SuppressLint("SetTextI18n")
            @Override
            public void onRewardedVideoCompleted(@NonNull Set<String> adUnitIds, @NonNull MoPubReward reward) {
                // Called when a rewarded video is completed and the user should be rewarded.
                // You can query the reward object with boolean isSuccessful(), String getLabel(), and int getAmount().
                Toast.makeText(summonSimulator.this, "You earned 20 Multi Summons!", Toast.LENGTH_SHORT).show();
                summonsLeft = summonsLeft + 20;
                SharedPreferences.Editor editor = settings.edit();
                editor.putInt("summonsLeft", summonsLeft);
                editor.apply();
                summonsLeftText.setText("Summons Left: " + summonsLeft);
            }
        };
        MoPubRewardedVideos.setRewardedVideoListener(rewardedVideoListener);


        rand = new Random();
        rCharacterName = new ArrayList<>();
        rCharacters = new ArrayList<>();
        rCharactersImage = new ArrayList<>();
        srCharacterName = new ArrayList<>();
        srCharacters = new ArrayList<>();
        srCharactersImage = new ArrayList<>();
        ssrCharacterName = new ArrayList<>();
        ssrCharacters = new ArrayList<>();
        ssrCharactersImage = new ArrayList<>();
        rssrCharacterName = new ArrayList<>();
        rssrCharacters = new ArrayList<>();
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
        banner6Name = findViewById(R.id.banner6);
        banner7Name = findViewById(R.id.banner7);
        banner8Name = findViewById(R.id.banner8);
        banner9Name = findViewById(R.id.banner9);
        banner10Name = findViewById(R.id.banner10);
        banner1Name.setVisibility(View.GONE);
        banner2Name.setVisibility(View.GONE);
        banner3Name.setVisibility(View.GONE);
        banner4Name.setVisibility(View.GONE);
        banner5Name.setVisibility(View.GONE);
        banner6Name.setVisibility(View.GONE);
        banner7Name.setVisibility(View.GONE);
        banner8Name.setVisibility(View.GONE);
        banner9Name.setVisibility(View.GONE);
        banner10Name.setVisibility(View.GONE);
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
        RadioGroup rGroup = findViewById(R.id.banner_filter);
        rGroup.setOnCheckedChangeListener((group, checkedId) -> {
            // This will get the radiobutton that has changed in its check state
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
        });
        created = 1;
        backgroundColor = findViewById(R.id.summoning_background_colour);
        //Recall the storage
        darkMode = settings.getString("darkMode", darkMode);
        if (darkMode.equals("yes")) {
            backgroundColor.setBackgroundColor(Color.parseColor("#616161"));
        } else {
            backgroundColor.setBackgroundColor(Color.parseColor("#4DFF4E50"));
        }

        Log.e("tag", "settings = " + settings.contains("summonsLeft"));
        if (!settings.contains("summonsLeft")) {
            SharedPreferences.Editor editor = settings.edit();
            editor.putInt("summonsLeft", 20);
            editor.apply();
        }
        //Recall the storage
        summonsLeft = settings.getInt("summonsLeft", summonsLeft);
        summonsLeftText.setText("Summons Left: " + summonsLeft);
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
        if (summonsLeft > 119) {
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
                    "SR", "SR", "SR", "SR", "SR", "SR", "SR", "SR", "SR", "SR",
                    "SR", "SR",
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
        } else if (isRateUp == 2 || isRateUp == 3) {
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
            Log.v("summoned", "2 or 3 Rate up");
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
            if (!rCharacters.isEmpty()) {
                characterSummoned = rCharacters.get(rand.nextInt(rCharacters.size()));
                int index = rCharacters.indexOf(characterSummoned);
                String image_url = rCharactersImage.get(index);
                Picasso.with(summonSimulator.this).load(image_url).fit().into(summonView);
                summonedRarity = rarity;
                summonedCharacter = characterSummoned;
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
            if (!srCharacters.isEmpty()) {
                characterSummoned = srCharacters.get(rand.nextInt(srCharacters.size()));
                int index = srCharacters.indexOf(characterSummoned);
                String image_url = srCharactersImage.get(index);
                Picasso.with(summonSimulator.this).load(image_url).fit().into(summonView);
                summonedRarity = rarity;
                summonedCharacter = characterSummoned;
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
            if (!ssrCharacters.isEmpty()) {
                characterSummoned = ssrCharacters.get(rand.nextInt(ssrCharacters.size()));
                int index = ssrCharacters.indexOf(characterSummoned);
                String image_url = ssrCharactersImage.get(index);
                Picasso.with(summonSimulator.this).load(image_url).fit().into(summonView);
                summonedRarity = rarity;
                summonedCharacter = characterSummoned;
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
            if (!rssrCharacters.isEmpty()) {
                characterSummoned = rssrCharacters.get(rand.nextInt(rssrCharacters.size()));
                int index = rssrCharacters.indexOf(characterSummoned);
                String image_url = rssrCharactersImage.get(index);
                Picasso.with(summonSimulator.this).load(image_url).fit().into(summonView);
                summonedRarity = rarity;
                summonedCharacter = characterSummoned;
                Log.v("summoned", "Character = " + summonedCharacter);
                summonedCharacterNoType = characterSummoned;
                summonedImage = image_url;
            } else {
                summonedRarity = "R";
                summonedCharacter = "Error";
                summonedCharacterNoType = "ErrorError";
                summonedImage = "https://firebasestorage.googleapis.com/v0/b/ds-grand-cross-database.appspot.com/o/undergoing_maintenance.png?alt=media&token=7938ecd6-dcf1-48ae-9d2f-cd428a589e05";
            }
        }
        int checkSummons = countStringOccurance(checkForSummons, summonedCharacter);
        int checkSummonsDoNotRemove = countStringOccurance(summonedCharacterDoNotRemove, summonedCharacterNoType);
        checkForSummons.add(summonedCharacter);
        summonedCharacterDoNotRemove.add(summonedCharacterNoType);
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
            summonsLeft = summonsLeft - 1;
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
            Toast.makeText(summonSimulator.this, "Watch an ad by clicking on '+20' to get 20 multi summons", Toast.LENGTH_SHORT).show();
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

    public void callData() {
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    long count = dataSnapshot.getChildrenCount();
                    characters = (int) count;
                    if (created != 0) {
                        rCharacters.clear();
                        rCharacterName.clear();
                        rCharactersImage.clear();
                        srCharacters.clear();
                        srCharacterName.clear();
                        srCharactersImage.clear();
                        ssrCharacters.clear();
                        ssrCharacterName.clear();
                        ssrCharactersImage.clear();
                        rssrCharacters.clear();
                        rssrCharacterName.clear();
                        rssrCharactersImage.clear();
                    }
                    for (int characterId = 1; characterId <= characters; characterId++) {
                        String selectId = String.valueOf(characterId);
                        DataSnapshot characterIdChild = dataSnapshot.child(selectId);
                        String character = Objects.requireNonNull(characterIdChild.child("character").getValue()).toString();
                        String characterName = Objects.requireNonNull(characterIdChild.child("nameAndType").getValue()).toString();
                        String characterImage = Objects.requireNonNull(characterIdChild.child("characterImage").getValue()).toString();
                        String characterRarity = Objects.requireNonNull(characterIdChild.child("rarity").getValue()).toString();
                        String banners = Objects.requireNonNull(characterIdChild.child("banners").getValue()).toString();
                        String banner1 = Objects.requireNonNull(characterIdChild.child("banner1").getValue()).toString();
                        String rateUp1 = Objects.requireNonNull(characterIdChild.child("rateUp1").getValue()).toString();
                        String banner2 = Objects.requireNonNull(characterIdChild.child("banner2").getValue()).toString();
                        String rateUp2 = Objects.requireNonNull(characterIdChild.child("rateUp2").getValue()).toString();
                        String banner3 = Objects.requireNonNull(characterIdChild.child("banner3").getValue()).toString();
                        String rateUp3 = Objects.requireNonNull(characterIdChild.child("rateUp3").getValue()).toString();
                        String banner4 = Objects.requireNonNull(characterIdChild.child("banner4").getValue()).toString();
                        String rateUp4 = Objects.requireNonNull(characterIdChild.child("rateUp4").getValue()).toString();
                        String banner5 = Objects.requireNonNull(characterIdChild.child("banner5").getValue()).toString();
                        String rateUp5 = Objects.requireNonNull(characterIdChild.child("rateUp5").getValue()).toString();
                        String banner6 = Objects.requireNonNull(characterIdChild.child("banner6").getValue()).toString();
                        String rateUp6 = Objects.requireNonNull(characterIdChild.child("rateUp6").getValue()).toString();
                        String banner7 = Objects.requireNonNull(characterIdChild.child("banner7").getValue()).toString();
                        String rateUp7 = Objects.requireNonNull(characterIdChild.child("rateUp7").getValue()).toString();
                        String banner8 = Objects.requireNonNull(characterIdChild.child("banner8").getValue()).toString();
                        String rateUp8 = Objects.requireNonNull(characterIdChild.child("rateUp8").getValue()).toString();
                        String banner9 = Objects.requireNonNull(characterIdChild.child("banner9").getValue()).toString();
                        String rateUp9 = Objects.requireNonNull(characterIdChild.child("rateUp9").getValue()).toString();
                        String banner10 = Objects.requireNonNull(characterIdChild.child("banner10").getValue()).toString();
                        String rateUp10 = Objects.requireNonNull(characterIdChild.child("rateUp10").getValue()).toString();
                        if (bannerDisplayCount == 0) {
                            Log.e("summonSimulator", "selectId = " + selectId);
                            Log.e("summonSimulator", "banners = " + banners);
                            if (!banners.equals("-")) {
                                switch (selectId) {
                                    case "1":
                                        banner1Name.setText(banners);
                                        banner1Name.setVisibility(View.VISIBLE);
                                        break;
                                    case "2":
                                        banner2Name.setText(banners);
                                        banner2Name.setVisibility(View.VISIBLE);
                                        break;
                                    case "3":
                                        banner3Name.setText(banners);
                                        banner3Name.setVisibility(View.VISIBLE);
                                        break;
                                    case "4":
                                        banner4Name.setText(banners);
                                        banner4Name.setVisibility(View.VISIBLE);
                                        break;
                                    case "5":
                                        banner5Name.setText(banners);
                                        banner5Name.setVisibility(View.VISIBLE);
                                        break;
                                    case "6":
                                        banner6Name.setText(banners);
                                        banner6Name.setVisibility(View.VISIBLE);
                                        break;
                                    case "7":
                                        banner7Name.setText(banners);
                                        banner7Name.setVisibility(View.VISIBLE);
                                        break;
                                    case "8":
                                        banner8Name.setText(banners);
                                        banner8Name.setVisibility(View.VISIBLE);
                                        break;
                                    case "9":
                                        banner9Name.setText(banners);
                                        banner9Name.setVisibility(View.VISIBLE);
                                        break;
                                    case "10":
                                        banner10Name.setText(banners);
                                        banner10Name.setVisibility(View.VISIBLE);
                                        break;
                                }
                            }
                        }
                        String[] bannerSelector = {banner1, banner2, banner3, banner4, banner5, banner6, banner7, banner8, banner9, banner10};
                        String[] rateUpList = {rateUp1, rateUp2, rateUp3, rateUp4, rateUp5, rateUp6, rateUp7, rateUp8, rateUp9, rateUp10};
                        if (!banner.equals("-")) {
                            if (Arrays.asList(bannerSelector).contains(banner)) {
                                if (Arrays.asList(rateUpList).contains(banner)) {
                                    isRateUp = isRateUp + 1;
                                    bannerCharacters = bannerCharacters + 1;
                                    rssrCharacterName.add(character);
                                    rssrCharacters.add(character);
                                    rssrCharactersImage.add(characterImage);
                                } else {
                                    switch (characterRarity) {
                                        case "R":
                                            rCharacterName.add(character);
                                            rCharacters.add(characterName);
                                            rCharactersImage.add(characterImage);
                                            break;
                                        case "SR":
                                            srCharacterName.add(character);
                                            srCharacters.add(characterName);
                                            srCharactersImage.add(characterImage);
                                            break;
                                        case "SSR":
                                            ssrCharacterName.add(character);
                                            ssrCharacters.add(characterName);
                                            ssrCharactersImage.add(characterImage);
                                            bannerCharacters = bannerCharacters + 1;
                                            break;
                                    }
                                }
                            }
                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }

        });

    }

    public void selectBanner(View view) {
        Log.v("summonSimulator", "TEST");
        selectBannerView = findViewById(R.id.select_banner);
        selectBannerView.toggle();
        selectBannerView.initLayout();
        bannerDisplayCount = 1;
    }

    private SdkInitializationListener initSdkListener() {
        return () -> {
            /* MoPub SDK initialized.*/
        };
    }
}