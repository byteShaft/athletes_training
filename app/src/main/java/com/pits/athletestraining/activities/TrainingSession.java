package com.pits.athletestraining.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.pits.athletestraining.R;

import java.util.ArrayList;

public class TrainingSession extends AppCompatActivity {

    private ArrayList<String> staticData;
    private ArrayList<String> dynamicData;
    private ViewHolder viewHolder;
    private ListView mListView;
    private SessionDetailAdapter sessionDetailAdapter;
    private MenuItem menuItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_traning_session);
        dynamicData = new ArrayList<>();
        staticData = new ArrayList<>();
        staticList();
        dynamicList();
        mListView = (ListView) findViewById(R.id.session_details_list);
        sessionDetailAdapter = new SessionDetailAdapter(getApplicationContext(),
                R.layout.traning_session_delegate,
                staticData, dynamicData);
        mListView.setAdapter(sessionDetailAdapter);
    }

    private void dynamicList() {
        dynamicData.add("21/01/2016");
        dynamicData.add("01");
        dynamicData.add("2 Weeks");
        dynamicData.add("4");
        dynamicData.add("60 min");
        dynamicData.add("6 venue");
        dynamicData.add("Test Warm Up");
        dynamicData.add("Test Main part");
        dynamicData.add("Test Recovery");
        dynamicData.add("Test Main Goals");
        dynamicData.add("Test Other Info");
    }

    private void staticList() {
        staticData.add("Date");
        staticData.add("Hour");
        staticData.add("Week");
        staticData.add("Session Number");
        staticData.add("Duration");
        staticData.add("Venue");
        staticData.add("Warm Up");
        staticData.add("Main part");
        staticData.add("Recovery");
        staticData.add("Main Goals");
        staticData.add("Other Info");
    }

    class SessionDetailAdapter extends ArrayAdapter<String> {

        private ArrayList<String> staticData;
        private ArrayList<String> dataList;

        public SessionDetailAdapter(Context context, int resource, ArrayList<String> arrayList,
                                    ArrayList<String> dataList) {
            super(context, resource);
            this.staticData = arrayList;
            this.dataList = dataList;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                LayoutInflater layoutInflater = getLayoutInflater();
                convertView = layoutInflater.inflate(R.layout.traning_session_delegate, parent, false);
                viewHolder = new ViewHolder();
                viewHolder.staticPlaceHolder = (TextView) convertView.findViewById(R.id.static_place_holder);
                viewHolder.textView = (TextView) convertView.findViewById(R.id.full_name_text_view);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }

            viewHolder.staticPlaceHolder.setText(staticData.get(position));
            viewHolder.textView.setText(dataList.get(position));
            return convertView;
        }

        @Override
        public int getCount() {
            return staticData.size();
        }
    }

    static class ViewHolder{
        public TextView staticPlaceHolder;
        public TextView textView;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.training_session_activity_menu, menu);
        menuItem = menu.findItem(R.id.delete_session);
        menuItem = menu.findItem(R.id.calculation_view);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.calculation_view:
                startActivity(new Intent(TrainingSession.this, TabsActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
