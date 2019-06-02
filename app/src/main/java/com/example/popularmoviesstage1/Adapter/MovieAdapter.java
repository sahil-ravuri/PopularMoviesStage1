package com.example.popularmoviesstage1.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.popularmoviesstage1.DetailedActivity;
import com.example.popularmoviesstage1.MainActivity;
import com.example.popularmoviesstage1.R;
import com.example.popularmoviesstage1.Utilities.Json;
import com.example.popularmoviesstage1.Utilities.Network;
import com.squareup.picasso.Picasso;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.Holder> implements View.OnClickListener {

    public Context context;
    private String[] img;

    public MovieAdapter(Context context) {
        this.context = context;
    }

    public void setImages(String[] s){
        this.img = s;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.items,viewGroup,false);
        view.setOnClickListener(this);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(Holder holder, int i) {
        String picture = Network.buildImageString(img[i]);
        holder.itemView.setTag(i);
        Log.i("pic",picture);
        Picasso.with(context).load(picture).placeholder(R.drawable.icon).fit().into(holder.view);
        //Glide.with(context).load(picture).into(holder.view);
    }

    @Override
    public int getItemCount() {
        if ( img == null )
            return 0;
        else
            return img.length;
    }

    @Override
    public void onClick (View v){
        int position = (int) v.getTag();
        Json.arrangeData(position);
        Intent intent = new Intent(context, DetailedActivity.class);
        intent.putExtra(MainActivity.mainTitle, Json.getOriginalTitle());
        intent.putExtra(MainActivity.backdropPath,Json.getBackdropPath());
        intent.putExtra(MainActivity.voteCount,Json.getVoteAverage());
        intent.putExtra(MainActivity.overView,Json.getOverView());
        intent.putExtra(MainActivity.releaseDate,Json.getReleaseDate());
        context.startActivity(intent);
    }

    public class Holder extends RecyclerView.ViewHolder {
        private ImageView view;
        public Holder(@NonNull View itemView) {
            super(itemView);
            view = itemView.findViewById(R.id.id_poster);
        }
    }
}
