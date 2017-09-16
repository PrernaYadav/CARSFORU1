package com.fluidsoft.fluidsoft.carsforu.adapter;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.CountDownTimer;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.fluidsoft.fluidsoft.carsforu.R;
import com.fluidsoft.fluidsoft.carsforu.activity.CarDetailsActivity;
import com.fluidsoft.fluidsoft.carsforu.model.Home;

import java.io.InputStream;
import java.util.ArrayList;


public class HomeAdapter extends ArrayAdapter<Home> implements RecyclerView.OnItemTouchListener, View.OnClickListener {
    ArrayList<Home> homeList;
    LayoutInflater vi;
    int Resource;
    ViewHolder holder;
    View itemView;
    ItemClickListener clickListener;
    Activity activity;
    Context ctx;
    public String _stringVal;
    public int amount;
    public int incrementamount;
RecyclerView.LayoutManager layoutManager;
    CountDownTimer countDownTimer;
    public HomeAdapter(Context context, int resource, ArrayList<Home> objects) {
        super(context, resource, objects);
        vi = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        Resource = resource;
        homeList = objects;

    }
   

    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
        return false;
    }

    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {

    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }

    @Override
    public void onClick(View view) {

    }


    public interface ItemClickListener {
        void onClick(View view, int position);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // convert view = design
        View v = convertView;
        if (v == null) {
            holder = new ViewHolder();
            v = vi.inflate(Resource, null);
            holder.imageview = (ImageView) v.findViewById(R.id.carImagecurrent);
            holder.carName = (TextView) v.findViewById(R.id.carName);
            holder.carFuel = (TextView) v.findViewById(R.id.carFuel);
            holder.carKm = (TextView) v.findViewById(R.id.carKm);
            holder.carLocation = (TextView) v.findViewById(R.id.carLocation);
            holder.carModelYear = (TextView) v.findViewById(R.id.carModel);
            holder.carTransmission = (TextView) v.findViewById(R.id.carTransmission);
            final View finalV = v;
holder.time=(TextView)v.findViewById(R.id.countdownTimer);
            holder.imageview.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Intent intent=new Intent(getContext(),CarDetailsActivity.class);

        getContext().startActivity(intent);
    }
});
            holder.currentBid = (TextView) v.findViewById(R.id.currentbid);
            //  holder.raiseBy = (TextView) v.findViewById(R.id.raiseby);
           // holder.minus = (ImageView) v.findViewById(R.id.minus);
          //  holder.minus.setTag(position);
          //  holder.plus = (ImageView) v.findViewById(R.id.plus);
           // holder.plus.setTag(position);
            holder.noOfUsers = (TextView) v.findViewById(R.id.userscurrent);
            holder.startBidding = (TextView) v.findViewById(R.id.startbidding);
            holder.max = (TextView) v.findViewById(R.id.maxbid);
            v.setTag(holder);
        } else {
            holder = (ViewHolder) v.getTag();
        }
        holder.imageview.setImageResource(R.drawable.carsforuappicon);
        new DownloadImageTask(holder.imageview).execute(homeList.get(position).getImage());
        holder.carName.setText(homeList.get(position).getCarName());

        holder.carFuel.setText(homeList.get(position).getCarfuel());
        holder.carKm.setText(homeList.get(position).getCarkm());
        // holder.carLocation.setText(currentBidList.get(position).getCarlocation());
        holder.carModelYear.setText(homeList.get(position).getCarmodel());
        holder.carTransmission.setText(homeList.get(position).getCartransmission());
        holder.currentBid.setText(homeList.get(position).getCurrentbid());
        holder.noOfUsers.setText(homeList.get(position).getUsers());
        holder.time.setText(homeList.get(position).getTime());
        holder.startBidding.setText(homeList.get(position).getStart());
        holder.max.setText(homeList.get(position).getMax());

/*final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {

                updateListItem(position);
               Toast.makeText(getContext(), "Hello", Toast.LENGTH_SHORT).show();
                handler.postDelayed(this, 60 * 100);
            }
        });*/

/*
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
//                    gettingData();
                notifyDataSetChanged();
                     *//* TextView timee = (TextView) getView().findViewById(R.id.countdownTimer);
                    timee.setText(time);*//*
                Toast.makeText(getContext(), "Hello", Toast.LENGTH_SHORT).show();
                handler.postDelayed(this, 60 * 100);

            }
        });*/

        return v;


    }


static class ViewHolder {
        public ImageView imageview;
        public TextView carName;
        public TextView carFuel;
        public TextView carKm;
        public TextView carLocation;
        public TextView carModelYear;
        public TextView carTransmission;
public TextView time;
        public TextView currentBid;
        //  public TextView raiseBy;
        public TextView startBidding;
        public ImageView minus;
        public ImageView plus;
        public TextView noOfUsers;
public TextView max;

    }

 /*   private void updateListItem(int position) {
        View view = layoutManager.findViewByPosition(position);
        TextView timee = (TextView) view.findViewById(R.id.countdownTimer);
        timee.setVisibility(View.VISIBLE);
        timee.setText();

        medicinesArrayAdapter.notifyItemChanged(position);
    }
*/
    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }


        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }

    }

}