package com.myapplication;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.SpannableString;
import android.text.style.StyleSpan;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.InputStream;
import java.net.URL;

public class LoggedActivity extends AppCompatActivity {

    private ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logged);

        String name = getIntent().getStringExtra("NAME");
        TextView tv = (TextView) findViewById(R.id.text);
        tv.setText("Welcome,");
        SpannableString ss1=  new SpannableString(name);

        ss1.setSpan(new StyleSpan(Typeface.BOLD), 0, ss1.length(), 0);
        tv.append(ss1);

         mImageView = (ImageView) findViewById(R.id.image);

        new DownloadImage().execute("http://www.premierphotographer.com/category/Flowers/99/photos/99.jpg");

    }


    public class DownloadImage extends AsyncTask<String, Integer, Bitmap> {

        @Override
        protected Bitmap doInBackground(String... arg0) {

            return downloadImage(arg0[0]);
        }


        protected void onPostExecute(Bitmap image) {
            setImage(image);
        }
        private void setImage(Bitmap drawable)
        {
            mImageView.setImageBitmap(drawable);
        }

        private Bitmap downloadImage(String _url) {
            //Prepare to download image
            URL url;
            BufferedOutputStream out;
            InputStream in;
            BufferedInputStream buf;

            //BufferedInputStream buf;
            try {
                url = new URL(_url);
                in = url.openStream();


                // Read the inputstream
                buf = new BufferedInputStream(in);

                // Convert the BufferedInputStream to a Bitmap
                Bitmap bMap = BitmapFactory.decodeStream(buf);
                if (in != null) {
                    in.close();
                }
                if (buf != null) {
                    buf.close();
                }

                return bMap;

            } catch (Exception e) {
                Log.e("Error reading file", e.toString());
            }

            return null;
        }


    }
}
