package com.example.task_madar;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Map;

public class MainActivity extends AppCompatActivity {

    EditText name, age, title;
    RadioGroup gender;
    RadioButton genderBtn;
    TextView text;
    Button submit;
    DataBase dbUser;
    UserDetail userDetail;
    SharedPreferences mPrefs;
    SharedPreferences.Editor editor;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.edit_name);
        age = findViewById(R.id.edit_age);
        title = findViewById(R.id.edit_title);
        gender = findViewById(R.id.group_data);
        submit = findViewById(R.id.submit);
        text = findViewById(R.id.text_error);

        dbUser = new DataBase(this);
        userDetail = new UserDetail();
        mPrefs = getApplicationContext().getSharedPreferences("user", getApplicationContext().MODE_PRIVATE);
        editor = mPrefs.edit();


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!name.getText().toString().isEmpty() && gender.isClickable()) {

                    userDetail.setName(name.getText().toString().trim());
                    if (age.getText().toString().isEmpty()) userDetail.setAge(0);
                    else userDetail.setAge(Integer.parseInt(age.getText().toString()));

                    userDetail.setTitle(title.getText().toString().trim());

                    Boolean rres = gender.isClickable();
                    Log.d("iiid", String.valueOf(rres));

                    int selectedId = gender.getCheckedRadioButtonId();
                    genderBtn = findViewById(selectedId);
                    userDetail.setGender(genderBtn.getText().toString());

                    Boolean res = dbUser.insertData(userDetail);
                    Log.d("name", String.valueOf(res));
                    Log.d("name", genderBtn.getText().toString());
                    editor.putString("name", userDetail.getName()).apply();

                    Intent intent = new Intent(MainActivity.this, ViewData.class);
                    startActivity(intent);
                } else {
                    text.setText(R.string.error);
                }

            }
        });

    }
}
