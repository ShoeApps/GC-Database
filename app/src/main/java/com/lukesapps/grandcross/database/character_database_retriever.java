package com.lukesapps.grandcross.database;

public class character_database_retriever{

    private String mSelectId;
    private String mObtained;
    private String mRace;
    private String mRarity;
    private String mType;
    private String mName;
    private String mMinCC;
    private String mMaxCC;
    private String mMinHealth;
    private String mMaxHealth;
    private String mMinAttack;
    private String mMaxAttack;
    private String mMinDefense;
    private String mMaxDefense;
    private String mCharacterIcon;

    public character_database_retriever(String selectId, String obtained, String race, String rarity, String type, String name, String maxCC, String minCC, String minHealth, String maxHealth, String minAttack, String maxAttack, String minDefense, String maxDefense, String characterIcon) {
        mSelectId = selectId;
        mObtained = obtained;
        mRace = race;
        mRarity = rarity;
        mType = type;
        mName = name;
        mMinCC = minCC;
        mMaxCC = maxCC;
        mMinHealth = minHealth;
        mMaxHealth = maxHealth;
        mMinAttack = minAttack;
        mMaxAttack = maxAttack;
        mMinDefense = minDefense;
        mMaxDefense = maxDefense;
        mCharacterIcon = characterIcon;

    }

    public String getSelectId() {
        return mSelectId;
    }

    public String getObtained() {
        return mObtained;
    }

    public String getRace() {
        return mRace;
    }

    public String getRarity() {
        return mRarity;
    }

    public String getType() {
        return mType;
    }

    public String getName() {
        return mName;
    }

    public String getMaxCC() {
        return mMaxCC;
    }

    public String getMaxHealth() {
        return mMaxHealth;
    }

    public String getMaxAttack() {
        return mMaxAttack;
    }

    public String getMaxDefense() {
        return mMaxDefense;
    }

    public String getCharacterIcon() {
        return mCharacterIcon;
    }
}
