package com.example.shared_preferences;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;

import com.google.android.material.textfield.TextInputEditText;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private UserManager userManager;
    private String gender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userManager = new UserManager(this);

        final TextInputEditText fullNameEt = findViewById(R.id.et_main_fullName);
        fullNameEt.setText(userManager.getFullName());

        final TextInputEditText emailEt = findViewById(R.id.et_main_email);
        emailEt.setText(userManager.getEmail());

        final RadioGroup genderRadioGroup = findViewById(R.id.radioGroup_main_gender);
        gender = userManager.getGender();

        if (Objects.equals(gender, "male"))
            genderRadioGroup.check(R.id.radioBtn_main_male);
        else if (Objects.equals(gender, "female"))
            genderRadioGroup.check(R.id.radioBtn_main_female);

        genderRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.radioBtn_main_male)
                    gender = "male";
                else
                    gender = "female";
            }
        });

        final View clearBtn = findViewById(R.id.btn_main_clear);

        View.OnClickListener clearBtnOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userManager.clearAllInformation();
            }
        };

        clearBtn.setOnClickListener(clearBtnOnClickListener);

        final View saveBtn = findViewById(R.id.btn_main_save);
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userManager.saveUserInformation(fullNameEt.getText().toString(), emailEt.getText().toString(), gender);
            }
        });

    }
} 