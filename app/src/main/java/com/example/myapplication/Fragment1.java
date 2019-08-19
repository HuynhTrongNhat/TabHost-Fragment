package com.example.myapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Fragment1 extends Fragment {

    HinhAdapter adapter;
    ArrayList<Hinh> arrHinh;
    ListView lvHinh;
    String strTen, strMota, strLink;

    int vitri;

    public static final int REQUEST_CODE = 113;
    public static final int RESULT_CODE = 114;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.tab1_layout, container, false);
    }

    // Đối với Fragment chỉ có thể gọi hàm findViewByID trong phương thức onActivityCreated() và phải gọi hàm getView()
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        anhXa();
    }

    public void anhXa() {

        lvHinh = (ListView) getView().findViewById(R.id.listview);
        arrHinh = new ArrayList<>();

        arrHinh.add(new Hinh("Hình 1", "Người lái đò", "https://i-dulich.vnecdn.net/2019/02/26/1-1551149516_680x0.jpg"));
        arrHinh.add(new Hinh("Hình 2", "Cảnh đồng quê", "https://mb.dkn.tv/wp-content/uploads/2019/07/bn-0-countryside_960_720-e1564450649488.jpg"));
        arrHinh.add(new Hinh("Hình 3", "Hoa hồng", "https://benhvienmayanhsaigon.vn/wp-content/uploads/2018/06/nghe-thuat-chup-anh-hoa-8.jpg"));
        arrHinh.add(new Hinh("Hình 4", "Hoa bồ công anh", "https://wikicachlam.com/wp-content/uploads/2018/05/tong-hop-nhung-hinh-anh-dep-nhat-cua-bo-cong-anh-17.jpeg"));
        arrHinh.add(new Hinh("Hình 5", "Khinh khí cầu", "https://d25tv1xepz39hi.cloudfront.net/2017-08-21/files/landscape-photography_1645.jpg"));
        arrHinh.add(new Hinh("Hình 6", "Cáo", "https://www.elleman.vn/wp-content/uploads/2018/05/08/bo%CC%A3%CC%82-a%CC%89nh-%C4%91e%CC%A3p-ve%CC%82%CC%80-loa%CC%80i-ca%CC%81o-1-elleman-.jpg"));

        adapter = new HinhAdapter(getContext(), R.layout.image_item, arrHinh);
        lvHinh.setAdapter(adapter);

        Button btnThem = (Button) getView().findViewById(R.id.btnthem);
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createDialog();
            }
        });

        lvHinh.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                vitri = i;
                goHinhItem();
            }
        });
    }

    public void createDialog() {
        LayoutInflater inflater = getLayoutInflater();
        View alertLayout = inflater.inflate(R.layout.dialog_layout, null);

        final EditText editTen = (EditText) alertLayout.findViewById(R.id.editTextNhapTen);
        final EditText editMota = (EditText) alertLayout.findViewById(R.id.editTextNhapMota);
        final EditText editLink = (EditText) alertLayout.findViewById(R.id.editTextNhapLink);

        final AlertDialog.Builder alert = new AlertDialog.Builder(getContext());
        alert.setView(alertLayout);
        alert.setCancelable(false);
        alert.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getContext(), "Đã hủy!", Toast.LENGTH_SHORT).show();
            }
        });
        alert.setPositiveButton("Lưu", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (editTen.getText().toString().trim().equalsIgnoreCase("") && editMota.getText().toString().trim().equalsIgnoreCase("") && editLink.getText().toString().trim().equalsIgnoreCase("")) {
                    Toast.makeText(getContext(), "Không hoàn tất!", Toast.LENGTH_SHORT).show();
                    dialog.cancel();
                }
                if (editLink.getText().toString().trim().equalsIgnoreCase("")) {
                    Toast.makeText(getContext(), "Không hoàn tất!", Toast.LENGTH_SHORT).show();
                    dialog.cancel();
                } else {
                    strTen = editTen.getText().toString().trim();
                    strMota = editMota.getText().toString().trim();
                    strLink = editLink.getText().toString().trim();

                    Hinh hinhanh = new Hinh();
                    hinhanh.setTen(strTen);
                    hinhanh.setMota(strMota);
                    hinhanh.setLink(strLink);
                    arrHinh.add(hinhanh);
                    adapter.notifyDataSetChanged();
                    Toast.makeText(getContext(), "Đã lưu!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        AlertDialog dialog = alert.create();
        dialog.show();
    }

    public void goHinhItem() {
        //Khai báo intent tới HinhItem Activity
        Intent intent = new Intent(getActivity(), HinhItem.class);
        //Khai báo Bundle
        Bundle bundle = new Bundle();

        String imageName = arrHinh.get(vitri).getTen();
        String imageMota = arrHinh.get(vitri).getMota();
        String imageLink = arrHinh.get(vitri).getLink();

        //Truyền dữ liệu vào bundle
        bundle.putString("ten", imageName);
        bundle.putString("mota", imageMota);
        bundle.putString("link", imageLink);

        //Bỏ  bundle vào intent
        intent.putExtra("Data", bundle);
        startActivityForResult(intent, REQUEST_CODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE) {
            if (resultCode == RESULT_CODE) {
                arrHinh.remove(vitri);
                adapter.notifyDataSetChanged();
            }
        }
    }
}
