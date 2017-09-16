package com.fluidsoft.fluidsoft.carsforu.adapter;


import android.app.Activity;
import android.content.Context;
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
import com.fluidsoft.fluidsoft.carsforu.model.BidingOn;

import java.io.InputStream;
import java.util.ArrayList;


public class BidingOnAdapter extends ArrayAdapter<BidingOn> implements RecyclerView.OnItemTouchListener, View.OnClickListener {
    ArrayList<BidingOn> bidingOnList;
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
    public BidingOnAdapter(Context context, int resource, ArrayList<BidingOn> objects) {
        super(context, resource, objects);
        vi = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        Resource = resource;
        bidingOnList = objects;

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
    public View getView(int position, View convertView, ViewGroup parent) {
        // convert view = design
        View v = convertView;

        if (v == null) {
            holder = new ViewHolder();
            v = vi.inflate(Resource, null);
            holder.imageview = (ImageView) v.findViewById(R.id.carImagebid);
            holder.carName = (TextView) v.findViewById(R.id.carNamebid);
            holder.carFuel = (TextView) v.findViewById(R.id.carFuelbid);
            holder.carKm = (TextView) v.findViewById(R.id.carKmbid);
            holder.carLocation = (TextView) v.findViewById(R.id.carLocationbid);
            holder.carModelYear = (TextView) v.findViewById(R.id.carModelbid);
            holder.carTransmission = (TextView) v.findViewById(R.id.carTransmissionbid);
holder.max=(TextView)v.findViewById(R.id.maxbidbid);
            holder.currentBid = (TextView) v.findViewById(R.id.currentbid1bid);
            //  holder.raiseBy = (TextView) v.findViewById(R.id.raiseby);
            holder.minus = (ImageView) v.findViewById(R.id.minusbidbid);
            holder.minus.setTag(position);

            holder.plus = (ImageView) v.findViewById(R.id.plusbidbid);
            holder.plus.setTag(position);
            holder.noOfUsers = (TextView) v.findViewById(R.id.carIDbid);
          //  holder.startBidding = (TextView) v.findViewById(R.id.startbidding);
            holder.time=(TextView)v.findViewById(R.id.countdownTimerbid);
            v.setTag(holder);

        } else {
            holder = (ViewHolder) v.getTag();
        }
        holder.imageview.setImageResource(R.drawable.carsforuappicon);
        new DownloadImageTask(holder.imageview).execute(bidingOnList.get(position).getImage());
        holder.carName.setText(bidingOnList.get(position).getCarName());

        holder.carFuel.setText(bidingOnList.get(position).getCarfuel());
        holder.carKm.setText(bidingOnList.get(position).getCarkm());
        // holder.carLocation.setText(currentBidList.get(position).getCarlocation());
        holder.carModelYear.setText(bidingOnList.get(position).getCarmodel());
        holder.carTransmission.setText(bidingOnList.get(position).getCartransmission());
        holder.currentBid.setText(bidingOnList.get(position).getCurrentbid());
        holder.noOfUsers.setText(bidingOnList.get(position).getUsers());
        holder.max.setText(bidingOnList.get(position).getMax());
        holder.time.setText(bidingOnList.get(position).getTime());
       // holder.startBidding.setText(bidingOnList.get(position).getStart());
        //   holder.raiseBy.setText(currentBidList.get(position).getRaiseby());
      /*  holder.minus.setImageResource(R.drawable.icons8minus);
        new DownloadImageTask(holder.minus).execute(actorList.get(position).getImage());
        holder.plus.setImageResource(R.drawable.icons8plus);
        new DownloadImageTask(holder.plus).execute(actorList.get(position).getImage());
*/
     //   holder = (ViewHolder) v.getTag();  //keeping one global memory
     /*   holder.plus.setImageResource(v[position]);
        holder.textView.setText(names[position]);*/


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
public  TextView max;
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