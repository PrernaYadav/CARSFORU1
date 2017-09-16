package com.fluidsoft.fluidsoft.carsforu.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.fluidsoft.fluidsoft.carsforu.Class.ConfigInfo;
import com.fluidsoft.fluidsoft.carsforu.fragment.DetailsFragment;
import com.fluidsoft.fluidsoft.carsforu.R;
import com.fluidsoft.fluidsoft.carsforu.fragment.ImagesFragment;
import com.fluidsoft.fluidsoft.carsforu.fragment.TabFragment1;

import java.util.HashMap;
import java.util.Map;

public class CarDetailsActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    FragmentManager mFragmentManager;
    FragmentTransaction mFragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        String str = getIntent().getStringExtra("cardetails");
        String str1 = getIntent().getStringExtra("carimages");
        Bundle bundle = new Bundle();
        bundle.putString("text", str);

        Bundle bundle1 = new Bundle();
        bundle.putString("carimages", str1);
// set Fragmentclass Arguments

        DetailsFragment fragobj = new DetailsFragment();
        fragobj.setArguments(bundle);


        ImagesFragment imagesFragment=new ImagesFragment();
        imagesFragment.setArguments(bundle1);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        toolbar.setNavigationIcon(R.drawable.menu);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        mFragmentManager = getSupportFragmentManager();
        mFragmentTransaction = mFragmentManager.beginTransaction();
        mFragmentTransaction.replace(R.id.containerView, new TabFragment1()).commit();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.car_details, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement


        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_profile) {

            emailProfile();

        } else if (id == R.id.nav_my_biddings) {
           /* Intent intent=new Intent(this,DrawerActivity.class);*/
            emailSending();
           /* startActivity(intent);*/
        } else if (id == R.id.nav_terms_and_conditions) {

        } else if (id == R.id.nav_customer_support) {

        } else if (id == R.id.nav_about_us) {

        } else if (id == R.id.nav_logout) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void emailSending() {
        final LoginActivity loginActivity = new LoginActivity();

     /*final String email= loginActivity.getIntent().getStringExtra("email");*/
        StringRequest stringRequest = new StringRequest(Request.Method.POST, ConfigInfo.currentBid,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
//                        Intent intent=new Intent(,LoginActivity.class);
//                        startActivity(intent);

                        //   Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(CarDetailsActivity.this, DrawerActivity.class);
                        intent.putExtra("email", response.toString());
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
                String email = sharedPreferences.getString(ConfigInfo.EMAIL_SHARED_PREF, "");

                Map<String, String> params = new HashMap<String, String>();
                params.put("email", email);


                return params;
            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
    }

    private void emailProfile() {
        final LoginActivity loginActivity = new LoginActivity();

     /*final String email= loginActivity.getIntent().getStringExtra("email");*/
        StringRequest stringRequest = new StringRequest(Request.Method.POST, ConfigInfo.profileInfo,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
//                        Intent intent=new Intent(,LoginActivity.class);
//                        startActivity(intent);

                        //   Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(CarDetailsActivity.this, ProfileShowActivity.class);
                        intent.putExtra("email", response.toString());
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
                String email = sharedPreferences.getString(ConfigInfo.EMAIL_SHARED_PREF, "");

                Map<String, String> params = new HashMap<String, String>();
                params.put("email", email);


                return params;
            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
    }
}
