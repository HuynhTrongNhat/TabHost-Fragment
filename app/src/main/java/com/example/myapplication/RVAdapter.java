package com.example.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.AMViewHolder> {

    List<Anime> animes;

    public RVAdapter(List<Anime> animes){
        this.animes = animes;
    }

    @NonNull
    @Override
    public AMViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.anime_item, parent, false);
        AMViewHolder amViewHolder = new AMViewHolder(view);
        return amViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AMViewHolder holder, int position) {
        holder.animeName.setText(animes.get(position).getName());
        holder.animeDescription.setText(animes.get(position).getDescription());
        Picasso.get()
                .load(animes.get(position).getLinks())
                .error(R.drawable.ic_launcher_background)
                .into(holder.animePhoto, new Callback() {
                    @Override
                    public void onSuccess() {
                    }

                    @Override
                    public void onError(Exception e) {
                        e.printStackTrace();
                    }
                });
    }
    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public int getItemCount() {
        return animes.size();
    }


    public static class AMViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        TextView animeName;
        TextView animeDescription;
        ImageView animePhoto;

        AMViewHolder(View itemView) {
            super(itemView);
            cardView          = (CardView)itemView.findViewById(R.id.cardview);
            animeName         = (TextView)itemView.findViewById(R.id.textviewTenAnime);
            animeDescription  = (TextView)itemView.findViewById(R.id.textviewMotaAnime);
            animePhoto        = (ImageView)itemView.findViewById(R.id.imageviewAnime);
        }
    }

}