package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements TabHost.OnTabChangeListener, ViewPager.OnPageChangeListener {

    ViewPager viewPager;
    ViewPagerAdapter viewPagerAdapter;

    TabHost tab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(viewPagerAdapter);

        tabInit();

        tab.setOnTabChangedListener(this);
        viewPager.setOnPageChangeListener(this);


    }

    public void tabInit(){
        tab = (TabHost) findViewById(R.id.tabHost);

        tab.setup();

        View tabview1 = LayoutInflater.from(tab.getContext()).inflate(R.layout.tabs_bg, null);
        TextView tv = (TextView) tabview1.findViewById(R.id.tabsText);
        ImageView icon = (ImageView) tabview1.findViewById(R.id.tabsIcon);
        TabHost.TabSpec tab1 = tab.newTabSpec("Hình ảnh").setIndicator(tabview1).setContent(new FakeContentTab(this));
        tab.addTab(tab1);

        View tabview2 = LayoutInflater.from(tab.getContext()).inflate(R.layout.tabs2_bg, null);
        TextView tv2 = (TextView) tabview2.findViewById(R.id.tabsText2);
        ImageView icon2 = (ImageView) tabview2.findViewById(R.id.tabsIcon2);
        TabHost.TabSpec tab2 = tab.newTabSpec("Ngày giờ").setIndicator(tabview2).setContent(new FakeContentTab(this));
        tab.addTab(tab2);

        View tabview3 = LayoutInflater.from(tab.getContext()).inflate(R.layout.tabs3_bg, null);
        TextView tv3 = (TextView) tabview3.findViewById(R.id.tabsText3);
        ImageView icon3 = (ImageView) tabview3.findViewById(R.id.tabsIcon);
        TabHost.TabSpec tab3 = tab.newTabSpec("Anime").setIndicator(tabview3).setContent(new FakeContentTab(this));
        tab.addTab(tab3);

        tab.setCurrentTab(0);
    }

    @Override
    public void onTabChanged(String s) {
        int vitritab = tab.getCurrentTab();
        viewPager.setCurrentItem(vitritab);
        Toast.makeText(getApplicationContext(), "Đã chuyển sang " + s, Toast.LENGTH_LONG).show();
        if(s == "Ngày giờ"){
            TextView txtTime = (TextView) findViewById(R.id.txtTime);
            TextView txtDate = (TextView) findViewById(R.id.txtDate);

            //Khai báo calendar cho tab Ngày giờ
            Calendar calendar = Calendar.getInstance();
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm a");

            txtDate.setText(dateFormat.format(calendar.getTime()));
            txtTime.setText(timeFormat.format(calendar.getTime()));
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        tab.setCurrentTab(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    public class FakeContentTab implements TabHost.TabContentFactory {

        Context context;

        public FakeContentTab(Context context){
            this.context = context;
        }

        @Override
        public View createTabContent(String s) {
            View view = new View(context);
            view.setMinimumHeight(0);
            view.setMinimumWidth(0);
            return view;
        }
    }


}
