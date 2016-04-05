package com.pits.athletestraining.activities;


import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.pits.athletestraining.R;
import com.pits.athletestraining.utils.TeamsListHelpers;

import java.util.List;

public class TeamsListActivity extends AppCompatActivity {

    private ListView mTeamsListView;
    private ViewHolder holder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teams_list);
        mTeamsListView = (ListView) findViewById(R.id.list_teams);
        TeamsListAdapter adapter = new TeamsListAdapter(
                getApplicationContext(), R.layout.teams_list_delegate,
                TeamsListHelpers.getAllTeams());
        mTeamsListView.setAdapter(adapter);
        mTeamsListView.setDivider(null);
    }

    private class TeamsListAdapter extends ArrayAdapter<String[]> {

        public TeamsListAdapter(Context context, int resource, List<String[]> objects) {
            super(context, resource, objects);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                LayoutInflater inflater = (LayoutInflater)
                        getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.teams_list_delegate, parent, false);

                holder = new ViewHolder();
                holder.title = (TextView) convertView.findViewById(R.id.list_title_team);
                holder.logo = (ImageView) convertView.findViewById(R.id.list_logo_team);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            holder.title.setText(getItem(position)[1]);
//            holder.logo.setBackground(null);
            return convertView;
        }
    }

    static class ViewHolder {
        public ImageView logo;
        public TextView title;
    }
}
