package com.fluidsoft.fluidsoft.carsforu.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.fluidsoft.fluidsoft.carsforu.R;
import com.fluidsoft.fluidsoft.carsforu.adapter.CarImagesAdapter;
import com.fluidsoft.fluidsoft.carsforu.model.CarImages;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class ImagesFragment extends Fragment {

    ListView lv;
    ArrayList<CarImages> carImagesList;
    CarImagesAdapter adapter;
public String data;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // TODO Auto-generated method stub

        View vw = inflater.inflate(R.layout.fragment_images, container, false);
        lv = (ListView) vw.findViewById(R.id.listView_images);

        carImagesList = new ArrayList();
        //  new ImagesFragment.JSONAsynTask().execute(ConfigInfo.carImages);
        adapter = new CarImagesAdapter(getActivity(), R.layout.cardview_car_images, carImagesList);
    // data = getActivity().getIntent().getExtras().getString("carimage");


      /*  Bundle bundle = this.getArguments();
        if (bundle != null) {
            data = bundle.getString("carimages", "");
        }*/

     //   data = getActivity().getIntent().getStringExtra("carimages");
        settingImages();
        lv.setAdapter(adapter);
        return vw;
    }


    /*class JSONAsynTask extends AsyncTask<String, Void, Boolean> {
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
                    JSONArray jarray = jsono.getJSONArray("image");
                    String str = jarray.toString();
                    String[] items = str.split(",");
                    int length = items.length;
                    int i = 1;
                    for (String item : items) {
                        CarImages carImages = new CarImages();
                        // JSONObject object = jarray.getJSONObject(i);
                        if (i == 1) {
                            String a = item.substring(2);
                            String b = a.substring(0, a.length() - 1);
                            carImages.setImage(b);
                        } else if (i >= length) {
                            String a = item.substring(1);
                            String b = a.substring(0, a.length() - 2);
                            carImages.setImage(b);
                        }
                        else{
                            String a = item.substring(1);
                            String b = a.substring(0, a.length() - 1);
                            carImages.setImage(b);
                        }
                        i++;


                        carImagesList.add(carImages);
                      *//*  final Handler refreshHandler = new Handler();
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

    public void settingImages() {
       /* Intent intent = getIntent();*/
        String value = getActivity().getIntent().getExtras().getString("carimages");

//        data=String.valueOf(getActivity().getIntent().getExtras().getString("carimages"));
        try {
            JSONObject result = new JSONObject(value);
            JSONArray routearray = result.getJSONArray("image");
            String carimage=routearray.toString();
            int length1=routearray.length();
          /*  for (int i = 0; i < routearray.length(); i++) {
*/
              //  String carimage = routearray.getJSONObject(i).getString("car_gallery_image");

                CarImages bs = new CarImages();
                String str = carimage;
                String[] items = str.split(",");
                int length = items.length;
                int i = 1;
                for (String item : items) {
                    CarImages carImages = new CarImages();
                    // JSONObject object = jarray.getJSONObject(i);
                    if (i == 1) {
                        String a = item.substring(2);
                        String b = a.substring(0, a.length() - 1);
                        carImages.setImage(b);
                    } else if (i >= length) {
                        String a = item.substring(1);
                        String b = a.substring(0, a.length() - 2);
                        carImages.setImage(b);
                    } else {
                        String a = item.substring(1);
                        String b = a.substring(0, a.length() - 1);
                        carImages.setImage(b);
                    }
                    i++;


                    carImagesList.add(carImages);
                }

                adapter = new CarImagesAdapter(getContext(), R.layout.cardview_car_images, carImagesList);
                lv.setAdapter(adapter);
                //lv.setOnItemClickListener(this);
           /* }*/

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}