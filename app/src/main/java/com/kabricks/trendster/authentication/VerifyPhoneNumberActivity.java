package com.kabricks.trendster.authentication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.telephony.PhoneNumberUtils;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import com.kabricks.trendster.R;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class VerifyPhoneNumberActivity extends AppCompatActivity {

    // create map value for country codes
    Map<String, String> country_codes_map = new HashMap<>();
    TextView user_country;
    EditText user_country_code, user_phone_number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_phone_number);

        user_phone_number = findViewById(R.id.user_phone_number);
        user_country_code = findViewById(R.id.user_country_code);
        user_country = findViewById(R.id.user_country);

        // get all countries
        String[] countries = Locale.getISOCountries();

        // add the country codes;
        country_codes_map.put("UG", "256");
        country_codes_map.put("CA", "1");
        country_codes_map.put("US", "1");
        country_codes_map.put("FR", "33");
        country_codes_map.put("DE", "49");
        country_codes_map.put("KE", "254");
        country_codes_map.put("NG", "234");
        country_codes_map.put("RW", "250");
        country_codes_map.put("ZA", "27");
        country_codes_map.put("ES", "34");
        country_codes_map.put("TZ", "255");

        // get user current country
        TelephonyManager tm = (TelephonyManager)this.getSystemService(Context.TELEPHONY_SERVICE);
        Locale locale = new Locale("", tm.getSimCountryIso());
        user_country.setText(locale.getDisplayCountry());
        user_country_code.setText(country_codes_map.get(locale.getCountry()));
    }
}
