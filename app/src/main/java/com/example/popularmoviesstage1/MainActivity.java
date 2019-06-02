package com.example.popularmoviesstage1;

import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.popularmoviesstage1.Adapter.MovieAdapter;
import com.example.popularmoviesstage1.Utilities.Json;
import com.example.popularmoviesstage1.Utilities.Network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class MainActivity extends AppCompatActivity {
    private TextView error_msg;
    private ProgressBar progressBar;
    private RecyclerView recyclerView;
    private MovieAdapter recyclerAdapter;

    public static final String mainTitle = "original_title";
    public static final String backdropPath = "backdrop_path";
    public static final String voteCount = "vote_average";
    public static final String overView = "overview";
    public static final String releaseDate = "release_date";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);error_msg = findViewById(R.id.id_error_msg);
        progressBar = findViewById(R.id.id_progress_bar);
        recyclerView = findViewById(R.id.id_recycler_view);

        recyclerAdapter = new MovieAdapter(this);

        GridLayoutManager g=new GridLayoutManager(this,2);

        recyclerView.setLayoutManager(g);
        recyclerView.setHasFixedSize(true);

        recyclerView.setAdapter(recyclerAdapter);
        call("popular");
    }



    @RequiresApi(api = Build.VERSION_CODES.CUPCAKE)
    private void call(String item){
        URL url= Network.buildMovieUrl(item);
        new MyTask().execute(url);
    }

    @RequiresApi(api = Build.VERSION_CODES.CUPCAKE)
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();
        switch (id){
            case R.id.id_popular:
                recyclerAdapter.setImages(null);
                call("popular");
                return true;
            case R.id.id_top_rated:
                recyclerAdapter.setImages(null);
                call("top_rated");
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_items,menu);
        return true;
    }


    @RequiresApi(api = Build.VERSION_CODES.CUPCAKE)
    public class MyTask extends AsyncTask<URL,Void,String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
            error_msg.setVisibility(View.INVISIBLE);
        }

        @Override
        protected String doInBackground(URL... urls) {
            URL url=urls[0];
            try {
                URLConnection httpsURLConnection=url.openConnection();
                InputStream inputStream=httpsURLConnection.getInputStream();
                BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder stringBuilder=new StringBuilder();
                String line;
                while ((line=bufferedReader.readLine())!=null){
                    stringBuilder.append(line);
                }
                return stringBuilder.toString();
            }
            catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            progressBar.setVisibility(View.INVISIBLE);
            if (s==null){
                recyclerView.setVisibility(View.INVISIBLE);
                error_msg.setVisibility(View.VISIBLE);
            }
            else{
                error_msg.setVisibility(View.INVISIBLE);
                recyclerView.setVisibility(View.VISIBLE);
                try {
                    String[] strings= Json.getData(s);
                    recyclerAdapter.setImages(strings);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }
}
