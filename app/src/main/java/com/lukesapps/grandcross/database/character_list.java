package com.lukesapps.grandcross.database;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import com.github.aakira.expandablelayout.ExpandableLinearLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.mopub.mobileads.MoPubView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

public class character_list extends AppCompatActivity {
    MoPubSdk moPubSdk;
    LinearLayout adViewHolder;
    MoPubView moPubView;
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
    int loadedAd = 0;

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
        strengthFilterView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                makeToast("Strength (Red).");
                return true;
            }
        });
        hpFilterView = findViewById(R.id.hpFilter);
        hpFilterView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                makeToast("HP (Green).");
                return true;
            }
        });
        speedFilterView = findViewById(R.id.speedFilter);
        speedFilterView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                makeToast("Speed (Blue).");
                return true;
            }
        });
        globalFilterView = findViewById(R.id.globalFilter);
        globalFilterView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                makeToast("Global / Asia Units.");
                return true;
            }
        });
        jpFilterView = findViewById(R.id.jpFilter);
        jpFilterView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                makeToast("Japan / Korea Units.");
                return true;
            }
        });
        jpOnlyFilterView = findViewById(R.id.jpOnlyFilter);
        jpOnlyFilterView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                makeToast("Japan / Korea Only Units.\n(Not on global yet)");
                return true;
            }
        });
        rFilterView = findViewById(R.id.rFilter);
        rFilterView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                makeToast("Rare (R) Units.");
                return true;
            }
        });
        srFilterView = findViewById(R.id.srFilter);
        srFilterView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                makeToast("Super Rare (SR) Units.");
                return true;
            }
        });
        ssrFilterView = findViewById(R.id.ssrFilter);
        ssrFilterView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                makeToast("Super Super Rare (SSR) Units.");
                return true;
            }
        });
        humanFilterView = findViewById(R.id.humanFilter);
        humanFilterView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                makeToast("Human Race Units.");
                return true;
            }
        });
        fairyFilterView = findViewById(R.id.fairyFilter);
        fairyFilterView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                makeToast("Fairy Race Units.");
                return true;
            }
        });
        demonFilterView = findViewById(R.id.demonFilter);
        demonFilterView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                makeToast("Demon Race Units.");
                return true;
            }
        });
        goddessFilterView = findViewById(R.id.goddessFilter);
        goddessFilterView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                makeToast("Goddess Race Units.");
                return true;
            }
        });
        giantFilterView = findViewById(R.id.giantFilter);
        giantFilterView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                makeToast("Giant Race Units.");
                return true;
            }
        });
        unknownFilterView = findViewById(R.id.unknownFilter);
        unknownFilterView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                makeToast("Unknown Race Units.");
                return true;
            }
        });
        storyFilterView = findViewById(R.id.storyFilter);
        storyFilterView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                makeToast("Units obtainable from Story.\n(Includes Characters obtained from Missions, and as drops)\n(Characters might only be obtainable from story on one region)");
                return true;
            }
        });
        coinShopFilterView = findViewById(R.id.coinShopFilter);
        coinShopFilterView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                makeToast("Units obtainable from The Coin Shop.\n(Only shows permanent Coin Shop SSR units)");
                return true;
            }
        });
        normalGachaFilterView = findViewById(R.id.normalGachaFilter);
        normalGachaFilterView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                makeToast("Units obtainable from the General Pool in Banners.\n(Not all units are put on every banner)\n(Units are usually added to SSR Tickets)");
                return true;
            }
        });
        limitedGachaFilterView = findViewById(R.id.limitedGachaFilter);
        limitedGachaFilterView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                makeToast("Units obtainable from Limited Banners Only.\n(Units may never return again, but likely will return once a year)");
                return true;
            }
        });
        festivalGachaFilterView = findViewById(R.id.festivalGachaFilter);
        festivalGachaFilterView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                makeToast("Units obtainable from Festival Banners Only.\n(As of 13/06/2020, all Festival Units have been added to other Festival Banner)");
                return true;
            }
        });
        collabFilterView = findViewById(R.id.collabFilter);
        collabFilterView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                makeToast("Units obtainable from Collaborations.\n(These units will likely never be available once their original banner ends)");
                return true;
            }
        });
        pvpFilterView = findViewById(R.id.pvpFilter);
        pvpFilterView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                makeToast("Units obtainable as PvP Rewards.");
                return true;
            }
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
                    adapter.getFilter("true").filter(s.toString());
                }
                checkSorts();
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        backgroundColor = findViewById(R.id.linearlayoutlist);
        settings = getSharedPreferences(PREFS_NAME, 0);
        adViewHolder = findViewById(R.id.adViewHolder);
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                moPubSdk = MoPubSdk.getInstance(character_list.this);
                moPubSdk.isMoPubSdkInitialized().observe(character_list.this, new Observer<Boolean>() {
                    @Override
                    public void onChanged(Boolean aBoolean) {
                        if (aBoolean) {
                            moPubSdk.callAdView(adViewHolder);
                        }
                    }
                });
            }
        }, 1500);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectId = characterStatsList.get(position).getSelectId();
                Intent intent = new Intent(character_list.this, character_page.class);
                intent.putExtra("selectId", selectId);
                startActivity(intent);
            }
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
        if (sortedCC == 0 || sortedCC == 2) {
            Collections.sort(characterStatsList, (o1, o2) -> o2.getMaxCC().compareTo(o1.getMaxCC()));
            sortedCC = 1;
        } else {
            Collections.sort(characterStatsList, (o1, o2) -> o1.getMaxCC().compareTo(o2.getMaxCC()));
            sortedCC = 2;
        }
        adapter.notifyDataSetChanged();
        sortedHP = 0;
        sortedATK = 0;
        sortedDEF = 0;
    }

    public void sortHP(View view) {
        if (sortedHP == 0 || sortedHP == 2) {
            Collections.sort(characterStatsList, (o1, o2) -> o2.getMaxHealth().compareTo(o1.getMaxHealth()));
            sortedHP = 1;
        } else {
            Collections.sort(characterStatsList, (o1, o2) -> o1.getMaxHealth().compareTo(o2.getMaxHealth()));
            sortedHP = 2;
        }
        adapter.notifyDataSetChanged();
        sortedCC = 0;
        sortedATK = 0;
        sortedDEF = 0;
    }

    public void sortATK(View view) {
        if (sortedATK == 0 || sortedATK == 2) {
            Collections.sort(characterStatsList, (o1, o2) -> o2.getMaxAttack().compareTo(o1.getMaxAttack()));
            sortedATK = 1;
        } else {
            Collections.sort(characterStatsList, (o1, o2) -> o1.getMaxAttack().compareTo(o2.getMaxAttack()));
            sortedATK = 2;
        }
        adapter.notifyDataSetChanged();
        sortedCC = 0;
        sortedHP = 0;
        sortedDEF = 0;
    }

    public void sortDEF(View view) {
        if (sortedDEF == 0 || sortedDEF == 2) {
            Collections.sort(characterStatsList, (o1, o2) -> o2.getMaxDefense().compareTo(o1.getMaxDefense()));
            sortedDEF = 1;
        } else {
            Collections.sort(characterStatsList, (o1, o2) -> o1.getMaxDefense().compareTo(o2.getMaxDefense()));
            sortedDEF = 2;
        }
        adapter.notifyDataSetChanged();
        sortedCC = 0;
        sortedHP = 0;
        sortedATK = 0;
    }

    public void checkSorts() {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
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
            }
        }, 20);
    }


    public void strengthFilter(View view) {
        if (strengthClicked == 0) {
            strengthFilterView.setBackgroundColor(Color.parseColor("#4D15C2A6"));
            adapter.getFilter("false").filter("Strength");
            strengthClicked = 1;
        } else {
            strengthFilterView.setBackgroundColor(Color.parseColor("#00000000"));
            adapter.getFilter("false").filter("resetAttribute");
            strengthClicked = 0;
        }
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
        } else {
            hpFilterView.setBackgroundColor(Color.parseColor("#00000000"));
            adapter.getFilter("false").filter("resetAttribute");
            hpClicked = 0;
        }
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
        } else {
            speedFilterView.setBackgroundColor(Color.parseColor("#00000000"));
            adapter.getFilter("false").filter("resetAttribute");
            speedClicked = 0;
        }
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
        } else {
            globalFilterView.setBackgroundColor(Color.parseColor("#00000000"));
            adapter.getFilter("false").filter("resetServer");
            glbClicked = 0;
        }
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
        } else {
            jpFilterView.setBackgroundColor(Color.parseColor("#00000000"));
            adapter.getFilter("false").filter("resetServer");
            jpClicked = 0;
        }
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
        } else {
            jpOnlyFilterView.setBackgroundColor(Color.parseColor("#00000000"));
            adapter.getFilter("false").filter("resetServer");
            jpOnlyClicked = 0;
        }
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
        } else {
            rFilterView.setBackgroundColor(Color.parseColor("#00000000"));
            adapter.getFilter("false").filter("resetRarity");
            rClicked = 0;
        }
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
        } else {
            srFilterView.setBackgroundColor(Color.parseColor("#00000000"));
            adapter.getFilter("false").filter("resetRarity");
            srClicked = 0;
        }
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
        } else {
            ssrFilterView.setBackgroundColor(Color.parseColor("#00000000"));
            adapter.getFilter("false").filter("resetRarity");
            ssrClicked = 0;
        }
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
        } else {
            humanFilterView.setBackgroundColor(Color.parseColor("#00000000"));
            adapter.getFilter("false").filter("resetRace");
            humanClicked = 0;
        }
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
        } else {
            fairyFilterView.setBackgroundColor(Color.parseColor("#00000000"));
            adapter.getFilter("false").filter("resetRace");
            fairyClicked = 0;
        }
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
        } else {
            demonFilterView.setBackgroundColor(Color.parseColor("#00000000"));
            adapter.getFilter("false").filter("resetRace");
            demonClicked = 0;
        }
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
        } else {
            goddessFilterView.setBackgroundColor(Color.parseColor("#00000000"));
            adapter.getFilter("false").filter("resetRace");
            goddessClicked = 0;
        }
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
        } else {
            giantFilterView.setBackgroundColor(Color.parseColor("#00000000"));
            adapter.getFilter("false").filter("resetRace");
            giantClicked = 0;
        }
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
        } else {
            unknownFilterView.setBackgroundColor(Color.parseColor("#00000000"));
            adapter.getFilter("false").filter("resetRace");
            unknownClicked = 0;
        }
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
        } else {
            storyFilterView.setBackgroundColor(Color.parseColor("#00000000"));
            adapter.getFilter("false").filter("resetObtained");
            storyClicked = 0;
        }
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
        } else {
            coinShopFilterView.setBackgroundColor(Color.parseColor("#00000000"));
            adapter.getFilter("false").filter("resetObtained");
            coinShopClicked = 0;
        }
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
        } else {
            normalGachaFilterView.setBackgroundColor(Color.parseColor("#00000000"));
            adapter.getFilter("false").filter("resetObtained");
            normalGachaClicked = 0;
        }
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
        } else {
            limitedGachaFilterView.setBackgroundColor(Color.parseColor("#00000000"));
            adapter.getFilter("false").filter("resetObtained");
            limitedGachaClicked = 0;
        }
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
        } else {
            festivalGachaFilterView.setBackgroundColor(Color.parseColor("#00000000"));
            adapter.getFilter("false").filter("resetObtained");
            festivalGachaClicked = 0;
        }
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
        } else {
            collabFilterView.setBackgroundColor(Color.parseColor("#00000000"));
            adapter.getFilter("false").filter("resetObtained");
            collabClicked = 0;
        }
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
        } else {
            pvpFilterView.setBackgroundColor(Color.parseColor("#00000000"));
            adapter.getFilter("false").filter("resetObtained");
            pvpClicked = 0;
        }
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
    }

    protected void onRestart() {
        super.onRestart();
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


}
