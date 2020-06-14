package com.lukesapps.grandcross.database;

public class summonedCharactersList implements Comparable<summonedCharactersList> {


    private String mRarity;
    private String mName;
    private String mCharacterIcon;


    public summonedCharactersList(String rarity, String name, String characterIcon) {
        mRarity = rarity;
        mName = name;
        mCharacterIcon = characterIcon;
    }


    public String getCharacterName() {
        return mName;
    }

    public String getCharacterRarity() {
        return mRarity;
    }

    public String getCharacterIcon() {
        return mCharacterIcon;
    }


    @Override
    public int compareTo(summonedCharactersList o1) {
        return o1.mRarity.compareTo(this.mRarity);
    }
}
