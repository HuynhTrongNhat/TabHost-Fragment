package com.example.myapplication;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Fragment2 extends Fragment {

    TextView txtTime, txtDate;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.tab2_layout, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        txtTime = (TextView) getView().findViewById(R.id.txtTime);
        txtDate = (TextView) getView().findViewById(R.id.txtDate);

        //Khai báo calendar cho tab Ngày giờ
        Calendar calendar = Calendar.getInstance();

        //Sửa dụng lớp SimpleDateFormat để chỉnh sửa định dạng ngày giờ
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm a");

        txtDate.setText(dateFormat.format(calendar.getTime()));
        txtTime.setText(timeFormat.format(calendar.getTime()));
    }

}
