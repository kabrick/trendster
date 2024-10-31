package com.kabricks.trendster;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.kabricks.trendster.authentication.VerifyPhoneNumberActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = new Intent(this, VerifyPhoneNumberActivity.class);
        startActivity(intent);
    }
}
