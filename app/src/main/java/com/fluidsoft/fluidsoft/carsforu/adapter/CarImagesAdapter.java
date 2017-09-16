package com.fluidsoft.fluidsoft.carsforu.adapter;

/**
 * Created by prashant on 9/5/17.
 */
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

import com.fluidsoft.fluidsoft.carsforu.R;
import com.fluidsoft.fluidsoft.carsforu.model.CarImages;

public class CarImagesAdapter extends ArrayAdapter<CarImages> implements RecyclerView.OnItemTouchListener, View.OnClickListener{
        ArrayList<CarImages> carImagesList;
        LayoutInflater vi;
        int Resource;
        ViewHolder holder;
        View itemView;
        ItemClickListener clickListener;
        Activity activity;
        Context ctx;

public CarImagesAdapter(Context context, int resource, ArrayList<CarImages> objects) {
        super(context, resource, objects);
        vi = (LayoutInflater) context
        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        Resource = resource;
         carImagesList= objects;
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
            holder = new CarImagesAdapter.ViewHolder();
            v = vi.inflate(Resource, null);
            holder.imageview = (ImageView) v.findViewById(R.id.car_image);
            v.setTag(holder);
        } else {
            holder = (CarImagesAdapter.ViewHolder) v.getTag();
        }
     holder.imageview.setImageResource(R.drawable.carsforuappicon);
      new CarImagesAdapter.DownloadImageTask(holder.imageview).execute(carImagesList.get(position).getImage());

        return v;

    }

    static class ViewHolder {
        public ImageView imageview;



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