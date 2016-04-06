package com.pits.athletestraining.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.pits.athletestraining.R;

import java.util.ArrayList;

public class PlayerInfoActivity extends AppCompatActivity {


    private ArrayList<String> staticData;
    private ArrayList<String> dynamicData;
    private ViewHolder viewHolder;
    private ListView mListView;
    private InfoAdapter infoAdapter;
    private boolean showingEditMode = false;
    private MenuItem menuItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_info);
        dynamicData = new ArrayList<>();
        staticData = new ArrayList<>();
        staticList();
        dynamicList();
        mListView = (ListView) findViewById(R.id.user_info_list);
        infoAdapter = new InfoAdapter(getApplicationContext(), R.layout.team_info_delegate,
                staticData, dynamicData, false);
        mListView.setAdapter(infoAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.user_info_menu, menu);
        menuItem = menu.findItem(R.id.edit_user_info);
        if (!showingEditMode) {
            menuItem.setTitle("Edit Info");
        } else {
            menuItem.setTitle("Done Editing");
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.edit_user_info:
                if (!showingEditMode) {
                    infoAdapter = null;
                    infoAdapter = new InfoAdapter(getApplicationContext(), R.layout.team_info_delegate,
                            staticData, dynamicData, true);
                    mListView.setAdapter(infoAdapter);
                    menuItem.setTitle("Done Editing");
                } else {
                    infoAdapter = null;
                    infoAdapter = new InfoAdapter(getApplicationContext(), R.layout.team_info_delegate,
                            staticData, dynamicData, false);
                    mListView.setAdapter(infoAdapter);
                    Toast.makeText(PlayerInfoActivity.this, "Info updated", Toast.LENGTH_SHORT).show();
                    menuItem.setTitle("Edit Info");

                }
                return true;
            default: return false;
        }
    }

    private void dynamicList() {
        dynamicData.add("Bilal shahid");
        dynamicData.add("Bilal");
        dynamicData.add("Germany");
        dynamicData.add("1");
        dynamicData.add("21");
        dynamicData.add("Group B");
        dynamicData.add("7th street avenue london");
        dynamicData.add("test");
        dynamicData.add("21-09-1992");
        dynamicData.add("+92*******");
        dynamicData.add("example@something.com");
        dynamicData.add("other details about the player");
    }

    private void staticList() {
        staticData.add("Full Name");
        staticData.add("Name");
        staticData.add("Team");
        staticData.add("Position");
        staticData.add("Number");
        staticData.add("Group");
        staticData.add("From");
        staticData.add("To");
        staticData.add("Birthday");
        staticData.add("Phone number");
        staticData.add("Email");
        staticData.add("Other Info");
    }

    class InfoAdapter extends ArrayAdapter<String> {

        private ArrayList<String> staticData;
        private ArrayList<String> dataList;
        private boolean showEditText = false;

        public InfoAdapter(Context context, int resource, ArrayList<String> arrayList,
                           ArrayList<String> dataList, boolean showEditText) {
            super(context, resource);
            this.staticData = arrayList;
            this.dataList = dataList;
            this.showEditText = showEditText;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null){
                LayoutInflater layoutInflater = getLayoutInflater();
                convertView = layoutInflater.inflate(R.layout.team_info_delegate, parent, false);
                viewHolder = new ViewHolder();
                viewHolder.staticPlaceHolder = (TextView) convertView.findViewById(R.id.static_place_holder);
                viewHolder.textView = (TextView) convertView.findViewById(R.id.full_name_text_view);
                viewHolder.editText = (EditText) convertView.findViewById(R.id.edit_text_full_name);
                convertView.setTag(viewHolder);

            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            viewHolder.staticPlaceHolder.setText(staticData.get(position));
            viewHolder.textView.setText(dataList.get(position));
            if (showEditText) {
                viewHolder.editText.setVisibility(View.VISIBLE);
                viewHolder.editText.setText(dataList.get(position));
                viewHolder.textView.setText("");
                viewHolder.textView.setVisibility(View.GONE);
                showingEditMode = true;
            } else {
                showingEditMode = false;
            }
            return convertView;
        }

        @Override
        public int getCount() {
            System.out.println(staticData.size());
            return staticData.size();
        }
    }

    static class ViewHolder{
        public TextView staticPlaceHolder;
        public TextView textView;
        public EditText editText;
    }
}
