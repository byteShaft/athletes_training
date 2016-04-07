package com.pits.athletestraining.activities;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.pits.athletestraining.R;

import java.util.ArrayList;
import java.util.List;

public class TeamInfoActivity extends AppCompatActivity implements ListView.OnItemClickListener {

    private ListView mPlayersListView;
    private ViewHolder holder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_info);
        getSupportActionBar().hide();
        String titleText = getIntent().getExtras().getString("team_name");
        TextView title = (TextView) findViewById(R.id.list_title_team);
        title.setText(titleText);

        mPlayersListView = (ListView) findViewById(R.id.team_players_list);
        ArrayList<String[]> list = new ArrayList<>();
        String[] player1 = {"logo", "Messi", "1"};
        String[] player2 = {"logo", "Ronaldo", "2"};
        String[] player3 = {"logo", "Kaka", "8"};
        String[] player4 = {"logo", "Gerrald", "15"};
        String[] player5 = {"logo", "Carlos", "12"};
        list.add(player1);
        list.add(player2);
        list.add(player3);
        list.add(player4);
        list.add(player5);

        PlayersListAdapter adapter = new PlayersListAdapter(
                getApplicationContext(), R.layout.players_list_delegate, list);
        mPlayersListView.setAdapter(adapter);
        mPlayersListView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(getApplicationContext(), PlayerInfoActivity.class);
        String[] data = (String[]) mPlayersListView.getItemAtPosition(position);
        intent.putExtra("team_name", data[1]);
        startActivity(intent);
    }

    private class PlayersListAdapter extends ArrayAdapter<String[]> {

        public PlayersListAdapter(Context context, int resource, List<String[]> objects) {
            super(context, resource, objects);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                LayoutInflater inflater = (LayoutInflater)
                        getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.players_list_delegate, parent, false);

                holder = new ViewHolder();
                holder.title = (TextView) convertView.findViewById(R.id.players_list_title_team);
                holder.logo = (ImageView) convertView.findViewById(R.id.players_list_logo_team);
                holder.number = (TextView) convertView.findViewById(R.id.players_list_shirt_number);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            holder.title.setText(getItem(position)[1]);
            holder.number.setText(getItem(position)[2]);
//            holder.logo.setBackground(null);
            return convertView;
        }
    }

    static class ViewHolder {
        public ImageView logo;
        public TextView title;
        public TextView number;
    }
}
