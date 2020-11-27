package com.lukesapps.grandcross.database;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Objects;

public class cosmetics_list extends AppCompatActivity {

    public static final String PREFS_NAME = "MyPrefsFile";
    ListView cosmeticsListView;
    cosmetics_database_adapter adapter;
    ArrayList<cosmetics_database_retriever> cosmeticsList;
    DatabaseReference database;
    int characters;
    SharedPreferences settings;
    SharedPreferences.Editor editor;
    String darkMode;
    AdMobSdk adMobSdk;
    LinearLayout adViewHolder;
    LinearLayout backgroundColour;
    String characterName = "";
    int weaponSR = 0;
    int weaponSSR = 0;
    int weaponUR = 0;
    int clothesSR = 0;
    int clothesSSR = 0;
    int clothesUR = 0;
    int headSR = 0;
    int headSSR = 0;
    int headUR = 0;
    int one = 0;
    EditText filterCharacters;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        database = FirebaseDatabase.getInstance("https://ds-grand-cross-database-545c9.firebaseio.com/").getReference();
        database.keepSynced(true);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cosmetics_list);
        cosmeticsListView = findViewById(R.id.cosmetics_list);
        adViewHolder = findViewById(R.id.adViewHolder);
        ImageView cosmeticsBackground = findViewById(R.id.comsetics_background);
        backgroundColour = findViewById(R.id.backgroundColour);
        filterCharacters = findViewById(R.id.characters_filter);
        settings = getSharedPreferences(PREFS_NAME, 0);
        editor = settings.edit();
        Picasso.with(this).load(R.drawable.cosmetics_list_background).fit().centerCrop().into(cosmeticsBackground);
        cosmeticsListView.setAdapter(adapter);
        final Handler handler = new Handler();
        handler.postDelayed(() -> {
            adMobSdk = AdMobSdk.getInstance(this);
            adMobSdk.isAdMobSdkInitialized().observe(this, aBoolean -> {
                if (aBoolean) {
                    adMobSdk.callAdView(adViewHolder);
                }
            });
        }, 1500);
        cosmeticsListView.setOnItemClickListener((parent, view, position, id) -> {
            String charName = cosmeticsList.get(position).getCharName();
            editor.putString("selectedCharName", charName);
            editor.apply();
            Intent intent = new Intent(cosmetics_list.this, cosmetics_menu_main.class);
            startActivity(intent);
        });

        //Recall the storage
        darkMode = settings.getString("darkMode", darkMode);
        if (darkMode.equals("yes")) {
            backgroundColour.setBackgroundColor(Color.parseColor("#616161"));
            LinearLayout adViewHolder = findViewById(R.id.adViewHolder);
            adViewHolder.setBackgroundColor(Color.parseColor("#616161"));
        } else {
            backgroundColour.setBackgroundColor(Color.parseColor("#734527A0"));
            LinearLayout adViewHolder = findViewById(R.id.adViewHolder);
            adViewHolder.setBackgroundColor(Color.parseColor("#734527A0"));
        }
        cosmeticsList = new ArrayList<>();
        database.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    long count = dataSnapshot.getChildrenCount();
                    characters = (int) count;
                    for (int characterId = 1; characterId <= characters; characterId++) {
                        String selectId = String.valueOf(characterId);
                        String charName = Objects.requireNonNull(dataSnapshot.child(selectId).child("charName").getValue()).toString();
                        String rarity = Objects.requireNonNull(dataSnapshot.child(selectId).child("rarity").getValue()).toString();
                        String type = Objects.requireNonNull(dataSnapshot.child(selectId).child("type").getValue()).toString();
                        if (one == 0) {
                            characterName = charName;
                            one = 1;
                        }
                        if (!characterName.equals(charName)) {
                            cosmeticsList.add(new cosmetics_database_retriever(characterName, weaponSR, weaponSSR, weaponUR, clothesSR, clothesSSR, clothesUR, headSR, headSSR, headUR));
                            resetNumbers();
                            characterName = charName;
                        }
                        switch (type) {
                            case "Weapons":
                                switch (rarity) {
                                    case "SR":
                                        weaponSR = weaponSR + 1;
                                        break;
                                    case "SSR":
                                        weaponSSR = weaponSSR + 1;
                                        break;
                                    case "UR":
                                        weaponUR = weaponUR + 1;
                                        break;
                                }
                                break;
                            case "Clothes":
                                switch (rarity) {
                                    case "SR":
                                        clothesSR = clothesSR + 1;
                                        break;
                                    case "SSR":
                                        clothesSSR = clothesSSR + 1;
                                        break;
                                    case "UR":
                                        clothesUR = clothesUR + 1;
                                        break;
                                }
                                break;
                            case "Heads":
                                switch (rarity) {
                                    case "SR":
                                        headSR = headSR + 1;
                                        break;
                                    case "SSR":
                                        headSSR = headSSR + 1;
                                        break;
                                    case "UR":
                                        headUR = headUR + 1;
                                        break;
                                }
                                break;
                        }
                    }
                    adapter = new cosmetics_database_adapter(cosmetics_list.this, cosmeticsList);
                    cosmeticsListView.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        filterCharacters.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (adapter != null) {
                    adapter.getFilter().filter(s);
                }
            }


            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    public void resetNumbers() {
        weaponSR = 0;
        weaponSSR = 0;
        weaponUR = 0;
        clothesSR = 0;
        clothesSSR = 0;
        clothesUR = 0;
        headSR = 0;
        headSSR = 0;
        headUR = 0;
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
            Intent help = new Intent(cosmetics_list.this, help_menu.class);
            startActivity(help);
        }
        return super.onOptionsItemSelected(item);
    }
    protected void onRestart() {
        super.onRestart();
        darkMode = settings.getString("darkMode", darkMode);
        if (darkMode.equals("yes")) {
            backgroundColour.setBackgroundColor(Color.parseColor("#616161"));
        } else {
            backgroundColour.setBackgroundColor(Color.parseColor("#734527A0"));
        }
        adMobSdk.callAdView(adViewHolder);
    }

}