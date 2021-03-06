package com.pits.athletestraining.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.pits.athletestraining.R;
import com.pits.athletestraining.utils.AppGlobals;

public class Method3Fragment extends Fragment {

    private View mBaseView;
    private TextView teamPlayer;
    private TextView parametersView;
    private TextView valuesView;
    private String[] valuesParameters = new String[]{"parameter1 :", "parameter2 :", "parameter3 :",
            "parameter4 :", "parameter5 :", "parameter6 :"};

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mBaseView = inflater.inflate(R.layout.paramets_list_delegate, container, false);
        teamPlayer = (TextView) mBaseView.findViewById(R.id.team_players_textview);
        parametersView = (TextView) mBaseView.findViewById(R.id.parameters);
        valuesView = (TextView) mBaseView.findViewById(R.id.values);
        teamPlayer.setTypeface(AppGlobals.typefaceBold);
        parametersView.setTypeface(AppGlobals.typefaceBold);
        valuesView.setTypeface(AppGlobals.typefaceBold);
        ListView listView = (ListView) mBaseView.findViewById(R.id.parameters_list);
        ListAdapter listAdapter = new ListAdapter();
        listView.setAdapter(listAdapter);
        return mBaseView;
    }

    class ListAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return valuesParameters.length;
        }

        @Override
        public Object getItem(int position) {
            return valuesParameters[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            final ViewHolder holder;
            if (convertView == null) {
                holder = new ViewHolder();
                LayoutInflater layoutInflater = getActivity().getLayoutInflater();
                convertView = layoutInflater.inflate(R.layout.paremeters_values_delegate, null);
                holder.parameters = (TextView) convertView.findViewById(R.id.parameters_textview);
                holder.values = (EditText) convertView.findViewById(R.id.values_of_parameters);
                holder.parameters.setTypeface(AppGlobals.typeface);
                holder.values.setTypeface(AppGlobals.typeface);
                convertView.setTag(holder);
            }
            else {
                holder = (ViewHolder) convertView.getTag();
            }

            holder.parameters.setText(valuesParameters[position]);
            return convertView;
        }
    }

    private class ViewHolder {
        TextView parameters;
        EditText values;
    }
}
