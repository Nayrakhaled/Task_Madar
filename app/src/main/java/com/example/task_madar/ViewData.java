package com.example.task_madar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class ViewData extends AppCompatActivity {
    TextView nameText, ageText, titleText, genderText;
    SharedPreferences mPrefs;
    DataBase dbUser;
    UserDetail detail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_data);

        nameText = findViewById(R.id.text_name);
        ageText = findViewById(R.id.text_age);
        titleText = findViewById(R.id.text_title);
        genderText = findViewById(R.id.text_gender);

        mPrefs = getApplicationContext().getSharedPreferences("user", getApplicationContext().MODE_PRIVATE);
        detail = new UserDetail();
        dbUser = new DataBase(this);
        Log.d("nnnnn", mPrefs.getString("name", null));
        Log.d("nnnnn", String.valueOf(dbUser.getProfilesCount()));

        detail = dbUser.getAllData(mPrefs.getString("name", null));
        nameText.setText(detail.getName());
        String agee = String.valueOf(detail.getAge());
        ageText.setText(agee);
        titleText.setText(detail.getTitle());
        genderText.setText(detail.getGender());

    }
}
