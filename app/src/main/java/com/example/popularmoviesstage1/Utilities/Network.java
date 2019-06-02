package com.example.popularmoviesstage1.Utilities;

import android.net.Uri;

import java.net.URL;

public class Network {
    private static final String Base_Uri = "https://api.themoviedb.org";
    private static final String Name = "movie";
    private static final String Value = "3";
    private static final String API_PARM = "api_key";
    private static final String API_KEY = "993d63d636d0fbcf43f57a289173177b";
    public static URL buildMovieUrl(String item){
        Uri.Builder uriTo = Uri.parse(Base_Uri).buildUpon().appendPath(Value).appendPath(Name).appendPath(item).appendQueryParameter(API_PARM,API_KEY);
        URL url = null;
        try {
            url = new URL(uriTo.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return url;
    }
    public static String buildImageString(String s){
        return  "http://image.tmdb.org/t/p/original/"+s;
    }
}
