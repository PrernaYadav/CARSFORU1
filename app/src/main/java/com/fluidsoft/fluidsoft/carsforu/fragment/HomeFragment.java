package com.fluidsoft.fluidsoft.carsforu.fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.ParseException;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
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
import com.fluidsoft.fluidsoft.carsforu.model.Home;
import com.fluidsoft.fluidsoft.carsforu.adapter.HomeAdapter;
import com.fluidsoft.fluidsoft.carsforu.Class.Jsonparse;
import com.fluidsoft.fluidsoft.carsforu.activity.LoginActivity;
import com.fluidsoft.fluidsoft.carsforu.R;
import com.fluidsoft.fluidsoft.carsforu.activity.BidingOnActivity;
import com.fluidsoft.fluidsoft.carsforu.activity.CarDetailsActivity;

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

import static com.google.android.gms.internal.zzhl.runOnUiThread;

@SuppressWarnings("deprecation")
public class HomeFragment extends Fragment implements AdapterView.OnItemClickListener {
    ListView lv;
    ArrayList<Home> homeList;
    HomeAdapter adapter;
    ImageView image_plus;
    public int amount;
    public String _value;
    public int incrementamount;
    public String id, time;
    public String carIdSend;
    final Jsonparse jsonParser = new Jsonparse(getContext());
    public String email, cariD;
    public String _stringVal;
    public static long CountdownStartTimer;
    public TextView timetext;
    private CountDownTimer countDownTimer;
    public ProgressDialog pd;


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       

        View vw = inflater.inflate(R.layout.fragment_home, container, false);
        lv = (ListView) vw.findViewById(R.id.listViewhome);
        timetext = (TextView) lv.findViewById(R.id.countdownTimer);

        //  int timeValue=Integer.parseInt(timetext.getText().toString().trim());
        homeList = new ArrayList();
        adapter = new HomeAdapter(getActivity(), R.layout.cardview_home, homeList);
      /*  pd = new ProgressDialog(getActivity());
        pd.setMessage("Loading, please wait");
        pd.setTitle("Fetching Data");
        pd.show();
        pd.setCancelable(false);*/
        new gettingData().execute();


//lv.setAdapter(adapter);
        // setRepeatingAsyncTask();
        return vw;

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }

    class gettingData extends AsyncTask<Void, Void, Boolean> implements AdapterView.OnItemClickListener {

        ProgressDialog dialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected Boolean doInBackground(Void... urls) {
            try {

                HttpGet httppost = new HttpGet(ConfigInfo.home);
                HttpClient httpclient = new DefaultHttpClient();
                HttpResponse response = httpclient.execute(httppost);


                // StatusLine stat = response.getStatusLine();
                int status = response.getStatusLine().getStatusCode();

                if (status == 200) {
                    HttpEntity entity = response.getEntity();
                    String data = EntityUtils.toString(entity);
                    JSONObject jsono = new JSONObject(data);
                    JSONArray jarray = jsono.getJSONArray("car");
                    homeList.removeAll(homeList);

                    for (int i = 0; i < jarray.length(); i++) {
                        JSONObject object = jarray.getJSONObject(i);
                        String carname = object.getString("car_name");
                        String fuel = object.getString("fuel_type");
                        String carkms = object.getString("car_kms");
                        String carmodel = object.getString("car_model");
                        String ac = object.getString("ac");
                        String price = object.getString("price");
                        id = object.getString("id");
                        String image = object.getString("car_profile_img");
                        String timeee = object.getString("time");

                        String max = object.getString("max_bid");


                        Home home = new Home();
                        home.setCarName(carname);
                        home.setCarfuel(fuel);
                        home.setCarkm(carkms);
                        home.setCarmodel(carmodel);
                        home.setCartransmission(ac);
                        home.setCurrentbid(price);
                        home.setImage(image);
                        home.setTime(timeee);
                        if (timeee.equalsIgnoreCase("Auction Completed")) {
                            home.setStart("");
                          /*  TextView startbidding = (TextView) getActivity().findViewById(R.id.startbidding);
                            startbidfalse);*/
                        } else if (timeee.equals("Coming Soon")) {
                            home.setStart("");
                        } else {
//                            homeList.clear();
                            home.setStart("START BIDDING");
                            home.setMax(max);
                        }
//                        homeList.clear();
                        home.setUsers(id);
                        homeList.add(home);


//                        Toast.makeText(getActivity(),"clear",Toast.LENGTH_LONG).show();

// times up code
                      /*  if (timeee.equals("00:00:00")){


                            AlertDialog.Builder builder1 = new AlertDialog.Builder(getActivity());
                            builder1.setMessage(carname+"   :Time's Up..");
                            builder1.setCancelable(true);

                            builder1.setPositiveButton(
                                    "OK",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {
                                            dialog.cancel();
                                        }
                                    });

                            AlertDialog alert11 = builder1.create();
                            alert11.show();

                        }*/
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


//            pd.dismiss();
            // List<Item> newItems = databaseHandler.getItems();

            //ListArrayAdapter.addAll(newItems);
            //  ListArrayAdapter.notifyDataSetChanged();
            //databaseHandler.close();

            //   adapter = new HomeAdapter(getContext(), R.layout.cardview_home, homeList);
           /* if (lv!= null) {
                lv.invalidateViews();
            }*/
            if (lv.getAdapter() == null) { //Adapter not set yet.
                lv.setAdapter(adapter);
            } else { //Already has an adapter
         /*       adapter.clear();*/


                //lv.setAdapter(null);
                adapter.notifyDataSetChanged();

/*new  gettingData().execute();*/
            }

            setRepeatingAsyncTask();
            lv.setOnItemClickListener(this);

        }

     /*  @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }*/

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

            StringRequest stringRequest = new StringRequest(Request.Method.POST, ConfigInfo.singlecar,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Toast.makeText(getActivity(), response.toString(), Toast.LENGTH_LONG).show();
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

                    Map<String, String> params = new HashMap<String, String>();

                    params.put("car_id", carIdSend);


                    return params;
                }

            };

            RequestQueue requestQueue = Volley.newRequestQueue(getContext());
            requestQueue.add(stringRequest);
        }

        private void carImageGetting() {
            StringRequest stringRequest = new StringRequest(Request.Method.POST, ConfigInfo.carImages,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            //   Toast.makeText(getContext(), response.toString(), Toast.LENGTH_LONG).show();
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

                    Map<String, String> params = new HashMap<String, String>();
                    params.put("car_id", carIdSend);
                    return params;
                }

            };

            RequestQueue requestQueue = Volley.newRequestQueue(getContext());
            requestQueue.add(stringRequest);
        }

        public void updateData() {
            TextView timee = (TextView) getView().findViewById(R.id.countdownTimer);
//        orderQuantity.setVisibility(View.VISIBLE);
            timee.setText(time);
            adapter.setNotifyOnChange(true);
        }

        private void carDetailsGetting() {
            //   final LoginActivity loginActivity = new LoginActivity();

            // final String email= loginActivity.getIntent().getStringExtra("email");
            StringRequest stringRequest = new StringRequest(Request.Method.POST, ConfigInfo.carDetails,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            Toast.makeText(getContext(), response.toString(), Toast.LENGTH_LONG).show();
                            String carddetails = response.toString();
                            Intent intent = new Intent(getActivity(), CarDetailsActivity.class);

                            intent.putExtra("cardetails", carddetails);
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

    }


    private void setRepeatingAsyncTask() {

        final Handler handler = new Handler();
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                // Toast.makeText(getActivity(),"Calling",Toast.LENGTH_SHORT).show();
                //  lv.setAdapter(null);

                new gettingData().execute();
                //   handler.postDelayed(this, 10000);
            }
        });

    }

}
