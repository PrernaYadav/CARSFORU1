package com.fluidsoft.fluidsoft.carsforu.fragment;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.fluidsoft.fluidsoft.carsforu.R;
import com.fluidsoft.fluidsoft.carsforu.adapter.BidsWonAdapter;
import com.fluidsoft.fluidsoft.carsforu.model.BidsWon;

import org.json.JSONObject;

import java.util.ArrayList;


public class BidsWonFragment extends Fragment {

    ListView lv;
    ArrayList<BidsWon> bidsWonList;
    BidsWonAdapter adapter;

    public BidsWonFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View vw = inflater.inflate(R.layout.fragment_bids_won, container, false);
        lv = (ListView) vw.findViewById(R.id.listView3);

        bidsWonList = new ArrayList();
//        new JSONAsynTask().execute(ConfigInfo.wonBid);
        adapter = new BidsWonAdapter(getActivity(), R.layout.cardview_bids_won, bidsWonList);

//        lv.setAdapter(adapter);

        SharedPreferences settins = PreferenceManager
                .getDefaultSharedPreferences(getActivity());
        String data = settins.getString("bidlost", "");

        //  String data=String.valueOf(responseget);

        try {


            JSONObject result = new JSONObject(data);
            JSONObject routearray = result.getJSONObject("car");

            String carname = routearray.getString("car_name");
            String fuel = routearray.getString("fuel_type");
            String carkms = routearray.getString("car_kms");
            String carmodel = routearray.getString("car_model");
            String ac = routearray.getString("ac");
            String price = routearray.getString("price");
            String id = routearray.getString("id");
            String image = routearray.getString("car_profile_img");


            BidsWon bs = new BidsWon();
            bs.setCarName(carname);
            bs.setCarfuel(fuel);
            bs.setCarkm(carkms);
            bs.setCarmodel(carmodel);
            bs.setCartransmission(ac);
            bs.setCurrentbid(price);
            bs.setImage(image);
            bs.setUsers(id);
            bidsWonList.add(bs);


            adapter = new BidsWonAdapter(getActivity(), R.layout.cardview_bids_won, bidsWonList);
            lv.setAdapter(adapter);

        } catch (Exception e) {
            e.printStackTrace();
        }


        return vw;
    }

/*
    class JSONAsynTask extends AsyncTask<String, Void, Boolean> {
        String result;
        ProgressDialog dialog;


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = new ProgressDialog(getActivity());
            dialog.setMessage("Loading, please wait");
            dialog.setTitle("Connecting server");
            dialog.show();
            dialog.setCancelable(false);
        }

        @Override
        protected Boolean doInBackground(String... urls) {


            try {

                HttpGet httppost = new HttpGet(urls[0]);
                HttpClient httpclient = new DefaultHttpClient();
                HttpResponse response = httpclient.execute(httppost);
                int status = response.getStatusLine().getStatusCode();

                if (status == 200) {
                    HttpEntity entity = response.getEntity();
                    String data = EntityUtils.toString(entity);


                    JSONObject jsono = new JSONObject(data);
                    JSONArray jarray = jsono.getJSONArray("car");

                    for (int i = 0; i < jarray.length(); i++) {
                        JSONObject object = jarray.getJSONObject(i);

                        BidsWon bidsWon = new BidsWon();

                        bidsWon.setCarName(object.getString("car_name"));
                        bidsWon.setCarfuel(object.getString("fuel_type"));
                        bidsWon.setCarkm(object.getString("car_kms"));
                      //  bidsWon.setCarlocation(object.getString("IMG_NAME"));
                        bidsWon.setCarmodel(object.getString("car_model"));
                        bidsWon.setCartransmission(object.getString("ac"));
                        bidsWon.setCurrentbid(object.getString("price"));
                       // bidsWon.setRaiseby(object.getString("RegNo"));
                       // bidsWon.setUsers(object.getString("DeviceId"));
                        bidsWon.setImage(object.getString("car_profile_img"));

                        bidsWonList.add(bidsWon);
                       *//* final Handler refreshHandler = new Handler();
                        Runnable runnable = new Runnable() {
                            @Override
                            public void run() {
                                // do updates for imageview
                                refreshHandler.postDelayed(this, 30 * 1000);
                            }
                        };*//*
                    }
                    return true;
                }

            } catch (ParseException e1) {
                e1.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return false;

        }

        protected void onPostExecute(Boolean result) {

            dialog.dismiss();
            adapter.notifyDataSetChanged();
            if (result == false)
                Toast.makeText(getActivity(), "Unable to fetch data from server", Toast.LENGTH_LONG).show();

        }

    }*/
}
