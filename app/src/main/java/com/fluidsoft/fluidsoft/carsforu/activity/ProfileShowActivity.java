package com.fluidsoft.fluidsoft.carsforu.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Typeface;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.fluidsoft.fluidsoft.carsforu.Class.ConfigInfo;
import com.fluidsoft.fluidsoft.carsforu.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ProfileShowActivity extends AppCompatActivity implements View.OnClickListener {
TextView profile,name,address,location,preference,wheeler,edit,bids,email,mobile,pan,password;
    EditText location1,mobile1,pan1,email1,password1;
    Button logout;
    String c1 = "S"; // First letter in surname coming from the EditText (with P before)
    String c2 = "F";
    String rx = "[A-Z]{3}([CHFATBLJGP])(?:(?<=P)" + c1 + "|(?<!P)" + c2 + ")[0-9]{4}[A-Z]";
String passwordget;
    public String emailsp,mobilesp,pansp,locationsp,carpreferencesp,passwordsp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_show);
        Typeface typeface=Typeface.createFromAsset(getAssets(),"Helvetica-Regular.ttf");
String data=getIntent().getStringExtra("email");
        profile=(TextView)findViewById(R.id.profile);
        name=(TextView)findViewById(R.id.name);
        address=(TextView)findViewById(R.id.address);
        location=(TextView)findViewById(R.id.location);
        location1=(EditText) findViewById(R.id.location1);
        mobile=(TextView)findViewById(R.id.mobile);
        mobile1=(EditText)findViewById(R.id.mobile1);
        pan=(TextView)findViewById(R.id.pan);
        pan1=(EditText)findViewById(R.id.pan1);
        preference=(TextView)findViewById(R.id.pref);
        wheeler=(TextView)findViewById(R.id.wheeler);
        edit=(TextView)findViewById(R.id.edit);
        email=(TextView)findViewById(R.id.email);
        email1=(EditText)findViewById(R.id.email1);
      //  bids=(TextView)findViewById(R.id.bids);
        logout=(Button) findViewById(R.id.logout);
password1=(EditText)findViewById(R.id.password1) ;
        password=(TextView)findViewById(R.id.password);
settingData();
      //  bids.setTypeface(typeface);
        profile.setTypeface(typeface);
        name.setTypeface(typeface);
        address.setTypeface(typeface);
        location.setTypeface(typeface);
        location1.setTypeface(typeface);
        mobile.setTypeface(typeface);
        mobile1.setTypeface(typeface);
        pan.setTypeface(typeface);
        pan1.setTypeface(typeface);
        preference.setTypeface(typeface);
        wheeler.setTypeface(typeface);
        edit.setTypeface(typeface);
        email.setTypeface(typeface);
        email1.setTypeface(typeface);
        logout.setTypeface(typeface);
        password.setTypeface(typeface);
password1.setTypeface(typeface);

        edit.setOnClickListener(this);
        logout.setOnClickListener(this);

      /*  SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("UserProfile", Context.MODE_PRIVATE);
        emailsp =sharedPreferences.getString("email", "");
        mobilesp =sharedPreferences.getString("mobile", "");
        locationsp =sharedPreferences.getString("location", "");
       carpreferencesp =sharedPreferences.getString("car_preference", "");
        pansp=sharedPreferences.getString("pan", "");
        passwordsp= sharedPreferences.getString("password", "");
        email1.setText(emailsp);
        mobile1.setText(mobilesp);
        pan1.setText(pansp);
        wheeler.setText(carpreferencesp);
        location1.setText(locationsp);
        password1.setText(passwordsp);
        pan1.setEnabled(false);
        password1.setEnabled(false);
        location1.setEnabled(false);
        wheeler.setEnabled(false);
        mobile1.setEnabled(false);*/


    }
    public  void logout(){
        //Creating an alert dialog to confirm logout
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Are you sure you want to logout?");
        alertDialogBuilder.setPositiveButton("Yes",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {

                        //Getting out sharedpreferences
                        SharedPreferences preferences = getSharedPreferences(ConfigInfo.SHARED_PREF_NAME, Context.MODE_PRIVATE);
                        //Getting editor
                        SharedPreferences.Editor editor = preferences.edit();

                        //Puting the value false for loggedin
                        editor.putBoolean(ConfigInfo.LOGGEDIN_SHARED_PREF, false);

                        //Putting blank value to email
                        editor.putString(ConfigInfo.EMAIL_SHARED_PREF, "");

                        //Saving the sharedpreferences
                        editor.commit();

                        //Starting login activity
                        Intent intent = new Intent(ProfileShowActivity.this, LoginActivity.class);
                        startActivity(intent);
                    }
                });

        alertDialogBuilder.setNegativeButton("No",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {

                    }
                });

        //Showing the alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();

    }
@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public void editProfile(){
pan1.setEnabled(true);
    password1.setEnabled(true);
    ColorStateList colorStateList = ColorStateList.valueOf(getResources().getColor(R.color.AppColor));
    password1.setBackgroundTintList(colorStateList);
    location1.setEnabled(true);
    location1.setBackgroundTintList(colorStateList);
    wheeler.setEnabled(true);
    wheeler.setBackgroundTintList(colorStateList);
    mobile1.setEnabled(true);
    mobile1.setBackgroundTintList(colorStateList);
    logout.setText("Save Profile");
    Typeface typeface=Typeface.createFromAsset(getAssets(),"Helvetica-Regular.ttf");
    logout.setTypeface(typeface);
    logout.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String password=password1.getText().toString().trim();
            password1.setText(password);
            password1.setEnabled(false);
            String pan=pan1.getText().toString().trim();
            pan1.setText(pan);
            pan1.setEnabled(false);
            String mobile=mobile1.getText().toString().trim();
            mobile1.setText(mobile);
            mobile1.setEnabled(false);
            String wheeler1=wheeler.getText().toString().trim();
            wheeler.setText(wheeler1);
            wheeler.setEnabled(false);
            String location=location1.getText().toString().trim();
            location1.setText(location);
            location1.setEnabled(false);
            logout.setText("Logout");
            editinfo();
        }
    });

}
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onClick(View view) {
        if(view == logout){
        logout();}
        else if(view==edit){
            editProfile();
        }
    }
    public void settingData() {
        String data = getIntent().getStringExtra("email");

        try {


            JSONObject result = new JSONObject(data);
            JSONArray routearray = result.getJSONArray("profile");
            for (int i = 0; i < routearray.length(); i++) {

                String email = routearray.getJSONObject(i).getString("email");
                String password = routearray.getJSONObject(i).getString("password");
                String pan = routearray.getJSONObject(i).getString("pan_no");
                String mobile = routearray.getJSONObject(i).getString("mobile_no");
                String location = routearray.getJSONObject(i).getString("location");
                String prefrences = routearray.getJSONObject(i).getString("prefrences");
                //  String id = routearray.getJSONObject(i).getString("id");
                //  String image = routearray.getJSONObject(i).getString("car_profile_img");
name.setText(email.toUpperCase());
                address.setText(location.toUpperCase());
                passwordget= password;
                email1.setText(email);
                mobile1.setText(mobile);
                pan1.setText(pan);
                wheeler.setText(prefrences);
                location1.setText(location);
                password1.setText(password);
                pan1.setEnabled(false);
                email1.setEnabled(false);
                password1.setEnabled(false);
                location1.setEnabled(false);
                wheeler.setEnabled(false);
                mobile1.setEnabled(false);
                //  bidingOnList.add(bs);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    private void editinfo() {
        final LoginActivity loginActivity = new LoginActivity();

     /*final String email= loginActivity.getIntent().getStringExtra("email");*/
        StringRequest stringRequest = new StringRequest(Request.Method.POST, ConfigInfo.edit,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
//                        Intent intent=new Intent(,LoginActivity.class);
//                        startActivity(intent);

                        Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_LONG).show();
                       // Intent intent = new Intent(ProfileShowActivity.this, HomeActivity.class);
                       // intent.putExtra("email",email1.getText().toString());
                       // startActivity(intent);
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
              String emailsend=  email1.getText().toString();
              String passwordsend=    password1.getText().toString();
                String pansend=    pan1.getText().toString();
                String locationsend=    location1.getText().toString();
                String mobilesend=   mobile1.getText().toString();
                String preferencesend=     wheeler.getText().toString();
                Map<String, String> params = new HashMap<String, String>();
                params.put("email", emailsend);
                params.put("password", passwordsend);
                params.put("location", locationsend);
                params.put("prefrences", preferencesend);
                params.put("mobile", mobilesend);
                params.put("pan", pansend);
                return params;
            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
    }

}
