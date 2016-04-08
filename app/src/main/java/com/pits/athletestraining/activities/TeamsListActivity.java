package com.pits.athletestraining.activities;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.pits.athletestraining.R;
import com.pits.athletestraining.utils.SimpleDividerItemDecoration;
import com.pits.athletestraining.utils.TeamsListHelpers;

import java.util.ArrayList;

public class TeamsListActivity extends AppCompatActivity {

    public static CustomView viewHolder;
    public RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teams_list);
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView = (RecyclerView) findViewById(R.id.list_teams);
        CustomAdapter adapter = new CustomAdapter(TeamsListHelpers.getAllTeams());
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.canScrollVertically(LinearLayoutManager.VERTICAL);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.addItemDecoration(new SimpleDividerItemDecoration(getApplicationContext()));
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.addOnItemTouchListener(new CustomAdapter(TeamsListHelpers.getAllTeams(),
                getApplicationContext(),
                new CustomAdapter.OnItemClickListener() {
                    @Override
                    public void onItem(String item) {
                        Intent intent = new Intent(getApplicationContext(), TeamInfoActivity.class);
                        intent.putExtra("team_name", item);
                        startActivity(intent);
                    }
                }));
    }

    static class CustomAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements
            RecyclerView.OnItemTouchListener {

        private ArrayList<String[]> items;
        private OnItemClickListener mListener;
        private GestureDetector mGestureDetector;

        public CustomAdapter(ArrayList<String[]> categories, Context context, OnItemClickListener listener) {
            this.items = categories;
            mListener = listener;
            mGestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }
            });
        }

        public CustomAdapter(ArrayList<String[]> categories) {
            this.items = categories;
        }


        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(
                    R.layout.teams_list_delegate, parent, false);
            viewHolder = new CustomView(view);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
            holder.setIsRecyclable(false);
            viewHolder.title.setText(items.get(position)[1]);
        }

        @Override
        public int getItemCount() {
            return items.size();
        }

        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
            View childView = rv.findChildViewUnder(e.getX(), e.getY());
            if (childView != null && mListener != null && mGestureDetector.onTouchEvent(e)) {
                mListener.onItem(items.get(rv.getChildPosition(childView))[1]);
                return true;
            }
            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {

        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

        }

        public interface OnItemClickListener {
            void onItem(String item);
        }
    }

    // custom viewHolder to access xml elements requires a view in constructor
    public static class CustomView extends RecyclerView.ViewHolder {

        public ImageView logo;
        public TextView title;

        public CustomView(View itemView) {
            super(itemView);
            logo = (ImageView) itemView.findViewById(R.id.list_logo_team);
            title = (TextView) itemView.findViewById(R.id.list_title_team);
        }
    }
}
