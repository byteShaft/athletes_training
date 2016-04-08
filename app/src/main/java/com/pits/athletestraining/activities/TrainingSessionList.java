package com.pits.athletestraining.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.pits.athletestraining.R;

public class TrainingSessionList extends AppCompatActivity {

    private ListView mListView;
    public ViewHolder viewHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_training_session);
        getSupportActionBar().hide();
        mListView = (ListView) findViewById(R.id.sessions_list);
        Adapter adapter = new Adapter(getApplicationContext(), R.layout.training_sessions_delegate);
        mListView.setAdapter(adapter);

    }

    class Adapter extends ArrayAdapter<String> {

        public Adapter(Context context, int resource) {
            super(context, resource);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                LayoutInflater layoutInflater = getLayoutInflater();
                convertView = layoutInflater.inflate(R.layout.training_sessions_delegate, parent, false);
                viewHolder = new ViewHolder();
                viewHolder.date = (TextView) convertView.findViewById(R.id.date);
                viewHolder.time = (TextView) convertView.findViewById(R.id.time);
                viewHolder.minutes = (TextView) convertView.findViewById(R.id.minutes);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            viewHolder.date.setText("21-9-2016");
            viewHolder.time.setText("12:00");
            viewHolder.minutes.setText("120");

            return convertView;
        }

        @Override
        public int getCount() {
            return 1;

        }
    }

    static class ViewHolder {
        public TextView date;
        public TextView time;
        public TextView minutes;
    }
}
