package com.pits.athletestraining.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.pits.athletestraining.R;

public class UserInfoActivity extends AppCompatActivity implements View.OnClickListener {

    /**
     * initializing all the textViews and EditTexts
     */
    private TextView fullNameTextView;
    private EditText fullNameEditText;
    private TextView nameTextView;
    private EditText nameEditText;
    private TextView teamTextView;
    private EditText teamEditText;
    private TextView positionTextView;
    private EditText positionEditText;
    private TextView numberTextView;
    private EditText numberEditText;
    private TextView groupTextView;
    private EditText groupEditText;
    private TextView fromTextView;
    private EditText fromEditText;
    private TextView toTextView;
    private EditText toEditText;
    private TextView birthDayTextView;
    private EditText birthDayEditText;
    private TextView mobileNumberTextView;
    private EditText mobileNumberEditText;
    private TextView emailTextView;
    private EditText emailEditText;
    private TextView otherInfoTextView;
    private EditText otherEditText;
    private EditText[] allEditText;
    private TextView[] allTextViews;
    private int fadeIn = android.R.anim.fade_in;
    private int fadeOut = android.R.anim.fade_out;
    private Animation mAnimation;
    private String fullName = "";
    private String name = "";
    private String team = "";
    private String position = "";
    private String number = "";
    private String group = "";
    private String from = "";
    private String to = "";
    private String birthDay = "";
    private String mobileNumber = "";
    private String email = "";
    private String otherInfo = "";
    private Button updateButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_info);
        instantiateViews();
        infoList();
        updateButton = (Button) findViewById(R.id.button_update);
        updateButton.setOnClickListener(this);
    }

    private void flipViews(EditText[] editTexts, TextView[] textViews) {
        boolean showingEditText = false;

        for (TextView textView: textViews) {
            if (textView.getVisibility() == View.VISIBLE) {
                textView.setVisibility(View.GONE);
                mAnimation = AnimationUtils.loadAnimation(getApplicationContext(), fadeOut);
            } else {
                textView.setVisibility(View.VISIBLE);
                mAnimation = AnimationUtils.loadAnimation(getApplicationContext(), fadeIn);

            }
            textView.startAnimation(mAnimation);
        }
        for (EditText editText: editTexts) {

            if (editText.getVisibility() == View.VISIBLE) {
                updateButton.setVisibility(View.GONE);
                showingEditText = false;
                editText.setVisibility(View.GONE);
                mAnimation = AnimationUtils.loadAnimation(getApplicationContext(), fadeOut);
            } else {
                updateButton.setVisibility(View.VISIBLE);
                showingEditText = true;
                editText.setVisibility(View.VISIBLE);
                mAnimation = AnimationUtils.loadAnimation(getApplicationContext(), fadeIn);
            }
            editText.startAnimation(mAnimation);
        }
        if (showingEditText) {
            setTextForEditText();
        }
    }

    /**
     * first method to call to initialize all views
     */
    private void instantiateViews() {
        fullNameTextView = (TextView) findViewById(R.id.full_name_text_view);
        fullNameEditText = (EditText) findViewById(R.id.edit_text_full_name);
        nameTextView = (TextView) findViewById(R.id.name_text_view);
        nameEditText = (EditText) findViewById(R.id.edit_text_name);
        teamTextView = (TextView) findViewById(R.id.team_text_view);
        teamEditText = (EditText) findViewById(R.id.edit_text_team);
        positionTextView = (TextView) findViewById(R.id.position_text_view);
        positionEditText = (EditText) findViewById(R.id.edit_text_position);
        numberTextView = (TextView) findViewById(R.id.number_text_view);
        numberEditText = (EditText) findViewById(R.id.edit_text_number);
        groupTextView = (TextView) findViewById(R.id.group_text_view);
        groupEditText = (EditText) findViewById(R.id.edit_text_group);
        fromTextView = (TextView) findViewById(R.id.from_text_view);
        fromEditText = (EditText) findViewById(R.id.edit_text_from);
        toTextView = (TextView) findViewById(R.id.to_text_view);
        toEditText = (EditText) findViewById(R.id.edit_text_to);
        birthDayTextView = (TextView) findViewById(R.id.birthday_text_view);
        birthDayEditText = (EditText) findViewById(R.id.edit_text_birthday);
        mobileNumberTextView = (TextView) findViewById(R.id.mobile_number_text_view);
        mobileNumberEditText = (EditText) findViewById(R.id.edit_text_mobile_number);
        emailTextView = (TextView) findViewById(R.id.email_text_view);
        emailEditText = (EditText) findViewById(R.id.edit_text_email);
        otherInfoTextView = (TextView) findViewById(R.id.info_text_view);
        otherEditText = (EditText) findViewById(R.id.edit_text_other_info);
        allEditText = new EditText[]{fullNameEditText, nameEditText, teamEditText, positionEditText,
                numberEditText, groupEditText, fromEditText, toEditText, birthDayEditText, mobileNumberEditText,
                emailEditText, otherEditText};
        allTextViews = new TextView[] {fullNameTextView, nameTextView, teamTextView, positionTextView,
                numberTextView, groupTextView, fromTextView, toTextView, birthDayTextView, mobileNumberTextView,
                emailTextView, otherInfoTextView};
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.user_info_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.edit_user_info:
                flipViews(allEditText, allTextViews);
                return true;
            default: return false;
        }
    }

    private void infoList() {
        fullName = "Bilal shahid";
        name = "Bilal";
        team = "Germany";
        position = "1";
        number = "21";
        group = "Group B";
        from = "7th street avenue london";
        to = "test";
        birthDay = ("21-09-1992");
        mobileNumber = "+92*******";
        email = "example@something.com";
        otherInfo = "other details about the player";
        setTextForTextViews();
    }

    private void setTextForTextViews() {
        fullNameTextView.setText(fullName);
        nameTextView.setText(name);
        teamTextView.setText(team);
        positionTextView.setText(position);
        numberTextView.setText(number);
        groupTextView.setText(group);
        fromTextView.setText(from);
        toTextView.setText(to);
        birthDayTextView.setText(birthDay);
        mobileNumberTextView.setText(mobileNumber);
        emailTextView.setText(email);
        otherInfoTextView.setText(otherInfo);
    }

    private void setTextForEditText() {
        fullNameEditText.setText(fullName);
        nameEditText.setText(name);
        teamEditText.setText(team);
        positionEditText.setText(position);
        numberEditText.setText(number);
        groupEditText.setText(group);
        fromEditText.setText(from);
        toEditText.setText(to);
        birthDayEditText.setText(birthDay);
        mobileNumberEditText.setText(mobileNumber);
        emailEditText.setText(email);
        otherEditText.setText(otherInfo);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_update:
                flipViews(allEditText, allTextViews);
                fullName = fullNameEditText.getText().toString();
                name = nameEditText.getText().toString();
                team = teamEditText.getText().toString();
                position = positionEditText.getText().toString();
                number = numberEditText.getText().toString();
                group = groupEditText.getText().toString();
                from = fromEditText.getText().toString();
                to = toEditText.getText().toString();
                birthDay = birthDayEditText.getText().toString();
                mobileNumber = mobileNumberEditText.getText().toString();
                email = emailEditText.getText().toString();
                otherInfo = otherEditText.getText().toString();
                setTextForTextViews();
                Toast.makeText(UserInfoActivity.this, "player info updated", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
