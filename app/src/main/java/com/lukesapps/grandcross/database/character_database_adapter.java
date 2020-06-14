package com.lukesapps.grandcross.database;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class character_database_adapter extends ArrayAdapter<character_database_retriever> {

    character_database_retriever currentView;
    private List<character_database_retriever> characterListFull;

    public character_database_adapter(Activity context, ArrayList<character_database_retriever> characterList) {
        super(context, 0, characterList);
        characterListFull = new ArrayList<>(characterList);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.character_list_item, null);
            holder = new ViewHolder();
            holder.mImageView = convertView.findViewById(R.id.character_image);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        currentView = getItem(position);

        TextView characterName = convertView.findViewById(R.id.character_name);
        characterName.setText(currentView.getName());

        TextView characterCC = convertView.findViewById(R.id.character_cc);
        characterCC.setText("CC: " + currentView.getMaxCC());

        TextView characterHealth = convertView.findViewById(R.id.character_health);
        characterHealth.setText("Health: " + currentView.getMaxHealth());

        TextView characterAttack = convertView.findViewById(R.id.character_attack);
        characterAttack.setText("Attack: " + currentView.getMaxAttack());

        TextView characterDefense = convertView.findViewById(R.id.character_defense);
        characterDefense.setText("Defense: " + currentView.getMaxDefense());

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

    String searchingName = "false";
    String attribute = "";
    String server = "";
    String nameSearch = "";
    String rarity = "";
    String race = "";
    String obtained = "";

    public Filter getFilter(String passed) {
        searchingName = passed;
        return filter();
    }

    public Filter filter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults results = new FilterResults();
                List<character_database_retriever> suggestions = new ArrayList<>();
                if (constraint.equals("fullyReset")) {
                    attribute = "";
                    server = "";
                    nameSearch = "";
                    rarity = "";
                    race = "";
                    obtained = "";
                }
                if (constraint.equals("resetAttribute")) {
                    attribute = "";
                }
                if (constraint.equals("resetServer")) {
                    server = "";
                }
                if (constraint.equals("resetRarity")) {
                    rarity = "";
                }
                if (constraint.equals("resetRace")) {
                    race = "";
                }
                if (constraint.equals("resetObtained")) {
                    obtained = "";
                }
                for (character_database_retriever item : characterListFull) {
                    suggestions.add(item);

                    //Set Value
                    if (searchingName.equals("false")) {
                        if (constraint.equals("Global / Asia")) {
                            server = "Global / Asia";
                        }
                        if (constraint.equals("Japan / Korea")) {
                            server = "Japan / Korea";
                        }
                        if (constraint.equals("Japan / Korea Exclusive")) {
                            server = "Japan / Korea Exclusive";
                        }
                        if (constraint.toString().toLowerCase().equals("strength") || constraint.toString().toLowerCase().equals("speed") || constraint.toString().toLowerCase().equals("hp")) {
                            attribute = constraint.toString();
                        }
                        if (constraint.toString().toLowerCase().equals("r") || constraint.toString().toLowerCase().equals("sr") || constraint.toString().toLowerCase().equals("ssr")) {
                            rarity = constraint.toString();
                        }
                        if (constraint.toString().toLowerCase().equals("human") || constraint.toString().toLowerCase().equals("fairy") || constraint.toString().toLowerCase().equals("demon") || constraint.toString().toLowerCase().equals("goddess") || constraint.toString().toLowerCase().equals("giant") || constraint.toString().toLowerCase().equals("unknown")) {
                            race = constraint.toString();
                        }
                        if (constraint.toString().toLowerCase().contains("story") || constraint.toString().toLowerCase().contains("coin shop") || constraint.toString().toLowerCase().contains("normal gacha") || constraint.toString().toLowerCase().contains("limited gacha") || constraint.toString().toLowerCase().contains("festival gacha") || constraint.toString().toLowerCase().contains("collab") || constraint.toString().toLowerCase().contains("arena reward")) {
                            obtained = constraint.toString();
                        }
                    } else {
                        if (item.getName().toLowerCase().contains(constraint.toString().toLowerCase())) {
                            nameSearch = constraint.toString().toLowerCase();
                        }
                    }

                    //Set List
                    if (!server.equals("")) {
                        if (server.equals("Global / Asia")) {
                            if (item.getName().contains("(JP)")) {
                                suggestions.remove(item);
                            }
                        } else if (server.equals("Japan / Korea Exclusive")) {
                            if (!item.getName().contains("(JP)")) {
                                suggestions.remove(item);
                            }
                        }
                    }
                    if (!attribute.equals("")) {
                        if (!item.getType().equals(attribute)) {
                            suggestions.remove(item);
                        }
                    }
                    if (!nameSearch.equals("")) {
                        if (!item.getName().toLowerCase().contains(nameSearch.toLowerCase())) {
                            suggestions.remove(item);
                        }
                    }
                    if (!rarity.equals("")) {
                        if (!item.getRarity().equals(rarity)) {
                            suggestions.remove(item);
                        }
                    }
                    if (!race.equals("")) {
                        if (!item.getRace().equals(race)) {
                            suggestions.remove(item);
                        }
                    }
                    if (!obtained.equals("")) {
                        if (!item.getObtained().contains(obtained)) {
                            suggestions.remove(item);
                        }
                    }
                }

                results.values = suggestions;
                results.count = suggestions.size();
                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                clear();
                addAll((List) results.values);
                notifyDataSetChanged();
            }

            @Override
            public CharSequence convertResultToString(Object resultValue) {
                return "";
            }
        };
    }
}
