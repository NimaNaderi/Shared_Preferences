package com.example.shared_preferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

public class UserManager {

    private SharedPreferences sharedPreferences;

    public UserManager(Context context) {
        sharedPreferences = context.getSharedPreferences("user_information", Context.MODE_PRIVATE);

        SharedPreferences.OnSharedPreferenceChangeListener sharedPrefsChangeListener = new SharedPreferences.OnSharedPreferenceChangeListener() {
            @Override
            public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
                Toast.makeText(context, "Data With => " + key + " Changed", Toast.LENGTH_SHORT).show();
            }
        };
        sharedPreferences.registerOnSharedPreferenceChangeListener(sharedPrefsChangeListener);
//        sharedPreferences.unregisterOnSharedPreferenceChangeListener(sharedPrefsChangeListener);


    }

    public void saveUserInformation(String fullName, String email, String gender) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("full_name", fullName);
        editor.putString("email", email);
        editor.putString("gender", gender);
        editor.apply();
    }

    public String getFullName() {
        return sharedPreferences.getString("full_name", null);
    }

    public String getEmail() {
        return sharedPreferences.getString("email", null);
    }

    public String getGender() {
        return sharedPreferences.getString("gender", null);
    }

    public void clearAllInformation() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }

    public void removeFullName() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove("full_name");
        editor.apply();
    }
}
