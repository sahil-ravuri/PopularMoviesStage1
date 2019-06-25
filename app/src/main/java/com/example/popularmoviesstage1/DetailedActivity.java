package com.example.popularmoviesstage1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.popularmoviesstage1.Utilities.Network;
import com.squareup.picasso.Picasso;

public class DetailedActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed);
        ImageView imageView = findViewById(R.id.id_backdrop_poster);
        TextView title = findViewById(R.id.id_original_title);
        TextView count = findViewById(R.id.id_rating);
        TextView overView = findViewById(R.id.id_overview);
        TextView releaseDate = findViewById(R.id.id_release_date);
        Intent intent = getIntent();
        title.setText(intent.getStringExtra(MainActivity.mainTitle));
        count.setText(intent.getStringExtra(MainActivity.voteCount));
        overView.setText(intent.getStringExtra(MainActivity.overView));
        releaseDate.setText(intent.getStringExtra(MainActivity.releaseDate));
        String str = intent.getStringExtra(MainActivity.backdropPath);
        String string = Network.buildImageString(str);
        Glide.with(this).load(string).into(imageView);
        setTitle(intent.getStringExtra(MainActivity.mainTitle));

    }
}
