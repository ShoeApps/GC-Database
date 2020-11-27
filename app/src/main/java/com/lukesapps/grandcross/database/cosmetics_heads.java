package com.lukesapps.grandcross.database;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.util.Objects;

public class cosmetics_heads extends Fragment {

    public static final String PREFS_NAME = "MyPrefsFile";
    SharedPreferences settings;
    SharedPreferences.Editor editor;
    String selectedCharName;
    int characters = 2;
    int one = 0;
    DatabaseReference database;
    String characterName = "";
    int cosmeticCount = 0;
    ImageView cosmeticsRarity;
    ImageView cosmeticImage;
    TextView cosmeticsStat1;
    TextView cosmeticsStat2;
    TextView cosmeticsStat3;
    TextView cosmeticsStat4;
    TextView cosmeticsCC;
    TextView cosmeticsObtained;
    RelativeLayout cosmeticsFull1;
    ImageView cosmeticsRarity1;
    ImageView cosmeticImage1;
    TextView cosmeticsStat11;
    TextView cosmeticsStat21;
    TextView cosmeticsStat31;
    TextView cosmeticsStat41;
    RelativeLayout cosmeticsFull2;
    ImageView cosmeticsRarity2;
    ImageView cosmeticImage2;
    TextView cosmeticsStat12;
    TextView cosmeticsStat22;
    TextView cosmeticsStat32;
    TextView cosmeticsStat42;
    RelativeLayout cosmeticsFull3;
    ImageView cosmeticsRarity3;
    ImageView cosmeticImage3;
    TextView cosmeticsStat13;
    TextView cosmeticsStat23;
    TextView cosmeticsStat33;
    TextView cosmeticsStat43;
    RelativeLayout cosmeticsFull4;
    ImageView cosmeticsRarity4;
    ImageView cosmeticImage4;
    TextView cosmeticsStat14;
    TextView cosmeticsStat24;
    TextView cosmeticsStat34;
    TextView cosmeticsStat44;
    RelativeLayout cosmeticsFull5;
    ImageView cosmeticsRarity5;
    ImageView cosmeticImage5;
    TextView cosmeticsStat15;
    TextView cosmeticsStat25;
    TextView cosmeticsStat35;
    TextView cosmeticsStat45;
    RelativeLayout cosmeticsFull6;
    ImageView cosmeticsRarity6;
    ImageView cosmeticImage6;
    TextView cosmeticsStat16;
    TextView cosmeticsStat26;
    TextView cosmeticsStat36;
    TextView cosmeticsStat46;
    RelativeLayout cosmeticsFull7;
    ImageView cosmeticsRarity7;
    ImageView cosmeticImage7;
    TextView cosmeticsStat17;
    TextView cosmeticsStat27;
    TextView cosmeticsStat37;
    TextView cosmeticsStat47;
    RelativeLayout cosmeticsFull8;
    ImageView cosmeticsRarity8;
    ImageView cosmeticImage8;
    TextView cosmeticsStat18;
    TextView cosmeticsStat28;
    TextView cosmeticsStat38;
    TextView cosmeticsStat48;
    RelativeLayout cosmeticsFull9;
    ImageView cosmeticsRarity9;
    ImageView cosmeticImage9;
    TextView cosmeticsStat19;
    TextView cosmeticsStat29;
    TextView cosmeticsStat39;
    TextView cosmeticsStat49;
    RelativeLayout cosmeticsFull10;
    ImageView cosmeticsRarity10;
    ImageView cosmeticImage10;
    TextView cosmeticsStat110;
    TextView cosmeticsStat210;
    TextView cosmeticsStat310;
    TextView cosmeticsStat410;
    RelativeLayout cosmeticsFull11;
    ImageView cosmeticsRarity11;
    ImageView cosmeticImage11;
    TextView cosmeticsStat111;
    TextView cosmeticsStat211;
    TextView cosmeticsStat311;
    TextView cosmeticsStat411;
    RelativeLayout cosmeticsFull12;
    ImageView cosmeticsRarity12;
    ImageView cosmeticImage12;
    TextView cosmeticsStat112;
    TextView cosmeticsStat212;
    TextView cosmeticsStat312;
    TextView cosmeticsStat412;
    RelativeLayout cosmeticsFull13;
    ImageView cosmeticsRarity13;
    ImageView cosmeticImage13;
    TextView cosmeticsStat113;
    TextView cosmeticsStat213;
    TextView cosmeticsStat313;
    TextView cosmeticsStat413;
    RelativeLayout cosmeticsFull14;
    ImageView cosmeticsRarity14;
    ImageView cosmeticImage14;
    TextView cosmeticsStat114;
    TextView cosmeticsStat214;
    TextView cosmeticsStat314;
    TextView cosmeticsStat414;
    RelativeLayout cosmeticsFull15;
    ImageView cosmeticsRarity15;
    ImageView cosmeticImage15;
    TextView cosmeticsStat115;
    TextView cosmeticsStat215;
    TextView cosmeticsStat315;
    TextView cosmeticsStat415;
    RelativeLayout cosmeticsFull16;
    ImageView cosmeticsRarity16;
    ImageView cosmeticImage16;
    TextView cosmeticsStat116;
    TextView cosmeticsStat216;
    TextView cosmeticsStat316;
    TextView cosmeticsStat416;
    RelativeLayout cosmeticsFull17;
    ImageView cosmeticsRarity17;
    ImageView cosmeticImage17;
    TextView cosmeticsStat117;
    TextView cosmeticsStat217;
    TextView cosmeticsStat317;
    TextView cosmeticsStat417;
    RelativeLayout cosmeticsFull18;
    ImageView cosmeticsRarity18;
    ImageView cosmeticImage18;
    TextView cosmeticsStat118;
    TextView cosmeticsStat218;
    TextView cosmeticsStat318;
    TextView cosmeticsStat418;
    RelativeLayout cosmeticsFull19;
    ImageView cosmeticsRarity19;
    ImageView cosmeticImage19;
    TextView cosmeticsStat119;
    TextView cosmeticsStat219;
    TextView cosmeticsStat319;
    TextView cosmeticsStat419;
    RelativeLayout cosmeticsFull20;
    ImageView cosmeticsRarity20;
    ImageView cosmeticImage20;
    TextView cosmeticsStat120;
    TextView cosmeticsStat220;
    TextView cosmeticsStat320;
    TextView cosmeticsStat420;
    TextView cosmeticscc1;
    TextView cosmeticscc2;
    TextView cosmeticscc3;
    TextView cosmeticscc4;
    TextView cosmeticscc5;
    TextView cosmeticscc6;
    TextView cosmeticscc7;
    TextView cosmeticscc8;
    TextView cosmeticscc9;
    TextView cosmeticscc10;
    TextView cosmeticscc11;
    TextView cosmeticscc12;
    TextView cosmeticscc13;
    TextView cosmeticscc14;
    TextView cosmeticscc15;
    TextView cosmeticscc16;
    TextView cosmeticscc17;
    TextView cosmeticscc18;
    TextView cosmeticscc19;
    TextView cosmeticscc20;
    TextView cosmeticsobtained1;
    TextView cosmeticsobtained2;
    TextView cosmeticsobtained3;
    TextView cosmeticsobtained4;
    TextView cosmeticsobtained5;
    TextView cosmeticsobtained6;
    TextView cosmeticsobtained7;
    TextView cosmeticsobtained8;
    TextView cosmeticsobtained9;
    TextView cosmeticsobtained10;
    TextView cosmeticsobtained11;
    TextView cosmeticsobtained12;
    TextView cosmeticsobtained13;
    TextView cosmeticsobtained14;
    TextView cosmeticsobtained15;
    TextView cosmeticsobtained16;
    TextView cosmeticsobtained17;
    TextView cosmeticsobtained18;
    TextView cosmeticsobtained19;
    TextView cosmeticsobtained20;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.cosmetics_heads_layout, container, false);
        cosmeticsFull1 = v.findViewById(R.id.cosmetic_1_full);
        cosmeticsFull2 = v.findViewById(R.id.cosmetic_2_full);
        cosmeticsFull3 = v.findViewById(R.id.cosmetic_3_full);
        cosmeticsFull4 = v.findViewById(R.id.cosmetic_4_full);
        cosmeticsFull5 = v.findViewById(R.id.cosmetic_5_full);
        cosmeticsFull6 = v.findViewById(R.id.cosmetic_6_full);
        cosmeticsFull7 = v.findViewById(R.id.cosmetic_7_full);
        cosmeticsFull8 = v.findViewById(R.id.cosmetic_8_full);
        cosmeticsFull9 = v.findViewById(R.id.cosmetic_9_full);
        cosmeticsFull10 = v.findViewById(R.id.cosmetic_10_full);
        cosmeticsFull11 = v.findViewById(R.id.cosmetic_11_full);
        cosmeticsFull12 = v.findViewById(R.id.cosmetic_12_full);
        cosmeticsFull13 = v.findViewById(R.id.cosmetic_13_full);
        cosmeticsFull14 = v.findViewById(R.id.cosmetic_14_full);
        cosmeticsFull15 = v.findViewById(R.id.cosmetic_15_full);
        cosmeticsFull16 = v.findViewById(R.id.cosmetic_16_full);
        cosmeticsFull17 = v.findViewById(R.id.cosmetic_17_full);
        cosmeticsFull18 = v.findViewById(R.id.cosmetic_18_full);
        cosmeticsFull19 = v.findViewById(R.id.cosmetic_19_full);
        cosmeticsFull20 = v.findViewById(R.id.cosmetic_20_full);
        cosmeticsFull1.setVisibility(View.GONE);
        cosmeticsFull2.setVisibility(View.GONE);
        cosmeticsFull3.setVisibility(View.GONE);
        cosmeticsFull4.setVisibility(View.GONE);
        cosmeticsFull5.setVisibility(View.GONE);
        cosmeticsFull6.setVisibility(View.GONE);
        cosmeticsFull7.setVisibility(View.GONE);
        cosmeticsFull8.setVisibility(View.GONE);
        cosmeticsFull9.setVisibility(View.GONE);
        cosmeticsFull10.setVisibility(View.GONE);
        cosmeticsFull11.setVisibility(View.GONE);
        cosmeticsFull12.setVisibility(View.GONE);
        cosmeticsFull13.setVisibility(View.GONE);
        cosmeticsFull14.setVisibility(View.GONE);
        cosmeticsFull15.setVisibility(View.GONE);
        cosmeticsFull16.setVisibility(View.GONE);
        cosmeticsFull17.setVisibility(View.GONE);
        cosmeticsFull18.setVisibility(View.GONE);
        cosmeticsFull19.setVisibility(View.GONE);
        cosmeticsFull20.setVisibility(View.GONE);
        cosmeticsRarity1 = v.findViewById(R.id.cosmetic_1_rarity);
        cosmeticImage1 = v.findViewById(R.id.cosmetic_1_image);
        cosmeticsStat11 = v.findViewById(R.id.cosmetic_1_stat1);
        cosmeticsStat21 = v.findViewById(R.id.cosmetic_1_stat2);
        cosmeticsStat31 = v.findViewById(R.id.cosmetic_1_stat3);
        cosmeticsStat41 = v.findViewById(R.id.cosmetic_1_stat4);
        cosmeticsRarity2 = v.findViewById(R.id.cosmetic_2_rarity);
        cosmeticImage2 = v.findViewById(R.id.cosmetic_2_image);
        cosmeticsStat12 = v.findViewById(R.id.cosmetic_2_stat1);
        cosmeticsStat22 = v.findViewById(R.id.cosmetic_2_stat2);
        cosmeticsStat32 = v.findViewById(R.id.cosmetic_2_stat3);
        cosmeticsStat42 = v.findViewById(R.id.cosmetic_2_stat4);
        cosmeticsRarity3 = v.findViewById(R.id.cosmetic_3_rarity);
        cosmeticImage3 = v.findViewById(R.id.cosmetic_3_image);
        cosmeticsStat13 = v.findViewById(R.id.cosmetic_3_stat1);
        cosmeticsStat23 = v.findViewById(R.id.cosmetic_3_stat2);
        cosmeticsStat33 = v.findViewById(R.id.cosmetic_3_stat3);
        cosmeticsStat43 = v.findViewById(R.id.cosmetic_3_stat4);
        cosmeticsRarity4 = v.findViewById(R.id.cosmetic_4_rarity);
        cosmeticImage4 = v.findViewById(R.id.cosmetic_4_image);
        cosmeticsStat14 = v.findViewById(R.id.cosmetic_4_stat1);
        cosmeticsStat24 = v.findViewById(R.id.cosmetic_4_stat2);
        cosmeticsStat34 = v.findViewById(R.id.cosmetic_4_stat3);
        cosmeticsStat44 = v.findViewById(R.id.cosmetic_4_stat4);
        cosmeticsRarity5 = v.findViewById(R.id.cosmetic_5_rarity);
        cosmeticImage5 = v.findViewById(R.id.cosmetic_5_image);
        cosmeticsStat15 = v.findViewById(R.id.cosmetic_5_stat1);
        cosmeticsStat25 = v.findViewById(R.id.cosmetic_5_stat2);
        cosmeticsStat35 = v.findViewById(R.id.cosmetic_5_stat3);
        cosmeticsStat45 = v.findViewById(R.id.cosmetic_5_stat4);
        cosmeticsRarity6 = v.findViewById(R.id.cosmetic_6_rarity);
        cosmeticImage6 = v.findViewById(R.id.cosmetic_6_image);
        cosmeticsStat16 = v.findViewById(R.id.cosmetic_6_stat1);
        cosmeticsStat26 = v.findViewById(R.id.cosmetic_6_stat2);
        cosmeticsStat36 = v.findViewById(R.id.cosmetic_6_stat3);
        cosmeticsStat46 = v.findViewById(R.id.cosmetic_6_stat4);
        cosmeticsRarity7 = v.findViewById(R.id.cosmetic_7_rarity);
        cosmeticImage7 = v.findViewById(R.id.cosmetic_7_image);
        cosmeticsStat17 = v.findViewById(R.id.cosmetic_7_stat1);
        cosmeticsStat27 = v.findViewById(R.id.cosmetic_7_stat2);
        cosmeticsStat37 = v.findViewById(R.id.cosmetic_7_stat3);
        cosmeticsStat47 = v.findViewById(R.id.cosmetic_7_stat4);
        cosmeticsRarity8 = v.findViewById(R.id.cosmetic_8_rarity);
        cosmeticImage8 = v.findViewById(R.id.cosmetic_8_image);
        cosmeticsStat18 = v.findViewById(R.id.cosmetic_8_stat1);
        cosmeticsStat28 = v.findViewById(R.id.cosmetic_8_stat2);
        cosmeticsStat38 = v.findViewById(R.id.cosmetic_8_stat3);
        cosmeticsStat48 = v.findViewById(R.id.cosmetic_8_stat4);
        cosmeticsRarity9 = v.findViewById(R.id.cosmetic_9_rarity);
        cosmeticImage9 = v.findViewById(R.id.cosmetic_9_image);
        cosmeticsStat19 = v.findViewById(R.id.cosmetic_9_stat1);
        cosmeticsStat29 = v.findViewById(R.id.cosmetic_9_stat2);
        cosmeticsStat39 = v.findViewById(R.id.cosmetic_9_stat3);
        cosmeticsStat49 = v.findViewById(R.id.cosmetic_9_stat4);
        cosmeticsRarity10 = v.findViewById(R.id.cosmetic_10_rarity);
        cosmeticImage10 = v.findViewById(R.id.cosmetic_10_image);
        cosmeticsStat110 = v.findViewById(R.id.cosmetic_10_stat1);
        cosmeticsStat210 = v.findViewById(R.id.cosmetic_10_stat2);
        cosmeticsStat310 = v.findViewById(R.id.cosmetic_10_stat3);
        cosmeticsStat410 = v.findViewById(R.id.cosmetic_10_stat4);
        cosmeticsRarity11 = v.findViewById(R.id.cosmetic_11_rarity);
        cosmeticImage11 = v.findViewById(R.id.cosmetic_11_image);
        cosmeticsStat111 = v.findViewById(R.id.cosmetic_11_stat1);
        cosmeticsStat211 = v.findViewById(R.id.cosmetic_11_stat2);
        cosmeticsStat311 = v.findViewById(R.id.cosmetic_11_stat3);
        cosmeticsStat411 = v.findViewById(R.id.cosmetic_11_stat4);
        cosmeticsRarity12 = v.findViewById(R.id.cosmetic_12_rarity);
        cosmeticImage12 = v.findViewById(R.id.cosmetic_12_image);
        cosmeticsStat112 = v.findViewById(R.id.cosmetic_12_stat1);
        cosmeticsStat212 = v.findViewById(R.id.cosmetic_12_stat2);
        cosmeticsStat312 = v.findViewById(R.id.cosmetic_12_stat3);
        cosmeticsStat412 = v.findViewById(R.id.cosmetic_12_stat4);
        cosmeticsRarity13 = v.findViewById(R.id.cosmetic_13_rarity);
        cosmeticImage13 = v.findViewById(R.id.cosmetic_13_image);
        cosmeticsStat113 = v.findViewById(R.id.cosmetic_13_stat1);
        cosmeticsStat213 = v.findViewById(R.id.cosmetic_13_stat2);
        cosmeticsStat313 = v.findViewById(R.id.cosmetic_13_stat3);
        cosmeticsStat413 = v.findViewById(R.id.cosmetic_13_stat4);
        cosmeticsRarity14 = v.findViewById(R.id.cosmetic_14_rarity);
        cosmeticImage14 = v.findViewById(R.id.cosmetic_14_image);
        cosmeticsStat114 = v.findViewById(R.id.cosmetic_14_stat1);
        cosmeticsStat214 = v.findViewById(R.id.cosmetic_14_stat2);
        cosmeticsStat314 = v.findViewById(R.id.cosmetic_14_stat3);
        cosmeticsStat414 = v.findViewById(R.id.cosmetic_14_stat4);
        cosmeticsRarity15 = v.findViewById(R.id.cosmetic_15_rarity);
        cosmeticImage15 = v.findViewById(R.id.cosmetic_15_image);
        cosmeticsStat115 = v.findViewById(R.id.cosmetic_15_stat1);
        cosmeticsStat215 = v.findViewById(R.id.cosmetic_15_stat2);
        cosmeticsStat315 = v.findViewById(R.id.cosmetic_15_stat3);
        cosmeticsStat415 = v.findViewById(R.id.cosmetic_15_stat4);
        cosmeticsRarity16 = v.findViewById(R.id.cosmetic_16_rarity);
        cosmeticImage16 = v.findViewById(R.id.cosmetic_16_image);
        cosmeticsStat116 = v.findViewById(R.id.cosmetic_16_stat1);
        cosmeticsStat216 = v.findViewById(R.id.cosmetic_16_stat2);
        cosmeticsStat316 = v.findViewById(R.id.cosmetic_16_stat3);
        cosmeticsStat416 = v.findViewById(R.id.cosmetic_16_stat4);
        cosmeticsRarity17 = v.findViewById(R.id.cosmetic_17_rarity);
        cosmeticImage17 = v.findViewById(R.id.cosmetic_17_image);
        cosmeticsStat117 = v.findViewById(R.id.cosmetic_17_stat1);
        cosmeticsStat217 = v.findViewById(R.id.cosmetic_17_stat2);
        cosmeticsStat317 = v.findViewById(R.id.cosmetic_17_stat3);
        cosmeticsStat417 = v.findViewById(R.id.cosmetic_17_stat4);
        cosmeticsRarity18 = v.findViewById(R.id.cosmetic_18_rarity);
        cosmeticImage18 = v.findViewById(R.id.cosmetic_18_image);
        cosmeticsStat118 = v.findViewById(R.id.cosmetic_18_stat1);
        cosmeticsStat218 = v.findViewById(R.id.cosmetic_18_stat2);
        cosmeticsStat318 = v.findViewById(R.id.cosmetic_18_stat3);
        cosmeticsStat418 = v.findViewById(R.id.cosmetic_18_stat4);
        cosmeticsRarity19 = v.findViewById(R.id.cosmetic_19_rarity);
        cosmeticImage19 = v.findViewById(R.id.cosmetic_19_image);
        cosmeticsStat119 = v.findViewById(R.id.cosmetic_19_stat1);
        cosmeticsStat219 = v.findViewById(R.id.cosmetic_19_stat2);
        cosmeticsStat319 = v.findViewById(R.id.cosmetic_19_stat3);
        cosmeticsStat419 = v.findViewById(R.id.cosmetic_19_stat4);
        cosmeticsRarity20 = v.findViewById(R.id.cosmetic_20_rarity);
        cosmeticImage20 = v.findViewById(R.id.cosmetic_20_image);
        cosmeticsStat120 = v.findViewById(R.id.cosmetic_20_stat1);
        cosmeticsStat220 = v.findViewById(R.id.cosmetic_20_stat2);
        cosmeticsStat320 = v.findViewById(R.id.cosmetic_20_stat3);
        cosmeticsStat420 = v.findViewById(R.id.cosmetic_20_stat4);
        cosmeticscc1 = v.findViewById(R.id.cosmetic_1_cc);
        cosmeticscc2 = v.findViewById(R.id.cosmetic_2_cc);
        cosmeticscc3 = v.findViewById(R.id.cosmetic_3_cc);
        cosmeticscc4 = v.findViewById(R.id.cosmetic_4_cc);
        cosmeticscc5 = v.findViewById(R.id.cosmetic_5_cc);
        cosmeticscc6 = v.findViewById(R.id.cosmetic_6_cc);
        cosmeticscc7 = v.findViewById(R.id.cosmetic_7_cc);
        cosmeticscc8 = v.findViewById(R.id.cosmetic_8_cc);
        cosmeticscc9 = v.findViewById(R.id.cosmetic_9_cc);
        cosmeticscc10 = v.findViewById(R.id.cosmetic_10_cc);
        cosmeticscc11 = v.findViewById(R.id.cosmetic_11_cc);
        cosmeticscc12 = v.findViewById(R.id.cosmetic_12_cc);
        cosmeticscc13 = v.findViewById(R.id.cosmetic_13_cc);
        cosmeticscc14 = v.findViewById(R.id.cosmetic_14_cc);
        cosmeticscc15 = v.findViewById(R.id.cosmetic_15_cc);
        cosmeticscc16 = v.findViewById(R.id.cosmetic_16_cc);
        cosmeticscc17 = v.findViewById(R.id.cosmetic_17_cc);
        cosmeticscc18 = v.findViewById(R.id.cosmetic_18_cc);
        cosmeticscc19 = v.findViewById(R.id.cosmetic_19_cc);
        cosmeticscc20 = v.findViewById(R.id.cosmetic_20_cc);
        cosmeticsobtained1 = v.findViewById(R.id.cosmetic_1_obtained);
        cosmeticsobtained2 = v.findViewById(R.id.cosmetic_2_obtained);
        cosmeticsobtained3 = v.findViewById(R.id.cosmetic_3_obtained);
        cosmeticsobtained4 = v.findViewById(R.id.cosmetic_4_obtained);
        cosmeticsobtained5 = v.findViewById(R.id.cosmetic_5_obtained);
        cosmeticsobtained6 = v.findViewById(R.id.cosmetic_6_obtained);
        cosmeticsobtained7 = v.findViewById(R.id.cosmetic_7_obtained);
        cosmeticsobtained8 = v.findViewById(R.id.cosmetic_8_obtained);
        cosmeticsobtained9 = v.findViewById(R.id.cosmetic_9_obtained);
        cosmeticsobtained10 = v.findViewById(R.id.cosmetic_10_obtained);
        cosmeticsobtained11 = v.findViewById(R.id.cosmetic_11_obtained);
        cosmeticsobtained12 = v.findViewById(R.id.cosmetic_12_obtained);
        cosmeticsobtained13 = v.findViewById(R.id.cosmetic_13_obtained);
        cosmeticsobtained14 = v.findViewById(R.id.cosmetic_14_obtained);
        cosmeticsobtained15 = v.findViewById(R.id.cosmetic_15_obtained);
        cosmeticsobtained16 = v.findViewById(R.id.cosmetic_16_obtained);
        cosmeticsobtained17 = v.findViewById(R.id.cosmetic_17_obtained);
        cosmeticsobtained18 = v.findViewById(R.id.cosmetic_18_obtained);
        cosmeticsobtained19 = v.findViewById(R.id.cosmetic_19_obtained);
        cosmeticsobtained20 = v.findViewById(R.id.cosmetic_20_obtained);
        return v;
    }

    public void onCreate(Bundle savedInstanceState) {
        database = FirebaseDatabase.getInstance("https://ds-grand-cross-database-545c9.firebaseio.com/").getReference();
        database.keepSynced(true);
        super.onCreate(savedInstanceState);
        settings = Objects.requireNonNull(this.getActivity()).getSharedPreferences(PREFS_NAME, 0);
        editor = settings.edit();
        selectedCharName = settings.getString("selectedCharName", "");
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
                        String image = Objects.requireNonNull(dataSnapshot.child(selectId).child("image").getValue()).toString();
                        String type = Objects.requireNonNull(dataSnapshot.child(selectId).child("type").getValue()).toString();
                        String hp = Objects.requireNonNull(dataSnapshot.child(selectId).child("hp").getValue()).toString();
                        String reg = Objects.requireNonNull(dataSnapshot.child(selectId).child("reg").getValue()).toString();
                        String rec = Objects.requireNonNull(dataSnapshot.child(selectId).child("rec").getValue()).toString();
                        String lifesteal = Objects.requireNonNull(dataSnapshot.child(selectId).child("lifesteal").getValue()).toString();
                        String cc = Objects.requireNonNull(dataSnapshot.child(selectId).child("cc").getValue()).toString();
                        String obtained = Objects.requireNonNull(dataSnapshot.child(selectId).child("obtained").getValue()).toString();
                        if (selectedCharName.equals(charName)) {
                            if (one == 0) {
                                characterName = charName;
                                one = 1;
                            }
                            if(charName.equals(characterName)) {
                                if (type.equals("Heads")) {
                                    cosmeticCount = cosmeticCount + 1;
                                    switch (cosmeticCount) {
                                        case 1:
                                            cosmeticsFull1.setVisibility(View.VISIBLE);
                                            cosmeticsFull2.setVisibility(View.INVISIBLE);
                                            cosmeticsRarity = cosmeticsRarity1;
                                            cosmeticImage = cosmeticImage1;
                                            cosmeticsStat1 = cosmeticsStat11;
                                            cosmeticsStat2 = cosmeticsStat21;
                                            cosmeticsStat3 = cosmeticsStat31;
                                            cosmeticsStat4 = cosmeticsStat41;
                                            cosmeticsCC = cosmeticscc1;
                                            cosmeticsObtained = cosmeticsobtained1;
                                            break;
                                        case 2:
                                            cosmeticsFull2.setVisibility(View.VISIBLE);
                                            cosmeticsRarity = cosmeticsRarity2;
                                            cosmeticImage = cosmeticImage2;
                                            cosmeticsStat1 = cosmeticsStat12;
                                            cosmeticsStat2 = cosmeticsStat22;
                                            cosmeticsStat3 = cosmeticsStat32;
                                            cosmeticsStat4 = cosmeticsStat42;
                                            cosmeticsCC = cosmeticscc2;
                                            cosmeticsObtained = cosmeticsobtained2;
                                            break;
                                        case 3:
                                            cosmeticsFull3.setVisibility(View.VISIBLE);
                                            cosmeticsFull4.setVisibility(View.INVISIBLE);
                                            cosmeticsRarity = cosmeticsRarity3;
                                            cosmeticImage = cosmeticImage3;
                                            cosmeticsStat1 = cosmeticsStat13;
                                            cosmeticsStat2 = cosmeticsStat23;
                                            cosmeticsStat3 = cosmeticsStat33;
                                            cosmeticsStat4 = cosmeticsStat43;
                                            cosmeticsCC = cosmeticscc3;
                                            cosmeticsObtained = cosmeticsobtained3;
                                            break;
                                        case 4:
                                            cosmeticsFull4.setVisibility(View.VISIBLE);
                                            cosmeticsRarity = cosmeticsRarity4;
                                            cosmeticImage = cosmeticImage4;
                                            cosmeticsStat1 = cosmeticsStat14;
                                            cosmeticsStat2 = cosmeticsStat24;
                                            cosmeticsStat3 = cosmeticsStat34;
                                            cosmeticsStat4 = cosmeticsStat44;
                                            cosmeticsCC = cosmeticscc4;
                                            cosmeticsObtained = cosmeticsobtained4;
                                            break;
                                        case 5:
                                            cosmeticsFull5.setVisibility(View.VISIBLE);
                                            cosmeticsFull6.setVisibility(View.INVISIBLE);
                                            cosmeticsRarity = cosmeticsRarity5;
                                            cosmeticImage = cosmeticImage5;
                                            cosmeticsStat1 = cosmeticsStat15;
                                            cosmeticsStat2 = cosmeticsStat25;
                                            cosmeticsStat3 = cosmeticsStat35;
                                            cosmeticsStat4 = cosmeticsStat45;
                                            cosmeticsCC = cosmeticscc5;
                                            cosmeticsObtained = cosmeticsobtained5;
                                            break;
                                        case 6:
                                            cosmeticsFull6.setVisibility(View.VISIBLE);
                                            cosmeticsRarity = cosmeticsRarity6;
                                            cosmeticImage = cosmeticImage6;
                                            cosmeticsStat1 = cosmeticsStat16;
                                            cosmeticsStat2 = cosmeticsStat26;
                                            cosmeticsStat3 = cosmeticsStat36;
                                            cosmeticsStat4 = cosmeticsStat46;
                                            cosmeticsCC = cosmeticscc6;
                                            cosmeticsObtained = cosmeticsobtained6;
                                            break;
                                        case 7:
                                            cosmeticsFull7.setVisibility(View.VISIBLE);
                                            cosmeticsFull8.setVisibility(View.INVISIBLE);
                                            cosmeticsRarity = cosmeticsRarity7;
                                            cosmeticImage = cosmeticImage7;
                                            cosmeticsStat1 = cosmeticsStat17;
                                            cosmeticsStat2 = cosmeticsStat27;
                                            cosmeticsStat3 = cosmeticsStat37;
                                            cosmeticsStat4 = cosmeticsStat47;
                                            cosmeticsCC = cosmeticscc7;
                                            cosmeticsObtained = cosmeticsobtained7;
                                            break;
                                        case 8:
                                            cosmeticsFull8.setVisibility(View.VISIBLE);
                                            cosmeticsRarity = cosmeticsRarity8;
                                            cosmeticImage = cosmeticImage8;
                                            cosmeticsStat1 = cosmeticsStat18;
                                            cosmeticsStat2 = cosmeticsStat28;
                                            cosmeticsStat3 = cosmeticsStat38;
                                            cosmeticsStat4 = cosmeticsStat48;
                                            cosmeticsCC = cosmeticscc8;
                                            cosmeticsObtained = cosmeticsobtained8;
                                            break;
                                        case 9:
                                            cosmeticsFull9.setVisibility(View.VISIBLE);
                                            cosmeticsFull10.setVisibility(View.INVISIBLE);
                                            cosmeticsRarity = cosmeticsRarity9;
                                            cosmeticImage = cosmeticImage9;
                                            cosmeticsStat1 = cosmeticsStat19;
                                            cosmeticsStat2 = cosmeticsStat29;
                                            cosmeticsStat3 = cosmeticsStat39;
                                            cosmeticsStat4 = cosmeticsStat49;
                                            cosmeticsCC = cosmeticscc9;
                                            cosmeticsObtained = cosmeticsobtained9;
                                            break;
                                        case 10:
                                            cosmeticsFull10.setVisibility(View.VISIBLE);
                                            cosmeticsRarity = cosmeticsRarity10;
                                            cosmeticImage = cosmeticImage10;
                                            cosmeticsStat1 = cosmeticsStat110;
                                            cosmeticsStat2 = cosmeticsStat210;
                                            cosmeticsStat3 = cosmeticsStat310;
                                            cosmeticsStat4 = cosmeticsStat410;
                                            cosmeticsCC = cosmeticscc10;
                                            cosmeticsObtained = cosmeticsobtained10;
                                            break;
                                        case 11:
                                            cosmeticsFull11.setVisibility(View.VISIBLE);
                                            cosmeticsFull12.setVisibility(View.INVISIBLE);
                                            cosmeticsRarity = cosmeticsRarity11;
                                            cosmeticImage = cosmeticImage11;
                                            cosmeticsStat1 = cosmeticsStat111;
                                            cosmeticsStat2 = cosmeticsStat211;
                                            cosmeticsStat3 = cosmeticsStat311;
                                            cosmeticsStat4 = cosmeticsStat411;
                                            cosmeticsCC = cosmeticscc11;
                                            cosmeticsObtained = cosmeticsobtained11;
                                            break;
                                        case 12:
                                            cosmeticsFull12.setVisibility(View.VISIBLE);
                                            cosmeticsRarity = cosmeticsRarity12;
                                            cosmeticImage = cosmeticImage12;
                                            cosmeticsStat1 = cosmeticsStat112;
                                            cosmeticsStat2 = cosmeticsStat212;
                                            cosmeticsStat3 = cosmeticsStat312;
                                            cosmeticsStat4 = cosmeticsStat412;
                                            cosmeticsCC = cosmeticscc12;
                                            cosmeticsObtained = cosmeticsobtained12;
                                            break;
                                        case 13:
                                            cosmeticsFull13.setVisibility(View.VISIBLE);
                                            cosmeticsFull14.setVisibility(View.INVISIBLE);
                                            cosmeticsRarity = cosmeticsRarity13;
                                            cosmeticImage = cosmeticImage13;
                                            cosmeticsStat1 = cosmeticsStat113;
                                            cosmeticsStat2 = cosmeticsStat213;
                                            cosmeticsStat3 = cosmeticsStat313;
                                            cosmeticsStat4 = cosmeticsStat413;
                                            cosmeticsCC = cosmeticscc13;
                                            cosmeticsObtained = cosmeticsobtained13;
                                            break;
                                        case 14:
                                            cosmeticsFull14.setVisibility(View.VISIBLE);
                                            cosmeticsRarity = cosmeticsRarity14;
                                            cosmeticImage = cosmeticImage14;
                                            cosmeticsStat1 = cosmeticsStat114;
                                            cosmeticsStat2 = cosmeticsStat214;
                                            cosmeticsStat3 = cosmeticsStat314;
                                            cosmeticsStat4 = cosmeticsStat414;
                                            cosmeticsCC = cosmeticscc14;
                                            cosmeticsObtained = cosmeticsobtained14;
                                            break;
                                        case 15:
                                            cosmeticsFull15.setVisibility(View.VISIBLE);
                                            cosmeticsFull16.setVisibility(View.INVISIBLE);
                                            cosmeticsRarity = cosmeticsRarity15;
                                            cosmeticImage = cosmeticImage15;
                                            cosmeticsStat1 = cosmeticsStat115;
                                            cosmeticsStat2 = cosmeticsStat215;
                                            cosmeticsStat3 = cosmeticsStat315;
                                            cosmeticsStat4 = cosmeticsStat415;
                                            cosmeticsCC = cosmeticscc15;
                                            cosmeticsObtained = cosmeticsobtained15;
                                            break;
                                        case 16:
                                            cosmeticsFull16.setVisibility(View.VISIBLE);
                                            cosmeticsRarity = cosmeticsRarity16;
                                            cosmeticImage = cosmeticImage16;
                                            cosmeticsStat1 = cosmeticsStat116;
                                            cosmeticsStat2 = cosmeticsStat216;
                                            cosmeticsStat3 = cosmeticsStat316;
                                            cosmeticsStat4 = cosmeticsStat416;
                                            cosmeticsCC = cosmeticscc16;
                                            cosmeticsObtained = cosmeticsobtained16;
                                            break;
                                        case 17:
                                            cosmeticsFull17.setVisibility(View.VISIBLE);
                                            cosmeticsFull18.setVisibility(View.INVISIBLE);
                                            cosmeticsRarity = cosmeticsRarity17;
                                            cosmeticImage = cosmeticImage17;
                                            cosmeticsStat1 = cosmeticsStat117;
                                            cosmeticsStat2 = cosmeticsStat217;
                                            cosmeticsStat3 = cosmeticsStat317;
                                            cosmeticsStat4 = cosmeticsStat417;
                                            cosmeticsCC = cosmeticscc17;
                                            cosmeticsObtained = cosmeticsobtained17;
                                            break;
                                        case 18:
                                            cosmeticsFull18.setVisibility(View.VISIBLE);
                                            cosmeticsRarity = cosmeticsRarity18;
                                            cosmeticImage = cosmeticImage18;
                                            cosmeticsStat1 = cosmeticsStat118;
                                            cosmeticsStat2 = cosmeticsStat218;
                                            cosmeticsStat3 = cosmeticsStat318;
                                            cosmeticsStat4 = cosmeticsStat418;
                                            cosmeticsCC = cosmeticscc18;
                                            cosmeticsObtained = cosmeticsobtained18;
                                            break;
                                        case 19:
                                            cosmeticsFull19.setVisibility(View.VISIBLE);
                                            cosmeticsFull20.setVisibility(View.INVISIBLE);
                                            cosmeticsRarity = cosmeticsRarity19;
                                            cosmeticImage = cosmeticImage19;
                                            cosmeticsStat1 = cosmeticsStat119;
                                            cosmeticsStat2 = cosmeticsStat219;
                                            cosmeticsStat3 = cosmeticsStat319;
                                            cosmeticsStat4 = cosmeticsStat419;
                                            cosmeticsCC = cosmeticscc19;
                                            cosmeticsObtained = cosmeticsobtained19;
                                            break;
                                        case 20:
                                            cosmeticsFull20.setVisibility(View.VISIBLE);
                                            cosmeticsRarity = cosmeticsRarity20;
                                            cosmeticImage = cosmeticImage20;
                                            cosmeticsStat1 = cosmeticsStat120;
                                            cosmeticsStat2 = cosmeticsStat220;
                                            cosmeticsStat3 = cosmeticsStat320;
                                            cosmeticsStat4 = cosmeticsStat420;
                                            cosmeticsCC = cosmeticscc20;
                                            cosmeticsObtained = cosmeticsobtained20;
                                            break;
                                    }
                                    switch (rarity) {
                                        case "R":
                                            cosmeticsRarity.setImageResource(R.drawable.r_icon);
                                            break;
                                        case "SR":
                                            cosmeticsRarity.setImageResource(R.drawable.sr_icon);
                                            break;
                                        case "SSR":
                                            cosmeticsRarity.setImageResource(R.drawable.ssr_icon);
                                            break;
                                        case "UR":
                                            cosmeticsRarity.setImageResource(R.drawable.ur_icon);
                                            break;
                                    }
                                    loadImage(image, cosmeticImage);
                                    if (hp.equals("0")) {
                                        cosmeticsStat1.setVisibility(View.GONE);
                                    } else {
                                        cosmeticsStat1.setText("Health: " + hp);
                                    }
                                    if (reg.equals("0")) {
                                        cosmeticsStat2.setVisibility(View.GONE);
                                    } else {
                                        cosmeticsStat2.setText("Regeneration: " + reg);
                                    }
                                    if (rec.equals("0")) {
                                        cosmeticsStat3.setVisibility(View.GONE);
                                    } else {
                                        cosmeticsStat3.setText("Recovery Rate: " + rec);
                                    }
                                    if (lifesteal.equals("0")) {
                                        cosmeticsStat4.setVisibility(View.GONE);
                                    } else {
                                        cosmeticsStat4.setText("Lifesteal: " + lifesteal);
                                    }
                                    cosmeticsCC.setText("CC Gained: " + cc);
                                    cosmeticsObtained.setText("Obtained: " + obtained);
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

    public void loadImage(final String image, final ImageView view) {
        Picasso.with(getContext()).setIndicatorsEnabled(false);
        Picasso.with(getContext())
                .load(image)
                .networkPolicy(NetworkPolicy.OFFLINE)
                .into(view, new Callback() {
                    @Override
                    public void onSuccess() {

                    }

                    @Override
                    public void onError() {
                        //Try again online if cache failed
                        Picasso.with(getContext())
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
    }
}
