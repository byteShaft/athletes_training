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

public class TeamInfoActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_info);
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        getSupportActionBar().hide();
        String titleText = getIntent().getExtras().getString("team_name");
        TextView title = (TextView) findViewById(R.id.list_title_team);
        title.setText(titleText);

        mRecyclerView = (RecyclerView) findViewById(R.id.team_players_list);
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
        CustomAdapter adapter = new CustomAdapter(list);
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
                        Intent intent = new Intent(getApplicationContext(), PlayerInfoActivity.class);
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
        private static CustomView viewHolder;

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
                    R.layout.players_list_delegate, parent, false);
            viewHolder = new CustomView(view);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
            holder.setIsRecyclable(false);
            System.out.println(viewHolder == null);
            System.out.println(viewHolder.title == null);
            viewHolder.title.setText(items.get(position)[1]);
            viewHolder.number.setText(items.get(position)[2]);
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
        public TextView number;

        public CustomView(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.players_list_title_team);
            logo = (ImageView) itemView.findViewById(R.id.players_list_logo_team);
            number = (TextView) itemView.findViewById(R.id.players_list_shirt_number);
        }
    }


}
