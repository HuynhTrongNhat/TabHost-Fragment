package com.example.myapplication;

import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class Fragment3 extends Fragment {

    RVAdapter rvAdapter;
    ArrayList<Anime> arrayAnime;
    RecyclerView rcvAnime;
    String strName, strDescription, strLinks;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.tab3_layout, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        anhXa();
    }

    public void anhXa(){

        rcvAnime = (RecyclerView) getView().findViewById(R.id.recyclerview);
        arrayAnime = new ArrayList<>();

        arrayAnime.add(new Anime("One Piece", "Tác giả: Oda Eiichiro", "https://i.pinimg.com/474x/b6/63/69/b663690d5be5b8fe65c0628bf49639a9.jpg"));
        arrayAnime.add(new Anime("Gintama", "Tác giả: Sorachi Hideaki", "https://i2.wp.com/en-news.qoo-app.com/wp-content/uploads/sites/3/2017/07/17071305490869.jpg?resize=575%2C400"));
        arrayAnime.add(new Anime("Naruto", "Tác giả: Masashi Kishimoto", "https://prodimage.images-bn.com/pimages/9781421579757_p0_v1_s550x406.jpg"));
        arrayAnime.add(new Anime("Bleach", "Tác giả: Tite Kubo", "https://img1.ak.crunchyroll.com/i/spire1/61cdc3bdeef74ea432e8f4f00f6d689f1529998090_full.jpg"));
        arrayAnime.add(new Anime("Dragon Ball Z", "Tác giả: Akira Toriyama", "https://i.pinimg.com/originals/5c/be/f2/5cbef28f8c91350056ad4316645ad967.jpg"));
        arrayAnime.add(new Anime("Fairy Tail", "Tác giả: Hiro Mashima", "https://i.pinimg.com/originals/a4/c4/ca/a4c4ca393c146db726d68c3f1cb91362.jpg"));
        arrayAnime.add(new Anime("Death Note", "Tác giả: Ohba Tsugumi \n Obata Takeshi minh họa", "https://stuartngbooks.com/images/detailed/29/37913b.jpg"));
        arrayAnime.add(new Anime("Your Name", "Tác giả: Makoto Shinkai", "https://s3.amazonaws.com/media.thecrimson.com/photos/2018/02/12/230029_1327878.jpg"));

        LinearLayoutManager linearLayout = new LinearLayoutManager(getContext());
        rcvAnime.setLayoutManager(linearLayout);
        rvAdapter = new RVAdapter(arrayAnime);
        rcvAnime.setAdapter(rvAdapter);

        Button btnThemAnime = (Button) getView().findViewById(R.id.btnthemAnime);
        btnThemAnime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createDialogAnime();
            }
        });

    }

    public void createDialogAnime() {
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
                    strName = editTen.getText().toString().trim();
                    strDescription = editMota.getText().toString().trim();
                    strLinks = editLink.getText().toString().trim();
                    Anime anime = new Anime();
                    anime.setName(strName);
                    anime.setDescription(strDescription);
                    anime.setLinks(strLinks);
                    arrayAnime.add(anime);
                    rvAdapter.notifyDataSetChanged();
                    Toast.makeText(getContext(), "Đã lưu!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        AlertDialog dialog = alert.create();
        dialog.show();
    }

}
