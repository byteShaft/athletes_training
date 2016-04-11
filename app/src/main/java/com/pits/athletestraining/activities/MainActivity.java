package com.pits.athletestraining.activities;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentSender;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;
import com.pits.athletestraining.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,
        GoogleApiClient.ConnectionCallbacks , GoogleApiClient.OnConnectionFailedListener {

    private static final int COMPLETE_AUTHORIZATION_REQUEST_CODE = 1;
    private static final int RESOLVE_CONNECTION_REQUEST_CODE = 2;
    private EditText mUsernameEntry;
    private EditText mPasswordEntry;

    // google authentication
    private GoogleApiClient mGoogleApiClient;

    private static final String TAG = "Athlete Training";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
        setContentView(R.layout.content_main);
//        mGoogleApiClient = new GoogleApiClient.Builder(this)
//                .addApi(Drive.API)
//                .addScope(Drive.SCOPE_FILE)
//                .addConnectionCallbacks(this)
//                .addOnConnectionFailedListener(this)
//                .build();
//        startActivity(new Intent(getApplicationContext(), TrainingSessionList.class));
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        toolbar.setVisibility(View.GONE);
//        getSupportActionBar().hide();
        mUsernameEntry = (EditText) findViewById(R.id.username_entry);
        mPasswordEntry = (EditText) findViewById(R.id.password_entry);
//        startActivity(new Intent(getApplicationContext(), PlayerInfoActivity.class));
    }

    @Override
    protected void onStart() {
        super.onStart();
//        mGoogleApiClient.connect();
    }


    @Override
    protected void onResume() {
        super.onResume();
        if (mGoogleApiClient == null) {
            // Create the API client and bind it to an instance variable.
            // We use this instance as the callback for connection and connection
            // failures.
            // Since no account name is passed, the user is prompted to choose.
//            mGoogleApiClient = new GoogleApiClient.Builder(this)
//                    .addApi(Drive.API)
//                    .addScope(Drive.SCOPE_FILE)
//                    .addConnectionCallbacks(this)
//                    .addOnConnectionFailedListener(this)
//                    .build();
        }
        // Connect the client.
//        mGoogleApiClient.connect();
    }

    @Override
    protected void onPause() {
        if (mGoogleApiClient != null) {
            mGoogleApiClient.disconnect();
        }
        super.onPause();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.txv_login:
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

        if (isEmpty(mUsernameEntry)) {
            showToast(getString(R.string.inputUsername));
            return false;
        }

        else if (isEmpty(mPasswordEntry)) {
            showToast(getString(R.string.inputPwd));
            return false;
        }

        return true;
    }

    @Override
    protected void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        switch (requestCode) {
            case COMPLETE_AUTHORIZATION_REQUEST_CODE:
                if (resultCode == Activity.RESULT_OK) {
                    // App is authorized, you can go back to sending the API request
                } else {
                    // User denied access, show him the account chooser again
                }
                break;
        }
    }

    @Override
    public void onConnected(Bundle bundle) {
        Log.i(TAG, "API client connected.");
    }

    @Override
    public void onConnectionSuspended(int i) {
        Log.i(TAG, "GoogleApiClient connection suspended");
    }

    @Override
    public void onConnectionFailed(ConnectionResult result) {

        if (result.hasResolution()) {
            try {
                result.startResolutionForResult(this, RESOLVE_CONNECTION_REQUEST_CODE);
            } catch (IntentSender.SendIntentException e) {
                // Unable to resolve, message user appropriately
            }
        } else {
            GooglePlayServicesUtil.getErrorDialog(result.getErrorCode(), this, 0).show();
        }
    }
}
