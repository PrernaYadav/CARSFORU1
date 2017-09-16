package com.fluidsoft.fluidsoft.carsforu.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.ParseException;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.fluidsoft.fluidsoft.carsforu.model.BidingOn;
import com.fluidsoft.fluidsoft.carsforu.adapter.BidingOnAdapter;
import com.fluidsoft.fluidsoft.carsforu.Class.ConfigInfo;
import com.fluidsoft.fluidsoft.carsforu.R;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BidingOnActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    ListView lv;
    ArrayList<BidingOn> bidingOnList;
    BidingOnAdapter adapter;
    ImageView image_plus;
    public int amount;
    public String _value;
    public int incrementamount;
    public String carid;
    //    final Jsonparse jsonParser = new Jsonparse(getApplicationContext());
    public String email, cariD;
    public String _stringVal;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_biding_on);
Toolbar toolbar=(Toolbar) findViewById(R.id.toolbarbid);
        toolbar.setTitle("Currently Bidding On");
        toolbar.setTitleTextColor(getResources().getColor(R.color.green));
        lv = (ListView) findViewById(R.id.listView5);
        bidingOnList = new ArrayList();

        adapter = new BidingOnAdapter(getApplicationContext(), R.layout.cardview_bidding,bidingOnList);
       /* incrementBid=adapter.bid;*/

        //  new   JSONAsynTask();
        new settingData().execute();


    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }

    public class settingData extends AsyncTask<Void, Void, Boolean> implements AdapterView.OnItemClickListener {
        ProgressDialog pd;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
//Toast.makeText(getApplicationContext(),"Pre Execute",Toast.LENGTH_SHORT).show();
             /*  pd = new ProgressDialog(getApplicationContext());
        pd.setMessage("Loading, please wait");
        pd.setTitle("Fetching Data");
        pd.show();
        pd.setCancelable(false);
*/
        }

        @Override
        protected Boolean doInBackground(Void... voids) {
            try {
                String urlget = getIntent().getStringExtra("carid");

                HttpGet httppost = new HttpGet(urlget);
                HttpClient httpclient = new DefaultHttpClient();
                HttpResponse response = httpclient.execute(httppost);


                // StatusLine stat = response.getStatusLine();
                int status = response.getStatusLine().getStatusCode();

                if (status == 200) {
                    HttpEntity entity = response.getEntity();
                    String data = EntityUtils.toString(entity);
                    JSONObject jsono = new JSONObject(data);
                    JSONArray jarray = jsono.getJSONArray("car");
                    bidingOnList.removeAll(bidingOnList);

                    for (int i = 0; i < jarray.length(); i++) {
                        JSONObject object = jarray.getJSONObject(i);
                        String carname = object.getString("car_name");
                        String fuel = object.getString("fuel_type");
                        String carkms = object.getString("car_kms");
                        String carmodel = object.getString("car_model");
                        String ac = object.getString("ac");
                        String price = object.getString("price");
                        String id = object.getString("id");
                        String image = object.getString("car_profile_img");
                        String timeee = object.getString("time");

                        String max = object.getString("max_bid");


                        BidingOn home = new BidingOn();
                        home.setCarName(carname);
                        home.setCarfuel(fuel);
                        home.setCarkm(carkms);
                        home.setCarmodel(carmodel);
                        home.setCartransmission(ac);
                        home.setCurrentbid(price);
                        home.setImage(image);
                        home.setTime(timeee);

                        home.setMax(max);

//                        homeList.clear();
                        home.setUsers(id);
                        bidingOnList.add(home);

                    }
                    return true;

                }

                //------------------>>

            } catch (ParseException e1) {
                e1.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return false;
        }

        @Override
        protected void onPostExecute(Boolean result) {

            if (lv.getAdapter() == null) {
                lv.setAdapter(adapter);
            } else {
lv.setAdapter(adapter);
                adapter.notifyDataSetChanged();


            }

            setRepeatingAsyncTask();
            lv.setOnItemClickListener(this);
        }


        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            ImageView _decrease = (ImageView) view.findViewById(R.id.minusbidbid);
            ImageView _increase = (ImageView) view.findViewById(R.id.plusbidbid);
            final TextView _value = (TextView) view.findViewById(R.id.maxbidbid);
            final TextView _valuecurrent = (TextView) view.findViewById(R.id.currentbid1bid);
            String _valuestring = _value.getText().toString();
            String _valuecurrentstring=_valuecurrent.getText().toString();
            amount = Integer.parseInt(_value.getText().toString().trim());
            incrementamount = Integer.parseInt(_value.getText().toString());
final int current=Integer.parseInt(_valuecurrentstring);

            _decrease.setOnClickListener(new View.OnClickListener() {


                public void onClick(View v) {

                    Log.d("src", "Decreasing value...");
                    if (incrementamount > current) {
                        incrementamount = incrementamount - 2000;
                        _stringVal = String.valueOf(incrementamount);
                        _value.setText(_stringVal);
                        registerUser();
                    } else {
                        Log.d("src", "Value can't be less than 0");
                    }

                }
            });

            _increase.setOnClickListener(new View.OnClickListener() {


                public void onClick(View v) {


                    Log.d("src", "Increasing value...");
                    incrementamount = Integer.parseInt(_value.getText().toString());
                    incrementamount = incrementamount + 2000;
                    _stringVal = String.valueOf(incrementamount);
                    _value.setText(_stringVal);
                    registerUser();
                }
            });

        }
    }


        private void registerUser() {
            final LoginActivity loginActivity = new LoginActivity();

//        final String email= loginActivity.getIntent().getStringExtra("email");
            StringRequest stringRequest = new StringRequest(Request.Method.POST, ConfigInfo.bid_price,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
//                        Intent intent=new Intent(,LoginActivity.class);
//                        startActivity(intent);
                            Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_LONG).show();

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
                    email = sharedPreferences.getString(ConfigInfo.EMAIL_SHARED_PREF, "");
                    // SharedPreferences sharedPreferences1 = getApplicationContext().getSharedPreferences(ConfigInfo.SHARED_PREF_NAME, Context.MODE_PRIVATE);
                    TextView _carid = (TextView) findViewById(R.id.carIDbid);
                    String _caridstr = _carid.getText().toString().trim();
                    cariD = _caridstr;
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("email", email);
                    params.put("id", cariD);
                    params.put("price", _stringVal);


                    return params;
                }

            };

            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
            requestQueue.add(stringRequest);
        }



    private void setRepeatingAsyncTask() {

        final Handler handler = new Handler();
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                // Toast.makeText(getActivity(),"Calling",Toast.LENGTH_SHORT).show();
                //  lv.setAdapter(null);

                new settingData().execute();
                /*handler.postDelayed(this, 1000);*/
            }
        });

    }
}









