package com.fluidsoft.fluidsoft.carsforu.adapter;


import java.io.InputStream;
import java.util.ArrayList;

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
import com.fluidsoft.fluidsoft.carsforu.model.BidsWon;


public class BidsWonAdapter extends ArrayAdapter<BidsWon> implements RecyclerView.OnItemTouchListener, View.OnClickListener {
    ArrayList<BidsWon> bidsWonList;
    LayoutInflater vi;
    int Resource;
    ViewHolder holder;
    View itemView;
    ItemClickListener clickListener;
    Activity activity;
    Context ctx;
    public BidsWonAdapter(Context context, int resource, ArrayList<BidsWon> objects) {
        super(context, resource, objects);
        vi = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        Resource = resource;
        bidsWonList = objects;

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
            holder.imageview = (ImageView) v.findViewById(R.id.carImage);
            holder.carName = (TextView) v.findViewById(R.id.carName);
            holder.carFuel = (TextView) v.findViewById(R.id.carFuel);
            holder.carKm = (TextView) v.findViewById(R.id.carKm);
            holder.carLocation = (TextView) v.findViewById(R.id.carLocation);
            holder.carModelYear = (TextView) v.findViewById(R.id.carModel);
            holder.carTransmission = (TextView) v.findViewById(R.id.carTransmission);

            holder.currentBid = (TextView) v.findViewById(R.id.currentbid);
            holder.raiseBy = (TextView) v.findViewById(R.id.raiseby);
           // holder.minus = (ImageView) v.findViewById(R.id.minus);
           // holder.plus = (ImageView) v.findViewById(R.id.plusw);
           /* holder.noOfUsers=(TextView) v.findViewById(R.id.users);*/
            holder.startBidding=(TextView)v.findViewById(R.id.startbidding);
            v.setTag(holder);
        } else {
            holder = (ViewHolder) v.getTag();
        }
        holder.imageview.setImageResource(R.drawable.carsforuappicon);
        new DownloadImageTask(holder.imageview).execute(bidsWonList.get(position).getImage());
        holder.carName.setText(bidsWonList.get(position).getCarName());
      /*  holder.imageview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(),CarDetailsActivity.class);
                getContext().startActivity(intent);

            }
        });*/
        holder.carFuel.setText(bidsWonList.get(position).getCarfuel());
        holder.carKm.setText(bidsWonList.get(position).getCarkm());
        holder.carLocation.setText(bidsWonList.get(position).getCarlocation());
        holder.carModelYear.setText(bidsWonList.get(position).getCarmodel());
        holder.carTransmission.setText(bidsWonList.get(position).getCartransmission());
        holder.currentBid.setText(bidsWonList.get(position).getCurrentbid());
      /*  holder.noOfUsers.setText(bidsWonList.get(position).getUsers());*/
        holder.raiseBy.setText(bidsWonList.get(position).getRaiseby());
      /*  holder.minus.setImageResource(R.drawable.icons8minus);
        new DownloadImageTask(holder.minus).execute(actorList.get(position).getImage());
        holder.plus.setImageResource(R.drawable.icons8plus);
        new DownloadImageTask(holder.plus).execute(actorList.get(position).getImage());
*/


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
        public TextView raiseBy;
        public TextView startBidding;
        public ImageView minus;
        public ImageView plus;
        public TextView noOfUsers;


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