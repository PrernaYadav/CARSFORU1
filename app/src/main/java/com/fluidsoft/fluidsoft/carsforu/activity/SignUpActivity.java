package com.fluidsoft.fluidsoft.carsforu.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.fluidsoft.fluidsoft.carsforu.Class.ConfigInfo;
import com.fluidsoft.fluidsoft.carsforu.Class.Jsonparse;
import com.fluidsoft.fluidsoft.carsforu.R;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {
    String email, password, pan, mobile, location, car_preference;

    Button button_signup;
    EditText editText_email, editText_password, editText_pan, editText_mobile, editText_location, editText_car_preference;
    private static final String email_pattern =
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    final Jsonparse jsonParser = new Jsonparse(this);

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        button_signup = (Button) findViewById(R.id.button_signup);
        // Find ID of EditText
        editText_car_preference = (EditText) findViewById(R.id.editText_car_preference);
        editText_email = (EditText) findViewById(R.id.editText_Email1);
        editText_password = (EditText) findViewById(R.id.editText_Password1);
        editText_pan = (EditText) findViewById(R.id.editText_PAN_No);
        editText_mobile = (EditText) findViewById(R.id.editText_mobile);
        editText_location = (EditText) findViewById(R.id.editText_location);

        // Setting Typeface

        final Typeface typeface = Typeface.createFromAsset(getAssets(), "Helvetica-Regular.ttf");
        editText_email.setTypeface(typeface);
        editText_password.setTypeface(typeface);
        editText_mobile.setTypeface(typeface);
        editText_location.setTypeface(typeface);
        editText_pan.setTypeface(typeface);
        editText_car_preference.setTypeface(typeface);
        String strEmailAddress = editText_email.getText().toString().trim();
        Matcher matcherObj = Pattern.compile(email_pattern).matcher(strEmailAddress);
        editText_email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editText_email.setHint("Email");
                editText_email.setHintTextColor(getResources().getColor(R.color.AppColor));
                editText_email.setTypeface(typeface);
                ColorStateList colorStateList = ColorStateList.valueOf(getResources().getColor(R.color.AppColor));
                editText_email.setBackgroundTintList(colorStateList);
                editText_email.setTextColor(getResources().getColor(R.color.AppColor));
            }
        });


        editText_email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                editText_email.setHint("Email");
                editText_email.setHintTextColor(getResources().getColor(R.color.AppColor));
                ColorStateList colorStateList = ColorStateList.valueOf(getResources().getColor(R.color.AppColor));
                editText_email.setBackgroundTintList(colorStateList);
                editText_email.setTypeface(typeface);
                editText_email.setTextColor(getResources().getColor(R.color.AppColor));
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        editText_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                editText_password.setHint("Password");
                editText_password.setTypeface(typeface);
                editText_password.setHintTextColor(getResources().getColor(R.color.AppColor));
                ColorStateList colorStateList = ColorStateList.valueOf(getResources().getColor(R.color.AppColor));
                editText_password.setBackgroundTintList(colorStateList);
                editText_password.setTextColor(getResources().getColor(R.color.AppColor));
            }

        });

        editText_password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                editText_password.setHint("Password");
                editText_password.setHintTextColor(getResources().getColor(R.color.AppColor));
                editText_password.setTypeface(typeface);
                ColorStateList colorStateList = ColorStateList.valueOf(getResources().getColor(R.color.AppColor));
                editText_password.setBackgroundTintList(colorStateList);
                editText_password.setTextColor(getResources().getColor(R.color.AppColor));
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        editText_car_preference.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editText_car_preference.setHint("Car Preference");
                editText_car_preference.setHintTextColor(getResources().getColor(R.color.AppColor));
                editText_car_preference.setTypeface(typeface);
                ColorStateList colorStateList = ColorStateList.valueOf(getResources().getColor(R.color.AppColor));
                editText_car_preference.setBackgroundTintList(colorStateList);
                editText_car_preference.setTextColor(getResources().getColor(R.color.AppColor));
            }
        });


        editText_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editText_location.setHint("Location");
                editText_location.setHintTextColor(getResources().getColor(R.color.AppColor));
                editText_location.setTypeface(typeface);
                ColorStateList colorStateList = ColorStateList.valueOf(getResources().getColor(R.color.AppColor));
                editText_location.setBackgroundTintList(colorStateList);
                editText_location.setTextColor(getResources().getColor(R.color.AppColor));
            }
        });

        editText_location.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                editText_location.setHint("Location");
                editText_location.setHintTextColor(getResources().getColor(R.color.AppColor));
                editText_location.setTypeface(typeface);
                ColorStateList colorStateList = ColorStateList.valueOf(getResources().getColor(R.color.AppColor));
                editText_location.setBackgroundTintList(colorStateList);
                editText_location.setTextColor(getResources().getColor(R.color.AppColor));
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        editText_pan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editText_pan.setHint("PAN Number (Optional) ");
                editText_pan.setHintTextColor(getResources().getColor(R.color.AppColor));
                editText_pan.setTypeface(typeface);
                ColorStateList colorStateList = ColorStateList.valueOf(getResources().getColor(R.color.AppColor));
                editText_pan.setBackgroundTintList(colorStateList);
                editText_pan.setTextColor(getResources().getColor(R.color.AppColor));
            }
        });
        editText_pan.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                editText_pan.setHint("PAN Number (Optional)");
                editText_pan.setHintTextColor(getResources().getColor(R.color.AppColor));
                editText_pan.setTypeface(typeface);
                ColorStateList colorStateList = ColorStateList.valueOf(getResources().getColor(R.color.AppColor));
                editText_pan.setBackgroundTintList(colorStateList);
                editText_pan.setTextColor(getResources().getColor(R.color.AppColor));
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        editText_mobile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editText_mobile.setHint("Mobile Number");
                editText_mobile.setHintTextColor(getResources().getColor(R.color.AppColor));
                editText_mobile.setTypeface(typeface);
                ColorStateList colorStateList = ColorStateList.valueOf(getResources().getColor(R.color.AppColor));
                editText_mobile.setBackgroundTintList(colorStateList);
                editText_mobile.setTextColor(getResources().getColor(R.color.AppColor));
            }
        });
        editText_mobile.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                editText_mobile.setHint("Mobile Number");
                editText_mobile.setHintTextColor(getResources().getColor(R.color.AppColor));
                editText_mobile.setTypeface(typeface);
                ColorStateList colorStateList = ColorStateList.valueOf(getResources().getColor(R.color.AppColor));
                editText_mobile.setBackgroundTintList(colorStateList);
                editText_mobile.setTextColor(getResources().getColor(R.color.AppColor));
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        button_signup.setOnClickListener(this);
         }

    private void registerUser() {
        email = editText_email.getText().toString().trim();
        password = editText_password.getText().toString().trim();
        mobile = editText_mobile.getText().toString().trim();
        pan = editText_pan.getText().toString().trim();
        location = editText_location.getText().toString().trim();
        car_preference = editText_car_preference.getText().toString().trim();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, ConfigInfo.signUp,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                       // Toast.makeText(getApplicationContext(),response.toString(),Toast.LENGTH_LONG).show();
                     Intent intent=new Intent(SignUpActivity.this,LoginActivity.class);
                       startActivity(intent);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(SignUpActivity.this, error.toString(), Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("UserProfile", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("email", email);
                editor.putString("password", password);
                editor.putString("pan", pan);
                editor.putString("mobile", mobile);
                editor.putString("location", location);
                editor.putString("car_preference", car_preference);
                editor.commit();
                Map<String, String> params = new HashMap<String, String>();
                params.put("email", email);
                params.put("password", password);
                params.put("pan", pan);
                params.put("mobile", mobile);
                params.put("location", location);
                params.put("prefrences", car_preference);

                return params;
            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onClick(View view) {
        email = editText_email.getText().toString().trim();
        password = editText_password.getText().toString().trim();
        mobile=editText_mobile.getText().toString().trim();
        location=editText_location.getText().toString().trim();
        car_preference=editText_car_preference.getText().toString().trim();
        final Typeface typeface = Typeface.createFromAsset(getAssets(), "Helvetica-Regular.ttf");

        Matcher matcherObj = Pattern.compile(email_pattern).matcher(email);
        if (!matcherObj.matches()) {
            editText_email.setText(null);
            editText_email.setHint("Invalid Email");
            editText_email.setTypeface(typeface);
            editText_email.setHintTextColor(getResources().getColor(R.color.red));
            ColorStateList colorStateList = ColorStateList.valueOf(getResources().getColor(R.color.red));
            editText_email.setBackgroundTintList(colorStateList);


        } else if (editText_password.getText().toString().length() <= 6) {
            editText_password.setText(null);
            editText_password.setHint("Invalid Password");
            editText_password.setTypeface(typeface);
            editText_password.setHintTextColor(getResources().getColor(R.color.red));
            ColorStateList colorStateList = ColorStateList.valueOf(getResources().getColor(R.color.red));

            editText_password.setBackgroundTintList(colorStateList);

        }else if (editText_mobile.getText().toString().length() <= 9) {
            editText_mobile.setText(null);
            editText_mobile.setHint("Invalid Mobile");
            editText_mobile.setTypeface(typeface);
            editText_mobile.setHintTextColor(getResources().getColor(R.color.red));
            ColorStateList colorStateList = ColorStateList.valueOf(getResources().getColor(R.color.red));

            editText_mobile.setBackgroundTintList(colorStateList);}

        else if (editText_location.getText().toString().length() <= 1) {
            editText_location.setText(null);
            editText_location.setHint("Invalid Location");
            editText_location.setTypeface(typeface);
            editText_location.setHintTextColor(getResources().getColor(R.color.red));
            ColorStateList colorStateList = ColorStateList.valueOf(getResources().getColor(R.color.red));

            editText_location.setBackgroundTintList(colorStateList);}

        else {
            registerUser();

        }
    }
}
