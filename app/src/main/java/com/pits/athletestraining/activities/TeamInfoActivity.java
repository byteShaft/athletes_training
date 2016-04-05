package com.pits.athletestraining.activities;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.pits.athletestraining.R;

public class TeamInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_info);
        getSupportActionBar().hide();
    }
}
