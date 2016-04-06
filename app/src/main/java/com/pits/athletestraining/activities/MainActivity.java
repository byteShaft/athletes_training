package com.pits.athletestraining.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.pits.athletestraining.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText mUsernameEntry;
    private EditText mPasswordEntry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        startActivity(new Intent(getApplicationContext(), PlayerInfoActivity.class));
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setVisibility(View.GONE);
//        getSupportActionBar().hide();
//        getSupportActionBar().hide();
//        mUsernameEntry = (EditText) findViewById(R.id.username_entry);
//        mPasswordEntry = (EditText) findViewById(R.id.password_entry);
//        startActivity(new Intent(getApplicationContext(), PlayerInfoActivity.class));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_login:
                if (validateInput()) {
                    startActivity(new Intent(getApplicationContext(), TeamsListActivity.class));
                }
        }
    }

    private boolean isEmpty(EditText et) {
        return et.getText().toString().isEmpty();
    }

    private void showToast(String text) {
        Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();
    }

    private boolean validateInput() {
        if (isEmpty(mUsernameEntry) && isEmpty(mPasswordEntry)) {
            showToast("Username and Password fields empty");
            return false;
        }

        if (isEmpty(mUsernameEntry)) {
            showToast("Username empty");
            return false;
        }

        if (isEmpty(mPasswordEntry)) {
            showToast("Password empty");
            return false;
        }

        return true;
    }
}
