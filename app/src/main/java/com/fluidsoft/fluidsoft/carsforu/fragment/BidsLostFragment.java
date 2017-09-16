package com.fluidsoft.fluidsoft.carsforu.fragment;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.fluidsoft.fluidsoft.carsforu.R;
import com.fluidsoft.fluidsoft.carsforu.adapter.BidsLostAdapter;
import com.fluidsoft.fluidsoft.carsforu.model.BidsLost;

import org.json.JSONObject;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class BidsLostFragment extends Fragment {
//**

    ListView lv;
    ArrayList<BidsLost> bidsLostList;
    BidsLostAdapter adapter;
    public String data;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // TODO Auto-generated method stub

        View vw = inflater.inflate(R.layout.fragment_bids_lost, container, false);
        lv = (ListView) vw.findViewById(R.id.listView2);

        bidsLostList = new ArrayList();
        // new JSONAsynTask().execute(ConfigInfo.lostBid);
        adapter = new BidsLostAdapter(getActivity(), R.layout.cardview_bids_lost, bidsLostList);

        //  data=String.valueOf(getArguments().getString("bidlost"));
//        settingData();

        SharedPreferences settins = PreferenceManager
                .getDefaultSharedPreferences(getActivity());
        data = settins.getString("bidlost", "");

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


                BidsLost bs = new BidsLost();
                bs.setCarName(carname);
                bs.setCarfuel(fuel);
                bs.setCarkm(carkms);
                bs.setCarmodel(carmodel);
                bs.setCartransmission(ac);
                bs.setCurrentbid(price);
                bs.setImage(image);
                bs.setUsers(id);
                bidsLostList.add(bs);


            adapter = new BidsLostAdapter(getActivity(), R.layout.cardview_bids_lost, bidsLostList);
            lv.setAdapter(adapter);

        } catch (Exception e) {
            e.printStackTrace();
        }


        return vw;
    }



}