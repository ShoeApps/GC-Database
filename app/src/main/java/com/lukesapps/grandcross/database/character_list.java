package com.lukesapps.grandcross.database;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.github.aakira.expandablelayout.ExpandableLinearLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

public class character_list extends AppCompatActivity {

    MoPubSdk moPubSdk;
    SharedPreferences.Editor editor;
    LinearLayout adViewHolder;
    character_database_adapter adapter;
    ExpandableLinearLayout filterView;
    ArrayList<character_database_retriever> characterStatsList;
    int characters = 2;
    EditText filterCharacters;
    ImageView mainBackground;
    FirebaseDatabase database;
    DatabaseReference ref;
    ListView listView;
    public static final String PREFS_NAME = "MyPrefsFile";
    SharedPreferences settings;
    String darkMode;
    LinearLayout backgroundColor;
    ImageView strengthFilterView;
    ImageView hpFilterView;
    ImageView speedFilterView;
    ImageView globalFilterView;
    ImageView jpFilterView;
    ImageView jpOnlyFilterView;
    ImageView rFilterView;
    ImageView srFilterView;
    ImageView ssrFilterView;
    ImageView humanFilterView;
    ImageView fairyFilterView;
    ImageView demonFilterView;
    ImageView goddessFilterView;
    ImageView giantFilterView;
    ImageView unknownFilterView;
    ImageView storyFilterView;
    ImageView coinShopFilterView;
    ImageView normalGachaFilterView;
    ImageView limitedGachaFilterView;
    ImageView festivalGachaFilterView;
    ImageView collabFilterView;
    ImageView pvpFilterView;
    int speedClicked = 0;
    int hpClicked = 0;
    int strengthClicked = 0;
    int glbClicked = 0;
    int jpClicked = 0;
    int jpOnlyClicked = 0;
    int rClicked = 0;
    int srClicked = 0;
    int ssrClicked = 0;
    int humanClicked = 0;
    int fairyClicked = 0;
    int demonClicked = 0;
    int goddessClicked = 0;
    int giantClicked = 0;
    int unknownClicked = 0;
    int storyClicked = 0;
    int coinShopClicked = 0;
    int normalGachaClicked = 0;
    int limitedGachaClicked = 0;
    int festivalGachaClicked = 0;
    int collabClicked = 0;
    int pvpClicked = 0;
    String type;
    String region;
    String rarity;
    String race;
    String obtained;
    int listClicked = 0;
    int clickDestroy = 0;
    String editText;

    @SuppressLint("CommitPrefEdits")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Thread.setDefaultUncaughtExceptionHandler(new DefaultExceptionHandler(this));
        database = FirebaseDatabase.getInstance();
        ref = database.getReference();
        ref.keepSynced(true);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.character_list);
        adViewHolder = findViewById(R.id.adViewHolder);
        filterView = findViewById(R.id.filter_view);
        strengthFilterView = findViewById(R.id.strengthFilter);
        settings = getSharedPreferences(PREFS_NAME, 0);
        editor = settings.edit();
        strengthFilterView.setOnLongClickListener(v -> {
            makeToast("Strength (Red).");
            return true;
        });
        hpFilterView = findViewById(R.id.hpFilter);
        hpFilterView.setOnLongClickListener(v -> {
            makeToast("HP (Green).");
            return true;
        });
        speedFilterView = findViewById(R.id.speedFilter);
        speedFilterView.setOnLongClickListener(v -> {
            makeToast("Speed (Blue).");
            return true;
        });
        globalFilterView = findViewById(R.id.globalFilter);
        globalFilterView.setOnLongClickListener(v -> {
            makeToast("Global / Asia Units.");
            return true;
        });
        jpFilterView = findViewById(R.id.jpFilter);
        jpFilterView.setOnLongClickListener(v -> {
            makeToast("Japan / Korea Units.");
            return true;
        });
        jpOnlyFilterView = findViewById(R.id.jpOnlyFilter);
        jpOnlyFilterView.setOnLongClickListener(v -> {
            makeToast("Japan / Korea Only Units.\n(Not on global yet)");
            return true;
        });
        rFilterView = findViewById(R.id.rFilter);
        rFilterView.setOnLongClickListener(v -> {
            makeToast("Rare (R) Units.");
            return true;
        });
        srFilterView = findViewById(R.id.srFilter);
        srFilterView.setOnLongClickListener(v -> {
            makeToast("Super Rare (SR) Units.");
            return true;
        });
        ssrFilterView = findViewById(R.id.ssrFilter);
        ssrFilterView.setOnLongClickListener(v -> {
            makeToast("Super Super Rare (SSR) Units.");
            return true;
        });
        humanFilterView = findViewById(R.id.humanFilter);
        humanFilterView.setOnLongClickListener(v -> {
            makeToast("Human Race Units.");
            return true;
        });
        fairyFilterView = findViewById(R.id.fairyFilter);
        fairyFilterView.setOnLongClickListener(v -> {
            makeToast("Fairy Race Units.");
            return true;
        });
        demonFilterView = findViewById(R.id.demonFilter);
        demonFilterView.setOnLongClickListener(v -> {
            makeToast("Demon Race Units.");
            return true;
        });
        goddessFilterView = findViewById(R.id.goddessFilter);
        goddessFilterView.setOnLongClickListener(v -> {
            makeToast("Goddess Race Units.");
            return true;
        });
        giantFilterView = findViewById(R.id.giantFilter);
        giantFilterView.setOnLongClickListener(v -> {
            makeToast("Giant Race Units.");
            return true;
        });
        unknownFilterView = findViewById(R.id.unknownFilter);
        unknownFilterView.setOnLongClickListener(v -> {
            makeToast("Unknown Race Units.");
            return true;
        });
        storyFilterView = findViewById(R.id.storyFilter);
        storyFilterView.setOnLongClickListener(v -> {
            makeToast("Units obtainable from Story.\n(Includes Characters obtained from Missions, and as drops)\n(Characters might only be obtainable from story on one region)");
            return true;
        });
        coinShopFilterView = findViewById(R.id.coinShopFilter);
        coinShopFilterView.setOnLongClickListener(v -> {
            makeToast("Units obtainable from The Coin Shop.\n(Only shows permanent Coin Shop SSR units)");
            return true;
        });
        normalGachaFilterView = findViewById(R.id.normalGachaFilter);
        normalGachaFilterView.setOnLongClickListener(v -> {
            makeToast("Units obtainable from the General Pool in Banners.\n(Not all units are put on every banner)\n(Units are usually added to SSR Tickets)");
            return true;
        });
        limitedGachaFilterView = findViewById(R.id.limitedGachaFilter);
        limitedGachaFilterView.setOnLongClickListener(v -> {
            makeToast("Units obtainable from Limited Banners Only.\n(Units may never return again, but likely will return once a year)");
            return true;
        });
        festivalGachaFilterView = findViewById(R.id.festivalGachaFilter);
        festivalGachaFilterView.setOnLongClickListener(v -> {
            makeToast("Units obtainable from Festival Banners Only.\n(As of 13/06/2020, all Festival Units have been added to other Festival Banner)");
            return true;
        });
        collabFilterView = findViewById(R.id.collabFilter);
        collabFilterView.setOnLongClickListener(v -> {
            makeToast("Units obtainable from Collaborations.\n(These units will likely never be available once their original banner ends)");
            return true;
        });
        pvpFilterView = findViewById(R.id.pvpFilter);
        pvpFilterView.setOnLongClickListener(v -> {
            makeToast("Units obtainable as PvP Rewards.");
            return true;
        });
        mainBackground = findViewById(R.id.character_list_background);
        Picasso.with(this).load(R.drawable.character_list_background).fit().centerCrop().into(mainBackground);
        filterCharacters = findViewById(R.id.characters_filter);
        characterStatsList = new ArrayList<>();
        listView = findViewById(R.id.characters_list);
        listView.setAdapter(adapter);
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    long count = dataSnapshot.getChildrenCount();
                    characters = (int) count;
                    for (int characterId = 1; characterId <= characters; characterId++) {
                        String selectId = String.valueOf(characterId);
                        String obtained = Objects.requireNonNull(dataSnapshot.child(selectId).child("obtained").getValue()).toString();
                        String race = Objects.requireNonNull(dataSnapshot.child(selectId).child("race").getValue()).toString();
                        String rarity = Objects.requireNonNull(dataSnapshot.child(selectId).child("rarity").getValue()).toString();
                        String type = Objects.requireNonNull(dataSnapshot.child(selectId).child("type").getValue()).toString();
                        String name = Objects.requireNonNull(dataSnapshot.child(selectId).child("character").getValue()).toString();
                        String minCC = Objects.requireNonNull(dataSnapshot.child(selectId).child("maxCombatClass").getValue()).toString();
                        String maxCC = Objects.requireNonNull(dataSnapshot.child(selectId).child("maxCombatClass").getValue()).toString();
                        String minHealth = Objects.requireNonNull(dataSnapshot.child(selectId).child("minHealth").getValue()).toString();
                        String maxHealth = Objects.requireNonNull(dataSnapshot.child(selectId).child("maxHealth").getValue()).toString();
                        String minAttack = Objects.requireNonNull(dataSnapshot.child(selectId).child("minAttack").getValue()).toString();
                        String maxAttack = Objects.requireNonNull(dataSnapshot.child(selectId).child("maxAttack").getValue()).toString();
                        String minDefence = Objects.requireNonNull(dataSnapshot.child(selectId).child("minDefence").getValue()).toString();
                        String maxDefence = Objects.requireNonNull(dataSnapshot.child(selectId).child("maxDefence").getValue()).toString();
                        String characterImage = Objects.requireNonNull(dataSnapshot.child(selectId).child("characterImage").getValue()).toString();
                        characterStatsList.add(new character_database_retriever(selectId, obtained, race, rarity, type, name, minCC, maxCC, minHealth, maxHealth, minAttack, maxAttack, minDefence, maxDefence, characterImage));
                    }
                    adapter = new character_database_adapter(character_list.this, characterStatsList);
                    listView.setAdapter(adapter);
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
                if (!s.equals("")) {
                    editText = s.toString();
                    adapter.getFilter("true").filter(editText);
                    editor.putString("charSearch", editText);
                    editor.apply();
                }
                checkSorts();
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        backgroundColor = findViewById(R.id.linearlayoutlist);
        adViewHolder = findViewById(R.id.adViewHolder);
        final Handler handler = new Handler();
        handler.postDelayed(() -> {
            moPubSdk = MoPubSdk.getInstance(character_list.this);
            moPubSdk.isMoPubSdkInitialized().observe(character_list.this, aBoolean -> {
                if (aBoolean) {
                    moPubSdk.callAdView(adViewHolder);
                }
            });
        }, 1500);
        listView.setOnItemClickListener((parent, view, position, id) -> {
            clickDestroy = 1;
            String selectId = characterStatsList.get(position).getSelectId();
            Intent intent = new Intent(character_list.this, character_page.class);
            intent.putExtra("selectId", selectId);
            startActivity(intent);
        });

        //Recall the storage
        darkMode = settings.getString("darkMode", darkMode);
        if (darkMode.equals("yes")) {
            backgroundColor.setBackgroundColor(Color.parseColor("#616161"));
            LinearLayout adViewHolder = findViewById(R.id.adViewHolder);
            adViewHolder.setBackgroundColor(Color.parseColor("#616161"));
        } else {
            backgroundColor.setBackgroundColor(Color.parseColor("#4D15C2A6"));
            LinearLayout adViewHolder = findViewById(R.id.adViewHolder);
            adViewHolder.setBackgroundColor(Color.parseColor("#4D15C2A6"));
        }
        if (!settings.contains("typeFilter")) {
            editor.putString("typeFilter", "none");
            editor.apply();
        }
        if (!settings.contains("regionFilter")) {
            editor.putString("regionFilter", "none");
            editor.apply();
        }
        if (!settings.contains("rarityFilter")) {
            editor.putString("rarityFilter", "none");
            editor.apply();
        }
        if (!settings.contains("raceFilter")) {
            editor.putString("raceFilter", "none");
            editor.apply();
        }
        if (!settings.contains("obtainedFilter")) {
            editor.putString("obtainedFilter", "none");
            editor.apply();
        }
        listClicked = settings.getInt("listClicked", listClicked);
    }

    public void makeToast(String text) {
        Toast toast = Toast.makeText(this, text, Toast.LENGTH_SHORT);
        TextView v = (TextView) toast.getView().findViewById(android.R.id.message);
        if (v != null) v.setGravity(Gravity.CENTER);
        toast.show();
    }

    int sortedCC = 0;
    int sortedHP = 0;
    int sortedATK = 0;
    int sortedDEF = 0;

    public void sortCC(View view) {
        ccSorter();
    }
    public void ccSorter() {
        if (sortedCC == 0 || sortedCC == 2) {
            Collections.sort(characterStatsList, (o1, o2) -> o2.getMaxCC().compareTo(o1.getMaxCC()));
            editor.putInt("sortedCC", sortedCC);
            sortedCC = 1;
        } else {
            Collections.sort(characterStatsList, (o1, o2) -> o1.getMaxCC().compareTo(o2.getMaxCC()));
            editor.putInt("sortedCC", sortedCC);
            sortedCC = 2;
        }
        editor.putString("sortBy", "CC");
        editor.apply();
        adapter.notifyDataSetChanged();
        sortedHP = 0;
        sortedATK = 0;
        sortedDEF = 0;
    }

    public void sortHP(View view) {
        hpSorter();
    }
    public void hpSorter() {
        if (sortedHP == 0 || sortedHP == 2) {
            Collections.sort(characterStatsList, (o1, o2) -> o2.getMaxHealth().compareTo(o1.getMaxHealth()));
            editor.putInt("sortedHP", sortedHP);
            sortedHP = 1;
        } else {
            Collections.sort(characterStatsList, (o1, o2) -> o1.getMaxHealth().compareTo(o2.getMaxHealth()));
            editor.putInt("sortedHP", sortedHP);
            sortedHP = 2;
        }
        editor.putString("sortBy", "HP");
        editor.apply();
        adapter.notifyDataSetChanged();
        sortedCC = 0;
        sortedATK = 0;
        sortedDEF = 0;
    }

    public void sortATK(View view) {
        atkSorter();
    }
    public void atkSorter() {
        if (sortedATK == 0 || sortedATK == 2) {
            Collections.sort(characterStatsList, (o1, o2) -> o2.getMaxAttack().compareTo(o1.getMaxAttack()));
            editor.putInt("sortedATK", sortedATK);
            sortedATK = 1;
        } else {
            Collections.sort(characterStatsList, (o1, o2) -> o1.getMaxAttack().compareTo(o2.getMaxAttack()));
            editor.putInt("sortedATK", sortedATK);
            sortedATK = 2;
        }
        editor.putString("sortBy", "ATK");
        editor.apply();
        adapter.notifyDataSetChanged();
        sortedCC = 0;
        sortedHP = 0;
        sortedDEF = 0;
    }

    public void sortDEF(View view) {
        defSorter();
    }
    public void defSorter() {
        if (sortedDEF == 0 || sortedDEF == 2) {
            Collections.sort(characterStatsList, (o1, o2) -> o2.getMaxDefense().compareTo(o1.getMaxDefense()));
            editor.putInt("sortedDEF", sortedDEF);
            sortedDEF = 1;
        } else {
            Collections.sort(characterStatsList, (o1, o2) -> o1.getMaxDefense().compareTo(o2.getMaxDefense()));
            editor.putInt("sortedDEF", sortedDEF);
            sortedDEF = 2;
        }
        editor.putString("sortBy", "DEF");
        editor.apply();
        adapter.notifyDataSetChanged();
        sortedCC = 0;
        sortedHP = 0;
        sortedATK = 0;
    }

    public void checkSorts() {
        final Handler handler = new Handler();
        handler.postDelayed(() -> {
            if (sortedCC == 1) {
                Collections.sort(characterStatsList, (o1, o2) -> o2.getMaxCC().compareTo(o1.getMaxCC()));
            } else if (sortedCC == 2) {
                Collections.sort(characterStatsList, (o1, o2) -> o1.getMaxCC().compareTo(o2.getMaxCC()));
            } else if (sortedHP == 1) {
                Collections.sort(characterStatsList, (o1, o2) -> o2.getMaxHealth().compareTo(o1.getMaxHealth()));
            } else if (sortedHP == 2) {
                Collections.sort(characterStatsList, (o1, o2) -> o1.getMaxHealth().compareTo(o2.getMaxHealth()));
            } else if (sortedATK == 1) {
                Collections.sort(characterStatsList, (o1, o2) -> o2.getMaxAttack().compareTo(o1.getMaxAttack()));
            } else if (sortedATK == 2) {
                Collections.sort(characterStatsList, (o1, o2) -> o1.getMaxAttack().compareTo(o2.getMaxAttack()));
            } else if (sortedDEF == 1) {
                Collections.sort(characterStatsList, (o1, o2) -> o2.getMaxDefense().compareTo(o1.getMaxDefense()));
            } else if (sortedDEF == 2) {
                Collections.sort(characterStatsList, (o1, o2) -> o1.getMaxDefense().compareTo(o2.getMaxDefense()));
            }
            adapter.notifyDataSetChanged();
        }, 20);
    }


    public void strengthFilter(View view) {
        if (strengthClicked == 0) {
            strengthFilterView.setBackgroundColor(Color.parseColor("#4D15C2A6"));
            adapter.getFilter("false").filter("Strength");
            strengthClicked = 1;
            editor.putString("typeFilter", "strength");
        } else {
            strengthFilterView.setBackgroundColor(Color.parseColor("#00000000"));
            adapter.getFilter("false").filter("resetAttribute");
            strengthClicked = 0;
            editor.putString("typeFilter", "none");
        }
        editor.apply();
        hpFilterView.setBackgroundColor(Color.parseColor("#00000000"));
        speedFilterView.setBackgroundColor(Color.parseColor("#00000000"));
        hpClicked = 0;
        speedClicked = 0;
        checkSorts();
    }

    public void hpFilter(View view) {
        if (hpClicked == 0) {
            hpFilterView.setBackgroundColor(Color.parseColor("#4D15C2A6"));
            adapter.getFilter("false").filter("HP");
            hpClicked = 1;
            editor.putString("typeFilter", "hp");
        } else {
            hpFilterView.setBackgroundColor(Color.parseColor("#00000000"));
            adapter.getFilter("false").filter("resetAttribute");
            hpClicked = 0;
            editor.putString("typeFilter", "none");
        }
        editor.apply();
        strengthFilterView.setBackgroundColor(Color.parseColor("#00000000"));
        speedFilterView.setBackgroundColor(Color.parseColor("#00000000"));
        strengthClicked = 0;
        speedClicked = 0;
        checkSorts();
    }

    public void speedFilter(View view) {
        if (speedClicked == 0) {
            speedFilterView.setBackgroundColor(Color.parseColor("#4D15C2A6"));
            adapter.getFilter("false").filter("Speed");
            speedClicked = 1;
            editor.putString("typeFilter", "speed");
        } else {
            speedFilterView.setBackgroundColor(Color.parseColor("#00000000"));
            adapter.getFilter("false").filter("resetAttribute");
            speedClicked = 0;
            editor.putString("typeFilter", "none");
        }
        editor.apply();
        strengthFilterView.setBackgroundColor(Color.parseColor("#00000000"));
        hpFilterView.setBackgroundColor(Color.parseColor("#00000000"));
        strengthClicked = 0;
        hpClicked = 0;
        checkSorts();
    }

    public void globalFilter(View view) {
        if (glbClicked == 0) {
            globalFilterView.setBackgroundColor(Color.parseColor("#4D15C2A6"));
            adapter.getFilter("false").filter("Global / Asia");
            glbClicked = 1;
            editor.putString("regionFilter", "global");
        } else {
            globalFilterView.setBackgroundColor(Color.parseColor("#00000000"));
            adapter.getFilter("false").filter("resetServer");
            glbClicked = 0;
            editor.putString("regionFilter", "none");
        }
        editor.apply();
        jpFilterView.setBackgroundColor(Color.parseColor("#00000000"));
        jpOnlyFilterView.setBackgroundColor(Color.parseColor("#00000000"));
        jpClicked = 0;
        jpOnlyClicked = 0;
        checkSorts();
    }

    public void jpFilter(View view) {
        if (jpClicked == 0) {
            jpFilterView.setBackgroundColor(Color.parseColor("#4D15C2A6"));
            adapter.getFilter("false").filter("Japan / Korea");
            jpClicked = 1;
            editor.putString("regionFilter", "jp");
        } else {
            jpFilterView.setBackgroundColor(Color.parseColor("#00000000"));
            adapter.getFilter("false").filter("resetServer");
            jpClicked = 0;
            editor.putString("regionFilter", "none");
        }
        editor.apply();
        globalFilterView.setBackgroundColor(Color.parseColor("#00000000"));
        jpOnlyFilterView.setBackgroundColor(Color.parseColor("#00000000"));
        glbClicked = 0;
        jpOnlyClicked = 0;
        checkSorts();
    }

    public void jpOnlyFilter(View view) {
        if (jpOnlyClicked == 0) {
            jpOnlyFilterView.setBackgroundColor(Color.parseColor("#4D15C2A6"));
            adapter.getFilter("false").filter("Japan / Korea Exclusive");
            jpOnlyClicked = 1;
            editor.putString("regionFilter", "jpOnly");
        } else {
            jpOnlyFilterView.setBackgroundColor(Color.parseColor("#00000000"));
            adapter.getFilter("false").filter("resetServer");
            jpOnlyClicked = 0;
            editor.putString("regionFilter", "none");
        }
        editor.apply();
        globalFilterView.setBackgroundColor(Color.parseColor("#00000000"));
        jpFilterView.setBackgroundColor(Color.parseColor("#00000000"));
        glbClicked = 0;
        jpClicked = 0;
        checkSorts();
    }

    public void rFilter(View view) {
        if (rClicked == 0) {
            rFilterView.setBackgroundColor(Color.parseColor("#4D15C2A6"));
            adapter.getFilter("false").filter("R");
            rClicked = 1;
            editor.putString("rarityFilter", "r");
        } else {
            rFilterView.setBackgroundColor(Color.parseColor("#00000000"));
            adapter.getFilter("false").filter("resetRarity");
            rClicked = 0;
            checkSorts();
            editor.putString("rarityFilter", "none");
        }
        editor.apply();
        srFilterView.setBackgroundColor(Color.parseColor("#00000000"));
        ssrFilterView.setBackgroundColor(Color.parseColor("#00000000"));
        srClicked = 0;
        ssrClicked = 0;
        checkSorts();
    }

    public void srFilter(View view) {
        if (srClicked == 0) {
            srFilterView.setBackgroundColor(Color.parseColor("#4D15C2A6"));
            adapter.getFilter("false").filter("SR");
            srClicked = 1;
            editor.putString("rarityFilter", "sr");
        } else {
            srFilterView.setBackgroundColor(Color.parseColor("#00000000"));
            adapter.getFilter("false").filter("resetRarity");
            srClicked = 0;
            editor.putString("rarityFilter", "none");
        }
        editor.apply();
        rFilterView.setBackgroundColor(Color.parseColor("#00000000"));
        ssrFilterView.setBackgroundColor(Color.parseColor("#00000000"));
        rClicked = 0;
        ssrClicked = 0;
        checkSorts();
    }

    public void ssrFilter(View view) {
        if (ssrClicked == 0) {
            ssrFilterView.setBackgroundColor(Color.parseColor("#4D15C2A6"));
            adapter.getFilter("false").filter("SSR");
            ssrClicked = 1;
            editor.putString("rarityFilter", "ssr");
        } else {
            ssrFilterView.setBackgroundColor(Color.parseColor("#00000000"));
            adapter.getFilter("false").filter("resetRarity");
            ssrClicked = 0;
            editor.putString("rarityFilter", "none");
        }
        editor.apply();
        rFilterView.setBackgroundColor(Color.parseColor("#00000000"));
        srFilterView.setBackgroundColor(Color.parseColor("#00000000"));
        rClicked = 0;
        srClicked = 0;
        checkSorts();
    }

    public void humanFilter(View view) {
        if (humanClicked == 0) {
            humanFilterView.setBackgroundColor(Color.parseColor("#4D15C2A6"));
            adapter.getFilter("false").filter("Human");
            humanClicked = 1;
            editor.putString("raceFilter", "human");
        } else {
            humanFilterView.setBackgroundColor(Color.parseColor("#00000000"));
            adapter.getFilter("false").filter("resetRace");
            humanClicked = 0;
            editor.putString("raceFilter", "none");
        }
        editor.apply();
        fairyFilterView.setBackgroundColor(Color.parseColor("#00000000"));
        demonFilterView.setBackgroundColor(Color.parseColor("#00000000"));
        goddessFilterView.setBackgroundColor(Color.parseColor("#00000000"));
        giantFilterView.setBackgroundColor(Color.parseColor("#00000000"));
        unknownFilterView.setBackgroundColor(Color.parseColor("#00000000"));
        fairyClicked = 0;
        demonClicked = 0;
        goddessClicked = 0;
        giantClicked = 0;
        unknownClicked = 0;
        checkSorts();
    }

    public void fairyFilter(View view) {
        if (fairyClicked == 0) {
            fairyFilterView.setBackgroundColor(Color.parseColor("#4D15C2A6"));
            adapter.getFilter("false").filter("Fairy");
            fairyClicked = 1;
            editor.putString("raceFilter", "fairy");
        } else {
            fairyFilterView.setBackgroundColor(Color.parseColor("#00000000"));
            adapter.getFilter("false").filter("resetRace");
            fairyClicked = 0;
            editor.putString("raceFilter", "none");
        }
        editor.apply();
        humanFilterView.setBackgroundColor(Color.parseColor("#00000000"));
        demonFilterView.setBackgroundColor(Color.parseColor("#00000000"));
        goddessFilterView.setBackgroundColor(Color.parseColor("#00000000"));
        giantFilterView.setBackgroundColor(Color.parseColor("#00000000"));
        unknownFilterView.setBackgroundColor(Color.parseColor("#00000000"));
        humanClicked = 0;
        demonClicked = 0;
        goddessClicked = 0;
        giantClicked = 0;
        unknownClicked = 0;
        checkSorts();
    }

    public void demonFilter(View view) {
        if (demonClicked == 0) {
            demonFilterView.setBackgroundColor(Color.parseColor("#4D15C2A6"));
            adapter.getFilter("false").filter("Demon");
            demonClicked = 1;
            editor.putString("raceFilter", "demon");
        } else {
            demonFilterView.setBackgroundColor(Color.parseColor("#00000000"));
            adapter.getFilter("false").filter("resetRace");
            demonClicked = 0;
            editor.putString("raceFilter", "none");
        }
        editor.apply();
        humanFilterView.setBackgroundColor(Color.parseColor("#00000000"));
        fairyFilterView.setBackgroundColor(Color.parseColor("#00000000"));
        goddessFilterView.setBackgroundColor(Color.parseColor("#00000000"));
        giantFilterView.setBackgroundColor(Color.parseColor("#00000000"));
        unknownFilterView.setBackgroundColor(Color.parseColor("#00000000"));
        humanClicked = 0;
        fairyClicked = 0;
        goddessClicked = 0;
        giantClicked = 0;
        unknownClicked = 0;
        checkSorts();
    }

    public void goddessFilter(View view) {
        if (goddessClicked == 0) {
            goddessFilterView.setBackgroundColor(Color.parseColor("#4D15C2A6"));
            adapter.getFilter("false").filter("Goddess");
            goddessClicked = 1;
            editor.putString("raceFilter", "goddess");
        } else {
            goddessFilterView.setBackgroundColor(Color.parseColor("#00000000"));
            adapter.getFilter("false").filter("resetRace");
            goddessClicked = 0;
            editor.putString("raceFilter", "none");
        }
        editor.apply();
        humanFilterView.setBackgroundColor(Color.parseColor("#00000000"));
        fairyFilterView.setBackgroundColor(Color.parseColor("#00000000"));
        demonFilterView.setBackgroundColor(Color.parseColor("#00000000"));
        giantFilterView.setBackgroundColor(Color.parseColor("#00000000"));
        unknownFilterView.setBackgroundColor(Color.parseColor("#00000000"));
        humanClicked = 0;
        fairyClicked = 0;
        demonClicked = 0;
        giantClicked = 0;
        unknownClicked = 0;
        checkSorts();
    }

    public void giantFilter(View view) {
        if (giantClicked == 0) {
            giantFilterView.setBackgroundColor(Color.parseColor("#4D15C2A6"));
            adapter.getFilter("false").filter("Giant");
            giantClicked = 1;
            editor.putString("raceFilter", "giant");
        } else {
            giantFilterView.setBackgroundColor(Color.parseColor("#00000000"));
            adapter.getFilter("false").filter("resetRace");
            giantClicked = 0;
            editor.putString("raceFilter", "none");
        }
        editor.apply();
        humanFilterView.setBackgroundColor(Color.parseColor("#00000000"));
        fairyFilterView.setBackgroundColor(Color.parseColor("#00000000"));
        demonFilterView.setBackgroundColor(Color.parseColor("#00000000"));
        goddessFilterView.setBackgroundColor(Color.parseColor("#00000000"));
        unknownFilterView.setBackgroundColor(Color.parseColor("#00000000"));
        humanClicked = 0;
        fairyClicked = 0;
        demonClicked = 0;
        goddessClicked = 0;
        unknownClicked = 0;
        checkSorts();
    }

    public void unknownFilter(View view) {
        if (unknownClicked == 0) {
            unknownFilterView.setBackgroundColor(Color.parseColor("#4D15C2A6"));
            adapter.getFilter("false").filter("Unknown");
            unknownClicked = 1;
            editor.putString("raceFilter", "unknown");
        } else {
            unknownFilterView.setBackgroundColor(Color.parseColor("#00000000"));
            adapter.getFilter("false").filter("resetRace");
            unknownClicked = 0;
            editor.putString("raceFilter", "none");
        }
        editor.apply();
        humanFilterView.setBackgroundColor(Color.parseColor("#00000000"));
        fairyFilterView.setBackgroundColor(Color.parseColor("#00000000"));
        demonFilterView.setBackgroundColor(Color.parseColor("#00000000"));
        goddessFilterView.setBackgroundColor(Color.parseColor("#00000000"));
        giantFilterView.setBackgroundColor(Color.parseColor("#00000000"));
        humanClicked = 0;
        fairyClicked = 0;
        demonClicked = 0;
        goddessClicked = 0;
        giantClicked = 0;
        checkSorts();
    }

    public void storyFilter(View view) {
        if (storyClicked == 0) {
            storyFilterView.setBackgroundColor(Color.parseColor("#4D15C2A6"));
            adapter.getFilter("false").filter("Story");
            storyClicked = 1;
            editor.putString("obtainedFilter", "story");
        } else {
            storyFilterView.setBackgroundColor(Color.parseColor("#00000000"));
            adapter.getFilter("false").filter("resetObtained");
            storyClicked = 0;
            editor.putString("obtainedFilter", "none");
        }
        editor.apply();
        coinShopFilterView.setBackgroundColor(Color.parseColor("#00000000"));
        normalGachaFilterView.setBackgroundColor(Color.parseColor("#00000000"));
        limitedGachaFilterView.setBackgroundColor(Color.parseColor("#00000000"));
        festivalGachaFilterView.setBackgroundColor(Color.parseColor("#00000000"));
        collabFilterView.setBackgroundColor(Color.parseColor("#00000000"));
        pvpFilterView.setBackgroundColor(Color.parseColor("#00000000"));
        coinShopClicked = 0;
        normalGachaClicked = 0;
        limitedGachaClicked = 0;
        festivalGachaClicked = 0;
        collabClicked = 0;
        pvpClicked = 0;
        checkSorts();
    }

    public void coinShopFilter(View view) {
        if (coinShopClicked == 0) {
            coinShopFilterView.setBackgroundColor(Color.parseColor("#4D15C2A6"));
            adapter.getFilter("false").filter("Coin Shop");
            coinShopClicked = 1;
            editor.putString("obtainedFilter", "coinShop");
        } else {
            coinShopFilterView.setBackgroundColor(Color.parseColor("#00000000"));
            adapter.getFilter("false").filter("resetObtained");
            coinShopClicked = 0;
            editor.putString("obtainedFilter", "none");
        }
        editor.apply();
        storyFilterView.setBackgroundColor(Color.parseColor("#00000000"));
        normalGachaFilterView.setBackgroundColor(Color.parseColor("#00000000"));
        limitedGachaFilterView.setBackgroundColor(Color.parseColor("#00000000"));
        festivalGachaFilterView.setBackgroundColor(Color.parseColor("#00000000"));
        collabFilterView.setBackgroundColor(Color.parseColor("#00000000"));
        pvpFilterView.setBackgroundColor(Color.parseColor("#00000000"));
        storyClicked = 0;
        normalGachaClicked = 0;
        limitedGachaClicked = 0;
        festivalGachaClicked = 0;
        collabClicked = 0;
        pvpClicked = 0;
        checkSorts();
    }

    public void normalGachaFilter(View view) {
        if (normalGachaClicked == 0) {
            normalGachaFilterView.setBackgroundColor(Color.parseColor("#4D15C2A6"));
            adapter.getFilter("false").filter("Normal Gacha");
            normalGachaClicked = 1;
            editor.putString("obtainedFilter", "normalGacha");
        } else {
            normalGachaFilterView.setBackgroundColor(Color.parseColor("#00000000"));
            adapter.getFilter("false").filter("resetObtained");
            normalGachaClicked = 0;
            editor.putString("obtainedFilter", "none");
        }
        editor.apply();
        storyFilterView.setBackgroundColor(Color.parseColor("#00000000"));
        coinShopFilterView.setBackgroundColor(Color.parseColor("#00000000"));
        limitedGachaFilterView.setBackgroundColor(Color.parseColor("#00000000"));
        festivalGachaFilterView.setBackgroundColor(Color.parseColor("#00000000"));
        collabFilterView.setBackgroundColor(Color.parseColor("#00000000"));
        pvpFilterView.setBackgroundColor(Color.parseColor("#00000000"));
        storyClicked = 0;
        coinShopClicked = 0;
        limitedGachaClicked = 0;
        festivalGachaClicked = 0;
        collabClicked = 0;
        pvpClicked = 0;
        checkSorts();
    }

    public void limitedGachaFilter(View view) {
        if (limitedGachaClicked == 0) {
            limitedGachaFilterView.setBackgroundColor(Color.parseColor("#4D15C2A6"));
            adapter.getFilter("false").filter("Limited Gacha");
            limitedGachaClicked = 1;
            editor.putString("obtainedFilter", "limitedGacha");
        } else {
            limitedGachaFilterView.setBackgroundColor(Color.parseColor("#00000000"));
            adapter.getFilter("false").filter("resetObtained");
            limitedGachaClicked = 0;
            editor.putString("obtainedFilter", "none");
        }
        editor.apply();
        storyFilterView.setBackgroundColor(Color.parseColor("#00000000"));
        coinShopFilterView.setBackgroundColor(Color.parseColor("#00000000"));
        normalGachaFilterView.setBackgroundColor(Color.parseColor("#00000000"));
        festivalGachaFilterView.setBackgroundColor(Color.parseColor("#00000000"));
        collabFilterView.setBackgroundColor(Color.parseColor("#00000000"));
        pvpFilterView.setBackgroundColor(Color.parseColor("#00000000"));
        storyClicked = 0;
        coinShopClicked = 0;
        normalGachaClicked = 0;
        festivalGachaClicked = 0;
        collabClicked = 0;
        pvpClicked = 0;
        checkSorts();
    }

    public void festivalGachaFilter(View view) {
        if (festivalGachaClicked == 0) {
            festivalGachaFilterView.setBackgroundColor(Color.parseColor("#4D15C2A6"));
            adapter.getFilter("false").filter("Festival Gacha");
            festivalGachaClicked = 1;
            editor.putString("obtainedFilter", "festivalGacha");
        } else {
            festivalGachaFilterView.setBackgroundColor(Color.parseColor("#00000000"));
            adapter.getFilter("false").filter("resetObtained");
            festivalGachaClicked = 0;
            editor.putString("obtainedFilter", "none");
        }
        editor.apply();
        storyFilterView.setBackgroundColor(Color.parseColor("#00000000"));
        coinShopFilterView.setBackgroundColor(Color.parseColor("#00000000"));
        normalGachaFilterView.setBackgroundColor(Color.parseColor("#00000000"));
        limitedGachaFilterView.setBackgroundColor(Color.parseColor("#00000000"));
        collabFilterView.setBackgroundColor(Color.parseColor("#00000000"));
        pvpFilterView.setBackgroundColor(Color.parseColor("#00000000"));
        storyClicked = 0;
        coinShopClicked = 0;
        normalGachaClicked = 0;
        limitedGachaClicked = 0;
        collabClicked = 0;
        pvpClicked = 0;
        checkSorts();
    }

    public void collabFilter(View view) {
        if (collabClicked == 0) {
            collabFilterView.setBackgroundColor(Color.parseColor("#4D15C2A6"));
            adapter.getFilter("false").filter("Collab");
            collabClicked = 1;
            editor.putString("obtainedFilter", "collab");
        } else {
            collabFilterView.setBackgroundColor(Color.parseColor("#00000000"));
            adapter.getFilter("false").filter("resetObtained");
            collabClicked = 0;
            editor.putString("obtainedFilter", "none");
        }
        editor.apply();
        storyFilterView.setBackgroundColor(Color.parseColor("#00000000"));
        coinShopFilterView.setBackgroundColor(Color.parseColor("#00000000"));
        normalGachaFilterView.setBackgroundColor(Color.parseColor("#00000000"));
        limitedGachaFilterView.setBackgroundColor(Color.parseColor("#00000000"));
        festivalGachaFilterView.setBackgroundColor(Color.parseColor("#00000000"));
        pvpFilterView.setBackgroundColor(Color.parseColor("#00000000"));
        storyClicked = 0;
        coinShopClicked = 0;
        normalGachaClicked = 0;
        limitedGachaClicked = 0;
        festivalGachaClicked = 0;
        pvpClicked = 0;
        checkSorts();
    }

    public void pvpFilter(View view) {
        if (pvpClicked == 0) {
            pvpFilterView.setBackgroundColor(Color.parseColor("#4D15C2A6"));
            adapter.getFilter("false").filter("Arena Reward");
            pvpClicked = 1;

            editor.putString("obtainedFilter", "pvp");
        } else {
            pvpFilterView.setBackgroundColor(Color.parseColor("#00000000"));
            adapter.getFilter("false").filter("resetObtained");
            pvpClicked = 0;
            editor.putString("obtainedFilter", "none");
        }
        editor.apply();
        storyFilterView.setBackgroundColor(Color.parseColor("#00000000"));
        coinShopFilterView.setBackgroundColor(Color.parseColor("#00000000"));
        normalGachaFilterView.setBackgroundColor(Color.parseColor("#00000000"));
        limitedGachaFilterView.setBackgroundColor(Color.parseColor("#00000000"));
        festivalGachaFilterView.setBackgroundColor(Color.parseColor("#00000000"));
        collabFilterView.setBackgroundColor(Color.parseColor("#00000000"));
        storyClicked = 0;
        coinShopClicked = 0;
        normalGachaClicked = 0;
        limitedGachaClicked = 0;
        festivalGachaClicked = 0;
        collabClicked = 0;
        checkSorts();
    }


    public void resetFilter(View view) {
        resetFilters();
    }
    public void resetFilters() {
        strengthFilterView.setBackgroundColor(Color.parseColor("#00000000"));
        hpFilterView.setBackgroundColor(Color.parseColor("#00000000"));
        speedFilterView.setBackgroundColor(Color.parseColor("#00000000"));
        globalFilterView.setBackgroundColor(Color.parseColor("#00000000"));
        jpFilterView.setBackgroundColor(Color.parseColor("#00000000"));
        jpOnlyFilterView.setBackgroundColor(Color.parseColor("#00000000"));
        rFilterView.setBackgroundColor(Color.parseColor("#00000000"));
        srFilterView.setBackgroundColor(Color.parseColor("#00000000"));
        ssrFilterView.setBackgroundColor(Color.parseColor("#00000000"));
        storyFilterView.setBackgroundColor(Color.parseColor("#00000000"));
        coinShopFilterView.setBackgroundColor(Color.parseColor("#00000000"));
        normalGachaFilterView.setBackgroundColor(Color.parseColor("#00000000"));
        limitedGachaFilterView.setBackgroundColor(Color.parseColor("#00000000"));
        festivalGachaFilterView.setBackgroundColor(Color.parseColor("#00000000"));
        collabFilterView.setBackgroundColor(Color.parseColor("#00000000"));
        pvpFilterView.setBackgroundColor(Color.parseColor("#00000000"));
        humanFilterView.setBackgroundColor(Color.parseColor("#00000000"));
        fairyFilterView.setBackgroundColor(Color.parseColor("#00000000"));
        demonFilterView.setBackgroundColor(Color.parseColor("#00000000"));
        goddessFilterView.setBackgroundColor(Color.parseColor("#00000000"));
        giantFilterView.setBackgroundColor(Color.parseColor("#00000000"));
        unknownFilterView.setBackgroundColor(Color.parseColor("#00000000"));
        filterCharacters.setText("");
        adapter.getFilter("false").filter("fullyReset");
        speedClicked = 0;
        hpClicked = 0;
        strengthClicked = 0;
        glbClicked = 0;
        jpClicked = 0;
        jpOnlyClicked = 0;
        rClicked = 0;
        srClicked = 0;
        ssrClicked = 0;
        storyClicked = 0;
        coinShopClicked = 0;
        normalGachaClicked = 0;
        limitedGachaClicked = 0;
        festivalGachaClicked = 0;
        collabClicked = 0;
        pvpClicked = 0;
        humanClicked = 0;
        fairyClicked = 0;
        demonClicked = 0;
        goddessClicked = 0;
        giantClicked = 0;
        unknownClicked = 0;
        editor.putString("typeFilter", "none");
        editor.putString("regionFilter", "none");
        editor.putString("rarityFilter", "none");
        editor.putString("raceFilter", "none");
        editor.putString("obtainedFilter", "none");
        editor.putString("charSearch", "");
        editor.putString("sortedCC", "");
        editor.putString("sortedHP", "");
        editor.putString("sortedATK", "");
        editor.putString("sortedDEF", "");
        editor.putString("sortBy", "");
        editor.apply();
    }

    protected void onRestart() {
        super.onRestart();
        Log.v("test", "restart");
        darkMode = settings.getString("darkMode", darkMode);
        if (darkMode.equals("yes")) {
            backgroundColor.setBackgroundColor(Color.parseColor("#616161"));
        } else {
            backgroundColor.setBackgroundColor(Color.parseColor("#4D15C2A6"));
        }
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
            Intent help = new Intent(character_list.this, help_menu.class);
            startActivity(help);
        }
        return super.onOptionsItemSelected(item);
    }

    public void showFilter(View view) {
        filterView.toggle();
    }

    public void onResume() {
        if (adapter != null) {
            settings = getSharedPreferences(PREFS_NAME, 0);
            editor = settings.edit();
            redoFilters();
        } else {
            final Handler handler = new Handler();
            handler.postDelayed(this::redoFilters, 20);
        }
        super.onResume();
    }

    public void redoFilters() {
        type = settings.getString("typeFilter", type);
        region = settings.getString("regionFilter", region);
        rarity = settings.getString("rarityFilter", rarity);
        race = settings.getString("raceFilter", race);
        obtained = settings.getString("obtainedFilter", obtained);
        Log.v("type", " " + type);
        if (listClicked != 0) {
            switch (type) {
                case "strength":
                    strengthFilterView.setBackgroundColor(Color.parseColor("#4D15C2A6"));
                    adapter.getFilter("false").filter("Strength");
                    strengthClicked = 1;
                    break;
                case "hp":
                    hpFilterView.setBackgroundColor(Color.parseColor("#4D15C2A6"));
                    adapter.getFilter("false").filter("HP");
                    hpClicked = 1;
                    break;
                case "speed":
                    speedFilterView.setBackgroundColor(Color.parseColor("#4D15C2A6"));
                    adapter.getFilter("false").filter("Speed");
                    speedClicked = 1;
                    break;
                case "none":
                    break;
            }
            switch (region) {
                case "global":
                    adapter.getFilter("false").filter("Global / Asia");
                    globalFilterView.setBackgroundColor(Color.parseColor("#4D15C2A6"));
                    glbClicked = 1;
                    break;
                case "jp":
                    adapter.getFilter("false").filter("Japan / Korea");
                    jpFilterView.setBackgroundColor(Color.parseColor("#4D15C2A6"));
                    jpClicked = 1;
                    break;
                case "jpOnly":
                    adapter.getFilter("false").filter("Japan / Korea Exclusive");
                    jpOnlyFilterView.setBackgroundColor(Color.parseColor("#4D15C2A6"));
                    jpOnlyClicked = 1;
                    break;
                case "none":
                    break;
            }
            switch (rarity) {
                case "r":
                    Log.v("rarity", " " + rarity);
                    adapter.getFilter("false").filter("R");
                    rFilterView.setBackgroundColor(Color.parseColor("#4D15C2A6"));
                    rClicked = 1;
                    break;
                case "sr":
                    adapter.getFilter("false").filter("SR");
                    srFilterView.setBackgroundColor(Color.parseColor("#4D15C2A6"));
                    srClicked = 1;
                    break;
                case "ssr":
                    adapter.getFilter("false").filter("SSR");
                    ssrFilterView.setBackgroundColor(Color.parseColor("#4D15C2A6"));
                    ssrClicked = 1;
                    break;
                case "none":
                    break;
            }
            switch (race) {
                case "human":
                    adapter.getFilter("false").filter("Human");
                    humanFilterView.setBackgroundColor(Color.parseColor("#4D15C2A6"));
                    humanClicked = 1;
                    break;
                case "fairy":
                    adapter.getFilter("false").filter("Fairy");
                    fairyFilterView.setBackgroundColor(Color.parseColor("#4D15C2A6"));
                    fairyClicked = 1;
                    break;
                case "demon":
                    adapter.getFilter("false").filter("Demon");
                    demonFilterView.setBackgroundColor(Color.parseColor("#4D15C2A6"));
                    demonClicked = 1;
                    break;
                case "goddess":
                    adapter.getFilter("false").filter("Goddess");
                    goddessFilterView.setBackgroundColor(Color.parseColor("#4D15C2A6"));
                    goddessClicked = 1;
                    break;
                case "giant":
                    adapter.getFilter("false").filter("Giant");
                    giantFilterView.setBackgroundColor(Color.parseColor("#4D15C2A6"));
                    giantClicked = 1;
                    break;
                case "unknown":
                    adapter.getFilter("false").filter("Unknown");
                    unknownFilterView.setBackgroundColor(Color.parseColor("#4D15C2A6"));
                    unknownClicked = 1;
                    break;
                case "none":
                    break;
            }
            switch (obtained) {
                case "story":
                    adapter.getFilter("false").filter("Story");
                    storyFilterView.setBackgroundColor(Color.parseColor("#4D15C2A6"));
                    storyClicked = 1;
                    break;
                case "coinShop":
                    adapter.getFilter("false").filter("Coin Shop");
                    coinShopFilterView.setBackgroundColor(Color.parseColor("#4D15C2A6"));
                    coinShopClicked = 1;
                    break;
                case "normalGacha":
                    adapter.getFilter("false").filter("Normal Gacha");
                    normalGachaFilterView.setBackgroundColor(Color.parseColor("#4D15C2A6"));
                    normalGachaClicked = 1;
                    break;
                case "limitedGacha":
                    adapter.getFilter("false").filter("Limited Gacha");
                    limitedGachaFilterView.setBackgroundColor(Color.parseColor("#4D15C2A6"));
                    limitedGachaClicked = 1;
                    break;
                case "festivalGacha":
                    adapter.getFilter("false").filter("Festival Gacha");
                    festivalGachaFilterView.setBackgroundColor(Color.parseColor("#4D15C2A6"));
                    festivalGachaClicked = 1;
                    break;
                case "collab":
                    adapter.getFilter("false").filter("Collab");
                    collabFilterView.setBackgroundColor(Color.parseColor("#4D15C2A6"));
                    collabClicked = 1;
                    break;
                case "pvp":
                    adapter.getFilter("false").filter("Arena Reward");
                    pvpFilterView.setBackgroundColor(Color.parseColor("#4D15C2A6"));
                    pvpClicked = 1;
                    break;
                case "none":
                    break;
            }
            int ccNumber = settings.getInt("sortedCC", 0);
            int hpNumber = settings.getInt("sortedHP", 0);
            int atkNumber = settings.getInt("sortedATK", 0);
            int defNumber = settings.getInt("sortedDEF", 0);
            String sortBy = settings.getString("sortBy", "");
            switch (sortBy) {
                case "CC":
                    sortedCC = ccNumber;
                    ccSorter();
                    break;
                case "HP":
                    sortedHP = hpNumber;
                    hpSorter();
                    break;
                case "ATK":
                    sortedATK = atkNumber;
                    atkSorter();
                    break;
                case "DEF":
                    sortedDEF = defNumber;
                    defSorter();
                    break;
                case "" :
                    break;
            }
            editText = settings.getString("charSearch", "");
            filterCharacters.setText(editText);
            adapter.getFilter("true").filter(editText);
            editor.putInt("listClicked", 0);
            editor.apply();
        }
    }
}
