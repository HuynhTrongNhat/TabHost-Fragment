package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

public class ResizeHinh extends AppCompatActivity {

    ImageView imgResize;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resize_hinh);

        Bundle bundle = getIntent().getBundleExtra("links");
        imgResize  = (ImageView) findViewById(R.id.imgResize);
        Picasso.get()
                .load(bundle.getString("imgrs").trim())
                .error(R.mipmap.ic_launcher)
                .into(imgResize, new Callback() {
                    @Override
                    public void onSuccess() {
                    }

                    @Override
                    public void onError(Exception e) {
                        e.printStackTrace();
                    }
                });
    }
}
