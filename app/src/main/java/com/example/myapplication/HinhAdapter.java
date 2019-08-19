package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class HinhAdapter extends BaseAdapter {

    Context context;
    int layout;
    ArrayList<Hinh> arrayList;

    public HinhAdapter(Context context, int layout, ArrayList<Hinh> arrayList) {
        this.context = context;
        this.layout = layout;
        this.arrayList = arrayList;
    }

    @Override
    public int getCount() {
        return this.arrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if(view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout, null);
            holder = new ViewHolder();
            holder.txtTen = (TextView) view.findViewById(R.id.textviewTen);
            holder.txtMota = (TextView) view.findViewById(R.id.textviewMota);
            holder.imgHinh = (ImageView) view.findViewById(R.id.imageviewHinh);
            view.setTag(holder);
        } else  {
            holder = (ViewHolder) view.getTag();
        }
        Hinh hinh = arrayList.get(i);
        holder.txtTen.setText(hinh.getTen());
        holder.txtMota.setText(hinh.getMota());

        Picasso.get()
                .load(hinh.getLink())
                .error(R.mipmap.ic_launcher)
                .into(holder.imgHinh, new Callback() {
                    @Override
                    public void onSuccess() {
                    }
                    @Override
                    public void onError(Exception e) {
                        e.printStackTrace();
                    }
                });
        return view;
    }

    public class ViewHolder {
        TextView txtTen, txtMota;
        ImageView imgHinh;
    }

}
