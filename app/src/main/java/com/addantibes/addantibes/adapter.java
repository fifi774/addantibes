package com.addantibes.addantibes;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;


public class adapter extends RecyclerView.Adapter<adapter.ViewHolder> {

    private String[] texte;
    private String[] image;
    private TextView textView;
    private ImageView imageView;

    public adapter(String[] Array_texte, String [] Array_image) {
        texte = Array_texte;
        image = Array_image;
        Log.d ("ici", "public adapter");
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        Log.d ("ici", "public ViewHolder");
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cardview, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        Log.d ("ici", "public onBindViewHolder");
        viewHolder.display(texte, image, position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {


        public ViewHolder(View v) {
            super(v);
            textView = (TextView) itemView.findViewById(R.id.text);
            imageView = (ImageView) itemView.findViewById(R.id.image);
            Log.d ("ici", "public ViewHolder");

        }

        public void display(String [] texte, String [] image, int position) {
            Log.d ("ici", "public display");
            textView.setText(texte[position]);
            if (image [position] != "")
            {
                new DownloadImageTask (imageView)
                        .execute(image [position]);
            }
            else
            {
                imageView.setImageResource(R.drawable.ic_menu_camera);

            }

        }

    }


    private static class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            Log.d ("ici", "public DownloadImageTask");
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            Log.d ("ici", "protected Bitmap");
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
    }

    @Override
    public int getItemCount() {
        return texte.length;
    }


}
