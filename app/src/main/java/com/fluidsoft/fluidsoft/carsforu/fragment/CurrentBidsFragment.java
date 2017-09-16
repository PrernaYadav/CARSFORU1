package com.fluidsoft.fluidsoft.carsforu.fragment;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import android.app.ProgressDialog;
import android.content.Intent;
import android.net.ParseException;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.fluidsoft.fluidsoft.carsforu.Class.ConfigInfo;
import com.fluidsoft.fluidsoft.carsforu.Class.Jsonparse;
import com.fluidsoft.fluidsoft.carsforu.activity.LoginActivity;
import com.fluidsoft.fluidsoft.carsforu.R;
import com.fluidsoft.fluidsoft.carsforu.activity.BidingOnActivity;
import com.fluidsoft.fluidsoft.carsforu.activity.CarDetailsActivity;
import com.fluidsoft.fluidsoft.carsforu.adapter.CurrentBidsAdapter;
import com.fluidsoft.fluidsoft.carsforu.model.CurrentBids;

import static com.google.android.gms.internal.zzhl.runOnUiThread;

@SuppressWarnings("deprecation")
public class CurrentBidsFragment extends Fragment implements AdapterView.OnItemClickListener {
    ListView lv;
    ArrayList<CurrentBids> currentBidsList;
    CurrentBidsAdapter adapter;
    ImageView image_plus;
    public int amount;
    public String _value;
    public int incrementamount;
    public String id;
    public String carIdSend;
    final Jsonparse jsonParser = new Jsonparse(getContext());
    public String email, cariD;
    public String _stringVal;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // TODO Auto-generated method stub

        View vw = inflater.inflate(R.layout.fragment_current_bids, container, false);
        lv = (ListView) vw.findViewById(R.id.listView1);
        currentBidsList = new ArrayList();
        adapter = new CurrentBidsAdapter(getActivity(), R.layout.cardview_currentbids, currentBidsList);

        new settingData().execute();

return vw;
    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }

    public class settingData extends AsyncTask<Void, Void, Boolean> implements AdapterView.OnItemClickListener {
        ProgressDialog pd;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected Boolean doInBackground(Void... voids) {
            try {

                String urlget = getActivity().getIntent().getStringExtra("email");

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
               //     Toast.makeText(getActivity(),jarray.toString(),Toast.LENGTH_SHORT).show();
                    currentBidsList.removeAll(currentBidsList);

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


                        CurrentBids home = new CurrentBids();
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
                        currentBidsList.add(home);

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
            lv.setOnItemClickListener(this);
            setRepeatingAsyncTask();

        }

        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            TextView startbidding = (TextView) view.findViewById(R.id.startbidding);
            TextView carid = (TextView) view.findViewById(R.id.userscurrent);
            ImageView carimage = (ImageView) view.findViewById(R.id.carImagecurrent);

            final String caridstr = carid.getText().toString().trim();

            startbidding.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    carIdSend = caridstr;
                    carIDSending();
                }
            });

            carimage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    carIdSend = caridstr;
                    carImageGetting();
                    carDetailsGetting();
                }
            });
        }

        private void carIDSending() {
            final LoginActivity loginActivity = new LoginActivity();

     /*final String email= loginActivity.getIntent().getStringExtra("email");*/
            StringRequest stringRequest = new StringRequest(Request.Method.POST, ConfigInfo.singlecar,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                     /*Intent intent=new Intent(getContext(),BidingOn.class);
getContext().startActivity(intent);*/

                            //      Toast.makeText(getContext(), response.toString(), Toast.LENGTH_LONG).show();


                            Intent intent = new Intent(getActivity(), BidingOnActivity.class);
                            intent.putExtra("carid", response.toString());
                            startActivity(intent);

                        }
                    },

                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(getContext(), error.toString(), Toast.LENGTH_LONG).show();
                        }
                    }) {
                @Override
                protected Map<String, String> getParams() {
               /* SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences(ConfigInfo.SHARED_PREF_NAME, Context.MODE_PRIVATE);
                email =sharedPreferences.getString(ConfigInfo.EMAIL_SHARED_PREF, "");
*//*
                SharedPreferences sharedPreferences1 = getApplicationContext().getSharedPreferences("myCarId", Context.MODE_PRIVATE);
                cariD =sharedPreferences1.getString("carID", "");*/


                    Map<String, String> params = new HashMap<String, String>();
                    params.put("car_id", carIdSend);
                    return params;
                }

            };

            RequestQueue requestQueue = Volley.newRequestQueue(getContext());
            requestQueue.add(stringRequest);
        }

        private void carImageGetting() {
            //   final LoginActivity loginActivity = new LoginActivity();

            // final String email= loginActivity.getIntent().getStringExtra("email");
            StringRequest stringRequest = new StringRequest(Request.Method.POST, ConfigInfo.carImages,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {


                            // Toast.makeText(getContext(), response.toString(), Toast.LENGTH_LONG).show();

                            Intent intent = new Intent(getActivity(), CarDetailsActivity.class);

                            intent.putExtra("carimages", response.toString());

                            startActivity(intent);


                        }
                    },

                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(getContext(), error.toString(), Toast.LENGTH_LONG).show();
                        }
                    }) {
                @Override
                protected Map<String, String> getParams() {
                    //   SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences(ConfigInfo.SHARED_PREF_NAME, Context.MODE_PRIVATE);
                    //email =sharedPreferences.getString(ConfigInfo.EMAIL_SHARED_PREF, "");
//
                    //  SharedPreferences sharedPreferences1 = getApplicationContext().getSharedPreferences("myCarId", Context.MODE_PRIVATE);
                    //  cariD =sharedPreferences1.getString("carID", "");


                    Map<String, String> params = new HashMap<String, String>();

                    params.put("car_id", carIdSend);
                    return params;
                }

            };

            RequestQueue requestQueue = Volley.newRequestQueue(getContext());
            requestQueue.add(stringRequest);
        }


        private void carDetailsGetting() {
            //   final LoginActivity loginActivity = new LoginActivity();

            // final String email= loginActivity.getIntent().getStringExtra("email");
            StringRequest stringRequest = new StringRequest(Request.Method.POST, ConfigInfo.carDetails,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {


                            Toast.makeText(getContext(), response.toString(), Toast.LENGTH_LONG).show();

                            Intent intent = new Intent(getActivity(), CarDetailsActivity.class);

                            intent.putExtra("cardetails", response.toString());

                            startActivity(intent);


                        }
                    },

                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(getContext(), error.toString(), Toast.LENGTH_LONG).show();
                        }
                    }) {
                @Override
                protected Map<String, String> getParams() {
                    //   SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences(ConfigInfo.SHARED_PREF_NAME, Context.MODE_PRIVATE);
                    //email =sharedPreferences.getString(ConfigInfo.EMAIL_SHARED_PREF, "");
//
                    //  SharedPreferences sharedPreferences1 = getApplicationContext().getSharedPreferences("myCarId", Context.MODE_PRIVATE);
                    //  cariD =sharedPreferences1.getString("carID", "");


                    Map<String, String> params = new HashMap<String, String>();

                    params.put("car_id", carIdSend);


                    return params;
                }

            };

            RequestQueue requestQueue = Volley.newRequestQueue(getContext());
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

}
