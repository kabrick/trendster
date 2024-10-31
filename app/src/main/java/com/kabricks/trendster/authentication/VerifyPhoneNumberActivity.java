package com.kabricks.trendster.authentication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.telephony.PhoneNumberUtils;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.kabricks.trendster.R;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class VerifyPhoneNumberActivity extends AppCompatActivity {

    // create map value for country codes
    Map<String, String> country_codes_map = new HashMap<>();
    TextView user_country;
    EditText user_country_code, user_phone_number;
    String phoneNumber = "+";

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

        phoneNumber += country_codes_map.get(locale.getCountry());
    }

    public void verifyUser(View view){

        phoneNumber += user_phone_number.getText().toString();

        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                phoneNumber,        // Phone number to verify
                60,                 // Timeout duration
                TimeUnit.SECONDS,   // Unit of timeout
                this,               // Activity (for callback binding)
                mCallbacks);        // OnVerificationStateChangedCallbacks
    }

    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        @Override
        public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {

            //Getting the code sent by SMS
            String code = phoneAuthCredential.getSmsCode();

            if (code != null) {
                Log.e("code", code);
            }
        }

        @Override
        public void onVerificationFailed(FirebaseException e) {
            Toast.makeText(VerifyPhoneNumberActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
        }

        @Override
        public void onCodeSent(String verificationId, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            //storing the verification id that is sent to the user
            Log.e("verification_id", verificationId);
        }
    };
}
