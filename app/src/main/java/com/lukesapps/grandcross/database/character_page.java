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
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.github.aakira.expandablelayout.ExpandableLinearLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.util.Objects;

public class character_page extends AppCompatActivity {
    LinearLayout adViewHolder;
    TextView baseStatsButton;
    TextView maxStatsButton;
    String selectId;
    String selectedId;
    String type;
    String name;
    String minCC;
    String maxCC;
    String minHealth;
    String maxHealth;
    String minAttack;
    String maxAttack;
    String minDefence;
    String maxDefence;
    String minPierceRate;
    String maxPierceRate;
    String minResistance;
    String maxResistance;
    String minRegenRate;
    String maxRegenRate;
    String minCritChance;
    String maxCritChance;
    String minCritDamage;
    String maxCritDamage;
    String minCritRes;
    String maxCritRes;
    String minCritDef;
    String maxCritDef;
    String minRecoveryRate;
    String maxRecoveryRate;
    String minLifesteal;
    String maxLifesteal;
    String characterImage;
    String passiveName;
    String passiveDesc;
    String tpassiveName;
    String tpassiveDesc;
    String tpassiveImage;
    String commandment;
    String commandmentEffect;
    String blessing;
    String blessingEffect;
    String skill1Name;
    String skill1Desc1;
    String skill1Desc2;
    String skill1Desc3;
    String skill2Name;
    String skill2Desc1;
    String skill2Desc2;
    String skill2Desc3;
    String ultimateName;
    String ultimateDesc;
    String ultimateMulti;
    String tskill1Name;
    String tskill1Desc1;
    String tskill1Desc2;
    String tskill1Desc3;
    String tskill2Name;
    String tskill2Desc1;
    String tskill2Desc2;
    String tskill2Desc3;
    String tultimateName;
    String tultimateDesc;
    String tultimateMulti;
    String fateName;
    String fateDesc;
    String fateMulti;
    String awakening1HP;
    String awakening1CritRes;
    String awakening1RecRate;
    String awakening2Atk;
    String awakening2Def;
    String awakening2CritDmg;
    String awakening3HP;
    String awakening3RegenRate;
    String awakening3CritDef;
    String awakening4Atk;
    String awakening4Def;
    String awakening4CritChance;
    String awakening5HP;
    String awakening5Res;
    String awakening5Lifesteal;
    String awakening6Atk;
    String awakening6Def;
    String awakening6Pierce;
    String superawakening1HP;
    String superawakening1CritRes;
    String superawakening1RecRate;
    String superawakening2Atk;
    String superawakening2Def;
    String superawakening2CritDmg;
    String superawakening3HP;
    String superawakening3RegenRate;
    String superawakening3CritDef;
    String superawakening4Atk;
    String superawakening4Def;
    String superawakening4CritChance;
    String associate1;
    String associate1Effect;
    String associate1FullName1;
    String associate1Image1;
    String associate1ID1;
    String associate1FullName2;
    String associate1Image2;
    String associate1ID2;
    String associate1FullName3;
    String associate1Image3;
    String associate1ID3;
    String associate1FullName4;
    String associate1Image4;
    String associate1ID4;
    String associate2;
    String associate2Effect;
    String associate2FullName1;
    String associate2Image1;
    String associate2ID1;
    String associate2FullName2;
    String associate2Image2;
    String associate2ID2;
    String associate2FullName3;
    String associate2Image3;
    String associate2ID3;
    String associate2FullName4;
    String associate2Image4;
    String associate2ID4;
    String associate3;
    String associate3Effect;
    String associate3FullName1;
    String associate3Image1;
    String associate3ID1;
    String associate3FullName2;
    String associate3Image2;
    String associate3ID2;
    String associate3FullName3;
    String associate3Image3;
    String associate3ID3;
    String associate3FullName4;
    String associate3Image4;
    String associate3ID4;
    String associate4;
    String associate4Effect;
    String associate4FullName1;
    String associate4Image1;
    String associate4ID1;
    String associate4FullName2;
    String associate4Image2;
    String associate4ID2;
    String associate4FullName3;
    String associate4Image3;
    String associate4ID3;
    String associate4FullName4;
    String associate4Image4;
    String associate4ID4;
    String associate5;
    String associate5Effect;
    String associate5FullName1;
    String associate5Image1;
    String associate5ID1;
    String associate5FullName2;
    String associate5Image2;
    String associate5ID2;
    String associate5FullName3;
    String associate5Image3;
    String associate5ID3;
    String associate5FullName4;
    String associate5Image4;
    String associate5ID4;
    String passiveImage;
    String commandmentImage;
    String blessingImage;
    String skill1Image;
    String skill2Image;
    String ultImage;

    String tskill1Image;
    String tskill2Image;
    String tultImage;

    LinearLayout transformations;

    ImageView imageView;
    ImageView associate1Image1View;
    ImageView associate1Image2View;
    ImageView associate1Image3View;
    ImageView associate1Image4View;
    ImageView associate2Image1View;
    ImageView associate2Image2View;
    ImageView associate2Image3View;
    ImageView associate2Image4View;
    ImageView associate3Image1View;
    ImageView associate3Image2View;
    ImageView associate3Image3View;
    ImageView associate3Image4View;
    ImageView associate4Image1View;
    ImageView associate4Image2View;
    ImageView associate4Image3View;
    ImageView associate4Image4View;
    ImageView associate5Image1View;
    ImageView associate5Image2View;
    ImageView associate5Image3View;
    ImageView associate5Image4View;
    ImageView passiveImageView;
    ImageView commandmentImageView;
    ImageView blessingImageView;
    ImageView skill1ImageView;
    ImageView skill2ImageView;
    ImageView ultImageView;
    ExpandableLinearLayout maxStatsView;
    ExpandableLinearLayout passiveView;
    ExpandableLinearLayout commandmentView;
    ExpandableLinearLayout blessingView;
    ExpandableLinearLayout skill1View;
    ExpandableLinearLayout skill2View;
    ExpandableLinearLayout ultimatesView;
    ExpandableLinearLayout associationsView;
    ExpandableLinearLayout awakeningsView;
    ExpandableLinearLayout superawakeningsView;
    FirebaseDatabase database;
    DatabaseReference ref;
    public static final String PREFS_NAME = "MyPrefsFile";
    SharedPreferences settings;
    String darkMode;
    LinearLayout backgroundColor;
    boolean transformed = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        database = FirebaseDatabase.getInstance();
        ref = database.getReference();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.character_page);
        maxStatsView = findViewById(R.id.maxStats);
        passiveView = findViewById(R.id.passive);
        commandmentView = findViewById(R.id.commandment_expand);
        blessingView = findViewById(R.id.blessing_expand);
        skill1View = findViewById(R.id.skill1);
        skill2View = findViewById(R.id.skill2);
        ultimatesView = findViewById(R.id.ultimates);
        awakeningsView = findViewById(R.id.awakenings);
        superawakeningsView = findViewById(R.id.super_awakenings);
        associationsView = findViewById(R.id.assosiations);
        associate1Image1View = findViewById(R.id.associate1Image1);
        associate1Image2View = findViewById(R.id.associate1Image2);
        associate1Image3View = findViewById(R.id.associate1Image3);
        associate1Image4View = findViewById(R.id.associate1Image4);
        associate2Image1View = findViewById(R.id.associate2Image1);
        associate2Image2View = findViewById(R.id.associate2Image2);
        associate2Image3View = findViewById(R.id.associate2Image3);
        associate2Image4View = findViewById(R.id.associate2Image4);
        associate3Image1View = findViewById(R.id.associate3Image1);
        associate3Image2View = findViewById(R.id.associate3Image2);
        associate3Image3View = findViewById(R.id.associate3Image3);
        associate3Image4View = findViewById(R.id.associate3Image4);
        associate4Image1View = findViewById(R.id.associate4Image1);
        associate4Image2View = findViewById(R.id.associate4Image2);
        associate4Image3View = findViewById(R.id.associate4Image3);
        associate4Image4View = findViewById(R.id.associate4Image4);
        associate5Image1View = findViewById(R.id.associate5Image1);
        associate5Image2View = findViewById(R.id.associate5Image2);
        associate5Image3View = findViewById(R.id.associate5Image3);
        associate5Image4View = findViewById(R.id.associate5Image4);
        passiveImageView = findViewById(R.id.passive_image);
        transformations = findViewById(R.id.transformations);
        commandmentImageView = findViewById(R.id.commandment_image);
        blessingImageView = findViewById(R.id.blessing_image);
        skill1ImageView = findViewById(R.id.skill1_image);
        skill2ImageView = findViewById(R.id.skill2_image);
        ultImageView = findViewById(R.id.ult_image);
        settings = getSharedPreferences(PREFS_NAME, 0);
        ImageView characterImageBackground = findViewById(R.id.character_image_background);
        Picasso.with(this).load(R.drawable.character_background).fit().centerCrop().into(characterImageBackground);
        ImageView mainBackground = findViewById(R.id.character_page_background);
        Picasso.with(this).load(R.drawable.character_list_background).fit().centerCrop().into(mainBackground);
        Intent intent = getIntent();
        selectedId = intent.getStringExtra("selectId");
        callData();
        baseStatsButton = findViewById(R.id.base_stats);
        baseStatsButton.setOnClickListener(v -> {
            TextView ccView = findViewById(R.id.character_cc);
            ccView.setText(minCC);
            TextView healthView = findViewById(R.id.character_health);
            healthView.setText(minHealth);
            TextView attackView = findViewById(R.id.character_attack);
            attackView.setText(minAttack);
            TextView defenseView = findViewById(R.id.character_defense);
            defenseView.setText(minDefence);
            TextView pierceView = findViewById(R.id.character_pierce);
            pierceView.setText(minPierceRate + "%");
            TextView resistanceView = findViewById(R.id.character_resistance);
            resistanceView.setText(minResistance + "%");
            TextView regenView = findViewById(R.id.character_regen);
            regenView.setText(minRegenRate + "%");
            TextView critCView = findViewById(R.id.character_crit_chance);
            critCView.setText(minCritChance + "%");
            TextView critDView = findViewById(R.id.character_crit_damage);
            critDView.setText(minCritDamage + "%");
            TextView critDefView = findViewById(R.id.character_crit_def);
            critDefView.setText(minCritDef + "%");
            TextView critResView = findViewById(R.id.character_crit_res);
            critResView.setText(minCritRes + "%");
            TextView recoveryView = findViewById(R.id.character_recovery_rate);
            recoveryView.setText(minRecoveryRate + "%");
            TextView lifestealView = findViewById(R.id.character_lifesteal);
            lifestealView.setText(minLifesteal + "%");
            TextView baseORmax = findViewById(R.id.base_or_max);
            baseORmax.setText("Showing Base Stats");
        });
        maxStatsButton = findViewById(R.id.max_stats);
        maxStatsButton.setOnClickListener(v -> {
            TextView ccView = findViewById(R.id.character_cc);
            ccView.setText(maxCC);
            TextView healthView = findViewById(R.id.character_health);
            healthView.setText(maxHealth);
            TextView attackView = findViewById(R.id.character_attack);
            attackView.setText(maxAttack);
            TextView defenseView = findViewById(R.id.character_defense);
            defenseView.setText(maxDefence);
            TextView pierceView = findViewById(R.id.character_pierce);
            pierceView.setText(maxPierceRate + "%");
            TextView resistanceView = findViewById(R.id.character_resistance);
            resistanceView.setText(maxResistance + "%");
            TextView regenView = findViewById(R.id.character_regen);
            regenView.setText(maxRegenRate + "%");
            TextView critCView = findViewById(R.id.character_crit_chance);
            critCView.setText(maxCritChance + "%");
            TextView critDView = findViewById(R.id.character_crit_damage);
            critDView.setText(maxCritDamage + "%");
            TextView critDefView = findViewById(R.id.character_crit_def);
            critDefView.setText(maxCritDef + "%");
            TextView critResView = findViewById(R.id.character_crit_res);
            critResView.setText(maxCritRes + "%");
            TextView recoveryView = findViewById(R.id.character_recovery_rate);
            recoveryView.setText(maxRecoveryRate + "%");
            TextView lifestealView = findViewById(R.id.character_lifesteal);
            lifestealView.setText(maxLifesteal + "%");
            TextView baseORmax = findViewById(R.id.base_or_max);
            baseORmax.setText("Showing Max Stats");
        });
        associate1Image1View.setOnClickListener(view -> {
            selectedId = associate1ID1;
            callData();
            resizeExpandableLayouts();
        });
        associate1Image2View.setOnClickListener(view -> {
            selectedId = associate1ID2;
            callData();
            resizeExpandableLayouts();
        });
        associate1Image3View.setOnClickListener(view -> {
            selectedId = associate1ID3;
            callData();
            resizeExpandableLayouts();
        });
        associate1Image4View.setOnClickListener(view -> {
            selectedId = associate1ID4;
            callData();
            resizeExpandableLayouts();
        });
        associate2Image1View.setOnClickListener(view -> {
            selectedId = associate2ID1;
            callData();
            resizeExpandableLayouts();
        });
        associate2Image2View.setOnClickListener(view -> {
            selectedId = associate2ID2;
            callData();
            resizeExpandableLayouts();
        });
        associate2Image3View.setOnClickListener(view -> {
            selectedId = associate2ID3;
            callData();
            resizeExpandableLayouts();
        });
        associate2Image4View.setOnClickListener(view -> {
            selectedId = associate2ID4;
            callData();
            resizeExpandableLayouts();
        });
        associate3Image1View.setOnClickListener(view -> {
            selectedId = associate3ID1;
            callData();
            resizeExpandableLayouts();
        });
        associate3Image2View.setOnClickListener(view -> {
            selectedId = associate3ID2;
            callData();
            resizeExpandableLayouts();
        });
        associate3Image3View.setOnClickListener(view -> {
            selectedId = associate3ID3;
            callData();
            resizeExpandableLayouts();
        });
        associate3Image4View.setOnClickListener(view -> {
            selectedId = associate3ID4;
            callData();
            resizeExpandableLayouts();
        });
        associate4Image1View.setOnClickListener(view -> {
            selectedId = associate4ID1;
            callData();
            resizeExpandableLayouts();
        });
        associate4Image2View.setOnClickListener(view -> {
            selectedId = associate4ID2;
            callData();
            resizeExpandableLayouts();
        });
        associate4Image3View.setOnClickListener(view -> {
            selectedId = associate4ID3;
            callData();
            resizeExpandableLayouts();
        });
        associate4Image4View.setOnClickListener(view -> {
            selectedId = associate4ID4;
            callData();
            resizeExpandableLayouts();
        });
        associate5Image1View.setOnClickListener(view -> {
            selectedId = associate5ID1;
            callData();
            resizeExpandableLayouts();
        });
        associate5Image2View.setOnClickListener(view -> {
            selectedId = associate5ID2;
            callData();
            resizeExpandableLayouts();
        });
        associate5Image3View.setOnClickListener(view -> {
            selectedId = associate5ID3;
            callData();
            resizeExpandableLayouts();
        });
        associate5Image4View.setOnClickListener(view -> {
            selectedId = associate5ID4;
            callData();
            resizeExpandableLayouts();
        });
        resizeExpandableLayouts();
        backgroundColor = findViewById(R.id.character_page_background_colour);
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
            transformations.setBackgroundColor(Color.parseColor("#616161"));
        } else {
            backgroundColor.setBackgroundColor(Color.parseColor("#4D15C2A6"));
            LinearLayout adViewHolder = findViewById(R.id.adViewHolder);
            adViewHolder.setBackgroundColor(Color.parseColor("#4D15C2A6"));
            transformations.setBackgroundColor(Color.parseColor("#4D15C2A6"));
        }
        resizeExpandableLayouts();
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt("listClicked", 0);
        editor.apply();
    }

    protected void onRestart() {
        super.onRestart();
        LinearLayout adViewHolder = findViewById(R.id.adViewHolder);
        darkMode = settings.getString("darkMode", darkMode);
        if (darkMode.equals("yes")) {
            backgroundColor.setBackgroundColor(Color.parseColor("#616161"));
            adViewHolder.setBackgroundColor(Color.parseColor("#616161"));
            transformations.setBackgroundColor(Color.parseColor("#616161"));
        } else {
            backgroundColor.setBackgroundColor(Color.parseColor("#4D15C2A6"));
            adViewHolder.setBackgroundColor(Color.parseColor("#4D15C2A6"));
            transformations.setBackgroundColor(Color.parseColor("#4D15C2A6"));
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
        Log.v("test", "id = " + id);
        if (id == R.id.help_page) {
            Intent help = new Intent(character_page.this, help_menu.class);
            startActivity(help);
        }
        if (id == 16908332) {
            Log.v("test2", "id2");
            SharedPreferences.Editor editor = settings.edit();
            editor.putInt("listClicked", 1);
            editor.apply();
        }
        return super.onOptionsItemSelected(item);
    }

    TextView transformedText;

    public void showBaseForm(View view) {
        if (transformed) {
            transformedText = findViewById(R.id.transformedtext);
            transformedText.setText("Showing Base Form");
            if (!passiveImage.equals("-")) {
                loadImage(passiveImage, passiveImageView);
            } else {
                Picasso.with(character_page.this).load(R.drawable.no_image).into(passiveImageView);
            }
            TextView passiveNameView = findViewById(R.id.passive_name);
            passiveNameView.setText(passiveName);
            TextView passiveDescView = findViewById(R.id.passive_desc);
            passiveDescView.setText(passiveDesc);

            if (!skill1Image.equals("-")) {
                loadImage(skill1Image, skill1ImageView);
            } else {
                Picasso.with(character_page.this).load(R.drawable.no_image).into(skill1ImageView);
            }
            TextView skill1NameView = findViewById(R.id.skill_1_name);
            skill1NameView.setText(skill1Name);
            TextView skill11View = findViewById(R.id.skill11);
            skill11View.setText(skill1Desc1);
            TextView skill12View = findViewById(R.id.skill12);
            skill12View.setText(skill1Desc2);
            TextView skill13View = findViewById(R.id.skill13);
            skill13View.setText(skill1Desc3);

            if (!skill2Image.equals("-")) {
                loadImage(skill2Image, skill2ImageView);
            } else {
                Picasso.with(character_page.this).load(R.drawable.no_image).into(skill2ImageView);
            }
            TextView skill2NameView = findViewById(R.id.skill_2_name);
            skill2NameView.setText(skill2Name);
            TextView skill21View = findViewById(R.id.skill21);
            skill21View.setText(skill2Desc1);
            TextView skill22View = findViewById(R.id.skill22);
            skill22View.setText(skill2Desc2);
            TextView skill23View = findViewById(R.id.skill23);
            skill23View.setText(skill2Desc3);

            if (!ultImage.equals("-")) {
                loadImage(ultImage, ultImageView);
            } else {
                Picasso.with(character_page.this).load(R.drawable.no_image).into(ultImageView);
            }
            TextView ultimateName1View = findViewById(R.id.ultimate_name_1);
            ultimateName1View.setText(ultimateName);
            TextView ultimate1View = findViewById(R.id.ultimate_1);
            ultimate1View.setText(ultimateDesc);
            TextView ultimate1MultView = findViewById(R.id.ultimate_1_mult);
            ultimate1MultView.setText(ultimateMulti);
            transformed = false;
        }
    }

    public void showTransformed(View view) {
        if (!transformed) {
            transformedText = findViewById(R.id.transformedtext);
            transformedText.setText("Showing Transformed Form");
            if (!passiveImage.equals("-")) {
                loadImage(tpassiveImage, passiveImageView);
            } else {
                Picasso.with(character_page.this).load(R.drawable.no_image).into(passiveImageView);
            }
            TextView passiveNameView = findViewById(R.id.passive_name);
            passiveNameView.setText(tpassiveName);
            TextView passiveDescView = findViewById(R.id.passive_desc);
            passiveDescView.setText(tpassiveDesc);

            if (!skill1Image.equals("-")) {
                loadImage(tskill1Image, skill1ImageView);
            } else {
                Picasso.with(character_page.this).load(R.drawable.no_image).into(skill1ImageView);
            }
            TextView skill1NameView = findViewById(R.id.skill_1_name);
            skill1NameView.setText(tskill1Name);
            TextView skill11View = findViewById(R.id.skill11);
            skill11View.setText(tskill1Desc1);
            TextView skill12View = findViewById(R.id.skill12);
            skill12View.setText(tskill1Desc2);
            TextView skill13View = findViewById(R.id.skill13);
            skill13View.setText(tskill1Desc3);

            if (!skill2Image.equals("-")) {
                loadImage(tskill2Image, skill2ImageView);
            } else {
                Picasso.with(character_page.this).load(R.drawable.no_image).into(skill2ImageView);
            }
            TextView skill2NameView = findViewById(R.id.skill_2_name);
            skill2NameView.setText(tskill2Name);
            TextView skill21View = findViewById(R.id.skill21);
            skill21View.setText(tskill2Desc1);
            TextView skill22View = findViewById(R.id.skill22);
            skill22View.setText(tskill2Desc2);
            TextView skill23View = findViewById(R.id.skill23);
            skill23View.setText(tskill2Desc3);

            if (!ultImage.equals("-")) {
                loadImage(tultImage, ultImageView);
            } else {
                Picasso.with(character_page.this).load(R.drawable.no_image).into(ultImageView);
            }
            TextView ultimateName1View = findViewById(R.id.ultimate_name_1);
            ultimateName1View.setText(tultimateName);
            TextView ultimate1View = findViewById(R.id.ultimate_1);
            ultimate1View.setText(tultimateDesc);
            TextView ultimate1MultView = findViewById(R.id.ultimate_1_mult);
            ultimate1MultView.setText(tultimateMulti);
            transformed = true;
        }
    }

    public void showMaxStats(View view) {
        maxStatsView.toggle();
        passiveView.collapse();
        commandmentView.collapse();
        blessingView.collapse();
        skill1View.collapse();
        skill2View.collapse();
        ultimatesView.collapse();
        awakeningsView.collapse();
        superawakeningsView.collapse();
        associationsView.collapse();
        resizeExpandableLayouts();
    }

    public void showPassive(View view) {
        maxStatsView.collapse();
        passiveView.toggle();
        commandmentView.collapse();
        blessingView.collapse();
        skill1View.collapse();
        skill2View.collapse();
        ultimatesView.collapse();
        awakeningsView.collapse();
        superawakeningsView.collapse();
        associationsView.collapse();
        resizeExpandableLayouts();
    }

    public void showCommandment(View view) {
        maxStatsView.collapse();
        passiveView.collapse();
        commandmentView.toggle();
        blessingView.collapse();
        skill1View.collapse();
        skill2View.collapse();
        ultimatesView.collapse();
        awakeningsView.collapse();
        superawakeningsView.collapse();
        associationsView.collapse();
        resizeExpandableLayouts();
    }

    public void showBlessing(View view) {
        maxStatsView.collapse();
        passiveView.collapse();
        commandmentView.collapse();
        blessingView.toggle();
        skill1View.collapse();
        skill2View.collapse();
        ultimatesView.collapse();
        awakeningsView.collapse();
        superawakeningsView.collapse();
        associationsView.collapse();
        resizeExpandableLayouts();
    }

    public void showSkill1(View view) {
        maxStatsView.collapse();
        passiveView.collapse();
        commandmentView.collapse();
        blessingView.collapse();
        skill1View.toggle();
        skill2View.collapse();
        ultimatesView.collapse();
        awakeningsView.collapse();
        superawakeningsView.collapse();
        associationsView.collapse();
        resizeExpandableLayouts();
    }

    public void showSkill2(View view) {
        maxStatsView.collapse();
        passiveView.collapse();
        commandmentView.collapse();
        blessingView.collapse();
        skill1View.collapse();
        skill2View.toggle();
        ultimatesView.collapse();
        awakeningsView.collapse();
        superawakeningsView.collapse();
        associationsView.collapse();
        resizeExpandableLayouts();
    }

    public void showUltimates(View view) {
        maxStatsView.collapse();
        passiveView.collapse();
        commandmentView.collapse();
        blessingView.collapse();
        skill1View.collapse();
        skill2View.collapse();
        ultimatesView.toggle();
        awakeningsView.collapse();
        superawakeningsView.collapse();
        associationsView.collapse();
        resizeExpandableLayouts();
    }

    public void showAwakening(View view) {
        maxStatsView.collapse();
        passiveView.collapse();
        commandmentView.collapse();
        blessingView.collapse();
        skill1View.collapse();
        skill2View.collapse();
        ultimatesView.collapse();
        awakeningsView.toggle();
        superawakeningsView.collapse();
        associationsView.collapse();
        resizeExpandableLayouts();
    }

    public void showSuperAwakening(View view) {
        maxStatsView.collapse();
        passiveView.collapse();
        commandmentView.collapse();
        blessingView.collapse();
        skill1View.collapse();
        skill2View.collapse();
        ultimatesView.collapse();
        awakeningsView.collapse();
        superawakeningsView.toggle();
        associationsView.collapse();
        resizeExpandableLayouts();
    }

    public void showAssociations(View view) {
        maxStatsView.collapse();
        passiveView.collapse();
        commandmentView.collapse();
        blessingView.collapse();
        skill1View.collapse();
        skill2View.collapse();
        ultimatesView.collapse();
        awakeningsView.collapse();
        superawakeningsView.collapse();
        associationsView.toggle();
        resizeExpandableLayouts();
    }


    public void callData() {
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    selectId = selectedId;
                    type = Objects.requireNonNull(dataSnapshot.child(selectId).child("type").getValue()).toString();
                    name = Objects.requireNonNull(dataSnapshot.child(selectId).child("characterFullName").getValue()).toString();
                    minCC = Objects.requireNonNull(dataSnapshot.child(selectId).child("minCombatClass").getValue()).toString();
                    maxCC = Objects.requireNonNull(dataSnapshot.child(selectId).child("maxCombatClass").getValue()).toString();
                    minHealth = Objects.requireNonNull(dataSnapshot.child(selectId).child("minHealth").getValue()).toString();
                    maxHealth = Objects.requireNonNull(dataSnapshot.child(selectId).child("maxHealth").getValue()).toString();
                    minAttack = Objects.requireNonNull(dataSnapshot.child(selectId).child("minAttack").getValue()).toString();
                    maxAttack = Objects.requireNonNull(dataSnapshot.child(selectId).child("maxAttack").getValue()).toString();
                    minDefence = Objects.requireNonNull(dataSnapshot.child(selectId).child("minDefence").getValue()).toString();
                    maxDefence = Objects.requireNonNull(dataSnapshot.child(selectId).child("maxDefence").getValue()).toString();
                    minPierceRate = Objects.requireNonNull(dataSnapshot.child(selectId).child("minPierceRate").getValue()).toString();
                    minCritChance = Objects.requireNonNull(dataSnapshot.child(selectId).child("minCritChance").getValue()).toString();
                    minCritDamage = Objects.requireNonNull(dataSnapshot.child(selectId).child("minCritDamage").getValue()).toString();
                    minResistance = Objects.requireNonNull(dataSnapshot.child(selectId).child("minResistance").getValue()).toString();
                    minCritRes = Objects.requireNonNull(dataSnapshot.child(selectId).child("minCritRes").getValue()).toString();
                    minCritDef = Objects.requireNonNull(dataSnapshot.child(selectId).child("minCritDef").getValue()).toString();
                    minRegenRate = Objects.requireNonNull(dataSnapshot.child(selectId).child("minRegenRate").getValue()).toString();
                    minRecoveryRate = Objects.requireNonNull(dataSnapshot.child(selectId).child("minRecoveryRate").getValue()).toString();
                    minLifesteal = Objects.requireNonNull(dataSnapshot.child(selectId).child("minLifesteal").getValue()).toString();
                    maxPierceRate = Objects.requireNonNull(dataSnapshot.child(selectId).child("maxPierceRate").getValue()).toString();
                    maxCritChance = Objects.requireNonNull(dataSnapshot.child(selectId).child("maxCritChance").getValue()).toString();
                    maxCritDamage = Objects.requireNonNull(dataSnapshot.child(selectId).child("maxCritDamage").getValue()).toString();
                    maxResistance = Objects.requireNonNull(dataSnapshot.child(selectId).child("maxResistance").getValue()).toString();
                    maxCritRes = Objects.requireNonNull(dataSnapshot.child(selectId).child("maxCritRes").getValue()).toString();
                    maxCritDef = Objects.requireNonNull(dataSnapshot.child(selectId).child("maxCritDef").getValue()).toString();
                    maxRegenRate = Objects.requireNonNull(dataSnapshot.child(selectId).child("maxRegenRate").getValue()).toString();
                    maxRecoveryRate = Objects.requireNonNull(dataSnapshot.child(selectId).child("maxRecoveryRate").getValue()).toString();
                    maxLifesteal = Objects.requireNonNull(dataSnapshot.child(selectId).child("maxLifesteal").getValue()).toString();
                    characterImage = Objects.requireNonNull(dataSnapshot.child(selectId).child("characterImage").getValue()).toString();

                    passiveName = Objects.requireNonNull(dataSnapshot.child(selectId).child("passiveName").getValue()).toString();
                    passiveDesc = Objects.requireNonNull(dataSnapshot.child(selectId).child("passiveEffect").getValue()).toString();
                    commandment = Objects.requireNonNull(dataSnapshot.child(selectId).child("commandment").getValue()).toString();
                    commandmentEffect = Objects.requireNonNull(dataSnapshot.child(selectId).child("commandmentEffect").getValue()).toString();
                    blessing = Objects.requireNonNull(dataSnapshot.child(selectId).child("blessing").getValue()).toString();
                    blessingEffect = Objects.requireNonNull(dataSnapshot.child(selectId).child("blessingEffect").getValue()).toString();
                    skill1Name = Objects.requireNonNull(dataSnapshot.child(selectId).child("skill1Name").getValue()).toString();
                    skill1Desc1 = Objects.requireNonNull(dataSnapshot.child(selectId).child("skill1Desc1").getValue()).toString();
                    skill1Desc2 = Objects.requireNonNull(dataSnapshot.child(selectId).child("skill1Desc2").getValue()).toString();
                    skill1Desc3 = Objects.requireNonNull(dataSnapshot.child(selectId).child("skill1Desc3").getValue()).toString();
                    skill2Name = Objects.requireNonNull(dataSnapshot.child(selectId).child("skill2Name").getValue()).toString();
                    skill2Desc1 = Objects.requireNonNull(dataSnapshot.child(selectId).child("skill2Desc1").getValue()).toString();
                    skill2Desc2 = Objects.requireNonNull(dataSnapshot.child(selectId).child("skill2Desc2").getValue()).toString();
                    skill2Desc3 = Objects.requireNonNull(dataSnapshot.child(selectId).child("skill2Desc3").getValue()).toString();
                    ultimateName = Objects.requireNonNull(dataSnapshot.child(selectId).child("ultimateName").getValue()).toString();
                    ultimateDesc = Objects.requireNonNull(dataSnapshot.child(selectId).child("ultimateDesc").getValue()).toString();
                    ultimateMulti = Objects.requireNonNull(dataSnapshot.child(selectId).child("ultimateMulti").getValue()).toString();
                    fateName = Objects.requireNonNull(dataSnapshot.child(selectId).child("fateName").getValue()).toString();
                    fateDesc = Objects.requireNonNull(dataSnapshot.child(selectId).child("fateDesc").getValue()).toString();
                    fateMulti = Objects.requireNonNull(dataSnapshot.child(selectId).child("fateMulti").getValue()).toString();

                    tpassiveName = Objects.requireNonNull(dataSnapshot.child(selectId).child("tpassiveName").getValue()).toString();
                    tpassiveDesc = Objects.requireNonNull(dataSnapshot.child(selectId).child("tpassiveEffect").getValue()).toString();
                    tskill1Name = Objects.requireNonNull(dataSnapshot.child(selectId).child("tskill1Name").getValue()).toString();
                    tskill1Desc1 = Objects.requireNonNull(dataSnapshot.child(selectId).child("tskill1Desc1").getValue()).toString();
                    tskill1Desc2 = Objects.requireNonNull(dataSnapshot.child(selectId).child("tskill1Desc2").getValue()).toString();
                    tskill1Desc3 = Objects.requireNonNull(dataSnapshot.child(selectId).child("tskill1Desc3").getValue()).toString();
                    tskill2Name = Objects.requireNonNull(dataSnapshot.child(selectId).child("tskill2Name").getValue()).toString();
                    tskill2Desc1 = Objects.requireNonNull(dataSnapshot.child(selectId).child("tskill2Desc1").getValue()).toString();
                    tskill2Desc2 = Objects.requireNonNull(dataSnapshot.child(selectId).child("tskill2Desc2").getValue()).toString();
                    tskill2Desc3 = Objects.requireNonNull(dataSnapshot.child(selectId).child("tskill2Desc3").getValue()).toString();
                    tultimateName = Objects.requireNonNull(dataSnapshot.child(selectId).child("tultimateName").getValue()).toString();
                    tultimateDesc = Objects.requireNonNull(dataSnapshot.child(selectId).child("tultimateDesc").getValue()).toString();
                    tultimateMulti = Objects.requireNonNull(dataSnapshot.child(selectId).child("tultimateMulti").getValue()).toString();

                    awakening1HP = Objects.requireNonNull(dataSnapshot.child(selectId).child("awakening1HP").getValue()).toString();
                    awakening1CritRes = Objects.requireNonNull(dataSnapshot.child(selectId).child("awakening1CritRes").getValue()).toString();
                    awakening1RecRate = Objects.requireNonNull(dataSnapshot.child(selectId).child("awakening1RecRate").getValue()).toString();
                    awakening2Atk = Objects.requireNonNull(dataSnapshot.child(selectId).child("awakening2Atk").getValue()).toString();
                    awakening2Def = Objects.requireNonNull(dataSnapshot.child(selectId).child("awakening2Def").getValue()).toString();
                    awakening2CritDmg = Objects.requireNonNull(dataSnapshot.child(selectId).child("awakening2CritDmg").getValue()).toString();
                    awakening3HP = Objects.requireNonNull(dataSnapshot.child(selectId).child("awakening3HP").getValue()).toString();
                    awakening3RegenRate = Objects.requireNonNull(dataSnapshot.child(selectId).child("awakening3RegenRate").getValue()).toString();
                    awakening3CritDef = Objects.requireNonNull(dataSnapshot.child(selectId).child("awakening3CritDef").getValue()).toString();
                    awakening4Atk = Objects.requireNonNull(dataSnapshot.child(selectId).child("awakening4Atk").getValue()).toString();
                    awakening4Def = Objects.requireNonNull(dataSnapshot.child(selectId).child("awakening4Def").getValue()).toString();
                    awakening4CritChance = Objects.requireNonNull(dataSnapshot.child(selectId).child("awakening4CritChance").getValue()).toString();
                    awakening5HP = Objects.requireNonNull(dataSnapshot.child(selectId).child("awakening5HP").getValue()).toString();
                    awakening5Res = Objects.requireNonNull(dataSnapshot.child(selectId).child("awakening5Res").getValue()).toString();
                    awakening5Lifesteal = Objects.requireNonNull(dataSnapshot.child(selectId).child("awakening5Lifesteal").getValue()).toString();
                    awakening6Atk = Objects.requireNonNull(dataSnapshot.child(selectId).child("awakening6Atk").getValue()).toString();
                    awakening6Def = Objects.requireNonNull(dataSnapshot.child(selectId).child("awakening6Def").getValue()).toString();
                    awakening6Pierce = Objects.requireNonNull(dataSnapshot.child(selectId).child("awakening6Pierce").getValue()).toString();
                    superawakening1HP = Objects.requireNonNull(dataSnapshot.child(selectId).child("superawakening1HP").getValue()).toString();
                    superawakening1CritRes = Objects.requireNonNull(dataSnapshot.child(selectId).child("superawakening1CritRes").getValue()).toString();
                    superawakening1RecRate = Objects.requireNonNull(dataSnapshot.child(selectId).child("superawakening1RecRate").getValue()).toString();
                    superawakening2Atk = Objects.requireNonNull(dataSnapshot.child(selectId).child("superawakening2Atk").getValue()).toString();
                    superawakening2Def = Objects.requireNonNull(dataSnapshot.child(selectId).child("superawakening2Def").getValue()).toString();
                    superawakening2CritDmg = Objects.requireNonNull(dataSnapshot.child(selectId).child("superawakening2CritDmg").getValue()).toString();
                    superawakening3HP = Objects.requireNonNull(dataSnapshot.child(selectId).child("superawakening3HP").getValue()).toString();
                    superawakening3RegenRate = Objects.requireNonNull(dataSnapshot.child(selectId).child("superawakening3RegenRate").getValue()).toString();
                    superawakening3CritDef = Objects.requireNonNull(dataSnapshot.child(selectId).child("superawakening3CritDef").getValue()).toString();
                    superawakening4Atk = Objects.requireNonNull(dataSnapshot.child(selectId).child("superawakening4Atk").getValue()).toString();
                    superawakening4Def = Objects.requireNonNull(dataSnapshot.child(selectId).child("superawakening4Def").getValue()).toString();
                    superawakening4CritChance = Objects.requireNonNull(dataSnapshot.child(selectId).child("superawakening4CritChance").getValue()).toString();
                    associate1 = Objects.requireNonNull(dataSnapshot.child(selectId).child("associate1").getValue()).toString();
                    associate1Effect = Objects.requireNonNull(dataSnapshot.child(selectId).child("associate1Effect").getValue()).toString();
                    associate1FullName1 = Objects.requireNonNull(dataSnapshot.child(selectId).child("associate1FullName1").getValue()).toString();
                    associate1Image1 = Objects.requireNonNull(dataSnapshot.child(selectId).child("associate1Image1").getValue()).toString();
                    associate1ID1 = Objects.requireNonNull(dataSnapshot.child(selectId).child("associate1ID1").getValue()).toString();
                    associate1FullName2 = Objects.requireNonNull(dataSnapshot.child(selectId).child("associate1FullName2").getValue()).toString();
                    associate1Image2 = Objects.requireNonNull(dataSnapshot.child(selectId).child("associate1Image2").getValue()).toString();
                    associate1ID2 = Objects.requireNonNull(dataSnapshot.child(selectId).child("associate1ID2").getValue()).toString();
                    associate1FullName3 = Objects.requireNonNull(dataSnapshot.child(selectId).child("associate1FullName3").getValue()).toString();
                    associate1Image3 = Objects.requireNonNull(dataSnapshot.child(selectId).child("associate1Image3").getValue()).toString();
                    associate1ID3 = Objects.requireNonNull(dataSnapshot.child(selectId).child("associate1ID3").getValue()).toString();
                    associate1FullName4 = Objects.requireNonNull(dataSnapshot.child(selectId).child("associate1FullName4").getValue()).toString();
                    associate1Image4 = Objects.requireNonNull(dataSnapshot.child(selectId).child("associate1Image4").getValue()).toString();
                    associate1ID4 = Objects.requireNonNull(dataSnapshot.child(selectId).child("associate1ID4").getValue()).toString();
                    associate2 = Objects.requireNonNull(dataSnapshot.child(selectId).child("associate2").getValue()).toString();
                    associate2Effect = Objects.requireNonNull(dataSnapshot.child(selectId).child("associate2Effect").getValue()).toString();
                    associate2FullName1 = Objects.requireNonNull(dataSnapshot.child(selectId).child("associate2FullName1").getValue()).toString();
                    associate2Image1 = Objects.requireNonNull(dataSnapshot.child(selectId).child("associate2Image1").getValue()).toString();
                    associate2ID1 = Objects.requireNonNull(dataSnapshot.child(selectId).child("associate2ID1").getValue()).toString();
                    associate2FullName2 = Objects.requireNonNull(dataSnapshot.child(selectId).child("associate2FullName2").getValue()).toString();
                    associate2Image2 = Objects.requireNonNull(dataSnapshot.child(selectId).child("associate2Image2").getValue()).toString();
                    associate2ID2 = Objects.requireNonNull(dataSnapshot.child(selectId).child("associate2ID2").getValue()).toString();
                    associate2FullName3 = Objects.requireNonNull(dataSnapshot.child(selectId).child("associate2FullName3").getValue()).toString();
                    associate2Image3 = Objects.requireNonNull(dataSnapshot.child(selectId).child("associate2Image3").getValue()).toString();
                    associate2ID3 = Objects.requireNonNull(dataSnapshot.child(selectId).child("associate2ID3").getValue()).toString();
                    associate2FullName4 = Objects.requireNonNull(dataSnapshot.child(selectId).child("associate2FullName4").getValue()).toString();
                    associate2Image4 = Objects.requireNonNull(dataSnapshot.child(selectId).child("associate2Image4").getValue()).toString();
                    associate2ID4 = Objects.requireNonNull(dataSnapshot.child(selectId).child("associate2ID4").getValue()).toString();
                    associate3 = Objects.requireNonNull(dataSnapshot.child(selectId).child("associate3").getValue()).toString();
                    associate3Effect = Objects.requireNonNull(dataSnapshot.child(selectId).child("associate3Effect").getValue()).toString();
                    associate3FullName1 = Objects.requireNonNull(dataSnapshot.child(selectId).child("associate3FullName1").getValue()).toString();
                    associate3Image1 = Objects.requireNonNull(dataSnapshot.child(selectId).child("associate3Image1").getValue()).toString();
                    associate3ID1 = Objects.requireNonNull(dataSnapshot.child(selectId).child("associate3ID1").getValue()).toString();
                    associate3FullName2 = Objects.requireNonNull(dataSnapshot.child(selectId).child("associate3FullName2").getValue()).toString();
                    associate3Image2 = Objects.requireNonNull(dataSnapshot.child(selectId).child("associate3Image2").getValue()).toString();
                    associate3ID2 = Objects.requireNonNull(dataSnapshot.child(selectId).child("associate3ID2").getValue()).toString();
                    associate3FullName3 = Objects.requireNonNull(dataSnapshot.child(selectId).child("associate3FullName3").getValue()).toString();
                    associate3Image3 = Objects.requireNonNull(dataSnapshot.child(selectId).child("associate3Image3").getValue()).toString();
                    associate3ID3 = Objects.requireNonNull(dataSnapshot.child(selectId).child("associate3ID3").getValue()).toString();
                    associate3FullName4 = Objects.requireNonNull(dataSnapshot.child(selectId).child("associate3FullName4").getValue()).toString();
                    associate3Image4 = Objects.requireNonNull(dataSnapshot.child(selectId).child("associate3Image4").getValue()).toString();
                    associate3ID4 = Objects.requireNonNull(dataSnapshot.child(selectId).child("associate3ID4").getValue()).toString();
                    associate4 = Objects.requireNonNull(dataSnapshot.child(selectId).child("associate4").getValue()).toString();
                    associate4Effect = Objects.requireNonNull(dataSnapshot.child(selectId).child("associate4Effect").getValue()).toString();
                    associate4FullName1 = Objects.requireNonNull(dataSnapshot.child(selectId).child("associate4FullName1").getValue()).toString();
                    associate4Image1 = Objects.requireNonNull(dataSnapshot.child(selectId).child("associate4Image1").getValue()).toString();
                    associate4ID1 = Objects.requireNonNull(dataSnapshot.child(selectId).child("associate4ID1").getValue()).toString();
                    associate4FullName2 = Objects.requireNonNull(dataSnapshot.child(selectId).child("associate4FullName2").getValue()).toString();
                    associate4Image2 = Objects.requireNonNull(dataSnapshot.child(selectId).child("associate4Image2").getValue()).toString();
                    associate4ID2 = Objects.requireNonNull(dataSnapshot.child(selectId).child("associate4ID2").getValue()).toString();
                    associate4FullName3 = Objects.requireNonNull(dataSnapshot.child(selectId).child("associate4FullName3").getValue()).toString();
                    associate4Image3 = Objects.requireNonNull(dataSnapshot.child(selectId).child("associate4Image3").getValue()).toString();
                    associate4ID3 = Objects.requireNonNull(dataSnapshot.child(selectId).child("associate4ID3").getValue()).toString();
                    associate4FullName4 = Objects.requireNonNull(dataSnapshot.child(selectId).child("associate4FullName4").getValue()).toString();
                    associate4Image4 = Objects.requireNonNull(dataSnapshot.child(selectId).child("associate4Image4").getValue()).toString();
                    associate4ID4 = Objects.requireNonNull(dataSnapshot.child(selectId).child("associate4ID4").getValue()).toString();
                    associate5 = Objects.requireNonNull(dataSnapshot.child(selectId).child("associate5").getValue()).toString();
                    associate5Effect = Objects.requireNonNull(dataSnapshot.child(selectId).child("associate5Effect").getValue()).toString();
                    associate5FullName1 = Objects.requireNonNull(dataSnapshot.child(selectId).child("associate5FullName1").getValue()).toString();
                    associate5Image1 = Objects.requireNonNull(dataSnapshot.child(selectId).child("associate5Image1").getValue()).toString();
                    associate5ID1 = Objects.requireNonNull(dataSnapshot.child(selectId).child("associate5ID1").getValue()).toString();
                    associate5FullName2 = Objects.requireNonNull(dataSnapshot.child(selectId).child("associate5FullName2").getValue()).toString();
                    associate5Image2 = Objects.requireNonNull(dataSnapshot.child(selectId).child("associate5Image2").getValue()).toString();
                    associate5ID2 = Objects.requireNonNull(dataSnapshot.child(selectId).child("associate5ID2").getValue()).toString();
                    associate5FullName3 = Objects.requireNonNull(dataSnapshot.child(selectId).child("associate5FullName3").getValue()).toString();
                    associate5Image3 = Objects.requireNonNull(dataSnapshot.child(selectId).child("associate5Image3").getValue()).toString();
                    associate5ID3 = Objects.requireNonNull(dataSnapshot.child(selectId).child("associate5ID3").getValue()).toString();
                    associate5FullName4 = Objects.requireNonNull(dataSnapshot.child(selectId).child("associate5FullName4").getValue()).toString();
                    associate5Image4 = Objects.requireNonNull(dataSnapshot.child(selectId).child("associate5Image4").getValue()).toString();
                    associate5ID4 = Objects.requireNonNull(dataSnapshot.child(selectId).child("associate5ID4").getValue()).toString();
                    passiveImage = Objects.requireNonNull(dataSnapshot.child(selectId).child("passiveImage").getValue()).toString();
                    commandmentImage = Objects.requireNonNull(dataSnapshot.child(selectId).child("commandmentImage").getValue()).toString();
                    blessingImage = Objects.requireNonNull(dataSnapshot.child(selectId).child("blessingImage").getValue()).toString();
                    skill1Image = Objects.requireNonNull(dataSnapshot.child(selectId).child("skill1Image").getValue()).toString();
                    skill2Image = Objects.requireNonNull(dataSnapshot.child(selectId).child("skill2Image").getValue()).toString();
                    ultImage = Objects.requireNonNull(dataSnapshot.child(selectId).child("ult1Image").getValue()).toString();

                    tpassiveImage = Objects.requireNonNull(dataSnapshot.child(selectId).child("tpassiveImage").getValue()).toString();
                    tskill1Image = Objects.requireNonNull(dataSnapshot.child(selectId).child("tskill1Image").getValue()).toString();
                    tskill2Image = Objects.requireNonNull(dataSnapshot.child(selectId).child("tskill2Image").getValue()).toString();
                    tultImage = Objects.requireNonNull(dataSnapshot.child(selectId).child("tult1Image").getValue()).toString();

                    imageView = findViewById(R.id.character_image);
                    if (!characterImage.equals("-")) {
                        loadImage(characterImage, imageView);
                    } else {
                        Picasso.with(character_page.this).load(R.drawable.no_image).into(imageView);
                    }
                    if (!passiveImage.equals("-")) {
                        loadImage(passiveImage, passiveImageView);
                    } else {
                        Picasso.with(character_page.this).load(R.drawable.no_image).into(passiveImageView);
                    }
                    if (!commandmentImage.equals("-")) {
                        if (!commandmentImage.equals("")) {
                            loadImage(commandmentImage, commandmentImageView);
                        }
                    } else {
                        Picasso.with(character_page.this).load(R.drawable.no_image).into(commandmentImageView);
                    }
                    if (!blessingImage.equals("-")) {
                        if (!blessingImage.equals("")) {
                            loadImage(blessingImage, blessingImageView);
                        }
                    } else {
                        Picasso.with(character_page.this).load(R.drawable.no_image).into(blessingImageView);
                    }
                    if (!skill1Image.equals("-")) {
                        loadImage(skill1Image, skill1ImageView);
                    } else {
                        Picasso.with(character_page.this).load(R.drawable.no_image).into(skill1ImageView);
                    }
                    if (!skill2Image.equals("-")) {
                        loadImage(skill2Image, skill2ImageView);
                    } else {
                        Picasso.with(character_page.this).load(R.drawable.no_image).into(skill2ImageView);
                    }
                    if (!ultImage.equals("-")) {
                        loadImage(ultImage, ultImageView);
                    } else {
                        Picasso.with(character_page.this).load(R.drawable.no_image).into(ultImageView);
                    }
                    TextView nameView = findViewById(R.id.character_name);
                    nameView.setText(name);
                    TextView ccView = findViewById(R.id.character_cc);
                    ccView.setText(maxCC);
                    TextView healthView = findViewById(R.id.character_health);
                    healthView.setText(maxHealth);
                    TextView attackView = findViewById(R.id.character_attack);
                    attackView.setText(maxAttack);
                    TextView defenseView = findViewById(R.id.character_defense);
                    defenseView.setText(maxDefence);
                    TextView pierceView = findViewById(R.id.character_pierce);
                    pierceView.setText(maxPierceRate + "%");
                    TextView resistanceView = findViewById(R.id.character_resistance);
                    resistanceView.setText(maxResistance + "%");
                    TextView regenView = findViewById(R.id.character_regen);
                    regenView.setText(maxRegenRate + "%");
                    TextView critCView = findViewById(R.id.character_crit_chance);
                    critCView.setText(maxCritChance + "%");
                    TextView critDView = findViewById(R.id.character_crit_damage);
                    critDView.setText(maxCritDamage + "%");
                    TextView critDefView = findViewById(R.id.character_crit_def);
                    critDefView.setText(maxCritDef + "%");
                    TextView critResView = findViewById(R.id.character_crit_res);
                    critResView.setText(maxCritRes + "%");
                    TextView recoveryView = findViewById(R.id.character_recovery_rate);
                    recoveryView.setText(maxRecoveryRate + "%");
                    TextView lifestealView = findViewById(R.id.character_lifesteal);
                    lifestealView.setText(maxLifesteal + "%");
                    TextView passiveNameView = findViewById(R.id.passive_name);
                    passiveNameView.setText(passiveName);
                    TextView passiveDescView = findViewById(R.id.passive_desc);
                    passiveDescView.setText(passiveDesc);
                    TextView commandmentNameView = findViewById(R.id.commandment_name);
                    commandmentNameView.setText(commandment);
                    TextView commandmentView = findViewById(R.id.commandment);
                    commandmentView.setText(commandmentEffect);
                    LinearLayout fullCommandment = findViewById(R.id.full_commandment);
                    if (commandment.equals("-")) {
                        fullCommandment.setVisibility(View.GONE);
                    }
                    TextView blessingNameView = findViewById(R.id.blessingt_name);
                    blessingNameView.setText(blessing);
                    TextView blessingView = findViewById(R.id.blessing);
                    blessingView.setText(blessingEffect);
                    LinearLayout fullBlessing = findViewById(R.id.full_blessing);
                    if (blessing.equals("-")) {
                        fullBlessing.setVisibility(View.GONE);
                    }
                    TextView skill1NameView = findViewById(R.id.skill_1_name);
                    skill1NameView.setText(skill1Name);
                    TextView skill11View = findViewById(R.id.skill11);
                    skill11View.setText(skill1Desc1);
                    TextView skill12View = findViewById(R.id.skill12);
                    skill12View.setText(skill1Desc2);
                    TextView skill13View = findViewById(R.id.skill13);
                    skill13View.setText(skill1Desc3);
                    TextView skill2NameView = findViewById(R.id.skill_2_name);
                    skill2NameView.setText(skill2Name);
                    TextView skill21View = findViewById(R.id.skill21);
                    skill21View.setText(skill2Desc1);
                    TextView skill22View = findViewById(R.id.skill22);
                    skill22View.setText(skill2Desc2);
                    TextView skill23View = findViewById(R.id.skill23);
                    skill23View.setText(skill2Desc3);
                    TextView ultimateName1View = findViewById(R.id.ultimate_name_1);
                    ultimateName1View.setText(ultimateName);
                    TextView ultimate1View = findViewById(R.id.ultimate_1);
                    ultimate1View.setText(ultimateDesc);
                    TextView ultimate1MultView = findViewById(R.id.ultimate_1_mult);
                    ultimate1MultView.setText(ultimateMulti);
                    TextView ultimateName2View = findViewById(R.id.ultimate_name_2);
                    TextView ultimate2View = findViewById(R.id.ultimate_2);
                    TextView ultimate2MultView = findViewById(R.id.ultimate_2_mult);
                    LinearLayout combinedUltimateView = findViewById(R.id.combined_ultimate);
                    if (fateName.equals("-")) {
                        combinedUltimateView.setVisibility(View.GONE);
                    } else {
                        ultimateName2View.setText(fateName);
                        ultimate2View.setText(fateDesc);
                        ultimate2MultView.setText(fateMulti);

                        combinedUltimateView.setVisibility(View.VISIBLE);

                    }

                    TextView awakening1HPView = findViewById(R.id.awakening1HP);
                    awakening1HPView.setText(awakening1HP);
                    TextView awakening1CritResView = findViewById(R.id.awakening1CritRes);
                    awakening1CritResView.setText(awakening1CritRes + "%");
                    TextView awakening1RecRateView = findViewById(R.id.awakening1RecRate);
                    awakening1RecRateView.setText(awakening1RecRate + "%");
                    TextView awakening2AtkView = findViewById(R.id.awakening2ATK);
                    awakening2AtkView.setText(awakening2Atk);
                    TextView awakening2DefView = findViewById(R.id.awakening2Def);
                    awakening2DefView.setText(awakening2Def);
                    TextView awakening2CritDmgView = findViewById(R.id.awakening2CritDamage);
                    awakening2CritDmgView.setText(awakening2CritDmg + "%");
                    TextView awakening3HPView = findViewById(R.id.awakening3HP);
                    awakening3HPView.setText(awakening3HP);
                    TextView awakening3RegenRateView = findViewById(R.id.awakening3RegenRate);
                    awakening3RegenRateView.setText(awakening3RegenRate + "%");
                    TextView awakening3CritDefView = findViewById(R.id.awakening3CritDef);
                    awakening3CritDefView.setText(awakening3CritDef + "%");
                    TextView awakening4AtkView = findViewById(R.id.awakening4ATK);
                    awakening4AtkView.setText(awakening4Atk);
                    TextView awakening4DefView = findViewById(R.id.awakening4Def);
                    awakening4DefView.setText(awakening4Def);
                    TextView awakening4CritChanceView = findViewById(R.id.awakening4CritChance);
                    awakening4CritChanceView.setText(awakening4CritChance + "%");
                    TextView awakening5HPView = findViewById(R.id.awakening5HP);
                    awakening5HPView.setText(awakening5HP);
                    TextView awakening5ResView = findViewById(R.id.awakening5Res);
                    awakening5ResView.setText(awakening5Res + "%");
                    TextView awakening5LifestealView = findViewById(R.id.awakening5Lifesteal);
                    awakening5LifestealView.setText(awakening5Lifesteal + "%");
                    TextView awakening6AtkView = findViewById(R.id.awakening6ATK);
                    awakening6AtkView.setText(awakening6Atk);
                    TextView awakening6DefView = findViewById(R.id.awakening6Def);
                    awakening6DefView.setText(awakening6Def);
                    TextView awakening6PierceView = findViewById(R.id.awakening6Pierce);
                    awakening6PierceView.setText(awakening6Pierce + "%");

                    TextView superawakening1HPView = findViewById(R.id.superawakening1HP);
                    superawakening1HPView.setText(superawakening1HP);
                    TextView superawakening1CritResView = findViewById(R.id.superawakening1CritRes);
                    superawakening1CritResView.setText(superawakening1CritRes + "%");
                    TextView superawakening1RecRateView = findViewById(R.id.superawakening1RecRate);
                    superawakening1RecRateView.setText(superawakening1RecRate + "%");
                    TextView superawakening2AtkView = findViewById(R.id.superawakening2ATK);
                    superawakening2AtkView.setText(superawakening2Atk);
                    TextView superawakening2DefView = findViewById(R.id.superawakening2Def);
                    superawakening2DefView.setText(superawakening2Def);
                    TextView superawakening2CritDmgView = findViewById(R.id.superawakening2CritDamage);
                    superawakening2CritDmgView.setText(superawakening2CritDmg + "%");
                    TextView superawakening3HPView = findViewById(R.id.superawakening3HP);
                    superawakening3HPView.setText(superawakening1HP);
                    TextView superawakening3RegenRateView = findViewById(R.id.superawakening3RegenRate);
                    superawakening3RegenRateView.setText(superawakening3RegenRate + "%");
                    TextView superawakening3CritDefView = findViewById(R.id.superawakening3CritDef);
                    superawakening3CritDefView.setText(superawakening3CritDef + "%");
                    TextView superawakening4AtkView = findViewById(R.id.superawakening4ATK);
                    superawakening4AtkView.setText(superawakening4Atk);
                    TextView superawakening4DefView = findViewById(R.id.superawakening4Def);
                    superawakening4DefView.setText(superawakening4Def);
                    TextView superawakening4CritChanceView = findViewById(R.id.superawakening4CritChance);
                    superawakening4CritChanceView.setText(superawakening4CritChance + "%");

                    LinearLayout allAssociates = findViewById(R.id.allAssociates);
                    ImageView associate1Image1View = findViewById(R.id.associate1Image1);
                    ImageView associate1Image2View = findViewById(R.id.associate1Image2);
                    ImageView associate1Image3View = findViewById(R.id.associate1Image3);
                    ImageView associate1Image4View = findViewById(R.id.associate1Image4);
                    if (!associate1Image1.equals("-")) {
                        loadImage(associate1Image1, associate1Image1View);
                        associate1Image1View.setVisibility(View.VISIBLE);
                    } else {
                        associate1Image1View.setVisibility(View.GONE);
                    }
                    if (!associate1Image2.equals("-")) {
                        loadImage(associate1Image2, associate1Image2View);
                        associate1Image2View.setVisibility(View.VISIBLE);
                    } else {
                        associate1Image2View.setVisibility(View.GONE);
                    }
                    if (!associate1Image3.equals("-")) {
                        loadImage(associate1Image3, associate1Image3View);
                        associate1Image3View.setVisibility(View.VISIBLE);
                    } else {
                        associate1Image3View.setVisibility(View.GONE);
                    }
                    if (!associate1Image4.equals("-")) {
                        loadImage(associate1Image4, associate1Image4View);
                        associate1Image4View.setVisibility(View.VISIBLE);
                    } else {
                        associate1Image4View.setVisibility(View.GONE);
                    }
                    TextView associate1NameView = findViewById(R.id.associate1);
                    associate1NameView.setText(associate1);
                    TextView associate1EffectView = findViewById(R.id.associate1Effect);
                    associate1EffectView.setText(associate1Effect);
                    ImageView associate2Image1View = findViewById(R.id.associate2Image1);
                    ImageView associate2Image2View = findViewById(R.id.associate2Image2);
                    ImageView associate2Image3View = findViewById(R.id.associate2Image3);
                    ImageView associate2Image4View = findViewById(R.id.associate2Image4);
                    if (!associate2Image1.equals("-")) {
                        loadImage(associate2Image1, associate2Image1View);
                        associate2Image1View.setVisibility(View.VISIBLE);
                    } else {
                        associate2Image1View.setVisibility(View.GONE);
                    }
                    if (!associate2Image2.equals("-")) {
                        loadImage(associate2Image2, associate2Image2View);
                        associate2Image2View.setVisibility(View.VISIBLE);
                    } else {
                        associate2Image2View.setVisibility(View.GONE);
                    }
                    if (!associate2Image3.equals("-")) {
                        loadImage(associate2Image3, associate2Image3View);
                        associate2Image3View.setVisibility(View.VISIBLE);
                    } else {
                        associate2Image3View.setVisibility(View.GONE);
                    }
                    if (!associate2Image4.equals("-")) {
                        loadImage(associate2Image4, associate2Image4View);
                        associate2Image4View.setVisibility(View.VISIBLE);
                    } else {
                        associate2Image4View.setVisibility(View.GONE);
                    }
                    TextView associate2NameView = findViewById(R.id.associate2);
                    associate2NameView.setText(associate2);
                    TextView associate2EffectView = findViewById(R.id.associate2Effect);
                    associate2EffectView.setText(associate2Effect);

                    ImageView associate3Image1View = findViewById(R.id.associate3Image1);
                    ImageView associate3Image2View = findViewById(R.id.associate3Image2);
                    ImageView associate3Image3View = findViewById(R.id.associate3Image3);
                    ImageView associate3Image4View = findViewById(R.id.associate3Image4);
                    if (!associate3Image1.equals("-")) {
                        loadImage(associate3Image1, associate3Image1View);
                        associate3Image1View.setVisibility(View.VISIBLE);
                    } else {
                        associate3Image1View.setVisibility(View.GONE);
                    }
                    if (!associate3Image2.equals("-")) {
                        loadImage(associate3Image2, associate3Image2View);
                        associate3Image2View.setVisibility(View.VISIBLE);
                    } else {
                        associate3Image2View.setVisibility(View.GONE);
                    }
                    if (!associate3Image3.equals("-")) {
                        loadImage(associate3Image3, associate3Image3View);
                        associate3Image3View.setVisibility(View.VISIBLE);
                    } else {
                        associate3Image3View.setVisibility(View.GONE);
                    }
                    if (!associate3Image4.equals("-")) {
                        loadImage(associate3Image4, associate3Image4View);
                        associate3Image4View.setVisibility(View.VISIBLE);
                    } else {
                        associate3Image4View.setVisibility(View.GONE);
                    }
                    TextView associate3NameView = findViewById(R.id.associate3);
                    associate3NameView.setText(associate3);
                    TextView associate3EffectView = findViewById(R.id.associate3Effect);
                    associate3EffectView.setText(associate3Effect);
                    ImageView associate4Image1View = findViewById(R.id.associate4Image1);
                    ImageView associate4Image2View = findViewById(R.id.associate4Image2);
                    ImageView associate4Image3View = findViewById(R.id.associate4Image3);
                    ImageView associate4Image4View = findViewById(R.id.associate4Image4);
                    if (!associate4Image1.equals("-")) {
                        loadImage(associate4Image1, associate4Image1View);
                        associate4Image1View.setVisibility(View.VISIBLE);
                    } else {
                        associate4Image1View.setVisibility(View.GONE);
                    }
                    if (!associate4Image2.equals("-")) {
                        loadImage(associate4Image2, associate4Image2View);
                        associate4Image2View.setVisibility(View.VISIBLE);
                    } else {
                        associate4Image2View.setVisibility(View.GONE);
                    }
                    if (!associate4Image3.equals("-")) {
                        loadImage(associate4Image3, associate4Image3View);
                        associate4Image3View.setVisibility(View.VISIBLE);
                    } else {
                        associate4Image3View.setVisibility(View.GONE);
                    }
                    if (!associate4Image4.equals("-")) {
                        loadImage(associate4Image4, associate4Image4View);
                        associate4Image4View.setVisibility(View.VISIBLE);
                    } else {
                        associate4Image4View.setVisibility(View.GONE);
                    }
                    TextView associate4NameView = findViewById(R.id.associate4);
                    associate4NameView.setText(associate4);
                    TextView associate4EffectView = findViewById(R.id.associate4Effect);
                    associate4EffectView.setText(associate4Effect);
                    ImageView associate5Image1View = findViewById(R.id.associate5Image1);
                    ImageView associate5Image2View = findViewById(R.id.associate5Image2);
                    ImageView associate5Image3View = findViewById(R.id.associate5Image3);
                    ImageView associate5Image4View = findViewById(R.id.associate5Image4);
                    if (!associate5Image1.equals("-")) {
                        loadImage(associate5Image1, associate5Image1View);
                        associate5Image1View.setVisibility(View.VISIBLE);
                    } else {
                        associate5Image1View.setVisibility(View.GONE);
                    }
                    if (!associate5Image2.equals("-")) {
                        loadImage(associate5Image2, associate5Image2View);
                        associate5Image2View.setVisibility(View.VISIBLE);
                    } else {
                        associate5Image2View.setVisibility(View.GONE);
                    }
                    if (!associate5Image3.equals("-")) {
                        loadImage(associate5Image3, associate5Image3View);
                        associate5Image3View.setVisibility(View.VISIBLE);
                    } else {
                        associate5Image3View.setVisibility(View.GONE);
                    }
                    if (!associate5Image4.equals("-")) {
                        loadImage(associate5Image4, associate5Image4View);
                        associate5Image4View.setVisibility(View.VISIBLE);
                    } else {
                        associate5Image4View.setVisibility(View.GONE);
                    }
                    TextView associate5NameView = findViewById(R.id.associate5);
                    associate5NameView.setText(associate5);
                    TextView associate5EffectView = findViewById(R.id.associate5Effect);
                    associate5EffectView.setText(associate5Effect);

                    LinearLayout fullAssociate1 = findViewById(R.id.fullAssociate1);
                    LinearLayout fullAssociate2 = findViewById(R.id.fullAssociate2);
                    LinearLayout fullAssociate3 = findViewById(R.id.fullAssociate3);
                    LinearLayout fullAssociate4 = findViewById(R.id.fullAssociate4);
                    LinearLayout fullAssociate5 = findViewById(R.id.fullAssociate5);

                    if (associate1.equals("null") || associate1.equals("-") || associate1.equals("") || associate1.equals("#N/A")) {
                        fullAssociate1.setVisibility(View.GONE);
                        fullAssociate2.setVisibility(View.GONE);
                        fullAssociate3.setVisibility(View.GONE);
                        fullAssociate4.setVisibility(View.GONE);
                        fullAssociate5.setVisibility(View.GONE);
                    } else {
                        fullAssociate1.setVisibility(View.VISIBLE);
                    }

                    if (associate2.equals("null") || associate2.equals("-") || associate2.equals("") || associate2.equals("#N/A")) {
                        fullAssociate2.setVisibility(View.GONE);
                        fullAssociate3.setVisibility(View.GONE);
                        fullAssociate4.setVisibility(View.GONE);
                        fullAssociate5.setVisibility(View.GONE);
                    } else {
                        fullAssociate2.setVisibility(View.VISIBLE);
                    }

                    if (associate3.equals("null") || associate3.equals("-") || associate3.equals("") || associate3.equals("#N/A")) {
                        fullAssociate3.setVisibility(View.GONE);
                        fullAssociate4.setVisibility(View.GONE);
                        fullAssociate5.setVisibility(View.GONE);
                    } else {
                        fullAssociate3.setVisibility(View.VISIBLE);
                    }

                    if (associate4.equals("null") || associate4.equals("-") || associate4.equals("") || associate4.equals("#N/A")) {
                        fullAssociate4.setVisibility(View.GONE);
                        fullAssociate5.setVisibility(View.GONE);
                    } else {
                        fullAssociate4.setVisibility(View.VISIBLE);
                    }

                    if (associate5.equals("null") || associate5.equals("-") || associate5.equals("") || associate5.equals("#N/A")) {
                        fullAssociate5.setVisibility(View.GONE);
                    } else {
                        fullAssociate5.setVisibility(View.VISIBLE);
                    }

                    TextView baseORmax = findViewById(R.id.base_or_max);
                    baseORmax.setText("Showing Max Stats");
                    resizeExpandableLayouts();
                }
                if(tpassiveDesc.equals("-") || tpassiveDesc.equals("")) {
                    transformations.setVisibility(View.GONE);
                }
                else {
                    transformations.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }

    public void moreInfo(View view) {
        Intent help = new Intent(character_page.this, help_menu.class);
        startActivity(help);
    }

    public void awakeningCost(View view) {
        Intent costInfo = new Intent(character_page.this, awakening.class);
        startActivity(costInfo);
    }

    public void loadImage(final String image, final ImageView view) {
        Picasso.with(character_page.this).setIndicatorsEnabled(false);
        Picasso.with(character_page.this)
                .load(image)
                .networkPolicy(NetworkPolicy.OFFLINE)
                .into(view, new Callback() {
                    @Override
                    public void onSuccess() {

                    }

                    @Override
                    public void onError() {
                        //Try again online if cache failed
                        Picasso.with(character_page.this)
                                .load(image)
                                .error(R.drawable.offline_error)
                                .into(view, new Callback() {
                                    @Override
                                    public void onSuccess() {

                                    }

                                    @Override
                                    public void onError() {
                                        Log.v("Picasso", "Could not fetch image");
                                    }
                                });
                    }
                });
        resizeExpandableLayouts();
    }

    public void resizeExpandableLayouts() {
        ExpandableLinearLayout passiveView = findViewById(R.id.passive);
        passiveView.initLayout();
        ExpandableLinearLayout commandmentExpandView = findViewById(R.id.commandment_expand);
        commandmentExpandView.initLayout();
        ExpandableLinearLayout blessingExpandView = findViewById(R.id.blessing_expand);
        blessingExpandView.initLayout();
        ExpandableLinearLayout skill1View = findViewById(R.id.skill1);
        skill1View.initLayout();
        ExpandableLinearLayout skill2View = findViewById(R.id.skill2);
        skill2View.initLayout();
        ExpandableLinearLayout ultimatesView = findViewById(R.id.ultimates);
        ultimatesView.initLayout();
        ExpandableLinearLayout associationsView = findViewById(R.id.assosiations);
        associationsView.initLayout();
    }

    @Override
    public void onBackPressed() {
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt("listClicked", 1);
        editor.apply();
        super.onBackPressed();
    }
}
