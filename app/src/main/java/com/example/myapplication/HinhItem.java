package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

public class HinhItem extends AppCompatActivity {

    TextView textTen, textMota;
    ImageView imageHinh;
    Button btnxoa, btnback;

    String links;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hinh_item);
        textTen   = (TextView) findViewById(R.id.textTen);
        textMota  = (TextView) findViewById(R.id.textMota);
        imageHinh = (ImageView) findViewById(R.id.imageitem);
        btnxoa    = (Button) findViewById(R.id.btnxoa);
        btnback   = (Button) findViewById(R.id.btnback);

        Bundle bundle = getIntent().getBundleExtra("Data");
        textTen.setText(bundle.getString("ten"));
        textMota.setText(bundle.getString("mota"));
        links = bundle.getString("link").trim();
        Picasso.get()
                .load(bundle.getString("link").trim())
                .error(R.mipmap.ic_launcher)
                .into(imageHinh, new Callback() {
                    @Override
                    public void onSuccess() {
                    }

                    @Override
                    public void onError(Exception e) {
                        e.printStackTrace();
                    }
                });
        btnxoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(Fragment1.RESULT_CODE, getIntent());
                finish();
            }
        });
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        imageHinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goResize();
            }
        });
    }

    public void goResize(){
        Intent intent2 = new Intent(this, ResizeHinh.class);
        Bundle bundle2 = new Bundle();
        bundle2.putString("imgrs", links);
        intent2.putExtra("links", bundle2);
        startActivity(intent2);
    }
}
