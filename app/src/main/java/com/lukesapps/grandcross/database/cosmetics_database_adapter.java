package com.lukesapps.grandcross.database;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

public class cosmetics_database_adapter extends ArrayAdapter<cosmetics_database_retriever> {

    cosmetics_database_retriever currentView;
    private List<cosmetics_database_retriever> characterListFull;

    public cosmetics_database_adapter(Activity context, ArrayList<cosmetics_database_retriever> cosmeticsList) {
        super(context, 0, cosmeticsList);
        characterListFull = new ArrayList<>(cosmeticsList);
    }

    @NonNull
    @Override
    public View getView(final int position, View convertView, @NonNull ViewGroup parent) {
        final ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.cosmetics_list_item, null);
            holder = new ViewHolder();
            convertView.setTag(holder);
        }

        currentView = getItem(position);

        TextView characterName = convertView.findViewById(R.id.ccharacter_name);
        characterName.setText(currentView.getCharName());
        TextView srWeapons = convertView.findViewById(R.id.srweapons);
        srWeapons.setText(currentView.getWeaponSR());
        TextView ssrWeapons = convertView.findViewById(R.id.ssrweapons);
        ssrWeapons.setText(currentView.getWeaponSSR());
        TextView urWeapons = convertView.findViewById(R.id.urweapons);
        urWeapons.setText(currentView.getWeaponUR());
        TextView srClothes = convertView.findViewById(R.id.srclothes);
        srClothes.setText(currentView.getClothesSR());
        TextView ssrClothes = convertView.findViewById(R.id.ssrclothes);
        ssrClothes.setText(currentView.getClothesSSR());
        TextView urClothes = convertView.findViewById(R.id.urclothes);
        urClothes.setText(currentView.getClothesUR());
        TextView srHeads = convertView.findViewById(R.id.srheads);
        srHeads.setText(currentView.getHeadSR());
        TextView ssrHeads = convertView.findViewById(R.id.ssrheads);
        ssrHeads.setText(currentView.getHeadSSR());
        TextView urHeads = convertView.findViewById(R.id.urheads);
        urHeads.setText(currentView.getHeadUR());


        return convertView;
    }

    private static class ViewHolder {
    }

    @NonNull
    public Filter getFilter() {
        return filter();
    }

    public Filter filter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults results = new FilterResults();
                List<cosmetics_database_retriever> suggestions = new ArrayList<>();
                String characterSearch = constraint.toString();
                Log.v("test", "characterSearch" + characterSearch);

                for (cosmetics_database_retriever item : characterListFull) {
                    Log.v("test", "item.getCharName()" + item.getCharName());
                    if (item.getCharName().toLowerCase().contains(characterSearch.toLowerCase())) {
                        suggestions.add(item);
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
