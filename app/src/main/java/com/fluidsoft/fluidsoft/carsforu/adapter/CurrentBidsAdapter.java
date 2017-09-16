package com.fluidsoft.fluidsoft.carsforu.adapter;


import java.io.InputStream;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
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
import com.fluidsoft.fluidsoft.carsforu.model.CurrentBids;


public class CurrentBidsAdapter extends ArrayAdapter<CurrentBids> implements RecyclerView.OnItemTouchListener, View.OnClickListener {
    ArrayList<CurrentBids> currentBidList;
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
    public CurrentBidsAdapter(Context context, int resource, ArrayList<CurrentBids> objects) {
        super(context, resource, objects);
        vi = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        Resource = resource;
        currentBidList = objects;

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
holder.max=(TextView)v.findViewById(R.id.maxbid);
            holder.time=(TextView) v.findViewById(R.id.countdownTimercurrent);
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

            v.setTag(holder);
        } else {
            holder = (ViewHolder) v.getTag();
        }
        holder.imageview.setImageResource(R.drawable.carsforuappicon);
        new DownloadImageTask(holder.imageview).execute(currentBidList.get(position).getImage());
        holder.carName.setText(currentBidList.get(position).getCarName());

        holder.carFuel.setText(currentBidList.get(position).getCarfuel());
        holder.carKm.setText(currentBidList.get(position).getCarkm());
        // holder.carLocation.setText(currentBidList.get(position).getCarlocation());
        holder.carModelYear.setText(currentBidList.get(position).getCarmodel());
        holder.carTransmission.setText(currentBidList.get(position).getCartransmission());
        holder.currentBid.setText(currentBidList.get(position).getCurrentbid());
        holder.noOfUsers.setText(currentBidList.get(position).getUsers());
holder.time.setText(currentBidList.get(position).getTime());
holder.max.setText(currentBidList.get(position).getMax());

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

        public TextView currentBid;
        //  public TextView raiseBy;
        public TextView startBidding;
        public ImageView minus;
        public ImageView plus;
        public TextView noOfUsers;
public TextView max;
    public TextView time;

    }

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