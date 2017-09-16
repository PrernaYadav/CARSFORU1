package com.fluidsoft.fluidsoft.carsforu.activity;

import android.content.Context;
import android.content.Intent;

import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Typeface;
import android.os.Build;
import android.preference.PreferenceManager;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.fluidsoft.fluidsoft.carsforu.Class.ConfigInfo;
import com.fluidsoft.fluidsoft.carsforu.R;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    EditText editText_email, editText_password;
    String email, password;
    private boolean loggedIn = false;
    TextView textView_signup, textView_show_password, textView_forgot, textView_doesnot;
    Button btn_login;
    private static final String email_pattern =
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";



    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final Typeface typeface = Typeface.createFromAsset(getAssets(), "Helvetica-Regular.ttf");

        textView_show_password = (TextView) findViewById(R.id.textview_show_password);
        textView_forgot = (TextView) findViewById(R.id.textview_forgot_password);
        editText_email = (EditText) findViewById(R.id.editText_Email);
        editText_email.setTypeface(typeface);
        editText_password = (EditText) findViewById(R.id.editText_Password);
        editText_password.setTypeface(typeface);
        textView_forgot.setTypeface(typeface);
        textView_doesnot = (TextView) findViewById(R.id.textView_doesnot);
        textView_doesnot.setTypeface(typeface);
        textView_signup = (TextView) findViewById(R.id.textview_signup);

        textView_signup.setTypeface(typeface);
        textView_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });
        textView_show_password.setTypeface(typeface);
        textView_show_password.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {

                switch (event.getAction()) {

                    case MotionEvent.ACTION_UP:
                        editText_password.setTypeface(typeface);
                        editText_password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                        break;

                    case MotionEvent.ACTION_DOWN:
                        editText_password.setTypeface(typeface);
                        editText_password.setInputType(InputType.TYPE_CLASS_TEXT);
                        break;

                }
                return true;
            }
        });



        editText_email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editText_email.setHint("Email");
                editText_email.setHintTextColor(getResources().getColor(R.color.gray));
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
                editText_email.setTypeface(typeface);
                ColorStateList colorStateList = ColorStateList.valueOf(getResources().getColor(R.color.AppColor));
                editText_email.setBackgroundTintList(colorStateList);
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
                editText_password.setHintTextColor(getResources().getColor(R.color.AppColor));
                editText_password.setTypeface(typeface);
                ColorStateList colorStateList = ColorStateList.valueOf(getResources().getColor(R.color.AppColor));
                editText_password.setBackgroundTintList(colorStateList);
                editText_password.setTextColor(getResources().getColor(R.color.AppColor));
                editText_password.setTextColor(getResources().getColor(R.color.AppColor));
            }
        });
        editText_password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                editText_password.setTypeface(typeface);
                ColorStateList colorStateList = ColorStateList.valueOf(getResources().getColor(R.color.AppColor));
                editText_password.setBackgroundTintList(colorStateList);
                editText_password.setTextColor(getResources().getColor(R.color.AppColor));
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


        btn_login = (Button) findViewById(R.id.button_signin);
        btn_login.setTypeface(typeface, 1);
        btn_login.setOnClickListener(this);
        email = editText_email.getText().toString().trim();

    }

     @Override
     protected void onResume() {
         super.onResume();
         //In onresume fetching value from sharedpreference
         SharedPreferences sharedPreferences = getSharedPreferences(ConfigInfo.SHARED_PREF_NAME, Context.MODE_PRIVATE);

         //Fetching the boolean value form sharedpreferences
         loggedIn = sharedPreferences.getBoolean(ConfigInfo.LOGGEDIN_SHARED_PREF, false);

         //If we will get true
         if(loggedIn){
             //We will start the Profile Activity
emailSending();
         emailSendinglost();
         emailSendingWin();
emailSendingCurrent();

         }
     }
    private void login() {
        //Getting values from edit texts
        email = editText_email.getText().toString().trim();
        password = editText_password.getText().toString().trim();

   /*     new signin().execute();*/
        //Creating a string request
        StringRequest stringRequest = new StringRequest(Request.Method.POST, ConfigInfo.LOGIN_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //If we are getting success from server
                        if(response.equalsIgnoreCase(ConfigInfo.LOGIN_SUCCESS)){
                            //Creating a shared preference
                            SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences(ConfigInfo.SHARED_PREF_NAME, Context.MODE_PRIVATE);

                            //Creating editor to store values to shared preferences
                            SharedPreferences.Editor editor = sharedPreferences.edit();

                            //Adding values to editor
                            editor.putBoolean(ConfigInfo.LOGGEDIN_SHARED_PREF, true);
                            editor.putString(ConfigInfo.EMAIL_SHARED_PREF, email);

                            //Saving values to editor
                            editor.commit();
                            emailSending();
                            //Starting profile activity
                            Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                            intent.putExtra("email",email);
                            startActivity(intent);
                        }else{
                            //If the server response is not success
                            //Displaying an error message on toast
                            Toast.makeText(LoginActivity.this, "Invalid username or password", Toast.LENGTH_LONG).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //You can handle error here if you want
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                //Adding parameters to request
                params.put(ConfigInfo.KEY_EMAIL, email);
                params.put(ConfigInfo.KEY_PASSWORD, password);

                //returning parameter
                return params;
            }
        };

        //Adding the string request to the queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onClick(View v) {
        email = editText_email.getText().toString().trim();
        password = editText_password.getText().toString().trim();
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
        } else {
            login();

        }

    }
    private void emailSending() {
        final LoginActivity loginActivity = new LoginActivity();

     /*final String email= loginActivity.getIntent().getStringExtra("email");*/
        StringRequest stringRequest = new StringRequest(Request.Method.POST, ConfigInfo.home,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
//                        Intent intent=new Intent(,LoginActivity.class);
//                        startActivity(intent);

                 // Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_LONG).show();
                   Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                        intent.putExtra("email",response.toString());
                        startActivity(intent);
                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences(ConfigInfo.SHARED_PREF_NAME, Context.MODE_PRIVATE);
                email =sharedPreferences.getString(ConfigInfo.EMAIL_SHARED_PREF, "");

                Map<String, String> params = new HashMap<String, String>();
                params.put("email", email);


                return params;
            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
    }

    private void emailSendinglost() {
        final LoginActivity loginActivity = new LoginActivity();

//        final String email= loginActivity.getIntent().getStringExtra("email");
        StringRequest stringRequest = new StringRequest(Request.Method.POST, ConfigInfo.lostBid,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
//                        Intent intent=new Intent(,LoginActivity.class);
//                        startActivity(intent);
                       // Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_LONG).show();
                        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(LoginActivity.this);
                        SharedPreferences.Editor edit = settings.edit();
                        edit.putString("bidlost", response.toString());
                        edit.commit();
                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences(ConfigInfo.SHARED_PREF_NAME, Context.MODE_PRIVATE);
                String      email=sharedPreferences.getString(ConfigInfo.EMAIL_SHARED_PREF,"");
                // SharedPreferences sharedPreferences1 = getApplicationContext().getSharedPreferences(ConfigInfo.SHARED_PREF_NAME, Context.MODE_PRIVATE);

                Map<String, String> params = new HashMap<String, String>();
                params.put("email", email);

                return params;
            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
    }



    private void emailSendingWin() {
        final LoginActivity loginActivity = new LoginActivity();

//        final String email= loginActivity.getIntent().getStringExtra("email");
        StringRequest stringRequest = new StringRequest(Request.Method.POST, ConfigInfo.wonBid,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
//                        Intent intent=new Intent(,LoginActivity.class);
//                        startActivity(intent);
                      //  Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_LONG).show();
                        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(LoginActivity.this);
                        SharedPreferences.Editor edit = settings.edit();
                        edit.putString("bidwon", response.toString());
                        edit.commit();
                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences(ConfigInfo.SHARED_PREF_NAME, Context.MODE_PRIVATE);
                String      email=sharedPreferences.getString(ConfigInfo.EMAIL_SHARED_PREF,"");
                // SharedPreferences sharedPreferences1 = getApplicationContext().getSharedPreferences(ConfigInfo.SHARED_PREF_NAME, Context.MODE_PRIVATE);

                Map<String, String> params = new HashMap<String, String>();
                params.put("email", email);

                return params;
            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
    }

    private void emailSendingCurrent() {
        final LoginActivity loginActivity = new LoginActivity();

//        final String email= loginActivity.getIntent().getStringExtra("email");
        StringRequest stringRequest = new StringRequest(Request.Method.POST, ConfigInfo.wonBid,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
//                        Intent intent=new Intent(,LoginActivity.class);
//                        startActivity(intent);
                        //  Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_LONG).show();
                        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(LoginActivity.this);
                        SharedPreferences.Editor edit = settings.edit();
                        edit.putString("email", response.toString());
                        edit.commit();
                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences(ConfigInfo.SHARED_PREF_NAME, Context.MODE_PRIVATE);
                String      email=sharedPreferences.getString(ConfigInfo.EMAIL_SHARED_PREF,"");
                // SharedPreferences sharedPreferences1 = getApplicationContext().getSharedPreferences(ConfigInfo.SHARED_PREF_NAME, Context.MODE_PRIVATE);

                Map<String, String> params = new HashMap<String, String>();
                params.put("email", email);

                return params;
            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
    }
    }




