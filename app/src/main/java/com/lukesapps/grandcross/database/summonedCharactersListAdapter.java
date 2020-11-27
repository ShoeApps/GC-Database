package com.lukesapps.grandcross.database;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.squareup.picasso.Callback;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class summonedCharactersListAdapter extends ArrayAdapter<summonedCharactersList> {

    summonedCharactersList currentView;

    public summonedCharactersListAdapter(Activity context, ArrayList<summonedCharactersList> characterList) {
        super(context, 0, characterList);
    }

    @NonNull
    @Override
    public View getView(final int position, View convertView, @NonNull ViewGroup parent) {
        final summonedCharactersListAdapter.ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.summoned_list_item, null);
            holder = new summonedCharactersListAdapter.ViewHolder();
            holder.mImageView = convertView.findViewById(R.id.summoned_character_image);
            convertView.setTag(holder);
        } else {
            holder = (summonedCharactersListAdapter.ViewHolder) convertView.getTag();
        }

        currentView = getItem(position);

        TextView name = convertView.findViewById(R.id.summoned_character_name);
        name.setText(currentView.getCharacterName());

        TextView rarity = convertView.findViewById(R.id.summoned_character_rarity);
        rarity.setText(currentView.getCharacterRarity());

        final String img_url = currentView.getCharacterIcon();

        if (!img_url.equals("")) {
            Picasso.with(getContext()).cancelRequest(holder.mImageView);
            Picasso.with(getContext()).setIndicatorsEnabled(false);
            Picasso.with(getContext())
                    .load(img_url)
                    .fit()
                    .memoryPolicy(MemoryPolicy.NO_CACHE)
                    .networkPolicy(NetworkPolicy.NO_CACHE)
                    .placeholder(R.drawable.loading)
                    .error(R.drawable.offline_error)
                    .into(holder.mImageView, new Callback.EmptyCallback() {
                        @Override
                        public void onSuccess() {

                        }

                        @Override
                        public void onError() {
                            //Try again offline if online failed
                            Picasso.with(getContext())
                                    .load(img_url)
                                    .fit()
                                    .networkPolicy(NetworkPolicy.OFFLINE)
                                    .placeholder(R.drawable.loading)
                                    .error(R.drawable.offline_error)
                                    .into(holder.mImageView);
                        }
                    });
        } else {
            //todo - implement a default image in case img_url is indeed empty
            Picasso.with(getContext()).load(R.drawable.offline_error).into(holder.mImageView);
        }


        return convertView;
    }

    private static class ViewHolder {
        ImageView mImageView;
    }


}
